package com.comprehensive.board;

public class BoardDetailDTO {

	private int seq;
	private int fileSeq;
	private String title;
	private String writer;
	private String password;
	private String content;
	private String writedate;
	private String originalName;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	@Override
	public String toString() {
		return "BoardDetailDTO [seq=" + seq + ", fileSeq=" + fileSeq + ", title=" + title + ", writer=" + writer
				+ ", password=" + password + ", content=" + content + ", writedate=" + writedate + ", originalName="
				+ originalName + "]";
	}

}
