package com.itwillbs.res.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.res.db.ReservationDAO;
import com.itwillbs.res.db.ReservationDTO;

public class ReservationContent implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// content.jsp?num=1
		int num = Integer.parseInt(request.getParameter("num"));

		ReservationDAO dao = new ReservationDAO();
		ReservationDTO dto = dao.getReservation(num);

		request.setAttribute("dto", dto);

		ActionForward forward = new ActionForward();
		forward.setPath("./reservation/content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
