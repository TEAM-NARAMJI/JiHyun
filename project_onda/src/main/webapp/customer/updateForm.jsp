<%@page import="com.itwillbs.customer.db.CustomerDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>member/updateForm.jsp</title>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tutorialjinni.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.tutorialjinni.com/jquery-validate/1.19.1/jquery.validate.js"></script>
    <link href="https://cdn.tutorialjinni.com/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://cdn.tutorialjinni.com/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link href="https://cdn.tutorialjinni.com/font-awesome/5.12.0/css/all.css" rel="stylesheet">
    <link href="./css/login.css" rel="stylesheet" type="text/css">
	<link href="./css/menu.css" rel="stylesheet" type="text/css">
	<link href="./css/mypageMenu.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
CustomerDTO dto=(CustomerDTO)request.getAttribute("dto");
%>
<body>
  <div class="container">
     <div class="row">
         <div class="col-md-5 mx-auto">
              <div id="first">
                 <div class="myform form ">
                  <!-- 창닫기 -> 메인으로 이동 -->
                  <div class="close">
                      <a href="./CustomerMypage.cu">X</a>
                  </div>
                     <div class="logo mb-3">
                         <div class="col-md-12 text-center">
                             <h2>정보수정</h2>
                         </div>
                     </div>
                     <form action="./CustomerUpdatePro.cu" method="get">
                      <div>
                       <h6><font color="#0000FF">※ 카카오 간편가입 회원은 비밀번호가 가입시 기재한 이메일과 동일합니다.</font></h6>
                      </div>
                         <div class="form-group">
                         	<label for="exampleInputEmail1">아이디</label>
                             <input type="text" name="id" class="form-control" value="<%=dto.getCus_id() %>" readonly>
                         </div>
                         <div class="form-group">
                         	<label for="exampleInputEmail1">비밀번호</label>
                             <input type="password" name="pass" class="form-control" placeholder="비밀번호">
                         </div>
                         <div class="form-group">
                            <label for="exampleInputEmail1">이름</label>
                            <input type="text" name="name" class="form-control" value="<%=dto.getCus_name() %>">
                         </div>
                         <div class="form-group">
                            <label for="exampleInputEmail1">휴대폰 번호</label>
                            <input type="text" name="phone" class="form-control" value="<%=dto.getCus_phone() %>">
                         </div>
                         <div class="form-group">
                            <label for="exampleInputEmail1">이메일</label>
                            <input type="text" name="email" class="form-control" value="<%=dto.getCus_email() %>">
                         </div>
                         <div class="col-md-12 mb-3">
                             <button type="submit" class="btn btn-block mybtn btn-primary tx-tfm">확인</button>
                         </div>
                 	</form>
                 </div>
             </div>
         </div>
     </div>
  </div>   
</body>
</html>