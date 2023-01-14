package com.itwillbs.res.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.res.db.ReservationDAO;
import com.itwillbs.res.db.ReservationDTO;
import com.itwillbs.res.action.ActionForward;

public class AdminResList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ReservationDAO dao=new ReservationDAO();

		// 한 화면에 보여줄 글 개수 설정 (10개 설정)
		int pageSize=10;
		
		// 현 페이지 번호 파라미터값 가져오기
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
		
		//dao주소를 통해서 메서드 호출
		List<ReservationDTO> reservationList =dao.getAdminReservationList(startRow,pageSize);
	
		//여러글을 저장하는 List배열변수=dao.getBoardList(시작행,글개수);
//		List<ReservationDTO> ReservationList =dao.getReservationList(startRow,pageSize);
		
		int count=dao.getReservationCount2();
		
		//한 화면에 보여줄 페이지 개수 설정(10개 페이지) 
		int pageBlock=5; 
		
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		
		int endPage=startPage+pageBlock-1;
		
		int pageCount = count/pageSize
                +(count%pageSize==0 ? 0 : 1);
		if(endPage > pageCount){
			endPage=pageCount;
		}
		
		request.setAttribute("reservationList", reservationList);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./reservation/AdminResList.jsp");
		forward.setRedirect(false);
		return forward;
		}
	}
