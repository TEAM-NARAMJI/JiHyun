<%@page import="com.itwillbs.menu.db.menuDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menuInsertForm</title>
<link href="./css/update.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./script/jquery-3.6.3.js"></script>
<script type="text/javascript">
	
</script>


</head>
<body>
<%
 	// 세션값 가져오기 -->
 String id = (String) session.getAttribute("menu_num");
 menuDTO dto = new menuDTO();
%> 
	<h1>신메뉴 등록</h1>
	<form action="./MenuInsertPro.mn" method="post" enctype="multipart/form-data">
		<table id="insert" class="insertmenu">
			<tr>
				<th>카테고리</th>
				<td><label><input type="radio" name="menu_category" value="음료">음료</label>
					<label><input type="radio" name="menu_category" value="푸드">푸드</label> 
					<label><input type="radio" name="menu_category" value="굿즈">굿즈</label>
					</td>
			</tr>
			<tr>
				<th>메뉴 이름</th>
				<td><input type="text" name="menu_name"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="menu_price"></td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td><textarea name="menu_detail" rows="20" cols="40"></textarea></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td><input type="file" name="menu_img"></td>
			</tr>	
				<td ><input type="submit" value="메뉴추가"></td>
			
		</table>
	</form>



<!-- admin 관리자 아이디로 로그인시 접근 가능------->
<%-- <%  --%>
<!-- // if(cus_id != null) { -->
<!-- // 	if(cus_id.equals("admin")){ -->
<%-- 		%> --%>
<!-- 		<a href="#"></a><br> -->
<%-- 		<% --%>
<!-- // 	} -->
<!-- // } -->
<%-- %> --%>




</body>
</html>