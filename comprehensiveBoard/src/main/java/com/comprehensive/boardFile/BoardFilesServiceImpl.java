package com.comprehensive.boardFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardFilesServiceImpl implements BoardFilesService {
	
	@Autowired
	private BoardFilesDAO boardFilesDao;

	@Override
	public int maxSeq() {
		try {
			return boardFilesDao.selectMaxSeq();
		} catch(NullPointerException e) {
			return 0;
		}
	}

	@Override
	public int register(BoardFilesVO boardFilesVo) {
		return boardFilesDao.insert(boardFilesVo);
	}

	@Override
	public BoardFilesVO findOne(int fileSeq) {
		return boardFilesDao.select(fileSeq);
	}

	@Override
	public int modify(BoardFilesVO boardFilesVo) {
		return boardFilesDao.update(boardFilesVo);
	}

	@Override
	public int remove(BoardFilesVO boardFilesVo) {
		return boardFilesDao.delete(boardFilesVo);
	}

}
