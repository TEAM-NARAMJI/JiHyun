package com.itwillbs.cart.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.cart.db.CartDAO;

public class CartDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num=Integer.parseInt(request.getParameter("num"));
		
		CartDAO dao = new CartDAO();
		
		dao.deleteCart(num);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./CartList.ca");
		forward.setRedirect(true);
		return forward;
	}
	
	
}
