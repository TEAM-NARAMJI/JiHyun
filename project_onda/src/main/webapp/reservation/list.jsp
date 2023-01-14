<%@page import="java.util.List"%>
<%@page import="com.itwillbs.res.db.ReservationDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Resto - Restaurant Bootstrap 4 Template by GetTemplates.co</title>
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
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700|Josefin+Sans:300,400,700">
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    <!-- CSS -->
    <link rel="stylesheet" href="./css/style.min.css">

    <!-- Modernizr JS for IE8 support of HTML5 elements and media queries -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.js"></script>
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
						<h2>RESERVATION INFO</h2>
					</div>
					
					<div class="row mt-5">
					<%
					List<ReservationDTO> reservationList 
							= (List<ReservationDTO>)request.getAttribute("reservationList");
					int startPage = (Integer) request.getAttribute("startPage");
					int pageBlock = (Integer) request.getAttribute("pageBlock");
					int currentPage = (Integer) request.getAttribute("currentPage");
					int endPage = (Integer) request.getAttribute("endPage");
					int pageCount = (Integer) request.getAttribute("pageCount");
					%>
						<table class="table">
							<thead>
							<tr>
								<th scope="col">예약번호</th>
								<th scope="col">아이디</th>
								<th scope="col">이름</th>
								<th scope="col">테이블</th>
								<th scope="col">인원</th>
								<th scope="col">전화번호</th>
								<th scope="col">매장이용날짜</th>
								<th scope="col">예약시간</th>
								<th scope="col">신청일자</th>
								<th scope="col">예약 변경·취소</th>
							</tr>
							<%
							for (int i = 0; i < reservationList.size(); i++) {
								ReservationDTO dto = reservationList.get(i);
							%>
							<tr>
								<td><%=dto.getRes_num()%></td>
								<td><%=dto.getCus_id() %></td>
								<td><%=dto.getRes_name()%></td>
								<td><%=dto.getTb_num()%></td>
								<td><%=dto.getRes_mem()%></td>
								<td><%=dto.getRes_phone()%></td>
								<td><%=dto.getRes_use_date()%></td>
								<td><%=dto.getRes_time()%></td>
								<td><%=dto.getRes_date()%></td>
								<td><input type="button" value="변경" 
					         		onclick="location.href='./reservationUpdateForm.re?res_num=<%=dto.getRes_num()%>'">
					         		<input type="button" value="취소" 
					         		onclick="location.href='./reservationDelete.re?res_num=<%=dto.getRes_num()%>'"></td>
							</tr>
							<%
							}
							%>
							</thead>
						</table>
					</div>
					<%
					// 10페이지 이전 
					if(startPage > pageBlock){
						%>
					<a href="./reservationList.re?pageNum=<%=startPage-pageBlock%>">[10페이지 이전] </a>
						<%	
					}
					
					// 이전 currentPage-1
					if(currentPage > 1){
						%>
					<%-- 	<a href="./reservationList.re?pageNum=<%=currentPage-1%>">[1페이지 이전] </a> --%>
						<%
					}
					
					for(int i=startPage;i<=endPage;i++){
						%>
						<a href="./reservationList.re?pageNum=<%=i%>"><%=i %></a>
						<%
					}
					
					// 다음 currentPage+1
					if(currentPage < pageCount){
						%>
					<%-- 	<a href="./reservationList.re?pageNum=<%=currentPage+1%>">[1페이지 다음]</a> --%>
						<%
					}
					
					//10페이지 다음 
					if(endPage < pageCount){
						%>
						<a href="./reservationList.re?pageNum=<%=startPage+pageBlock%>">[10페이지 다음]</a>
						<%
					}
					%>
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

	<!-- Main JS -->
	<script src="./js/app.min.js "></script>
</body>
</html>