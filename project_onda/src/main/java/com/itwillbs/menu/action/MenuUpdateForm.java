package com.itwillbs.menu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.menu.db.menuDAO;
import com.itwillbs.menu.db.menuDTO;


public class MenuUpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int menu_num = Integer.parseInt(request.getParameter("menu_num"));

		menuDAO dao = new menuDAO();
		
		menuDTO dto = dao.getMenu(menu_num);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admin/updateMenu.jsp");
		forward.setRedirect(false);
		
		

		return forward;
	}

}
