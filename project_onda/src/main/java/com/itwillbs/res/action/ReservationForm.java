package com.itwillbs.res.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.res.db.TableDAO;
import com.itwillbs.res.db.TableDTO;

public class ReservationForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TableDAO dao = new TableDAO();
		List<TableDTO> tableList = dao.getTable();	
		request.setAttribute("tableList",tableList);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./reservation/reservationForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
