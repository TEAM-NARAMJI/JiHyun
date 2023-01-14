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
                                    <h2>아이디 찾기</h2>
                                </div>
                            </div>
                            <form action="./CustomerFindIdPro.cu" method="post" name="findidForm">
                                <div class="form-group">
                                    <input type="text" name="name" id="name" class="form-control" placeholder="이름">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="phone" id="phone" class="form-control" placeholder="휴대폰 번호">
                                </div>
                                <div class="col-md-12 mb-3">
                                    <button type="submit" class="btn btn-block mybtn btn-primary tx-tfm">아이디 찾기</button>
                                </div>
                                <div class="col-md-12 ">
                                    <div class="form-group">
                                        <p class="text-center"><a href="#" id="findpw">비밀번호 찾기</a></p>
                                    </div>
                                </div>
                        	</form>
                        </div>
                    </div>
                             
                    <div id="second">
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
                            <form action="./CustomerFindPwPro.cu" method="post" name="findpwForm">
                          	    <div class="form-group">
                                    <input type="text" name="id" class="form-control" id="id" placeholder="아이디">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="name" class="form-control" id="name" placeholder="이름">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="phone" id="phone" class="form-control" placeholder="휴대폰 번호">
                                </div>
                                <div class="col-md-12 mb-3">
                                    <button type="submit" class="btn btn-block mybtn btn-primary tx-tfm">비밀번호 찾기</button>
                                </div>
                                <div class="col-md-12 ">
                                    <div class="form-group">
                                        <p class="text-center"><a href="#" id="findid">아이디 찾기</a></p>
                                    </div>
                                </div>
                        	</form>
                        </div>
                    </div>
                </div>
            </div>
        </div>   
        <script>
            $("#findpw").click(function () {
                $("#first").fadeOut("fast", function () {
                    $("#second").fadeIn("fast");
                });
            });
            $("#findid").click(function () {
                $("#second").fadeOut("fast", function () {
                    $("#first").fadeIn("fast");
                });
            });
            
            $(function () {
                $("form[name='findidForm']").validate({
                    rules: {
                        name: {
                            required: true,
                        },
                        phone: {
                            required: true,
                            digits:true
                        }
                    },
                    messages: {
                        name: "이름을 입력하세요",
                        phone: {
                            required: "휴대폰 번호를 입력하세요",
                            digits: "' - ' 제외 숫자만 입력하세요"
                        }
                    },
                    submitHandler: function (form) {
                        form.submit();
                    }
                });
            });
            $(function () {
                $("form[name='findpwForm']").validate({
                    rules: {
                    	id: {
                    		required: true,
                    	},
                        name: "required",
                        phone: {
                        	required: true,
                        	digits:true
                        },
                        email: {
                            required: true,
                            email: true
                        },
                        ch1: "required",
                       	ch2: "required"
                    },
                    messages: {
                    	id: {
                            required: "아이디를 입력하세요",
                        },
                        name: "이름을 입력하세요",
                        phone: {
                        	required: "휴대폰 번호를 입력하세요",
                        	digits: "' - ' 제외 숫자만 입력하세요"
                        },
                    },
                    submitHandler: function (form) {
                        form.submit();
                    }
                });
            });
            
        </script>
    </body>
</html>
