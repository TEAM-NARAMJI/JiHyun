package com.itwillbs.cart.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.cart.db.CartDAO;
import com.itwillbs.cart.db.CartDTO;

public class CartList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

//		 TODO cus_id 변경
		HttpSession session = request.getSession();
		String cus_id = (String)session.getAttribute("id");
				

		ActionForward forward = null;
		
		if(cus_id == null) {
			
			forward=new ActionForward();
			//TODO jsp파일로 이동? java파일로 이동? 확인
			forward.setPath("./CustomerLoginForm.cu");
			forward.setRedirect(true);
			return forward;
			

		} else {
			//사용 => CartDAO 기억장소 할당(객체생성)
			CartDAO dao=new CartDAO();

			ArrayList<CartDTO> cartList = dao.getCartList(cus_id);
			
			
			// 데이터를 담아서 list.jsp 이동
			request.setAttribute("cartList",cartList);

			forward=new ActionForward();
			forward.setPath("./cart/cartList.jsp");
			forward.setRedirect(false);
			return forward;



		}



	}


}
