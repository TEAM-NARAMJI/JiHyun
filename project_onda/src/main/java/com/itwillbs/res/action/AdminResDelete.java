package com.itwillbs.res.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.res.db.ReservationDAO;

public class AdminResDelete implements Action {
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      int res_num = Integer.parseInt(request.getParameter("res_num"));
      
      ReservationDAO dao=new ReservationDAO();

      dao.deleteReservation(res_num);

      ActionForward forward = new ActionForward();
      forward.setPath("./AdminResList.re");
      forward.setRedirect(true);
      return forward;

   }

}