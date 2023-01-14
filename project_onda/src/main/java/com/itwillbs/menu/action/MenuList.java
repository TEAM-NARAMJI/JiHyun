package com.itwillbs.menu.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.menu.db.menuDAO;
import com.itwillbs.menu.db.menuDTO;


public class MenuList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int pageSize= 10;
		
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=startRow+pageSize-1;
		
		menuDAO dao=new menuDAO();
		List<menuDTO> menuList =dao.getMenuList(startRow,pageSize);
		
		// 한페이지 10개 페이지 번호 보이게 설정
		int count=dao.getMenuCount();
		int pageBlock=10;
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		
		int endPage=startPage+pageBlock-1;
		int pageCount = count/pageSize
                +(count%pageSize==0 ? 0 : 1);
		if(endPage > pageCount){
			endPage=pageCount;
		}
		
		// 글목록, 페이지 정보 => request에 저장
		request.setAttribute("menuList",menuList);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
				
		// 이동정보 저장 => ./center/notice.jsp  
		ActionForward forward=new ActionForward();
		forward.setPath("./menu/mainMenu.jsp");
		forward.setRedirect(false);
		return forward;
	}

}