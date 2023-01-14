package com.itwillbs.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.notice.db.NoticeDAO;
import com.itwillbs.notice.db.NoticeDTO;

public class NotiContent implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num=Integer.parseInt(request.getParameter("num"));
		NoticeDAO dao=new NoticeDAO();
		
		dao.updateReadcount(num);
		
		NoticeDTO dto=dao.getBoard(num); 
		
		request.setAttribute("dto", dto);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./notice/content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
