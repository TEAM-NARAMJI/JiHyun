<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

int qna_num=Integer.parseInt(request.getParameter("qna_num"));
String cus_id = request.getParameter("cus_id");
String qna_title = request.getParameter("qna_title");
String qna_content = request.getParameter("qna_content");

BoardDTO dto = new BoardDTO();

dto.setQna_num(qna_num);
dto.setCus_id(cus_id);
dto.setQna_title(qna_title);
dto.setQna_content(qna_content);

BoardDAO dao = new BoardDAO();

dao.updateBoard(dto);

// list.jsp 이동
response.sendRedirect("list.jsp");
%>