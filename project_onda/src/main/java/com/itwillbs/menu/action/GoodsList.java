package com.itwillbs.menu.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.menu.db.menuDAO;
import com.itwillbs.menu.db.menuDTO;


public class GoodsList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsList 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		String menu_category = request.getParameter("menu_category");
		System.out.println("카테고리: "+ menu_category);
	
		menuDAO dao = new menuDAO();
		
		List<menuDTO> goodsList =dao.GoodsList(menu_category);


	
		request.setAttribute("goodsList", goodsList);
		System.out.println(goodsList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./menu/goodsList.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
