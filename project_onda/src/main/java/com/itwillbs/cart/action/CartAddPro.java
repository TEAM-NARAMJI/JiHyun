package com.itwillbs.cart.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.cart.db.CartDAO;
import com.itwillbs.cart.db.CartDTO;

public class CartAddPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String cus_id = (String)session.getAttribute("cus_id");
		
		int menu_num = Integer.parseInt(request.getParameter("menu_num"));
		
		// TODO 수량값이 0일 경우 NumberFormatException 오류!
		int crt_count=Integer.parseInt(request.getParameter("crt_count"));
		
		
		// TODO 
		// cartAdd.jsp파일에 사용자가 바로 접근할 경우 menu_name에 전달받은 request값이 null이므로
		// null 이(가) 추가되었습니다. alert창 띄워짐
		// 방법1. => cartAdd.jsp로 직접 접근 할 경우 : selMenu.jsp로 되돌아가기
		// 방법2. => cartAdd.jsp로 직접 접근 할 경우 session.getAttribute("cus_id") 판단
//					null이면 로그인페이지로 되돌아가기
		// 두가지 방법 중 하나 고민하기!


		// 바구니에 상품정보 담기
		// CartDTO => 객체생성(기억장소 할당)
		CartDTO dto=new CartDTO();
		System.out.println("CartDTO 주소 : " + dto);
		
		dto.setCus_id(cus_id);
		dto.setMenu_num(menu_num);
		dto.setCrt_price(0);
		dto.setCrt_count(crt_count);
		
		CartDAO dao=new CartDAO();
		System.out.println("CartDAO 주소 : "+dao);
		// dao.insertCart(cus_id, menu_num, crt_count);
		// dao.insertCart(CartDTO 바구니 주소);
		dao.insertCart(dto);
		
		request.setAttribute("dto", dto);


		// 리턴 이동할 주소 
		// 이동 ./MemberLoginForm.me
		// ActionForward 객체생성
		// 이동경로, 이동방식 담아서 리턴
		ActionForward forward=new ActionForward();
		forward.setPath("./CartList.ca");
		forward.setRedirect(true);
		return forward;
	}


}
