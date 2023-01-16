package com.itwillbs.payment.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.payment.db.PaymentDAO;
import com.itwillbs.payment.db.PaymentDTO;

public class PaymentPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
//		HttpSession session = request.getSession();
//		String cus_id = (String)session.getAttribute("cus_id");
		String cus_id = "admin";
		int menu_num = Integer.parseInt(request.getParameter("menu_num"));
		int pay_price = Integer.parseInt(request.getParameter("crt_price"));
		int pay_count=Integer.parseInt(request.getParameter("crt_count"));
		String pay_pick = request.getParameter("pick_up");
		Timestamp pay_date = new Timestamp(System.currentTimeMillis());
		
		PaymentDAO dao = new PaymentDAO();
		
		List<PaymentDTO> payList
			= (List<PaymentDTO>)request.getAttribute("payList");
		for(int i=0; i < payList.size(); i++){
			PaymentDTO dto = payList.get(i);
			dto.setCus_id(cus_id);
			dto.setMenu_num(menu_num);		// 얘네는
			dto.setPay_price(pay_price);	// 시험용
			dto.setPay_count(pay_count);	// 입미다
//			dto.setMenu_num(dto.getMenu_num());
//			dto.setPay_price(dto.getPay_price());
//			dto.setPay_count(dto.getPay_count());
			dto.setPay_date(pay_date);
			dto.setPay_pick(pay_pick);
			
			dao.insertPay(dto);
		}
		
		ActionForward forward=new ActionForward();
		forward.setPath("./OrderList.pa");
		forward.setRedirect(true);
		return forward;
	}

}
