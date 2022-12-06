<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
	<div id="memberDiv">
		<c:forEach items="${lists}" var="member">
아이디 : ${member.id}<br />
이름 : ${member.name}<br />
주소 : ${member.addr}<br />
이메일 : ${member.email}<br />
			<a href="detail/${member.id}">상세보기</a>
			<button type="button" onclick="funDelete(${member.id})">삭제</button>
			<button type="button" class="btnDel" data-mid="${member.id}">삭제class</button>
			<br />
			<br />
		</c:forEach>
	</div>
	<script>
function funDelete(id){
	//alert(id)
	$.ajax({
		type : 'DELETE',
		url : '/delete/'+id
	})
	.done(function(resp){
		alert(resp+"번 삭제 완료")
		location.href="/list"
	})
	.fail(function(e){
		alert("삭제 실패")
	})
} // funDelete

// $(".btnDel").click(function(){ // 잘못된 방법. member.id 어떤건지 확정못함.
// 	//alert("dd")
// 		$.ajax({
// 		type : 'DELETE',
// 		url : '/delete/${member.id}'
// 	})
// 	.done(function(resp){
// 		alert(resp+"번 삭제 완료")
// 		location.href="/list"
// 	})
// 	.fail(function(e){
// 		alert("삭제 실패")
// 	})
// })

// $("#memberDiv").on("click",".btnDel", function(){
// 	//alert($(this).attr('data-mid'))
// 	//alert($(this).data('mid'))
// 	$.ajax({
// 		type : 'DELETE',
// 		url : '/delete/'+$(this).data("mid")
// 	})
// 	.done(function(resp){
// 		alert(resp+"번 삭제 완료")
// 		location.href="/list"
// 	})
// 	.fail(function(e){
// 		alert("삭제 실패")
// 	})
// })

var delfun=function(){
	$.ajax({
		type : 'DELETE',
		url : '/delete/'+id
	})
	.done(function(resp){
		alert(resp+"번 삭제 완료")
		location.href="/list"
	})
	.fail(function(e){
		alert("삭제 실패")
	})
}

$("#memberDiv").on("click",".btnDel",delfun);

</script>
</body>
</html>