<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login & Registration Bootstrap 4 Form Template</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.tutorialjinni.com/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://cdn.tutorialjinni.com/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.tutorialjinni.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.tutorialjinni.com/jquery-validate/1.19.1/jquery.validate.js"></script>
    <link href="https://cdn.tutorialjinni.com/font-awesome/5.12.0/css/all.css" rel="stylesheet">
    <link href="./css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
String pass = (String)request.getAttribute("pass");
%>

<div class="container">
    <div class="row">
        <div class="col-md-5 mx-auto">
             <div id="first">
                <div class="myform form ">
                 <!-- 창닫기 -> 메인으로 이동 -->
                 <div class="close">
                     <a href="./MainPage.cu">X</a>
                 </div>
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h2>비밀번호 찾기</h2>
                        </div>
                    </div>
                    <form action="./CustomerLoginForm.cu" method="post">
                        <%
						if(pass != null) {
						%>
						<h6>회원님의 비밀번호는 <%=pass %> 입니다.</h6><br>
						<div class="col-md-12 mb-3">
                            <button type="submit" class="btn btn-block mybtn btn-primary tx-tfm">로그인 하기</button>
                        </div>
						<%	
						} else {
						%>
						<h6>등록된 정보가 없습니다.</h6><br>
						<div class="col-md-12 mb-3">
                            <button type="submit" class="btn btn-block mybtn btn-primary tx-tfm" onclick="history.back()">다시 찾기</button>
                        </div>
						<%
						}
						%>
                	</form>
                </div>
            </div>
        </div>
    </div>
</div>   
</body>
</html>