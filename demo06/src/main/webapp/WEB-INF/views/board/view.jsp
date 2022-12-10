<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container mt-5">
	<input type="hidden" id="num" value="${board.num}">
	<h3>${board.user.username}의글보기</h3>
	<div class="form-group mt-5">
		<label for="num">글번호:</label> <input type="text" class="form-control"
			id="num" name="num" value="${board.num}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="title">제목:</label> <input type="text" class="form-control"
			id="title" name="title" value="${board.title}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="pwd">글쓴이:</label> <input type="text" class="form-control"
			id="writer" name="writer" value="${board.user.username}"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="content" name="content"
			readonly="readonly">${board.content}</textarea>
	</div>
	<sec:authorize access="isAuthenticated()">
		<c:if test="${principal.username == board.user.username}">
			<div align="right">
				<button type="button" class="btn btn-primary" id="btnUpdate">수정</button>
				<button type="button" class="btn btn-secondary" id="btnDelete">삭제</button>
			</div>
		</c:if>
	</sec:authorize>
	<br /> <br />
	<div align="center">
		<textarea rows="3" cols="50" id="msg"></textarea>
		<button type="button" class="btn btn-secondary btn-sm" id="btnComment">댓글쓰기</button>
	</div>
	<hr />
	<div id="replyResult"></div>
</div>

<script>
//댓글뿌리기(전체보기) 함수
var init=function(){
	$.ajax({
		type:'get',
		url:"/reply/list/"+$("#num").val()
	})
	.done(function(resp){
		var str="<table class='table table-hover'><b>댓글()</b>"
		  $.each(resp,function(key,val){
			  str+="<tr>"
			  str+="<td>"+val.user.username+"<td>"
			  str+="<td>"+val.content+"<td>"
			  str+="<td>"+val.regdate+"<td>"
			  str+="<td><a href='javascript:fdel("+val.cnum+")'>삭제</a><td>"
			  str+="</tr>"
		  })//each
		  str+="</table>"
		  $("#replyResult").html(str);
	})//done
}//init

//댓글삭제
function fdel(cnum){
	$.ajax({
		type:"DELETE",
		url:'/reply/delete/'+cnum
	})
	.done(function(resp){
		alert(resp + "번 댓글 삭제완료")
		init()
	})
	.fail(function(e){
		alert("댓글삭제 실패 : "+e)
	})
}//fdel


//댓글추가
$("#btnComment").click(function(){
	if(${empty principal.user}){
		alert("로그인 하세요.")
		location.href="/login";
		return;
	}
	if($("#msg").val()==""){
		alert("댓글을 입력하세요.")
		return;
	}
	data = {
			"bnum" : $("#num").val(),
			"content" : $("#msg").val()
		}
	$.ajax({
		type : 'post',
		url : '/reply/insert/'+$("#num").val(),
		contentType : 'application/json;charset=utf-8',
		data : JSON.stringify(data)
	})
	.done(function(resp,status) {
		alert(status)
		alert("댓글 추가 성공")
		init()
	}).fail(function(e) {
		alert("댓글 추가 실패 : " + e)
	})
	$("#msg").val("")
})

////////////////////////////////////////////////////
//게시글 수정폼 이동
$("#btnUpdate").click(function(){
	if(!confirm("글을 수정하시겠습니까?")) return false;
	location.href="/board/update/${board.num}"
}) //btnUpdate

//게시글 삭제
$("#btnDelete").click(function(){
	if(!confirm("정말 삭제하시겠습니까?")) return false;
	$.ajax({
		type:"DELETE",
		url:'/board/delete/${board.num}',
		success:function(resp){
			if(resp=="success"){
				alert("삭제되었습니다.")
				location.href="/board/list"
			}
		},
		error:function(e){
			alert("삭제 실패 : "+e)
		}
	}) //ajax
}) //btnDelete

init()
</script>
