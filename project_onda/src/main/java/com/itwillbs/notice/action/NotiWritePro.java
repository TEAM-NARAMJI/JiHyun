package com.itwillbs.notice.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.notice.db.NoticeDAO;
import com.itwillbs.notice.db.NoticeDTO;

public class NotiWritePro implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String nt_name = request.getParameter("name");
		String nt_title = request.getParameter("title");
		String nt_content = request.getParameter("content");

		NoticeDTO dto=new NoticeDTO();
		
		dto.setNt_name(nt_name);
		dto.setNt_title(nt_title);
		dto.setNt_content(nt_content);
		dto.setNt_date(new Timestamp(System.currentTimeMillis()));
		dto.setNt_view(0);
		
		NoticeDAO dao=new NoticeDAO();

		dao.insertBoard(dto);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./NotiList.no");
		forward.setRedirect(true);
		return forward;
	}
}

