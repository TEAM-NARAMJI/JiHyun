<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// board/delete.jsp
//deleteForm.jsp?num=1
int num=Integer.parseInt(request.getParameter("num"));

//BoardDAO 객체생성
BoardDAO dao = new BoardDAO();

// 리턴할형 없음 deleteBoard(int num 변수) 메서드 정의 
// 디비작업 주소를 통해서 deleteBoard(num) 호출
dao.deleteBoard(num);


// list.jsp 이동
response.sendRedirect("list.jsp");




%>