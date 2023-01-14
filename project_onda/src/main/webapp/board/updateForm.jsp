<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/updateForm.jsp</title>
</head>
<body>
<%
BoardDTO dto=(BoardDTO)request.getAttribute("dto");
%>
<h1>글수정</h1>
<form action="./BoardUpdatePro.bo" method="post">
<input type="hidden" name="num" value="<%=dto.getQna_num()%>">
<table border="1">
<tr><td>글쓴이</td>
<td><input type="text" name="name" value="<%=dto.getCus_id()%>" readonly></td></tr>
<tr><td>글제목</td>
<td><input type="text" name="title" value="<%=dto.getQna_title()%>"></td></tr>
<tr><td>글내용</td>
<td><textarea name="content" rows="10" cols="20"><%=dto.getQna_content() %></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글수정"></td></tr>
</table>
</form>
</body>
</html>


