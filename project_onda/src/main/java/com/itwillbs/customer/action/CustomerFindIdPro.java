package com.itwillbs.customer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.customer.db.CustomerDAO;

public class CustomerFindIdPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		CustomerDAO dao = new CustomerDAO();
		String id = dao.findId(name, phone);
		
		request.setAttribute("id", id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./customer/findIdPro.jsp");	
		forward.setRedirect(false);	
		
		return forward;
	}

}
