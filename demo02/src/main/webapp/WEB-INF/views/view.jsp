<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<div class="container mt-5">
	<h3>${board.writer}의글</h3>
	<div class="form-group">
		<label for="num">글번호:</label> <input type="text" class="form-control"
			id="num" name="num" value="${board.num }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="title">제목:</label> <input type="text" class="form-control"
			id="title" name="title" value="${board.title }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="pwd">글쓴이:</label> <input type="text" class="form-control"
			id="writer" name="writer" value="${board.writer }"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="content" name="content"
			readonly="readonly">${board.content }</textarea>
	</div>

	<div align="right">
		<button type="button" class="btn btn-primary" id="btnUpdate">수정</button>
		<button type="button" class="btn btn-secondary" id="btnDelete">삭제</button>
	</div>
	<br /> <br />
	<div align="center">
		<textarea rows="4" cols="50" id="msg"></textarea>
		<button type="button" class="btn btn-primary" id="btnComment">댓글쓰기</button>
	</div>
	<hr />
	<div id="replyResult"></div>
</div>

<script>
	var init = function() { // 댓글 전체보기 함수
		$.ajax({
			type : 'get',
			url : "/reply/commentList/${board.num}"
		})
		.done(function(resp){
			var str="<table class='table table-hover mt-3'>";
			str+="<tr>댓글("+ ")</tr>";
			$.each(resp, function(key,val){
				str+="<tr>"
				str+="<td>"+val.userid+"</td>"
				str+="<td>"+val.content+"</td>"
				str+="<td>"+val.regdate+"</td>"
				str+="<td><a href='javascript:fdel("+val.cnum+")'>삭제</a></td></tr>"
			}) //each
			$("#replyResult").html(str)
		}) //done
		.fail(function(e){
			alert("댓글 불러오기 실패"+e);
		}) //fail
	} //init

	$("#btnComment").click(function() { // 댓글 추가
		if ($("#msg").val() == "") {
			alert("댓글을 입력하세요.")
			return false;
		}
		data = {
			"bnum" : $("#num").val(),
			"content" : $("#msg").val(),
			"userid" : "bootANOU"
		}
		$.ajax({
			type : 'post',
			url : '/reply/commentInsert',
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(data)
		}) // ajax
		.done(function(resp) {
			alert("댓글 추가 성공")
			init();
		}).fail(function(e) {
			alert("댓글 추가 실패 : " + e)
		})
		$("#msg").val("")
	}) // btnComment
	
	function fdel(cnum){ // 댓글 삭제
		$.ajax({
			type:'delete',
			url:"/reply/commentDelete/"+cnum
		})
		.done(function(resp){
			alert(resp+"번 댓글 삭제 완료")
			init()
		})
		.fail(function(e){
			alert("댓글 삭제 실패 : "+e)
		})
	} //fdel

	////////////////////////////////////////////////////////////////

	$("#btnUpdate").click(function() { // 수정 버튼
		if (!confirm("글을 수정하시겠습니까?"))
			return false;
		location.href = "/update/${board.num}"
	})

	$("#btnDelete").click(function() { // 삭제 버튼
		if (confirm("글을 삭제하시겠습니까?") == false)
			return false;
		$.ajax({
			type : 'delete',
			url : "/delete/${board.num}",
			success : function(resp) {
				alert(resp + '번 글 삭제성공')
				location.href = "/list"
			}
		}) //ajax
	}) //btnDelete
	
	init()
</script>
