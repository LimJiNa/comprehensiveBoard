package com.comprehensive.boardFile;

public interface BoardFilesService {
	
	public int maxSeq();
	
	public int register(BoardFilesVO boardFileVo);
	
	public BoardFilesVO findOne(int fileSeq);
	
	public int modify(BoardFilesVO boardFilesVo);
	
	public int remove(BoardFilesVO boardFilesVo);

}
