
var checkIdResult = false;
var checkPassResult = false;
var checkPass2Result = false;
var checkPhoneResult = false;

var dupPhoneResult = false;

// 아이디 검사
function checkId() {
	
	var id = document.getElementById('id').value;
	var span = document.getElementById('checkIdResult');
	
	//아이디 (영문 또는 숫자 또는 영문+숫자, 4 ~ 8자리)
	var regex = /^[A-Za-z0-9]{4,8}$/;
	if(regex.test(id)){
		span.innerHTML = '사용 가능한 아이디입니다.';
		span.style.color = 'GREEN';
		
		checkIdResult = true;
	} else {
		span.innerHTML = '4 ~ 8 자리 영문자 또는 숫자만 입력 가능합니다!';
		span.style.color = 'RED';
		
		checkIdResult = false;
	}
}
// 아이디 필수
function checkId2(){
	
	var span = document.getElementById('checkIdResult');
	// null 체크
	if(id == ""){
		span.innerHTML = 'id를 입력해 주세요.';
		span.style.color = 'RED';
	}
}


// 비밀번호 검사
function checkPass() {
	var pass = document.getElementById('pass').value;
	var span = document.getElementById('checkPassResult');
	
	//비밀번호 (영문+숫자, 6 ~ 12자)
	var regex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,12}$/;
	if(!regex.test(pass)){
		checkPassResult = false;
		
		span.innerHTML = '6 ~ 12 자리 영문자, 숫자는 필수입니다!';
		span.style.color = 'RED';
	} else {
		checkPassResult = true;
		
		span.innerHTML = '사용 가능한 비밀번호입니다.';
		span.style.color = 'GREEN';
	}
}

// 비밀번호 필수
function checkPass2(){
	var pass = document.getElementById('pass').value;
	var span = document.getElementById('checkPassResult');
	// null 체크
	if(pass == ""){
		span.innerHTML = '비밀번호를 입력해 주세요.';
		span.style.color = 'RED';
	}
}

// 비밀번호 확인
function checkRetypePass() {
	
	var span = document.getElementById('checkRetypePassResult');
	
	var pass = document.getElementById('pass').value;
	var pass2 = document.getElementById('pass2').value;
	
	if(pass == pass2) {
		span.innerHTML = '비밀번호 일치';
		span.style.color = 'GREEN';
		checkPass2Result = true;
	} else {
		span.innerHTML = '비밀번호 불일치';
		span.style.color = 'RED';
		checkPass2Result = false;
	}
}
// 비번 확인 필수
function checkRetypePass2(){
	var pass2 = document.getElementById('pass2').value;
	var span = document.getElementById('checkRetypePassResult');
	// null 체크
	if(pass2 == ""){
		span.innerHTML = '비밀번호 확인을 입력해 주세요.';
		span.style.color = 'RED';
	}
}
	
	
// 이름 필수
function checkName(){
	var name = document.getElementById('name').value;
	var span = document.getElementById('checkName');
	// null 체크
	if(name == ""){
		span.innerHTML = '이름을 입력해 주세요.';
		span.style.color = 'RED';
	} else {
		span.innerHTML = '멋진 이름이네요!';
		span.style.color = 'GREEN';
	}
}


// 전화번호 검사
function checkPhone(phone) {
	var phone = document.getElementById('phone').value;
	var span = document.getElementById('checkPhone');
	//전화번호 (숫자만)
	var regex = /^[0-9]+$/;
	if(!regex.test(phone)){
		span.innerHTML = '숫자만 입력가능합니다.';
		span.style.color = 'RED';
	
		checkPhoneResult = false;
	} else {
		span.innerHTML = '';
		
		checkPhoneResult = true;
	}
}

//전화번호 필수
function checkPhone2(){
	var phone = document.getElementById('phone').value;
	var span = document.getElementById('checkPhone');
	// null 체크
	if(phone == ""){
		span.innerHTML = '휴대폰 번호를 입력해 주세요.';
		span.style.color = 'RED';
	}
}

//전화번호 중복 검사
function chkPh() {
	dupPhoneResult = true;
}

$(document).ready(function() {

	$('#btnPh').click(function(){
		$.ajax({
			url:'./CustomerPhoneCheck.cu',
			data: {'phone': $('#phone').val()},
			success: function(rdata) {
				if(rdata == "true") {
					$('.dupdiv').html("사용 가능합니다.").css('color', 'green');
				} else {
					$('.dupdiv').html("사용중인 번호입니다.").css('color', 'red');
				}
			}
		});
	
	});

});

// '-' 금지
function replacePhone() {
	var phone = document.getElementById('phone').value;
	var phoneElem = document.getElementById('phone');
	phoneElem.value = phone.replaceAll('-', '');
}

// 다시작성
function reset() {

	checkIdResult = false;
	checkPassResult = false;
	checkPass2Result = false;
	checkPhoneResult = false;
	dupPhoneResult = false;
	
	var span = document.getElementById('checkIdResult');
	span.innerHTML = '';
	var span = document.getElementById('checkPassResult');
	span.innerHTML = '';
	var span = document.getElementById('checkRetypePassResult');
	span.innerHTML = '';
	var span = document.getElementById('checkPhone');
	span.innerHTML = '';
}


// submit 제어
function valid() {
	if(!(checkIdResult && checkPassResult && checkPass2Result && checkPhoneResult)) {
		alert("다시 입력해 주세요.");
		return false;
	}
	
	if(!dupPhoneResult){
		alert("휴대폰 번호 중복확인이 필요합니다.")
		return false;
	}
}

window.onload = function(){
	
	btnEle = document.getElementById('reset');
	btnEle.addEventListener('click', reset);

	txtId = document.getElementById('id');
	txtId.addEventListener('keyup', checkId);
	txtId.addEventListener('blur', checkId2);
	
	txtPass = document.getElementById("pass");
	txtPass.addEventListener('keyup', checkPass);
	txtPass.addEventListener('blur', checkPass2);
	
	txtPass2 = document.getElementById("pass2");
	txtPass2.addEventListener("keyup", checkRetypePass);
	txtPass2.addEventListener("blur", checkRetypePass2);
	
	txtName = document.getElementById("name");
	txtName.addEventListener("blur", checkName);
	
	txtPhone = document.getElementById("phone");
	txtPhone.addEventListener("keyup", checkPhone);
	txtPhone.addEventListener("blur", checkPhone2);
	txtPhone.addEventListener("keyup", checkPhone2);
	txtPhone.addEventListener("keyup", replacePhone);
	txtPhone.addEventListener("keydown", replacePhone);
	
	btnPh = document.getElementById("btnPh");
	btnPh.addEventListener("click", chkPh);
}

	