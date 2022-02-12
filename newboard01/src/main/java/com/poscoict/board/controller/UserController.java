package com.poscoict.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.board.service.UserService;
import com.poscoict.board.vo.UserVo;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 로그인 페이지 이동
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	//회원가입 페이지 이동
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	//회원가입시 아이디 중복체크 및 가입 성공
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		UserVo user = userService.getUser(vo.getId());
		if(user!=null) {
			return "redirect:/user/join";
		}
		userService.insertUser(vo);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public void auth() {
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout() {
	}
	
	
	
	
}
