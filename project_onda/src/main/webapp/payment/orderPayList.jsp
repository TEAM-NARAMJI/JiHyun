<%@page import="com.itwillbs.cart.db.CartDAO"%>
<%@page import="com.itwillbs.cart.db.CartDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart/orderPayList.jsp</title>

<script type="text/javascript" src="./js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	
	function fun1() {
		history.back();
	}

// 	function isChecked() {

// 		var checkType = false;

// 		$('.crt_num').each(function(index, item) {
// 			if (item.checked) {
// 				checkType = true;
// 			}
// 		})
// 		return checkType;
// 	}
</script>
</head>
<body>
<h1>주문결제 페이지</h1>
<%
String cus_id=(String)session.getAttribute("cus_id");


// cus_id가 null(세션값이 없으면) loginForm.jsp 이동
if(cus_id == null) {
	response.sendRedirect("./CustomerLoginForm.cu");
}




// String[] chk =request.getParameterValues("box");

// 	out.println("이름:"+name);
// 	out.println("메모내용:"+memo);
// 	out.println("<br>");
// try{
//    // 선택한 값이 없으면 back.
//    if(chk == null || chk.length <= 0){
//      return;
//    }
//    for(int i=0; i<chk.length; i++){
//      out.println("선택한 값 : " + chk[i] + "<br>");
//    }
//    out.println(" ");
// }catch(Exception e){
//    System.out.println(e);
// }


List<CartDTO> cartList
=(List<CartDTO>)request.getAttribute("cartList");



// System.out.println("전달 받은 배열 주소 : "+cartList);



%>
	

<%= cus_id %> 님의 주문결제 목록<br>



<%
// ArrayList<String> cartList = (ArrayList<String>) session.getAttribute("cartList");

// if (cartList == null) {	// 세션에 장바구니 객체 (cartList)가 만들어지기 전에 장바구니 목록을 보면
// 	out.println("선택한 상품이 없습니다!");
	
// } else {	//cartList에 상품을 1개 이상 저장했으면 
// 	for (String item : cartList) 
// 		out.println(item + "<br>");
// }

List<CartDTO> orderPayList
=(List<CartDTO>)request.getAttribute("orderPayList");

String menu_name="";
String menu_img="";
CartDAO dao = new CartDAO();

// String[] chk = request.getParameterValues("crt_num");


// System.out.println("전달 받은 배열 주소 : "+cartList);

%> 
<form class = "payment" action="./PaymentPro.pa" method="post">
<table border="1">
<tr><td>no.</td><td>상품정보</td>
    <td>수량</td><td>금액</td></tr>
    <%
    for(int i=0; i<orderPayList.size(); i++){
		CartDTO dto=orderPayList.get(i);
		int menu_num = dto.getMenu_num();
		int crt_num = dto.getCrt_num();
		menu_name = dao.getMenuName(menu_num, crt_num);
		menu_img = dao.getMenuImg(menu_num, crt_num);
		
		// TODO
		// crt_num => 숨기기 또는 1, 2, 3 으로 보이게 제어
		// menu_num = > 히든
		// 
		%>

<tr>
	<td class="cart_info">
		<input type="text" id="crt_num_<%=i%>" name="crt_num" class="crt_num" value="<%=dto.getCrt_num() %>" readonly>
	</td>
    <td>
    <input type="text" name="menu_num" id="menu_num_<%=i%>" class="menu_num" value="<%=dto.getMenu_num() %>" readonly>
    <img src="./upload/<%=menu_img%>" width="300" height="300">
    <input type="text" name="menu_name" class="menu_name" value="<%=menu_name %>" readonly>
    </td>
    <td>
    <input type="text" id="crt_count_<%=i%>" name="crt_count" class="crt_count" value="<%=dto.getCrt_count() %>" readonly>
    </td>
    <td><input type="text" id="crt_price_<%=i%>" name="crt_price" class="crt_price" value="<%=dto.getCrt_price() %>" readonly></td></tr>   	
    	<%
    }
    %>

<%

%>
</table>
<hr>
<h3>픽업 시간</h3>
 <input type="radio" name="pick_up" value="1분내"> 1분내
 <input type="radio" name="pick_up" value="5분"> 5분
 <input type="radio" name="pick_up" value="10분"> 10분
 <input type="radio" name="pick_up" value="15분"> 15분
 <input type="radio" name="pick_up" value="20분"> 20분
 <hr>


<%
// TODO 모든 금액에 패턴적용하기 => ##,###

int totalPrice = dao.getTotalPrice(cus_id);
%>
총 주문금액 <input type="text" id="total_price" class="total_price" value="<%= totalPrice%>" readonly><br>
<hr>
<input type="submit" value="결제하기">	
<input type="button" value="주문수정" onclick="fun1()">
</form>

</body>
</html>