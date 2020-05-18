package com.comprehensive.board;

import java.util.List;

import com.comprehensive.common.SearchCriteria;

public interface BoardDAO {

	// 글 쓰기
	public int insert(BoardVO boardVo);

	// 글 목록
	public List<BoardVO> selectAll(SearchCriteria cri);

	// 페이징 처리
	public int selectAllCount(SearchCriteria cri);
	
	// 최대글 번호 구하기
	public int selectMaxSeq();
	
	// 게시글 상세
	public BoardDetailDTO select(int seq);
	
	// 게시물 번호에 해당하는 조회수 증가
	public int updateHit(int seq);
	
	// 게시물 비밀번호 동일한지 체크
	public int checkPw(BoardVO boardVo);
	
	// 게시글 수정
	public int update(BoardVO boardVo);
	
	// 게시글 삭제
	public int delete(int seq);

}
