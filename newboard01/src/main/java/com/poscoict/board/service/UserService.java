package com.poscoict.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.board.repository.UserRepository;
import com.poscoict.board.vo.UserVo;




@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserVo getUser(String id, String password) {
		UserVo authUser = userRepository.selectUser(id,password);
		return authUser;
	}
	
	public UserVo getUser(String id) {
		UserVo authUser = userRepository.findUser(id);
		return authUser;
	}
	
	public boolean insertUser(UserVo vo) {
		boolean result = userRepository.insertUser(vo);
		return result;
	}
	
	
	

}
