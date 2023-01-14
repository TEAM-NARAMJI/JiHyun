package com.itwillbs.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.notice.db.NoticeDAO;
import com.itwillbs.notice.db.NoticeDTO;


public class NotiUpdatePro implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int num=Integer.parseInt(request.getParameter("num"));
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");

		NoticeDTO dto=new NoticeDTO();
		dto.setNt_num(num);
		dto.setNt_name(name);
		dto.setNt_title(subject);
		dto.setNt_content(content);

		NoticeDAO dao=new NoticeDAO();
		dao.updateBoard(dto);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./NotiList.no");
		forward.setRedirect(true);
		return forward;
	}
}
