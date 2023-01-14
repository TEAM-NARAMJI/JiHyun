package com.itwillbs.cart.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.cart.db.CartDAO;
import com.itwillbs.cart.db.CartDTO;


public class LoginPro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cus_id=request.getParameter("cus_id");
		
		CartDAO dao = new CartDAO();
		System.out.println("디비작업하는 주소 : " + dao);
		
		HttpSession session= request.getSession();
		session.setAttribute("cus_id", cus_id);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./Menu.ca");
		forward.setRedirect(true);
		return forward;
		
			
		
	}
	
	
	
}
