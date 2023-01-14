
<%@page import="com.itwillbs.menu.db.menuDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin/menuAdminpage.jsp</title>
</head>
<body>
<h1>제품목록</h1>
<%
List<menuDTO> menuList
=(List<menuDTO>)request.getAttribute("menuList");
//startPage pageBlock currentPage endPage pageCount
int startPage=(Integer)request.getAttribute("startPage");
int pageBlock=(Integer)request.getAttribute("pageBlock");
int currentPage=(Integer)request.getAttribute("currentPage");
int endPage=(Integer)request.getAttribute("endPage");
int pageCount=(Integer)request.getAttribute("pageCount");

// menuDTO dto =(menuDTO)request.getAttribute("dto");
%>
<input type="button" value="메인페이지" onclick="location.href='/MenuDetailPage.mn'">

<table border="1">
<tr><td>번호</td><td>상품이름</td><td>가격</td>
    <td>상세설명</td><td>이미지</td></tr>
    <%
    for(int i=0; i <menuList.size(); i++){
    	menuDTO dto=menuList.get(i);
    	%>
<tr><td><%=dto.getMenu_num()%></td>
    <td><%=dto.getMenu_name() %></td>
    <td><%=dto.getMenu_price() %></td>
    <td><%=dto.getMenu_detail() %></td>
    <td><%=dto.getMenu_img() %></td>
    <td><input type="button" value="메뉴수정"  onclick="location.href='./MenuUpdateForm.mn?menu_num=<%=dto.getMenu_num()%>'"></a></td>
    <td><input type="button" value="메뉴삭제"  onclick="location.href='./MenuDelete.mn?menu_num=<%=dto.getMenu_num()%>'"></a></td></tr>    	
    	<%
    }
    %>
</table>
<br>

<%

// 10페이지 이전 
if(startPage > pageBlock){
	%>
<a href="./MenuBoardList.mn?pageNum=<%=startPage-pageBlock%>">[10페이지 이전] </a>
	<%	
}

// 이전 currentPage-1
if(currentPage > 1){
	%>
<%-- 	<a href="./BoardList.bo?pageNum=<%=currentPage-1%>">[1페이지 이전] </a> --%>
	<%
}

for(int i=startPage;i<=endPage;i++){
	%>
	<a href="./MenuBoardList.mn?pageNum=<%=i%>"><%=i %></a>
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
	<a href="./MenuBoardList.mn?pageNum=<%=startPage+pageBlock%>">[10페이지 다음]</a>
	<%
}
%>
 

</body>
</html>