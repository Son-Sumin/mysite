package com.bitacademy.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public int insert(BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}

	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	public int deleteByNo(Long no) {
		return sqlSession.delete("board.deleteByNo", no);
	}
	
	public boolean update(BoardVo boardVo) {
		int count = sqlSession.insert("board.update", boardVo);
		return count == 1;
	}
	
	public void updateHit(Long no) {
		sqlSession.update("board.updateHit", no);
	}
	
	public int updateOrderNo (Integer groupNo, Integer orderNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		
		return sqlSession.update("board.updateOrderNo", map);
	}
}
