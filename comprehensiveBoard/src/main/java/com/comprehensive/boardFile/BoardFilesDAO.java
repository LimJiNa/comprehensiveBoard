package com.comprehensive.boardFile;

public interface BoardFilesDAO {
	
	public int selectMaxSeq();
	
	public int insert(BoardFilesVO boardFilesVo);
	
	public BoardFilesVO select(int fileSeq);
	
	public int update(BoardFilesVO boardFilesVo);
	
	public int delete(BoardFilesVO boardFilesVo);

}
