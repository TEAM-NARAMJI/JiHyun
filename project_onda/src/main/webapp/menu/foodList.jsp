
<%@page import="com.itwillbs.menu.db.menuDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>menuList</title>
<style>
</style>
<link rel="stylesheet" type="text/css" href="./css/default.css">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/menuList.css">
</head>

<body>
<%

String id=(String)session.getAttribute("cus_id");
//로그인이 안된 상태면 장바구니 담기 불가----------------------------------------------------
List<menuDTO> foodList
=(List<menuDTO>)request.getAttribute("foodList");

%>

<h1>FOOD</h1>


<div class="category_select">
		<form action="" method="post">
			<fieldset>
				<!-- 관련컨텐츠를 하나의 그룹으로 묶음 -->
				<legend> 분류보기 </legend>
				<!-- fieldset의 제목 명령어 -->

				<ul class="cate_list">
					<!--  순서없는 항목  -->
					<li><input type="radio" style='vertical-align: middle;'
						name="category_1" id="category_all" checked="checked"> <label
						for="category_all">모두보기</label></li>

					<li><input type="radio" style='vertical-align: middle;'
						name="category_1" id="category_1"> <label for="category_1">카테고리1</label></li>

					<li><input type="radio" style='vertical-align: middle;'
						name="category_1" id="category_2"> <label for="category_2">카테고리2</label></li>

					<li><input type="radio" style='vertical-align: middle;'
						name="category_1" id="category_3"> <label for="category_3">카테고리3</label></li>

				</ul>
			</fieldset>
		</form>
	</div>


<%
for(int i = 0; i < foodList.size(); i++){
	menuDTO dto = foodList.get(i); 
	%>
	<ul class="menuList">
		<li>
			<dl>
				<dt style="text"><%=dto.getMenu_name()%></dt>
				<dd class="img">
					<img src="./upload/<%=dto.getMenu_img() %>" width="300" height="300" alt="">
				</dd>
				<dd><%=dto.getMenu_price() %></dd>
				<dd>
					<div>수량 <input type="number" step="1" min="0" style="text-align: left; width:40px; height:17px;">
<%-- 					<input type="button" value="담기" onclick="location.href='./카트주소?menu_num=<%=dto.getMenu_num()%>'">  --%>
						<!-- ajax -->
					</div>
				</dd>
				<dd></dd>
			</dl>
		</li>
	</ul>
	<%
}
%>




</body>
</html>