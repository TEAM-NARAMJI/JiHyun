			// 로그인, 회원가입 전환 페이지
			$(function() {
				$("#signup").click(function() {
	                $("#first").fadeOut("fast", function () {
	                    $("#second").fadeIn("fast");
	                });
	            });
	            $("#signin").click(function() {
	                $("#second").fadeOut("fast", function () {
	                    $("#first").fadeIn("fast");
	                });
	            });	
			});
            
            // 로그인폼
            $(function() {
                $("form[name='login']").validate({
                    rules: {
                        id: {
                            required: true
                        },
                        pass: {
                            required: true
                        }
                    },
                    messages: {
                        id: "아이디를 입력하세요",
                        pass: {
                            required: "비밀번호를 입력하세요",
                        }
                    },
                    submitHandler: function (form) {
                        form.submit();
                    }
                });
            });
            
            // 회원가입폼
            $(function() {
                $("form[name='registration']").validate({
                    rules: {
                    	id: {
                    		required: true,
                    		rangelength:[4,30],
                			remote: { // 아이디 중복체크
                				url: "./CustomerIdCheck.cu",
                				type: "post",
                				data: {
                					id: function() {
                						return $("#id").val();
                					}
                				}
                			}
                    	},
                    	pass1: {
                            required: true,
                            rangelength:[6,30]
                        },
                        pass2: {
            				equalTo: '#pass1'
            			},
                        name: "required",
                        phone: {
                        	required: true,
                        	digits:true,
                        	remote: {
                				url: "./CustomerPhoneCheck.cu",
                				type: "post",
                				data: {
                					phone: function() {
                						return $("#phone").val();
                					}
                				}
                			}
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
                            rangelength: "아이디는 4-30자 사이 영문,숫자만 허용합니다",
                            remote: "중복된 아이디입니다"
                        },
                        
                        pass1: {
                            required: "비밀번호를 입력하세요",
                            rangelength: "비밀번호는 6-30자 사이 영문,숫자만 허용합니다"
                        },
                        pass2: {
                        	equalTo: "비밀번호를 재확인하세요"
                        },
                        name: "이름을 입력하세요",
                        phone: {
                        	required: "휴대폰 번호를 입력하세요",
                        	digits: "' - ' 제외 숫자만 입력하세요",
                        	remote: "중복된 휴대폰 번호입니다"
                        },
                        email: {
                        	required: "이메일을 입력하세요",
                        	email: "이메일 형식이 아닙니다"
                        },
                       	ch1: "필수",
                        ch2: "필수"
                    },
                    submitHandler: function (form) {
                        form.submit();
                    }
                });
            });
            
            // 카카오 회원가입
            window.Kakao.init('46649a5dcd99d3819c79c2f83892ddb9');
            
            function kakaoJoin() {
            	Kakao.Auth.login({
            		scope:'account_email',
                    success: function (response) {
                        Kakao.API.request({
                            url: '/v2/user/me',
                            success: (res) => {
            					alert('회원가입 성공! 메인페이지에서 로그인해주세요')
                                kakaojoin.id.value=res.id;
                            	kakaojoin.pass.value=res.kakao_account.email;
                            	kakaojoin.name.value=res.properties.nickname;
                            	kakaojoin.email.value=res.kakao_account.email;
            					kakaojoin.submit()
                            },
                            fail: function (error) {
                                console.log(error);
                            },
                        })
                    },
                    fail: function (error) {
                        console.log(error);
                    },
                })
            }
            
            // 카카오 로그인
            function kakaoLogin() {
            	Kakao.Auth.login({
            		scope:'account_email',
                    success: function (response) {
                        Kakao.API.request({
                            url: '/v2/user/me',
                            success: (res) => {
                                kakaologin.id.value=res.id;
                                kakaologin.pass.value=res.kakao_account.email;
            					kakaologin.submit()
                            },
                            fail: function (error) {
                                console.log(error);
                            },
                        })
                    },
                    fail: function (error) {
                        console.log(error);
                    },
                })
            }