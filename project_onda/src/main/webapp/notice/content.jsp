<%@page import="com.itwillbs.notice.db.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<title>notice/content.jsp</title>
</head>
<body>
<h1>글내용</h1>
<%
NoticeDTO dto=(NoticeDTO)request.getAttribute("dto");
%>



<table border="1">
<tr><td>글번호</td><td><%=dto.getNt_num() %></td></tr>
<tr><td>작성자</td><td><%=dto.getNt_name() %></td></tr>
<tr><td>등록일</td><td><%=dto.getNt_date() %></td></tr>
<tr><td>조회수</td><td><%=dto.getNt_view() %></td></tr>
<tr><td>제목</td><td><%=dto.getNt_title() %></td></tr>
<tr><td>글내용</td><td><%=dto.getNt_content() %></td></tr>
<tr><td colspan="2">
<%
String cus_id=(String)session.getAttribute("cus_id");

if(cus_id.equals("admin")) {
%>
<input type="button" value="글수정" 
	onclick="location.href='./NotiUpdateForm.no?num=<%=dto.getNt_num()%>'">
<input type="button" value="글삭제" 
	onclick="location.href='./NotiDelete.no?num=<%=dto.getNt_num()%>'">	
<%
}
%>
<input type="button" value="글목록" 
onclick="location.href='./NotiList.no'">
</td></tr>
</table>
 	
  </body>
</html>


