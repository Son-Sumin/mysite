package com.bitacademy.mysite.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public void addContents(BoardVo vo) {  // 답글인지, 일반글인지 구별하기  // 답급 구
		boardRepository.insert(vo);
	}
	
	public BoardVo findContents(Long no, Long userNo) {  // 수정하는 화면갈 때
		return boardRepository.findByNo(no);
	} 
	
	public void updateContents(BoardVo vo) {
		boardRepository.update(vo);
	}
	
	public void deleteContents(Long no, Long userNo) {
		boardRepository.deleteByNo(no);
	}
	
	public BoardVo findContents(Long no) {  // 이때 hit 올리기
		return null;
	}
	
	public Map<String, Object> findContentsList(int currentPage) {
		// view의 페이징을 처리하기 위한 데이터의 값 계산
//		int beginPage = 0;
//		int endPage = 0;
//		
		Map<String, Object> map = new HashMap<>();
//		map.put("page2", boardRepository.findAll());
		
		// 리스트 가져오기
		
		return map;
	}
	
}
