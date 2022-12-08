<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
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