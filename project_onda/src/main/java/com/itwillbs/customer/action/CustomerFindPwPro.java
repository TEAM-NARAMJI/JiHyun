package com.itwillbs.customer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.customer.db.CustomerDAO;

public class CustomerFindPwPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		CustomerDAO dao = new CustomerDAO();
		String pw = dao.findPw(id, name, phone);
		
		request.setAttribute("pass", pw);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./customer/findPwPro.jsp");	
		forward.setRedirect(false);	
		
		return forward;
	}

}
