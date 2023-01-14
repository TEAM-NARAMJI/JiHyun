<%@page import="com.itwillbs.board.db.BoardDTO"%>
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
<title>board/list.jsp</title>
</head>
<body>
<h1>QNA</h1>
<%
String cus_id=(String)session.getAttribute("cus_id");
%>
<div class="accordion" id="accordionExample">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h2 class="mb-0">
        <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Q.
        </button>
      </h2>
    </div>

    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
      <div class="card-body">
        Some placeholder content for the first accordion panel. This panel is shown by default, thanks to the <code>.show</code> class.
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingTwo">
      <h2 class="mb-0">
        <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          Q.
        </button>
      </h2>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
      <div class="card-body">
        Some placeholder content for the second accordion panel. This panel is hidden by default.
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingThree">
      <h2 class="mb-0">
        <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          Q.
        </button>
      </h2>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
      <div class="card-body">
        And lastly, the placeholder content for the third and final accordion panel. This panel is hidden by default.
      </div>
    </div>
  </div>
</div>

<div class="container"></div>
<%
List<BoardDTO> boardList
=(List<BoardDTO>)request.getAttribute("boardList");
int startPage=(Integer)request.getAttribute("startPage");
int pageBlock=(Integer)request.getAttribute("pageBlock");
int currentPage=(Integer)request.getAttribute("currentPage");
int endPage=(Integer)request.getAttribute("endPage");
int pageCount=(Integer)request.getAttribute("pageCount");
%>

<h3>글 목록</h3>
<%
if(cus_id != null){
%>
	<a href="./BoardWriteForm.bo">글쓰기</a><br>	
	<a href="./MemberMain.me">마이페이지</a><br>
<%
}
%>
<a href="./BoardList.bo">글목록</a><br>


<table border="1">
	<tr><td>번호</td>
		<td>작성자</td>
		<td>제목</td>
    	<td>등록일</td>
    	<td>조회수</td></tr>
    <%
    for(int i=0; i < boardList.size(); i++){
    	BoardDTO dto=boardList.get(i);
    %>
	<tr><td><%=dto.getQna_num() %></td>
	    <td><%=dto.getCus_id().replaceAll("(?!.{3}).", "*")%></td>
	    <td><a href="./BoardContent.bo?num=<%=dto.getQna_num()%>">
	    	<%=dto.getQna_title() %></a></td>
	    <td><%=dto.getQna_reg() %></td>
	    <td><%=dto.getQna_view() %></td></tr>    	
	   	<%
	    }
	    %>
</table>
<br>
<%
// 10페이지 이전 
if(startPage > pageBlock){
	%>
<a href="./BoardList.bo?pageNum=<%=startPage-pageBlock%>">[10페이지 이전] </a>
	<%	
}
// 이전 currentPage-1
if(currentPage > 1){
	%>
<%-- 	<a href="./BoardList.bo?pageNum=<%=currentPage-1%>">[1페이지 이전] </a> --%>
	<%
}
for(int i=startPage; i<=endPage; i++){
	%>
	<a href="./BoardList.bo?pageNum=<%=i%>"><%=i %></a>
	<%
}
// 다음 currentPage+1
if(currentPage < pageCount){
	%>
<%-- 	<a href="./BoardList.bo?pageNum=<%=currentPage+1%>">[1페이지 다음]</a> --%>
	<%
}

//10페이지 다음 
if(endPage < pageCount){
	%>
	<a href="./BoardList.bo?pageNum=<%=startPage+pageBlock%>">[10페이지 다음]</a>
	<%
}
%>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
</body>
</html>