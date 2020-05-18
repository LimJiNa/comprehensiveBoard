package com.comprehensive.boardFile;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardFilesDAOImpl implements BoardFilesDAO {
	
	private static final String namespace = "com.comprehensive.board.mapper.boardFilesMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int selectMaxSeq() {
		return sqlSession.selectOne(namespace + ".maxSeq");
	}

	@Override
	public int insert(BoardFilesVO boardFilesVo) {
		return sqlSession.insert(namespace + ".insert", boardFilesVo);
	}

	@Override
	public BoardFilesVO select(int fileSeq) {
		BoardFilesVO boardFilesVo = new BoardFilesVO();
		boardFilesVo.setFileSeq(fileSeq);
		return sqlSession.selectOne(namespace + ".select", boardFilesVo);
	}

	@Override
	public int update(BoardFilesVO boardFilesVo) {
		return sqlSession.update(namespace + ".update", boardFilesVo);
	}

	@Override
	public int delete(BoardFilesVO boardFilesVo) {
		return sqlSession.delete(namespace + ".delete", boardFilesVo);
	}
	
}
