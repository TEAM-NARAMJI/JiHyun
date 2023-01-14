
<%@page import="com.itwillbs.menu.db.menuDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin/updateMenu.jsp</title>


<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">


<%
menuDTO dto = (menuDTO) request.getAttribute("dto");
%>
<h1>메뉴 수정</h1>
<form action="./MenuUpdatePro.mn" method="post" enctype="multipart/form-data">
	<input type="hidden" name="menu_num" value="<%=dto.getMenu_num()%>">
	<table id="update" class="updatemenu">
		<tr>
			<th>카테고리</th>
			<td>
			<label><input type="radio" name="menu_category" value="음료">음료</label> 
			<label><input type="radio" name="menu_category" value="푸드">푸드</label>
			<label><input type="radio" name="menu_category" value="굿즈">굿즈</label></td>
		</tr>
		<tr>
			<th>메뉴 이름</th>
			<td><input type="text" name="menu_name" value="<%=dto.getMenu_name()%>"></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><input type="text" name="menu_price" value="<%=dto.getMenu_price()%>"></td>
		</tr>
		<tr>
			<th>상세설명</th>
			<td><textarea name="menu_detail" rows="20"cols="40"value="<%=dto.getMenu_detail()%>"></textarea></td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td><input type="file" name="menu_img"> <%=dto.getMenu_img()%>
				<input type="hidden" name="oldfile" value="<%=dto.getMenu_img()%>">
			<td><input type="submit" value="메뉴수정" class="btn"></td>
	</table>
</form>
</body>
</html>
