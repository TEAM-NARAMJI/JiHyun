package com.itwillbs.customer.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.customer.db.CustomerDAO;
import com.itwillbs.customer.db.CustomerDTO;

public class CustomerUpdatePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");

		// 폼에서 입력한 파라미터값(id,pass,name)을 서버에 request에 저장
		// 변수 = request에 저장된 값 가져오기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		//MemberDAO 객체 생성
		CustomerDAO dao = new CustomerDAO();

		//MemberDTO dto =디비작업주소.userCheck(String id, String pass) 메서드 호출
		CustomerDTO dto=dao.userCheck(id,pass);

		//dto null이 아니면 =>"아이디 비밀번호 일치" => 수정, 메인이동
//		    null이면     => "아이디 비밀번호 틀림" => 뒤로이동

		if(dto!=null) {
		//  if 다음행이동=> 데이터 있으면 => true =>"아이디 비밀번호 일치"
			// 수정할 정보를 MemberDTO에 객체생성 set 호출 저장
			CustomerDTO dtoUpdate = new CustomerDTO();
			dtoUpdate.setCus_id(id);
			dtoUpdate.setCus_pass(pass);
			dtoUpdate.setCus_name(name);
			dtoUpdate.setCus_phone(phone);
			dtoUpdate.setCus_email(email);

			// 리턴할형 없음 void updateMember(수정할정보) 메서드 정의
			dao.updateCustomer(dtoUpdate);
			
			//MemberMain.me 이동
			ActionForward forward=new ActionForward();
			forward.setPath("./CustomerMypage.cu");
			forward.setRedirect(true);
			return forward;
			
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print("<script>");
			out.print("alert('입력하신 정보 틀림');");
			out.print("history.back();");
			out.print("</script>");	
			out.close();	
			return null;
		}
		
		
	}

	
	
}
