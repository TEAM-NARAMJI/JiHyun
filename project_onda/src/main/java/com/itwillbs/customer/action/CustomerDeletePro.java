package com.itwillbs.customer.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.customer.db.CustomerDAO;
import com.itwillbs.customer.db.CustomerDTO;

public class CustomerDeletePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		
		//MemberDAO 객체생성
		CustomerDAO dao = new CustomerDAO();

		// MemberDTO dto =디비작업주소.userCheck(id,pass) 메서드 호출
		CustomerDTO dto=dao.userCheck(id,pass);
	
		if(dto!=null){
			//다음행이동=> 데이터 있으면 => true =>"아이디 비밀번호 일치"
			// 리턴할형없음 void deleteMember(String id) 메서드 정의
			// deleteMember(id) 메서드 호출
			dao.deleteCustomer(id);

			// 세션초기화
			HttpSession session=request.getSession();
			session.invalidate();
			// main.jsp 이동
			ActionForward forward=new ActionForward();
			forward.setPath("./MainPage.cu");
			forward.setRedirect(true);
			return forward;
			
			} else{
			//데이터 없으면 => false => "아이디 비밀번호 틀림"	
//			 	"입력하신 정보 틀림", 뒤로이동
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
