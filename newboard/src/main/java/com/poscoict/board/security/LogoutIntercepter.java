package com.poscoict.board.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LogoutIntercepter extends HandlerInterceptorAdapter {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception{
		// session
		HttpSession session = request.getSession(true);
		if(session!=null) {
			session.removeAttribute("authUser");
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath());
		return false;	
	}


}
