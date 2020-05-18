package com.comprehensive.board;

import org.springframework.web.multipart.MultipartFile;

public class BoardWriteDTO {

	private String title;
	private String writer;
	private String password;
	private String content;
	private String writedate;
	private MultipartFile uploadFile;

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

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@Override
	public String toString() {
		return "BoardWriteDTO [title=" + title + ", writer=" + writer + ", password=" + password + ", content="
				+ content + ", writedate=" + writedate + ", uploadFile=" + uploadFile + "]";
	}

}
