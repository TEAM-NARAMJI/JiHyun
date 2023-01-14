package com.itwillbs.cart.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.cart.db.CartDAO;
import com.itwillbs.cart.db.CartDTO;

public class CartUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String cus_id = request.getParameter("cus_id");
		int crt_num = Integer.parseInt(request.getParameter("crt_num")); 
		int menu_num = Integer.parseInt(request.getParameter("menu_num"));
		int crt_count = Integer.parseInt(request.getParameter("crt_count"));
		
		CartDTO dto = new CartDTO();
		
		dto.setCus_id(cus_id);
		dto.setCrt_num(crt_num);
		dto.setMenu_num(menu_num);
		dto.setCrt_count(crt_count);
		
		CartDAO dao = new CartDAO();
		
		dao.updateCart(dto);
		int cartPrice = dao.getCartPrice(dto);
		// --------------------------------------------------
		int result = cartPrice;
		
		//웹으로 출력 => ajax 되돌아감
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(result);
		out.close();
				
		//forward = null 이동 안함
		return null;
		
		
		
		// --------------------------------------------------
		
//		ActionForward forward = new ActionForward();
//		forward.setPath("./CartList.ca");
//		forward.setRedirect(true);
//		return forward;
	
	}
	
	
	
}
