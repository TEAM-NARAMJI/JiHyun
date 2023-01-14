<%@page import="com.itwillbs.notice.db.NoticeDTO"%>
<%@page import="java.util.List"%>
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
<title>notice/list2.jsp</title>
</head>
<body>
<h1>NOTICE</h1>
<%
List<NoticeDTO> boardList
= (List<NoticeDTO>)request.getAttribute("boardList");
int startPage=(Integer)request.getAttribute("startPage");
int pageBlock=(Integer)request.getAttribute("pageBlock");
int currentPage=(Integer)request.getAttribute("currentPage");
int endPage=(Integer)request.getAttribute("endPage");
int pageCount=(Integer)request.getAttribute("pageCount");
%>
<!-- <div id="header"> -->
<!-- <div class="inner"> -->
<!--   <ul class="xans-element- xans-layout xans-layout-statelogoff "><li><a href="http://en.momos.co.kr/"></a></li> -->
<!-- <li><a href="/product/search.html">SEARCH</a></li> -->
<!-- <li><a href="/order/basket.html">MYCART<span class="count displaynone EC-Layout_Basket-count-display">(<span class="EC-Layout-Basket-count">0</span>)</span></a></li> -->
<!-- <li><a href="./MemberInfo.me">MYPAGE</a> -->
<!-- </li> -->
<!-- <li><a href="/myshop/order/list.html">ORDER</a></li> -->
<!-- <li> -->
<!-- <a href="/member/insertForm.jsp">JOIN</a></li> -->
<!-- <li> -->
<!-- <a href="/member/loginForm.jsp">LOGIN</a></li> -->
<!-- </ul> -->
<!-- </div>  -->
<!-- </div><hr class="layout"><div id="wrap"> -->

 <table class="table">
<%
for(int i=0; i < boardList.size(); i++){
  	NoticeDTO dto = boardList.get(i);
%>
<tr><td><%=dto.getNt_num() %></td>
    <td><%=dto.getNt_name() %></td>
    <td><a href="./NotiContent.no?num=<%=dto.getNt_num()%>">
    	<%=dto.getNt_title() %></a></td>
    <td><%=dto.getNt_date() %></td>
    <td><%=dto.getNt_view() %></td></tr>    	
    	<%
    }
    %>
<thead>
    <tr>
      <th scope="col">no</th>
      <th scope="col">name</th>
      <th scope="col">title</th>
      <th scope="col">date</th>
      <th scope="col">view</th>
    </tr>
  </thead>
  <tbody>
    
  </tbody>
</table>
<%
String cus_id = (String)session.getAttribute("cus_id");



	%>

<form id="boardSearchForm" name="" action="/board/list.jsp" method="get" target="_top" enctype="multipart/form-data">
<input id="board_no" name="board_no" value="1" type="hidden">
<input id="page" name="page" value="1" type="hidden">
<input id="board_sort" name="board_sort" value="" type="hidden"><div class="xans-element- xans-board xans-board-search-1002 xans-board-search xans-board-1002 ">
	<fieldset class="boardSearch">

<legend>게시물 검색</legend>
        <p><select id="search_date" name="search_date" fw-filter="" fw-label="" fw-msg="">
<option value="week">일주일</option>
<option value="month">한달</option>
<option value="month3">세달</option>
<option value="all">전체</option>
		</select> 
<select id="search_key" name="search_key" fw-filter="" fw-label="" fw-msg="">
<option value="nt_title">제목</option>
<option value="nt_content">내용</option>
<option value="cus_id">아이디</option>
</select><input id="search" name="search" fw-filter="" fw-label="" fw-msg="" class="inputTypeText" placeholder="" value="" type="text"> <a href="#none" onclick="BOARD.form_submit('boardSearchForm');"><img src="//img.echosting.cafe24.com/skin/base_ko_KR/board/btn_find.gif" alt="찾기"></a></p>
    </fieldset>
</div>
</form>
</body>

</body>
</html>