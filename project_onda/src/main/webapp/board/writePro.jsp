<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

String cus_id=request.getParameter("cus_id");
String qna_title = request.getParameter("qna_title");
String qna_content = request.getParameter("qna_content");

BoardDTO dto = new BoardDTO();

dto.setCus_id(cus_id);
dto.setQna_title(qna_title);
dto.setQna_content(qna_content);
dto.setQna_reg(new Timestamp(System.currentTimeMillis()));
dto.setQna_view(0);

BoardDAO dao = new BoardDAO();

dao.insertBoard(dto);

response.sendRedirect("list.jsp");

%>