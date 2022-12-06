package com.bitacademy.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.bitacademy.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean update(UserVo vo) {
		int count = sqlSession.update("user.update", vo);
		return count == 1;
	}
	
	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.findByNo", no);
	}
	
	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
		
//		// 어플의 실용성을 위해 실행 시간 재기 -> 코드 수정 발생 -> AOP @Around 사용하기
//		//StopWatch sw = new StopWatch();
//		//sw.start();
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("email", email);
//		map.put("password", password);
//		UserVo result = sqlSession.selectOne("user.findByEmailAndPassword", map);
//		
//		sw.stop();
//		Long totalTime = sw.getTotalTimeMillis();
//		System.out.println("---------> " + totalTime);
//		
//		return result;
	}
	
	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}

}
