package com.itwillbs.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.notice.db.NoticeDAO;
import com.itwillbs.notice.db.NoticeDTO;


public class NotiUpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		int num=Integer.parseInt(request.getParameter("num"));
		
		if(id == null) {
			response.sendRedirect("./MemberLogin.me");
			
		} else if(! (id.equals("admin"))) {
			response.sendRedirect("./NotiUpdateForm.no");
		}
		
		NoticeDAO dao=new NoticeDAO();
		NoticeDTO dto = dao.getBoard(num);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./notice/updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
