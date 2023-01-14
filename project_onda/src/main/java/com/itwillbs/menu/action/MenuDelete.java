package com.itwillbs.menu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.menu.db.menuDAO;



public class MenuDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// board/delete.jsp
		// delete.jsp?num=1
		int menu_num = Integer.parseInt(request.getParameter("menu_num"));
		//BoardDAO 객체생성
		menuDAO dao=new menuDAO();
		// 리턴할형없음 deleteBoard(int num)메서드 정의
		// 디비작업 주소를 통해서 deleteBoard(num) 호출
		dao.deleteMenu(menu_num);
		// list.jsp 이동
//		response.sendRedirect("list.jsp");
		
		ActionForward forward = new ActionForward();
		forward.setPath(" /MenuBoardList.mn");
		forward.setRedirect(true);
		System.out.println("메뉴 삭제 완료");
		return forward;
	}
	
}
