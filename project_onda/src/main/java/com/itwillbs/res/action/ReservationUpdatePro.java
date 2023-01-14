package com.itwillbs.res.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.res.action.ActionForward;
import com.itwillbs.res.db.ReservationDAO;
import com.itwillbs.res.db.ReservationDTO;

public class ReservationUpdatePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		int res_num=Integer.parseInt(request.getParameter("res_num"));
		String res_use_date=request.getParameter("res_use_date");
		String time=request.getParameter("time");
		int person=Integer.parseInt(request.getParameter("person"));
		String table=request.getParameter("table");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		Timestamp date=new Timestamp(System.currentTimeMillis());

		ReservationDTO dto = new ReservationDTO();
		
		dto.setRes_num(res_num);
		dto.setCus_id(id);
	    dto.setRes_use_date(res_use_date);
	    dto.setRes_phone(phone);
	    dto.setRes_time(time);
	    dto.setRes_mem(person);
	    dto.setTb_num(table);
	    dto.setRes_name(name);
	    dto.setRes_date(new Timestamp(System.currentTimeMillis()));
	    
	    ReservationDAO dao = new ReservationDAO();
		dao.updateReservation(dto);

		ActionForward forward = new ActionForward();
		forward.setPath("./reservationList.re");
		forward.setRedirect(true);
		return forward;
	}

}
