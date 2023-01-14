<%@page import="com.itwillbs.notice.db.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/common.css" rel="stylesheet">
<title>notice/updateForm.jsp</title>
</head>
<body>
<%
NoticeDTO dto=(NoticeDTO)request.getAttribute("dto");
%>
<h1>글수정</h1>
<form action="./NotiUpdatePro.no" method="post">
<input type="hidden" name="num" value="<%=dto.getNt_num()%>">
<table border="1">
<tr><td>글쓴이</td>
<td><input type="text" name="name" value="<%=dto.getNt_name() %>" readonly></td></tr>
<tr><td>글제목</td>
<td><input type="text" name="subject" value="<%=dto.getNt_title()%>"></td></tr>
<tr><td>글내용</td>
<td><textarea name="content" rows="10" cols="20"><%=dto.getNt_content() %></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글수정"></td></tr>
</table>
</form>
</body>
</html>


