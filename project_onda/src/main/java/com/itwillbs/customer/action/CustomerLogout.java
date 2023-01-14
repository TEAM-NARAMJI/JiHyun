package com.itwillbs.customer.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerLogout implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션값 초기화(세션내장객체 전체 삭제)
		HttpSession session=request.getSession();
		session.invalidate();
		
		// ./MemberMain.me 이동
		ActionForward forward=new ActionForward();
		forward.setPath("./MainPage.cu");
		forward.setRedirect(true);
		
		return forward;
	}

	
}
