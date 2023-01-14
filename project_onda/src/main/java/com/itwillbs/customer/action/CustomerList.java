package com.itwillbs.customer.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.customer.db.CustomerDAO;
import com.itwillbs.customer.db.CustomerDTO;
import com.itwillbs.customer.action.ActionForward;

public class CustomerList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		CustomerDAO dao = new CustomerDAO();
		
		int pageSize=10;
		
		String pageNum=request.getParameter("pageNum");
		// 페이지 번호가 없으면 => "1" 설정
		if(pageNum==null){
			pageNum="1";
		}
		//pageNum => 정수형 숫자 변경
		int currentPage=Integer.parseInt(pageNum);
		// 최근글 위로 정렬(num 기준으로 내림차순)
		int startRow=(currentPage-1)*pageSize+1;
		//끝행 알고리즘(계산식)으로 구하기
		int endRow=startRow+pageSize-1;

		List<CustomerDTO> customerList =dao.getCustomerList(startRow,pageSize);
		
		int count=dao.getCustomerCount();
		
		//한 화면에 보여줄 페이지 개수 설정(10개 페이지) 
		int pageBlock=5; 
		
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		
		int endPage=startPage+pageBlock-1;
		
		int pageCount = count/pageSize
                +(count%pageSize==0 ? 0 : 1);
		if(endPage > pageCount){
			endPage=pageCount;
		}
		
		request.setAttribute("customerList", customerList);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./customer/list.jsp");
		forward.setRedirect(false);
		return forward;
	}

	
}
