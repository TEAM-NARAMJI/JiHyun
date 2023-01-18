package com.itwillbs.payment.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.itwillbs.cart.db.CartDAO;
import com.itwillbs.cart.db.CartDTO;
import com.itwillbs.payment.db.PaymentDAO;
import com.itwillbs.payment.db.PaymentDTO;

public class PaymentInsertPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String cus_id = (String)session.getAttribute("cus_id");
		String pay_pick = request.getParameter("pick_up");
		
		Timestamp date=new Timestamp(System.currentTimeMillis());
		
		ArrayList<CartDTO> orderList = (ArrayList<CartDTO>)session.getAttribute("orderList");
		
		// list -> dto로 변환하기
//		ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>(); 
//		List<TestCartDTO> paymentList  = orderList.stream().map(TestCartDTO::entityToDTO).collect(Collectors.toList());
//		List<Integer> nameList = orderList.stream().map(CartDTO::getCrt_num).collect(Collectors.toList());
		
		ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
		for(int i=0; i<orderList.size(); i++) {
			PaymentDTO dto = new PaymentDTO();
			dto.setPay_num(0);
			dto.setCus_id(cus_id);
			dto.setMenu_num(orderList.get(i).getMenu_num());
			dto.setPay_price(orderList.get(i).getCrt_price());
			dto.setPay_count(orderList.get(i).getCrt_count());
			dto.setPay_date(date);
			dto.setPay_pick(pay_pick);
			
			paymentList.add(dto);
		}
		
		
//		request.setAttribute("paymentList",paymentList);
		PaymentDAO paymentDao = new PaymentDAO();
		CartDAO cartDao = new CartDAO();
		
		paymentDao.insertPayment(paymentList);
		
		for(int i=0; i<orderList.size(); i++) {
			cartDao.deleteCart(orderList.get(i).getCrt_num());
			
		}
		
		// 내결제 페이지 이동

		ActionForward forward=new ActionForward();
		forward.setPath("./PayComplete.pa");
		forward.setRedirect(true);
		return forward;
	}

}
