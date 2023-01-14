<%@page import="com.itwillbs.customer.db.CustomerDAO"%>
<%@page import="com.itwillbs.customer.db.CustomerDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/deletePro.jsp</title>
</head>
<body>
<%
//폼에서 입력한 파라미터값(id,pass)을 서버에 request에 저장
//변수 = request에 저장된 값 가져오기
String id=request.getParameter("id");
String pass=request.getParameter("pass");
//MemberDAO 객체생성
CustomerDAO dao = new CustomerDAO();

// MemberDTO dto =디비작업주소.userCheck(id,pass) 메서드 호출
CustomerDTO dto=dao.userCheck(id,pass);

//dto null이 아니면  =>"아이디 비밀번호 일치" =>삭제,메인이동
//    null이면      => "아이디 비밀번호 틀림" => 뒤로이동
if(dto!=null){
//다음행이동=> 데이터 있으면 => true =>"아이디 비밀번호 일치"
// 리턴할형없음 void deleteMember(String id) 메서드 정의
// deleteMember(id) 메서드 호출
dao.deleteCustomer(id);
// // 	3단계 sql delete, 4단계 실행, 세션초기화, main.jsp 이동
// sql="delete from members where id=?";
// pstmt=con.prepareStatement(sql);
// pstmt.setString(1, id);
// // 4단계 실행
// pstmt.executeUpdate();

// 세션초기화
session.invalidate();
// main.jsp 이동
response.sendRedirect("main.jsp");

}else{
//데이터 없으면 => false => "아이디 비밀번호 틀림"	
// 	"입력하신 정보 틀림", 뒤로이동
%>
	<script type="text/javascript">
		alert("입력하신 정보 틀림");
		history.back();
	</script>
	<%
}
%>
</body>
</html>