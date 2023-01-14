package com.itwillbs.notice.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.notice.db.NoticeDAO;
import com.itwillbs.notice.db.NoticeDTO;

public class NotiWrite implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		
		NoticeDTO dto = new NoticeDTO();

		dto.setNt_name(name);
		dto.setNt_title(subject);
		dto.setNt_content(content);
		dto.setNt_date(new Timestamp(System.currentTimeMillis()));
		dto.setNt_view(0);

		NoticeDAO dao = new NoticeDAO();

		dao.insertBoard(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./NotiList.no");
		forward.setRedirect(true);
		return forward;
	}
	

}
