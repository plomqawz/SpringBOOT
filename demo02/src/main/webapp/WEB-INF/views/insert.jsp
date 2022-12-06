<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<div class="container mt-5">
	<form action="insert" method="post">
		<div class="form-group">
			<label for="title">제목:</label> <input type="text"
				class="form-control" id="title" placeholder="Enter subject"
				name="title">
		</div>
		<div class="form-group">
			<label for="writer">글쓴이:</label> <input type="text"
				class="form-control" id="writer" placeholder="Enter writer"
				name="writer">
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" rows="5" id="content" name="content"></textarea>
		</div>

		<button type="submit" class="btn btn-primary">글쓰기</button>

	</form>
</div>