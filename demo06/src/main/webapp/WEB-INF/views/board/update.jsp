<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container mt-5">
	<h3>${board.user.username}의 게시글 수정</h3>
	<form action="update" method="post">
		<div class="form-group">
			<input type="hidden" value="${board.num}" id="num"> <label
				for="title">제목:</label> <input type="text" class="form-control"
				id="title" placeholder="Enter subject" name="title"
				value="${board.title}">
		</div>
		<div class="form-group">
			<label for="writer">글쓴이:</label> <input type="text"
				class="form-control" id="writer" placeholder="Enter writer"
				name="writer" value="${board.user.username}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" rows="5" id="content" name="content">${board.content}</textarea>
		</div>

		<button type="button" class="btn btn-primary" id="btnModify">수정하기</button>

	</form>
</div>

<script>
$("#btnModify").click(function(){
	data={
		"num":$("#num").val(),
		"title":$("#title").val(),
		"content":$("#content").val()
	}
	$.ajax({
		type:"PUT",
		url:"/board/update",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		success:function(resp){
			alert("수정되었습니다.")
			location.href="/board/list";
		},
		error:function(e){
			alert("수정실패 : "+e)
		}
	}) //ajax
}) //btnModify
</script>
</body>
</html>