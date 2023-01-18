package com.itwillbs.payment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.payment.db.PaymentDAO;

public class AdminPaymentDelete implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pay_num = Integer.parseInt(request.getParameter("pay_num"));

		PaymentDAO dao=new PaymentDAO();
		// 리턴할형없음 deleteBoard(int num)메서드 정의
		// 디비작업 주소를 통해서 deleteBoard(num) 호출
		dao.AdminDeletePayment(pay_num);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./AdminPaymentList.pa");
		forward.setRedirect(true);
		return forward;
	}
}
