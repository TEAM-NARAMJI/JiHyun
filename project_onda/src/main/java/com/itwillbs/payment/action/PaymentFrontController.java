package com.itwillbs.payment.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PaymentFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI=request.getRequestURI();
		System.out.println("requestURI : "+requestURI);

		String contextPath=request.getContextPath();
		System.out.println("contextPath : " + contextPath);
		System.out.println("contextPath길이 : " + contextPath.length());

		String strpath=request.getServletPath();
		System.out.println("뽑은 주소 strpath : "+strpath);

		ActionForward forward=null;
		Action action=null;
		
		if(strpath.equals("/OrderList.pa")) {
			action = new OrderList();
			
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} 
		
		// 테스트용 --------------------------------------------
//		else if(strpath.equals("/payment_test.pa")) {
//			forward = new ActionForward();
//			forward.setPath("./payment/payment_test.jsp");
//			forward.setRedirect(false);
			
//		} if(strpath.equals("/payment.pa")) {
//			forward = new ActionForward();
//			forward.setPath("./payment/payment.jsp");
//			forward.setRedirect(false);
		// ---------------------------------------------

//		}  
		
		else if(strpath.equals("/PaymentInsertPro.pa")) {
			action = new PaymentInsertPro();
			
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(strpath.equals("/paymentList.pa")) {
			action = new paymentList();
			
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if(strpath.equals("/AdminPaymentList.pa")) {
			action = new AdminPaymentList();
			
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else if(strpath.equals("/AdminPaymentDelete.pa")) {
			action = new AdminPaymentDelete();
				
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else if(strpath.equals("/PayComplete.pa")) {
	         forward = new ActionForward();
	         forward.setPath("./payment/payComplete.jsp");
	         forward.setRedirect(false);
	      }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}

		}
		
		
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
	
}
