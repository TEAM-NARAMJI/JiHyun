package com.itwillbs.customer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.customer.db.CustomerDAO;
import com.itwillbs.customer.db.CustomerDTO;


public class KakaoJoin implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		CustomerDTO dto = new CustomerDTO();
		dto.setCus_id(id);
		dto.setCus_email(pass);
		dto.setCus_name(name);
		dto.setCus_email(email);

		CustomerDAO dao = new CustomerDAO();
		
		dao.KinsertCustomer(dto);

		ActionForward forward=new ActionForward();
		forward.setPath("./CustomerLoginForm.cu");
		forward.setRedirect(true);
		
		return forward;
		
	}
	
}
