package com.comprehensive.board;

public class BoardPwCheckDTO {

	private int seq;
	private int fileSeq;
	private String password;
	private String actionType;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@Override
	public String toString() {
		return "BoardPwCheckDTO [seq=" + seq + ", fileSeq=" + fileSeq + ", password=" + password + ", actionType="
				+ actionType + "]";
	}

}
