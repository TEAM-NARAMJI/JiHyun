<%@page import="com.itwillbs.cart.db.CartDAO"%>
<%@page import="com.itwillbs.cart.db.CartDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart/orderList.jsp</title>

<script type="text/javascript" src="./js/jquery-3.6.3.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>


</head>
<body>
<h1>주문결제 페이지</h1>
<%
String cus_id=(String)session.getAttribute("cus_id");


// cus_id가 null(세션값이 없으면) loginForm.jsp 이동
if(cus_id == null) {
	response.sendRedirect("./CustomerLoginForm.cu");
}

// String[] chk =request.getParameterValues("crt_num");
// System.out.println(chk.toString());


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

%> 

<form class = "payment" action="./PaymentInsertPro.pa" method="post">

<%
List<CartDTO> orderList = (List<CartDTO>)request.getAttribute("orderList");

String menu_name="";
String menu_img="";
int totalPrice=0;
CartDAO dao = new CartDAO();


%>


<table border="1">
<tr><td>no.</td><td>상품정보</td>
    <td>수량</td><td>금액</td></tr>
    <%
    for(int i=0; i<orderList.size(); i++){
		CartDTO dto=orderList.get(i);
		int menu_num = dto.getMenu_num();
		int crt_num = dto.getCrt_num();
		menu_name = dao.getMenuName(menu_num, crt_num);
		menu_img = dao.getMenuImg(menu_num, crt_num);
		totalPrice += dto.getCrt_price();

		
		%>

<tr>
	<td>
<!-- 		TODO crt_num : hidden 처리-->
		<input type="hidden" id="crt_num_<%=i%>" name="crt_num" class="crt_num" value="<%=dto.getCrt_num() %>" readonly>
	</td>
    <td>
    <input type="text" name="menu_num" id="menu_num_<%=i%>" class="menu_num" value="<%=dto.getMenu_num() %>" readonly>
    <img src="./upload/<%=menu_img%>" width="300" height="300">
    <input type="text" id="menu_name_<%=i%>" name="menu_name" class="menu_name" value="<%=menu_name %>" readonly>
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
//	총 결제금액: readonly
%>
<hr>
총 결제금액 <input type="text" id="total_price" class="total_price" value="<%= totalPrice%>" readonly><br>
<hr>
</form>
<input type="submit" value="결제하기" onclick="requestPay()">	
<input type="button" value="주문수정" onclick="fun1()">

<script type="text/javascript">
	
	function requestPay() {
	   var IMP = window.IMP;  
	   IMP.init('imp84126554'); //iamport 대신 자신의 "가맹점 식별코드"를 사용
	     IMP.request_pay({
	       pg: 'html5_inicis',
	       pay_method: "card",
	       merchant_uid : 'merchant_'+new Date().getTime(),
	       name : '<%=menu_name%>' + ' 외 ' + <%=orderList.size()-1 %> + '건' ,   // 상품 이름
	       amount : <%=totalPrice %>,      // 가격
	       buyer_name : '<%=cus_id %>',
	     }, function (rsp) { // callback
	         if (rsp.success) {
	            var msg = "결제가 완료되었습니다.";
	            alert(msg);
	            $(".payment").submit();
	            
	         } else {
	            var msg = '결제에 실패하였습니다.\n';
	             msg += '에러내용 : ' + rsp.error_msg;
	             alert(msg);
	         }
	     });
	   }
	   
	function fun1() {
		history.back();
	}
</script>


</body>
</html>