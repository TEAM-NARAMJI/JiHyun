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
		
		
		
		
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./payment/paymentList.jsp");
		forward.setRedirect(false);
		
		return forward;

	}

}
