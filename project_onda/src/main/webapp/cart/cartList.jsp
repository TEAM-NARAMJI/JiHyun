<%@page import="com.itwillbs.cart.db.CartDAO"%>
<%@page import="com.itwillbs.cart.db.CartDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart/cartList.jsp</title>

<script type="text/javascript" src="./js/jquery-3.6.3.js"></script>
<script type="text/javascript">

	function calcMoney(){
		var totalPrice = 0;
		$('.crt_num').each(function(index, item){
		    if(item.checked){
		    	var i = item.id.split('_')[2];
		    	totalPrice += Number($('#crt_price_' + i).val());
		    }
		})
		
		$('#total_price').val(totalPrice);
	}

	$(document).ready(function() {
		
		$('.count_check').click(function(e){
			var index = e.currentTarget.id.split('_')[2]; 
			var crt_num = $('#crt_num_' + index).val();		// 카트 번호
			var menu_num = $('#menu_num_' + index).val();	// 메뉴 번호
			var crt_count = $('#crt_count_' + index).val();	// 수량
			$.ajax({
				url:'./CartUpdate.ca',
				data: { 'crt_num' : crt_num
					  , 'menu_num' : menu_num
					  , 'crt_count' : crt_count},
				success: function(rdata){	// rdata: 가상주소로 이동해서 받은 결과를 저장할 변수 이름을 임의로 지정
					$('#crt_price_' + index).val(rdata)
					calcMoney();
				}
			});
		})
		
		
		// 체크박스 전체선택/해제
		$( '#check-all' ).click( function() {
          $( '.crt_num' ).prop( 'checked', this.checked );
        });
		
		$('.crt_num').change(calcMoney);	
		$('#check-all').change(calcMoney);
		
		
});
		

	
	function fun1() {
		history.back();
	}
	
	
</script>
</head>
<body>
<h1>장바구니 목록</h1>
<%
String cus_id=(String)session.getAttribute("cus_id");


// cus_id가 null(세션값이 없으면) loginForm.jsp 이동
if(cus_id == null) {
	response.sendRedirect("./CustomerLoginForm.cu");
}

%>
	

<%= cus_id %> 님의 장바구니 목록<br>

<hr>



<%
// ArrayList<String> cartList = (ArrayList<String>) session.getAttribute("cartList");

// if (cartList == null) {	// 세션에 장바구니 객체 (cartList)가 만들어지기 전에 장바구니 목록을 보면
// 	out.println("선택한 상품이 없습니다!");
	
// } else {	//cartList에 상품을 1개 이상 저장했으면 
// 	for (String item : cartList) 
// 		out.println(item + "<br>");
// }

List<CartDTO> cartList
=(List<CartDTO>)request.getAttribute("cartList");

String menu_name="";
String menu_img="";
CartDAO dao = new CartDAO();

// System.out.println("전달 받은 배열 주소 : "+cartList);

%> 
<form action="./OrderPayList.pm" method="post">
전체선택 <input type="checkbox" name="check-all" id="check-all" class="check-all" checked="checked">
<table border="1">
<tr><td>no.</td><td>상품정보</td>
    <td>수량</td><td>금액</td></tr>
    <%
    for(int i=0; i<cartList.size(); i++){
		CartDTO dto=cartList.get(i);
		int menu_num = dto.getMenu_num();
		int crt_num = dto.getCrt_num();
		menu_name = dao.getMenuName(menu_num, crt_num);
		menu_img = dao.getMenuImg(menu_num, crt_num);
		
		
		// TODO crt_num : checkbox / menu_num : hidden 바꾸기
		// 개별 체크박스 중 하나라도 해제하면 자동으로 전체선택 체크박스가 선택해제되게 제어
    	%>

<tr>
	<td class="cart_info">
		<input type="checkbox" id="crt_num_<%=i%>" name="crt_num" class="crt_num" value="<%=dto.getCrt_num() %>" checked="checked">
	</td>
    <td>
    <input type="text" name="menu_num" id="menu_num_<%=i%>" class="menu_num" value="<%=dto.getMenu_num() %>" readonly>
    <img src="./upload/<%=menu_img%>" width="300" height="300">
    <input type="text" name="menu_name" class="menu_name" value="<%=menu_name %>" readonly>
    </td>
    <td>
    <!-- TODO 수량에 숫자가 아닌 문자 입력시 alert창 제어하기 -->
    <input type="text" id="crt_count_<%=i%>" name="crt_count" class="crt_count" value="<%=dto.getCrt_count() %>">
    <input type="button" id="count_check_<%=i%>" class="count_check" value="수량변경">
    <br>
    <input type="button" class="menu_delete" value="삭제"
    onclick="location.href='./CartDelete.ca?num=<%=dto.getCrt_num() %>'">
    </td>
    <td><input type="text" id="crt_price_<%=i%>" name="crt_price" class="crt_price" value="<%=dto.getCrt_price() %>" readonly></td></tr> 
    	<%
    }
    %>

</table>
<hr>

<%
// TODO 모든 금액에 패턴적용하기 => ##,###

int totalPrice = dao.getTotalPrice(cus_id);
%>
총 주문금액 <input type="text" id="total_price" class="total_price" value="<%= totalPrice%>" readonly><br>
<hr>
<input type="submit" value="주문하기">	
<input type="button" value="이전페이지" onclick="fun1()">
</form>
<input type="button" value="메뉴목록보기" 
onclick="location.href='./Menu.ca'"> <!-- TODO 삭제예정 -->
</body>
</html>