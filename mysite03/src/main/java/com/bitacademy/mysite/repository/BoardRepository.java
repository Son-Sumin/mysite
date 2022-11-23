package com.bitacademy.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}

	public Boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}

	public BoardVo findByNo(Long no) {
		//sqlSession.update("board.updateHit", no);
		return sqlSession.selectOne("board.findByNo", no);
	}

	public Boolean deleteByNo(Long no) {
		int count = sqlSession.delete("board.deleteByNo", no);
		return count == 1;
	}
	
	public boolean update(BoardVo vo) {
		int count = sqlSession.insert("board.update", vo);
		return count == 1;
	}
	
	public void updateHit(Long no) {
		sqlSession.update("board.updateHit", no);
	}
}
