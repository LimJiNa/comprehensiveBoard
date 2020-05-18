package com.comprehensive.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.comprehensive.boardFile.BoardFilesVO;
import com.comprehensive.common.SearchCriteria;

public interface BoardService {
	
	public int register(BoardWriteDTO writeDto);
	
	public List<BoardVO> list(SearchCriteria cri);
	
	public int listCount(SearchCriteria cri);
	
	public int selectMaxSeq();
	
	public BoardDetailDTO findOne(int seq);
	
	public int updateHit(int seq);
	
	public int checkPw(BoardVO boardVo);
	
	public int modify(BoardVO boardVo, BoardFilesVO boardFilesVo, MultipartFile multipartFile) throws Exception;
	
	public int modifyBoard(BoardVO boardVo);
	
	public int remove(BoardFilesVO boardFilesVo);
	
	public int removeBoard(int seq);

}
