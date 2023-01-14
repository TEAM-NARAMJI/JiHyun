function check() {
   
   let table = document.fr.table;
   if(table.value == ""){
	  alert("테이블 선택해 주세요.");
	  return false;
   }

   let name = document.fr.name;
   if(name.value == ""){
	  alert("이름을 입력해 주세요.");
      name.focus();
      return false;
   }
   
   let phone = document.fr.phone;
   if(phone.value == ""){
      alert("전화번호를 입력해 주세요.");
      phone.focus();
      return false;
   }
   
   regex = /^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/;
   if(!regex.test(phone.value)){
      alert("숫자로만 12또는 13자리를 입력해 주세요.");
      phone.focus();
      return false;
   }
   
   if(document.fr.ch.checked==false) {
      alert("이용약관 및 개인정보 처리방침에 동의하셔야 가입이 가능합니다.");
      return false;
   }
   document.fr.submit();
   
}

window.onload = function() {
	let dateElement = document.getElementById('res_use_date');
	let mindate = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().slice(0, 10);
	dateElement.value = mindate;
	dateElement.setAttribute("min", mindate);


	let dateElement2 = document.getElementById('res_use_date');
	let maxdate = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000*39).toISOString().slice(0, 10);
// 	dateElement2.value = maxdate;
	dateElement2.setAttribute("max", maxdate);
	
	// 테이블 null인거 싫어서 default 설정
	$("#table option:eq(0)").prop("selected", true); //첫번째 option 선택
};

$(document).ready(function() {
	// 예약시간 예약테이블 조회
	$("#res_use_date").on("change", selectTimeTable);	// 달력
	$("#time").on("change", selectTimeTable);			// 시간
	$("#table").on("change", selectTimeTable);			// 테이블
	
	function selectTimeTable(event){
		var res_use_date = $('#res_use_date').val();
		var time = $('#time').val();
		var table = $('#table').val();
		
		$.ajax({
			url:'./selectTimeTable.re',
			data:{ 'res_use_date' : res_use_date
				 , 'time' : time
				 , 'table' : table},
			success: function(rdata){
				// 예약 가능 여부
				var ResAvail = rdata == 0 ? true : false;
				if(!ResAvail){
					alert("예약불가");
				}
			}
		});
	}
})