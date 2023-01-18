<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itwillbs.payment.db.PaymentDAO"%>
<%@page import="com.itwillbs.payment.db.PaymentDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

    <!-- Modernizr JS for IE8 support of HTML5 elements and media queries -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.js"></script>
	<title>Payment List</title>
	<style>
	 .d1 {
	 		white-space:nowrap;
	 		font-weight: bold;
	 }
	</style>
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
                    	<h2>PAYMENT LIST</h2>
					</div>
					<div class="row mt-5">
					<%
					//세션값이 null이면 로그인페이지로 이동
					String cus_id=(String)session.getAttribute("cus_id");
					if(cus_id==null){
						response.sendRedirect("./CustomerLoginForm.cu");	
					}
					DecimalFormat df = new DecimalFormat("###,###");
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					List<PaymentDTO> paymentList 
									= (List<PaymentDTO>)request.getAttribute("paymentList");
					int count 		= (Integer)request.getAttribute("count");
					
					//메뉴 정보 가져오기
					PaymentDAO dao = new PaymentDAO();
						%>
					<div class="col-md-10 mb-2 d1">주문목록 내역 총 <b style="color: red; white-space: nowrap;"><%=count%></b> 건</div>
					<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">날짜/주문번호</th>
							<th scope="col">주문자명</th>
							<th scope="col">메뉴</th>
							<th scope="col">금액/수량</th>
							<th scope="col">리뷰</th>
						</tr>
							</thead>
						<%
						for (int i = 0; i < paymentList.size(); i++) {
							PaymentDTO dto = paymentList.get(i);
							String MenuImg = dao.getMenuImg(dto.getMenu_num());
							String MenuName = dao.getMenuName(dto.getMenu_num());
							
						%>
						<tr>
							<td><span style="color: grey; font-size: 14px"><%=dateFormat.format(dto.getPay_date()) %></span><br>
								<span style="color: blue; font-weight:bold"><%=dto.getPay_num() %></span></td>
							<td><%=dto.getCus_id() %></td>
							<td><img src = "./img/<%=MenuImg %>" alt="Menu Image" width=50px height=50px>
								<%=MenuName %>
							</td>
							<td><%=df.format(dto.getPay_price()) %>원 / <%=dto.getPay_count() %>개</td>
							<td><input type="button" value="리뷰쓰기" class="btn btn-primary btn-shadow btn-lg"
									    onclick="location.href='./ReviewWriteForm.rv?menu=<%=MenuName %>'"></td>
						</tr>
						<%
						}
						%>
					</table>
					</div>
                </div>
            </div>
        </div>
    </div>
</section>
</div>
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
</body>
</html>