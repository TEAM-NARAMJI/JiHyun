<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itwillbs.cart.db.CartDAO"%>
<%@page import="com.itwillbs.cart.db.CartDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.js"></script>
<script type="text/javascript" src="./js/jquery-3.6.3.js"></script>
<!-- Bootstrap CSS -->
<!-- <link -->
<!-- 	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" -->
<!-- 	rel="stylesheet" -->
<!-- 	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" -->
<!-- 	crossorigin="anonymous"> -->

<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="description" content="Resto"> -->
<!-- <meta name="viewport" -->
<!-- 	content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->

<!-- External CSS -->
<!-- <link rel="stylesheet" href="./vendor/bootstrap/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="./vendor/select2/select2.min.css"> -->
<!-- <link rel="stylesheet" href="./vendor/owlcarousel/owl.carousel.min.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdn.rawgit.com/noelboss/featherlight/1.7.13/release/featherlight.min.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.1/css/brands.css"> -->

<!-- <!-- Fonts -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" -->
<!-- 	rel="stylesheet"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" -->
<!-- 	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" -->
<!-- 	crossorigin="anonymous"> -->

<!-- <!-- CSS --> -->
<!-- <link rel="stylesheet" href="./css/style.min.css"> -->


<!-- Modernizr JS for IE8 support of HTML5 elements and media queries -->

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
			
			// TODO 특정체크박스 선택 => 주문하기 = > 다시 장바구니 되돌아가기 시 총 금액이 체크한 것만 계산되도록 반영안됨 
			$('#prePage').change(calcMoney);
		
	});
	
	function fun1() {
		history.back();
	}
	
	
</script>
<title>CART LIST</title>
</head>
<jsp:include page="../inc/headerMenu.jsp"></jsp:include>
<body data-spy="scroll" data-target="#navbar">
<div id="canvas-overlay"></div>
<div class="boxed-page">
<section id="gtco-menu" class="section-padding">
    <div class="container">
        <div class="section-content">
            <div class="row mb-5">
                <div class="col-md-12">
                    <div class="heading-section text-center">
						<h2>CART LIST</h2>
					</div>	
<%
String cus_id=(String)session.getAttribute("cus_id");


// cus_id가 null(세션값이 없으면) loginForm.jsp 이동
if(cus_id == null) {
	response.sendRedirect("./CustomerLoginForm.cu");
}

%>
	






<%
// ArrayList<String> cartList = (ArrayList<String>) session.getAttribute("cartList");

// if (cartList == null) {	// 세션에 장바구니 객체 (cartList)가 만들어지기 전에 장바구니 목록을 보면
// 	out.println("선택한 상품이 없습니다!");
	
// } else {	//cartList에 상품을 1개 이상 저장했으면 
// 	for (String item : cartList) 
// 		out.println(item + "<br>");
// }

DecimalFormat df = new DecimalFormat("###,###");

List<CartDTO> cartList
=(List<CartDTO>)request.getAttribute("cartList");

String menu_name="";
String menu_img="";
CartDAO dao = new CartDAO();
%> 

<form action="./OrderList.pa" method="post">
	<div class="col-md-10 mb-2 text-left">
	전체선택 <input type="checkbox" name="check-all" id="check-all" class="check-all" checked="checked">
	</div>
<table class="table">
<tr><td>no.</td><td>상품정보</td>
    <td>수량</td><td>금액</td></tr>
    <%
    for(int i=0; i<cartList.size(); i++){
		CartDTO dto=cartList.get(i);
		int menu_num = dto.getMenu_num();
		int crt_num = dto.getCrt_num();
		menu_name = dao.getMenuName(menu_num, crt_num);
		menu_img = dao.getMenuImg(menu_num, crt_num);
		
		// 개별 체크박스 중 하나라도 해제하면 자동으로 전체선택 체크박스가 선택해제되게 제어
    	%>

<tr>
	<td class="cart_info">
		<input type="checkbox" id="crt_num_<%=i%>" name="crt_num" class="crt_num" value="<%=dto.getCrt_num() %>" checked="checked">
	</td>
    <td>
    <input type="hidden" name="menu_num" id="menu_num_<%=i%>" class="menu_num" value="<%=dto.getMenu_num() %>" style="border:0;" readonly>
    <img src="./img/<%=menu_img%>" width="50" height="50">
    <input type="text" name="menu_name" class="menu_name" value="<%=menu_name %>" style="border:0;" readonly>
    </td>
    <td>
    <!-- TODO 수량에 숫자가 아닌 문자 입력시 alert창 제어하기 -->
    <!--  <input type="text" id="crt_count_<%=i%>" name="crt_count" class="crt_count" style="width:40px;" value="<%=dto.getCrt_count() %>"> -->
	<input type="number" class="crt_count" step="1" min="0" id="crt_count_<%=i%>" name="crt_count" required value="<%=dto.getCrt_count() %>" style="text-align: left; width: 40px; height: 25px;">
    <input type="button" id="count_check_<%=i%>" class="count_check" style="background:#B2CCFF; border:#B2CCFF;" value="수량변경">
    <input type="button" class="menu_delete" value="삭제" style="background:#B2CCFF; border:#B2CCFF;"
	 onclick="location.href='./CartDelete.ca?num=<%=dto.getCrt_num() %>'">
	</td>
    <td><input type="text" id="crt_price_<%=i%>" name="crt_price" class="crt_price" value="<%=df.format(dto.getCrt_price()) %>" style="border:0;" readonly></td></tr> 
    	<%
    }
    %>

</table>

<%
// TODO 모든 금액에 패턴적용하기 => ##,###

int totalPrice = dao.getTotalPrice(cus_id);
%>
<div class="text-center">
					총 주문금액 <input type="text" id="total_price" class="total_price" value="<%= df.format(totalPrice) %>" style="width: 70px; border:0; color:blue; font-weight:bold;" readonly>원
					</div>
					<div class="text-right">
						<input type="submit" class="btn btn-primary" value="주문하기">	
						<input type="button" class="btn btn-primary" value="이전페이지" onclick="fun1()">
					</div>
					</form>
				</div>
			</div>			
        </div>
    </div>
</section>
</div>	
</body>
<!-- Option 1: Bootstrap Bundle with Popper -->
<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script> -->

<!-- footer -->
<%-- <jsp:include page="../inc/footerMain.jsp"></jsp:include> --%>
<!-- 	<!-- External JS --> -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script> -->
<!-- 	<script src="./vendor/bootstrap/popper.min.js"></script> -->
<!-- 	<script src="./vendor/bootstrap/bootstrap.min.js"></script> -->
<!-- 	<script src="./vendor/select2/select2.min.js "></script> -->
<!-- 	<script src="./vendor/owlcarousel/owl.carousel.min.js"></script> -->
<!-- 	<script src="https://cdn.rawgit.com/noelboss/featherlight/1.7.13/release/featherlight.min.js"></script> -->
<!-- 	<script src="./vendor/stellar/jquery.stellar.js" type="text/javascript" charset="utf-8"></script> -->
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script> -->
<!-- <!-- Option 1: Bootstrap Bundle with Popper --> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script> -->
    
<!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> -->
<!-- 	<!-- Main JS --> -->
<!-- 	<script src="./js/app.min.js "></script> -->
</html>