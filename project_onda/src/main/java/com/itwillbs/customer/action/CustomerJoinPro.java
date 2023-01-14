package com.itwillbs.customer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.customer.db.CustomerDAO;
import com.itwillbs.customer.db.CustomerDTO;

public class CustomerJoinPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		//request 한글처리
		request.setCharacterEncoding("utf-8");

		// 폼에 입력한 데이터 => 서버request 저장
		// id pass name 변수 파라미터값 가져와서 저장
		String id = request.getParameter("id");
		String pass = request.getParameter("pass1");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		// package member, 파일이름 MemberDTO
		CustomerDTO dto = new CustomerDTO();
		// id,pass,name,date 변수 담기 => 변수 private 접근제한
		dto.setCus_id(id);
		dto.setCus_pass(pass);
		dto.setCus_name(name);
		dto.setCus_phone(phone);
		dto.setCus_email(email);

		CustomerDAO dao = new CustomerDAO();
		
		// dao.insertMember(id, pass, name, date);
		dao.insertCustomer(dto);

		//리턴 이동할 주소로그인 페이지로 이동
		// ActionForward 객체 생성
		// 이동경로, 이동방식 담아서 리턴
		ActionForward forward=new ActionForward();
		forward.setPath("./CustomerLoginForm.cu");
		forward.setRedirect(true);
		
		return forward;
		
	}
	
}
