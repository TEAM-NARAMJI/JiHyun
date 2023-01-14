package com.itwillbs.res.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ResFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		System.out.println(" Controller : requestURI = " + requestURI);

		String ctxPath = request.getContextPath();
		System.out.println(" Controller : ctxPath = " + ctxPath);

		String strpath = requestURI.substring(ctxPath.length());
		System.out.println(" Controller : strpath = " + strpath);

		Action action = null;
		ActionForward forward = null;

		if (strpath.equals("/reservationForm.re")) {
			action = new ReservationForm();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (strpath.equals("/selectTimeTable.re")) { // // 예약시간 예약테이블 조회 (Ajax)
			action = new SelectTimeTable();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (strpath.equals("/reservationPro.re")) {
			action = new ReservationPro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (strpath.equals("/reservationList.re")) {
			action = new ReservationList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (strpath.equals("/reservationUpdateForm.re")) {
			action = new ReservationUpdateForm();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (strpath.equals("/reservationUpdatePro.re")) {
			action = new ReservationUpdatePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (strpath.equals("/reservationDelete.re")) {
			action = new ReservationDelete();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	   
		} else if (strpath.equals("/AdminResList.re")) { //관리자 예약목록
			action = new AdminResList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		} else if (strpath.equals("/AdminResDelete.re")) { //관리자 예약삭제
			action = new AdminResDelete();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			
		}

		// 이동(경로정보, 이동방식 담아서 오면 이동) ActionForward
		if (forward != null) {
			if (forward.isRedirect()) {
				// true -> sendRedirect() 방식
				System.out.println("true:" + forward.getPath() + "sendRedirect()방식 이동");
				response.sendRedirect(forward.getPath());
			} else {
				// false -> foward() 방식
				System.out.println("false:" + forward.getPath() + "forward()방식 이동");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}