package com.itwillbs.cart.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartFrontController extends HttpServlet{

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
		
		if(strpath.equals("/LoginForm.ca")) {
			forward=new ActionForward();
			forward.setPath("./cart/loginForm.jsp");
			forward.setRedirect(false);

		} else if(strpath.equals("/LoginPro.ca")) {
			action = new LoginPro();
			
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(strpath.equals("/Menu.ca")) {
			forward=new ActionForward();
			forward.setPath("./cart/menu.jsp");
			forward.setRedirect(false);
			
		} else if(strpath.equals("/CartAddPro.ca")) {
			action = new CartAddPro();

			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(strpath.equals("/CartList.ca")) {
			action=new CartList();
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if(strpath.equals("/CartInsertPro.ca")) {
			action = new CartAddPro();

			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}


		} else if(strpath.equals("/CartUpdate.ca")) {
			action = new CartUpdate();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else if(strpath.equals("/CartDelete.ca")) {
			action = new CartDelete();
			
			try {
				// 메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			
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
