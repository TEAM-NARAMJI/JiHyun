package com.itwillbs.res.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.res.db.ReservationDAO;

public class SelectTimeTable implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String res_use_date = request.getParameter("res_use_date");
		String time = request.getParameter("time");
		String table = request.getParameter("table");
		
		Map<String, String> param = new HashMap<>();
		param.put("res_use_date", res_use_date);
		param.put("time", time);
		param.put("table", table);
		
		ReservationDAO dao = new ReservationDAO();
		int result = dao.getTimeTable(param);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(result);
		out.close();
		
		return null;
	}

	
}
