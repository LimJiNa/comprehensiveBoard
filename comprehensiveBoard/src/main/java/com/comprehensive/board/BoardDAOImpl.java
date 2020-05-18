package com.comprehensive.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comprehensive.common.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	private static final String namespace = "com.comprehensive.board.mapper.boardMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(BoardVO boardVo) {
		return sqlSession.insert(namespace + ".insert", boardVo);
	}

	@Override
	public List<BoardVO> selectAll(SearchCriteria cri) {
		return sqlSession.selectList(namespace + ".selectAll", cri);
	}

	@Override
	public int selectAllCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace + ".selectAllCount", cri);
	}

	@Override
	public int selectMaxSeq() {
		return sqlSession.selectOne(namespace + ".maxSeq");
	}

	@Override
	public BoardDetailDTO select(int seq) {
		return sqlSession.selectOne(namespace + ".select", seq);
	}

	@Override
	public int updateHit(int seq) {
		return sqlSession.update(namespace + ".updateHit", seq);
	}

	@Override
	public int checkPw(BoardVO boardVo) {
		return sqlSession.selectOne(namespace + ".checkPw", boardVo);
	}

	@Override
	public int update(BoardVO boardVo) {
		return sqlSession.update(namespace + ".update", boardVo);
	}

	@Override
	public int delete(int seq) {
		return sqlSession.delete(namespace + ".delete", seq);
	}

}
