<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart/menu.jsp</title>
</head>
<body>
<h1>메뉴페이지(상품선택)</h1>
<%
request.setCharacterEncoding("utf-8");

String cus_id=(String)session.getAttribute("id");

if(cus_id == null) {
	response.sendRedirect("./CustomerLoginForm.cu");
}

// TODO
// 만약 로그인폼에서 장바구니에 담고, selMenu.jsp페이지로 직접접근하면
// 아이디명: null님, 세션에 담긴 장바구니는 해당 아이디의 세션객체에 담긴 정보가 불러와지므로
// (로그아웃 안해서 세션값이 유지되니까)
// selMenu.jsp 주소로 직접 접근하는 방법 막기



%>

<%= cus_id %>님 접속! <br>

<form action="./CartAddPro.ca" method="post">

<!-- TODO -->
<!-- <input type="hidden"으로 menu_num값 가져가기 -->
메뉴번호: <input type="text" name="menu_num">

	<select name="menu_name">
	 	<option>아메리카노</option>
	 	<option>카페라떼</option>
	 	<option>페퍼민트</option>
	 	<option>딸기라떼</option>
	 	<option>그린티</option>
	</select>
수량: <input type=text name="crt_count">
	<input type="submit" value="장바구니담기"><br>
</form>
<a href="./CartList.ca">장바구니 리스트</a><br>

</body>
</html>