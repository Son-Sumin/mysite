package com.bitacademy.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.UserRepository;
import com.bitacademy.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void join(UserVo userVo) {
		userRepository.insert(userVo);		
	}
	
	public UserVo findUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);	
	}
	
	// UserVo findUser를 Controller에 맞게 수정하면서 override 하기
	public UserVo findUser(UserVo userVo) {
		return findUser(userVo.getEmail(), userVo.getPassword());
		
		// override 전 버전
		//return userRepository.findByEmailAndPassword(userVo.getEmail(), userVo.getPassword());
	}
	
	public UserVo findUser(Long no) {
		return userRepository.findByNo(no);
	}

	public void updateUser(UserVo userVo) {
		userRepository.update(userVo);
	}
}
