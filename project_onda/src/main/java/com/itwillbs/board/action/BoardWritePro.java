package com.itwillbs.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardWritePro implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String cus_id=request.getParameter("cus_id");
		String qna_title=request.getParameter("qna_title");
		String qna_content=request.getParameter("qna_content");

		BoardDTO dto=new BoardDTO();
		
		dto.setCus_id(cus_id);
		dto.setQna_title(qna_title);
		dto.setQna_content(qna_content);
		dto.setQna_reg(new Timestamp(System.currentTimeMillis()));
		dto.setQna_view(0);

		BoardDAO dao=new BoardDAO();
		dao.insertBoard(dto);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}
}

