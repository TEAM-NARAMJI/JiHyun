package com.itwillbs.customer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.customer.db.CustomerDAO;
import com.itwillbs.customer.db.CustomerDTO;

public class CustomerUpdateForm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		//MemberDAO 객체 생성
		CustomerDAO dao = new CustomerDAO();
		
		// 리턴할 형 MemberDTO getMember(String id)
		// 메서드 정의
		// MemberDTO dto=디비작업주소.getMember(id) 메서드 호출
		CustomerDTO dto = dao.getCustomer(id);
		
		// MemberDTO 정보를 request 담아서 오기
		request.setAttribute("dto", dto);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./customer/updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
