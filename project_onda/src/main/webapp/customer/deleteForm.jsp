<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>delete</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tutorialjinni.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.tutorialjinni.com/jquery-validate/1.19.1/jquery.validate.js"></script>
    <link href="https://cdn.tutorialjinni.com/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://cdn.tutorialjinni.com/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link href="https://cdn.tutorialjinni.com/font-awesome/5.12.0/css/all.css" rel="stylesheet">
    <link href="./css/login.css" rel="stylesheet" type="text/css">
</head>
<body>

<%String id = (String)session.getAttribute("id");%>

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
                                    <h2>회원탈퇴</h2>
                                </div>
                            </div>
                            <form action="./CustomerDeletePro.cu" method="get">
	                            <div>
		                            <h6>가입된 회원정보가 모두 삭제됩니다. 작성하신 게시물은 삭제되지 않습니다. 회원 탈퇴를 진행하시겠습니까?</h6>
		                            <h6><font color="#0000FF">※ 카카오 간편가입 회원은 비밀번호가 가입시 기재한 이메일과 동일합니다.</font></h6>
	                            </div>
                                <div class="form-group">
                                	<label for="exampleInputEmail1">아이디</label>
                                    <input type="text" name="id" class="form-control" value="<%=id %>" readonly>
                                </div>
                                <div class="form-group">
                                	<label for="exampleInputEmail1">비밀번호</label>
                                    <input type="password" name="pass" class="form-control" placeholder="비밀번호">
                                </div>
                                <div class="col-md-12 mb-3">
                                    <button type="submit" class="btn btn-block mybtn btn-primary tx-tfm">탈퇴하기</button>
                                </div>
                        	</form>
                        </div>
                    </div>
                </div>
            </div>
        </div>   
    </body>
</body>
</html>