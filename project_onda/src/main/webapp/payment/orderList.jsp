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
    
    <!-- jquery -->
    <script src="https://cdn.tutorialjinni.com/jquery/3.4.1/jquery.min.js"></script>
    
    <!-- iamport API -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Resto">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- External CSS -->
    <link rel="stylesheet" href="./vendor/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="./vendor/select2/select2.min.css">
    <link rel="stylesheet" href="./vendor/owlcarousel/owl.carousel.min.css">
    <link rel="stylesheet" href="https://cdn.rawgit.com/noelboss/featherlight/1.7.13/release/featherlight.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.1/css/brands.css">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    <!-- CSS -->
    <link rel="stylesheet" href="./css/style.min.css">
    <link rel="stylesheet" href="./css/review.css">

    <!-- Modernizr JS for IE8 support of HTML5 elements and media queries -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.js"></script>
	<script type="text/javascript" src="./js/jquery-3.6.3.js"></script>
</head>
<title>ORDER LIST</title>
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
						<h2>ORDER LIST</h2>
					</div>	
					<%
					String cus_id=(String)session.getAttribute("cus_id");
					
					if(cus_id == null) {
					   response.sendRedirect("./CustomerLoginForm.cu");
					}
					
// 					String[] chk =request.getParameterValues("crt_num");
					%>
					
					
					<%
					DecimalFormat df = new DecimalFormat("###,###");
					
					List<CartDTO> orderList
					=(List<CartDTO>)request.getAttribute("orderList");
					
					String menu_name="";
					String menu_img="";
					int totalPrice=0;
					CartDAO dao = new CartDAO();
					%> 
					<form class = "payment" action="./PaymentInsertPro.pa" method="post">
					<table class="table">
					<tr><th>no.</th><th>상품정보</th>
					    <th>수량</th><th>금액</th></tr>
					    <%
					    for(int i=0; i<orderList.size(); i++){
							CartDTO dto=orderList.get(i);
							int menu_num = dto.getMenu_num();
							int crt_num = dto.getCrt_num();
							menu_name = dao.getMenuName(menu_num, crt_num);
							menu_img = dao.getMenuImg(menu_num, crt_num);
							totalPrice += dto.getCrt_price();

					      // TODO crt_num => hidden 처리 또는 1, 2, 3.. 순으로 처리
					      %>
					
					<tr>
					   <td class="cart_info">
					      <input type="text" id="crt_num_<%=i%>" name="crt_num" class="crt_num" value="<%=dto.getCrt_num() %>" style="border:0;" readonly>
					   </td>
					    <td>
					    <input type="hidden" name="menu_num" id="menu_num_<%=i%>" class="menu_num" value="<%=dto.getMenu_num() %>" style="border:0;" readonly>
					    <img src="./img/<%=menu_img%>" width="50" height="50">
					    <input type="text" name="menu_name" class="menu_name" value="<%=menu_name %>" style="border:0;" readonly>
					    </td>
					    <td>
					    <input type="text" id="crt_count_<%=i%>" name="crt_count" class="crt_count" value="<%=dto.getCrt_count() %>" style="border:0;" readonly>
					    </td>
					    <td><input type="text" id="crt_price_<%=i%>" name="crt_price" class="crt_price" value="<%=df.format(dto.getCrt_price()) %>" style="border:0;" readonly></td></tr>      
					       <%
					    }
					    %>
					 
					</table>
					<br><br>
					<div class="text-center">
					<div class="heading-section text-center">
						<h2>픽업 시간</h2>
						</div>
						<table class="table">
						 <tr>
						 <th><input type="radio" name="pick_up" value="즉시"> 즉시</th>
						 <th><input type="radio" name="pick_up" value="5분"> 5분</th>
						 <th><input type="radio" name="pick_up" value="10분"> 10분</th>
						 <th><input type="radio" name="pick_up" value="15분"> 15분</th>
						 <th><input type="radio" name="pick_up" value="20분"> 20분</th>
						 </tr>
						 </table>
					</div><br>
				
					<div class="text-center">
						<h5>총 주문금액 <input type="text" id="total_price" class="total_price" value="<%= totalPrice%>" style="width: 70px; border:0; color:blue; font-weight:bold;" readonly>원</h5>
					</div>
					</form>
					<div class="text-right">
						<input type="submit" class="btn btn-primary" value="결제하기" onclick="requestPay()">   
						<input type="button" class="btn btn-primary" value="주문수정" onclick="fun1()">
					
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
					
					</div>
					
				</div>	
			</div>			
        </div>
    </div>
</section>
</div>	
</body>
	<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- footer -->
<jsp:include page="../inc/footerMain.jsp"></jsp:include>
	<!-- External JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
	<script src="./vendor/bootstrap/popper.min.js"></script>
	<script src="./vendor/bootstrap/bootstrap.min.js"></script>
	<script src="./vendor/select2/select2.min.js "></script>
	<script src="./vendor/owlcarousel/owl.carousel.min.js"></script>
	<script src="https://cdn.rawgit.com/noelboss/featherlight/1.7.13/release/featherlight.min.js"></script>
	<script src="./vendor/stellar/jquery.stellar.js" type="text/javascript" charset="utf-8"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
	<!-- Main JS -->
	<script src="./js/app.min.js "></script>
</html>