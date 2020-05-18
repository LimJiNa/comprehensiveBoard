package com.comprehensive.boardFile;

public class BoardFilesVO {

	private int fileSeq;
	private int boardSeq;
	private String fileName;
	private String originalName;

	public int getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	@Override
	public String toString() {
		return "BoardImagesVO [fileSeq=" + fileSeq + ", boardSeq=" + boardSeq + ", fileName=" + fileName
				+ ", originalName=" + originalName + "]";
	}

}
