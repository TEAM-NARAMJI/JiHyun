package com.itwillbs.payment.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.payment.db.PaymentDAO;
import com.itwillbs.payment.db.PaymentDTO;

public class paymentList implements Action {

	@Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      HttpSession session = request.getSession();
	      String cus_id = (String)session.getAttribute("cus_id");
	      
	      PaymentDAO dao = new PaymentDAO();

	      List<PaymentDTO> paymentList = dao.getPaymentList(cus_id);

	      int count = dao.getPaymentCount(cus_id);
	      System.out.println("count=" + count);
	      
	      request.setAttribute("paymentList", paymentList);
	      request.setAttribute("count", count);
	      
	      ActionForward forward = new ActionForward();
	      forward.setPath("./payment/paymentList.jsp");
	      forward.setRedirect(false);
	      
	      return forward;

	}

}
