<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/common.css" rel="stylesheet">
<title>notice/writeForm.jsp</title>
</head>
<body>
<%
String cus_id=(String)session.getAttribute("cus_id");
%>

<h1>글쓰기</h1>

<form action="./NotiWritePro.no" method="post">
<table border="1">
<tr><td>name</td>
<td><input type="text" name="cus_id" value="<%=cus_id %>" readonly></td></tr>
<tr><td>title</td>
<td><input type="text" name="nt_title"></td></tr>
<tr><td>content</td>
<td><textarea name="nt_content" rows="10" cols="50"></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글쓰기"></td></tr>
</table>
</form>
</body>
</html>


