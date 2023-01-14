package com.itwillbs.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.notice.db.NoticeDAO;
import com.itwillbs.notice.db.NoticeDTO;


public class NotiList implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NoticeDAO dao=new NoticeDAO();

		int pageSize = 10;
		System.out.println("pageSize="+pageSize);
		String pageNum=request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		System.out.println("pageNum="+pageNum);
		//pageNum => 정수형 숫자 변경
		int currentPage=Integer.parseInt(pageNum);
		// 최근글 위로 정렬(num 기준으로 내림차순)
		// 구간값 가져오기 limit 시작행, 글개수
		// select * from board order by num desc limit 1,10
		// select * from board order by num desc limit 11,10
		// select * from board order by num desc limit 21,10
		// 시작행 알고리즘(계산식)으로 구하기
		// currentPage  pageSize => startRow
//		     1           10    => (1-1)*10+1=>0*10+1=> 0+1=>1
//		     2           10    => (2-1)*10+1=>1*10+1=>10+1=>11
//		     3           10    => (3-1)*10+1=>2*10+1=>20+1=>21
		int startRow=(currentPage-1)*pageSize+1;
		System.out.println("startRow="+startRow);
		//끝행 알고리즘(계산식)으로 구하기
		// startRow  pageSize => endRow
//		     1          10   =>   1+10-1=>10
//		    11          10   =>  11+10-1=>20
//		    21          10   =>  21+10-1=>30
		int endRow = startRow + pageSize-1;
		System.out.println("endRow=" + endRow);

		List<NoticeDTO> boardList = dao.getBoardList(startRow, pageSize);

		int count = dao.getBoardCount();
		System.out.println("count="+count);
		
		//페이징
		//한 화면에 보여줄 페이지 개수 설정(10개 페이지) 
		int pageBlock = 10; 
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		System.out.println("startPage="+ startPage);
		
		int endPage=startPage+pageBlock-1;
		int pageCount = count/pageSize
		                +(count%pageSize==0 ? 0 : 1);
		System.out.println("pageCount="+pageCount);
		
		if(endPage > pageCount){
			endPage=pageCount;
		}
		System.out.println("endPage="+endPage);
		
		request.setAttribute("boardList",boardList);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./notice/list2.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
