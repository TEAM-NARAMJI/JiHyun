package com.itwillbs.customer.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.customer.db.CustomerDAO;

public class AdminCusDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cus_id=request.getParameter("cus_id");
		
		CustomerDAO dao = new CustomerDAO();

		dao.deleteCustomer(cus_id);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./CustomerList.cu");
		forward.setRedirect(true);
		return forward;
	}
	
}
