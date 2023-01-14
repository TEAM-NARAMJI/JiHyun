<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N">
<title>board/content.jsp</title>
</head>
<body>
<h1>Content</h1>
<%
BoardDTO dto = (BoardDTO)request.getAttribute("dto");
%>
<table border="1">
	<tr><td>글번호</td><td><%=dto.getQna_num() %></td></tr>
	<tr><td>작성자</td><td><%=dto.getCus_id() %></td></tr>
	<tr><td>등록일</td><td><%=dto.getQna_reg() %></td></tr>
	<tr><td>조회수</td><td><%=dto.getQna_view() %></td></tr>
	<tr><td>제목</td><td><%=dto.getQna_title() %></td></tr>
	<tr><td>글내용</td><td><%=dto.getQna_content() %></td></tr>
	
<tr><td colspan="2">
<%
String cus_id=(String)session.getAttribute("cus_id");

if(cus_id != null){
%>
<input type="button" value="글수정" 
onclick="location.href='./BoardUpdateForm.bo?num=<%=dto.getQna_num()%>'">
<input type="button" value="글삭제" 
onclick="location.href='./BoardDelete.bo?num=<%=dto.getQna_num()%>'">	
<% }

if(cus_id.equals("admin")) { %>
<input type="button" value="답글" 
onclick="location.href='./BoardReplyForm.bo?num=<%=dto.getQna_num()%>'">
<% } %>

<input type="button" value="글목록" 
onclick="location.href='./BoardList.bo'">
</td></tr>
</table>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
</body>
</html>


