package com.comprehensive.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.comprehensive.boardFile.BoardFilesService;
import com.comprehensive.boardFile.BoardFilesVO;
import com.comprehensive.common.FileUtil;
import com.comprehensive.common.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;

	@Autowired
	private BoardFilesService boardFilesService;

	@Transactional
	@Override
	public int register(BoardWriteDTO writeDto) {
		
		BoardVO boardVo = new BoardVO();
		BoardFilesVO boardFilesVo = new BoardFilesVO();
		String newName = "";
		if (!writeDto.getUploadFile().isEmpty()) {
			newName = FileUtil.rename(writeDto.getUploadFile().getOriginalFilename());
			boardFilesVo.setOriginalName(writeDto.getUploadFile().getOriginalFilename());
			boardFilesVo.setFileName(newName);
		}

		int boardSeq = boardDao.selectMaxSeq() + 1;
		boardVo.setSeq(boardSeq);
		boardVo.setTitle(writeDto.getTitle());
		boardVo.setWriter(writeDto.getWriter());
		boardVo.setPassword(writeDto.getPassword());
		boardVo.setContent(writeDto.getContent());

		boardDao.insert(boardVo);

		int fileSeq = boardFilesService.maxSeq() + 1;
		boardFilesVo.setBoardSeq(boardSeq);
		boardFilesVo.setFileSeq(fileSeq);

		// 업로드 폴더로 업로드한 파일 저장
		if (!boardFilesVo.getFileName().isEmpty()) {
			try {
				File file = new File(FileUtil.UPLOAD_PATH + "/" + newName);
				writeDto.getUploadFile().transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return boardFilesService.register(boardFilesVo);
	}

	@Override
	public List<BoardVO> list(SearchCriteria cri) {
		return boardDao.selectAll(cri);
	}

	@Override
	public int listCount(SearchCriteria cri) {
		return boardDao.selectAllCount(cri);
	}

	@Override
	public int selectMaxSeq() {
		try {
			return boardDao.selectMaxSeq();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@Override
	public BoardDetailDTO findOne(int seq) {
		return boardDao.select(seq);
	}

	@Override
	public int updateHit(int seq) {
		return boardDao.updateHit(seq);
	}

	@Override
	public int checkPw(BoardVO boardVo) {
		return boardDao.checkPw(boardVo);
	}

	@Transactional
	@Override
	public int modify(BoardVO boardVo, BoardFilesVO boardFilesVo, MultipartFile multipartFile) throws Exception {
		int result = boardDao.update(boardVo);

		if (multipartFile.isEmpty() && result == 1) {
			boardFilesVo = boardFilesService.findOne(boardFilesVo.getFileSeq());

			if (boardFilesVo.getFileName() != null) {
				FileUtil.removeFile(boardFilesVo.getFileName());
			}

			String newFileName = FileUtil.rename(multipartFile.getOriginalFilename());
			boardFilesVo.setOriginalName(multipartFile.getOriginalFilename());
			boardFilesVo.setFileName(newFileName);
			boardFilesVo.setFileSeq(boardFilesVo.getFileSeq());
			if (!boardFilesVo.getFileName().isEmpty()) {
				File file = new File(FileUtil.UPLOAD_PATH + "/" + newFileName);
				multipartFile.transferTo(file);
			}
			result = boardFilesService.modify(boardFilesVo);

		}
		return result;
	}

	@Override
	public int modifyBoard(BoardVO boardVo) {
		return boardDao.update(boardVo);
	}

	@Transactional
	@Override
	public int remove(BoardFilesVO boardFilesVo) {

		int result = boardFilesService.remove(boardFilesVo);
		if (result == 1) {
			removeBoard(boardFilesVo.getBoardSeq());
		}

		return result;
	}

	@Override
	public int removeBoard(int seq) {
		return boardDao.delete(seq);
	}

}
