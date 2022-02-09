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
	
//	// 로그인 할 때 아이디, 비밀번호 일치 체크
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(HttpSession session,
//			@RequestParam(value="id", required=true, defaultValue="")String id,
//			@RequestParam(value="password", required=true, defaultValue="")String password,
//			Model model){
//		UserVo authUser = userService.getUser(id, password);
//		
//		if(authUser==null) { // 아이디, 비밀번호가 일치하지 않을 때
//			return "redirect:/user/login";
//		}
//		// 인증 처리
//		session.setAttribute("authUser", authUser);
//		return "redirect:/board";
//	}
	
	// 로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		return "redirect:/board";
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
	
	
	
	
}
