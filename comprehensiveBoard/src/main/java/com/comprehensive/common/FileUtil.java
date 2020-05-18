package com.comprehensive.common;

import java.io.File;

public class FileUtil {
	
	// 업로드 및 다운로드 경로
	public static final String UPLOAD_PATH = "C:/Users/LimJiNa/Desktop/hwigproject_admin/comprehensiveBoard/src/main/webapp/upload";
	
	// 탐색기의 원본파일명만 받아서 처리해주는 메서드
	public static String rename(String filename) {
		if(filename == null) {
			return null;
		}
		String newName = Long.toString(System.currentTimeMillis()) + (int)(Math.random() * 50);
		return rename(filename, newName);
	}
	
	// 실제로 새로운 파일명 부여
	public static String rename(String filename, String newName) {
		if(filename == null) {
			return null;
		}
		
		// 확장자 구하기
		int idx = filename.lastIndexOf(".");
		// 확장자 저장
		String extention = "";
		// 새 파일명 저장
		String newFileName = "";
		if(idx != -1) {
			extention = filename.substring(idx);
		}
		
		// 새파일명에 확장자 붙이기
		int newIdx = newName.lastIndexOf(".");
		if(newIdx != -1) {
			newName = newName.substring(0, newIdx);
		}
		newFileName = newName + extention.toLowerCase();
		return newFileName;
	}
	
	// 기존 업로드된 파일 삭제 후 새로운 파일 업로드
	public static void removeFile(String filename) {
		File file = new File(UPLOAD_PATH, filename); // 1.경로, 2.파일명
		if(file.exists()) { // 위 경로에 파일이 존재한다면
			file.delete(); // 삭제해라
		}
	}

}
