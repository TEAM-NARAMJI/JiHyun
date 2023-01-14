package com.itwillbs.customer.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.customer.db.CustomerDAO;
import com.itwillbs.customer.db.CustomerDTO;


public class CustomerLoginPro implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		//MemberDAO 객체 생성
		CustomerDAO dao = new CustomerDAO();
		CustomerDTO dto=dao.userCheck(id,pass);

		//dto null이 아니면 =>"아이디 비밀번호 일치"
//		    null이면     => "아이디 비밀번호 틀림"

		//5단계 : 결과를 출력, 배열저장(select)
		if(dto!=null){
			//데이터 있으면 => true =>"아이디 비밀번호 일치"
//			out.println("아이디 비밀번호 일치");
			// 페이지 상관없이 값이 유지 => 세션값 부여(저장)
			// session 내장객체생성
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			
			// 주소가 변경되면서 이동./main.me 이동
			ActionForward forward=new ActionForward();
			forward.setPath("./MainPage.cu");
			forward.setRedirect(true);
			
			return forward;
		}else{
			//데이터 없으면 => false => "아이디 비밀번호 틀림"
//			out.println("아이디 비밀번호 틀림");
			// "입력하신 정보 틀림", 뒤로이동
			// response에 응답할 파일형식 지정
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print("<script>");
			out.print("alert('회원가입 유무 또는 입력하신 정보를 확인하시기 바랍니다.');");
			out.print("history.back();");
			out.print("</script>");	
			out.close();	
			// main.jsp 이동
			return null;
		}
		
	}
	
	
}


