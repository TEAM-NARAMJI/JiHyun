package com.itwillbs.customer.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//처리담당자(서블릿을 상속)
public class CustomerFrontController extends HttpServlet {
	//서블릿 파일이 동작할때 => 자동으로 메서드 호출 service()
	//	                 doGet() doPost()
	//웹서버에서 서블릿이 동작할때 자동으로 메서드 호출되면
	//메서드 오버라이딩해서 재정의
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String strpath=requestURI.substring(contextPath.length());
		
		ActionForward forward=null;
		Action action=null;
		
		if(strpath.equals("/MainPage.cu")) {
			forward = new ActionForward();
			forward.setPath("./customer/main.jsp");
			forward.setRedirect(false);
			
		} else if(strpath.equals("/About.cu")) {
			forward = new ActionForward();
			forward.setPath("./customer/about.jsp");
			forward.setRedirect(false);	
		
		} else if(strpath.equals("/CustomerAgree.cu")) {
			forward = new ActionForward();
			forward.setPath("./customer/agree.jsp");
			forward.setRedirect(false);
		
		} else if(strpath.equals("/CustomerJoinForm.cu")) {
			forward = new ActionForward();
			forward.setPath("./customer/loginJoinForm.jsp");
			forward.setRedirect(false);
			
		} else if(strpath.equals("/CustomerIdCheck.cu")) {
			action=new CustomerIdCheck();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
			
		} else if(strpath.equals("/CustomerPhoneCheck.cu")) {
			action=new CustomerPhoneCheck();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
			
		} else if(strpath.equals("/CustomerJoinPro.cu")) {
			action=new CustomerJoinPro();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		
		} else if(strpath.equals("/KakaoJoin.cu")) {
			action=new KakaoJoin();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
						
		} else if(strpath.equals("/CustomerLoginForm.cu")) {
			forward = new ActionForward();
			forward.setPath("./customer/loginJoinForm.jsp");
			forward.setRedirect(false);
		
		} else if(strpath.equals("/CustomerLoginPro.cu")) {
			action=new CustomerLoginPro();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		
		} else if(strpath.equals("/CustomerFindIdPw.cu")) {
			forward = new ActionForward();
			forward.setPath("./customer/findidpw.jsp");
			forward.setRedirect(false);	
		
		} else if(strpath.equals("/CustomerFindIdPro.cu")) {
			action = new CustomerFindIdPro();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
			
		} else if(strpath.equals("/CustomerFindPwPro.cu")) {
			action = new CustomerFindPwPro();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
			
		} else if(strpath.equals("/CustomerMypage.cu")) {
			forward = new ActionForward();
			forward.setPath("./customer/mypage.jsp");
			forward.setRedirect(false);
		
		} else if(strpath.equals("/CustomerAdminpage.cu")) {
			forward = new ActionForward();
			forward.setPath("./customer/adminpage.jsp");
			forward.setRedirect(false);	
			
		} else if(strpath.equals("/CustomerLogout.cu")) {
			action=new CustomerLogout();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
			
		} else if(strpath.equals("/CustomerUpdateForm.cu")) {
			action=new CustomerUpdateForm();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
			
		} else if(strpath.equals("/CustomerUpdatePro.cu")) {
			action=new CustomerUpdatePro();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
			
		} else if(strpath.equals("/CustomerDeleteForm.cu")) {
			// ./customer/deleteForm.jsp	
			forward = new ActionForward();
			forward.setPath("./customer/deleteForm.jsp");
			forward.setRedirect(false);
		
		} else if(strpath.equals("/CustomerDeletePro.cu")) {
			action=new CustomerDeletePro();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}

		} else if(strpath.equals("/CustomerList.cu")) {
			action=new CustomerList();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		
		} else if(strpath.equals("/AdminCusDelete.cu")) {
			action=new AdminCusDelete();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}

		}
		
		
			
		// 이동(경로정보, 이동방식 담아서 오면 이동) ActionForward
		if(forward !=null) {
			if(forward.isRedirect()) {
				// true -> sendRedirect() 방식
				System.out.println("true:" + forward.getPath() + "sendRedirect()방식 이동");
				response.sendRedirect(forward.getPath());
			} else {
				// false -> foward() 방식
				System.out.println("false:" + forward.getPath() + "forward()방식 이동");
				RequestDispatcher dis=request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		} 
		
} //doProcess() 메서드
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CustomerFrontController doGet()");
		// doProcess()호출
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CustomerFrontController doPost()");
		doProcess(request, response);
	}

	
	

}
