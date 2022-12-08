<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container mt-5">
	<form action="login" method="post">
		<div class="form-group">
			<label for="id">아이디:</label> <input type="text" class="form-control"
				id="userid" placeholder="Enter id" name="username">
		</div>
		<div class="form-group">
			<label for="pass">비밀번호:</label> <input type="password"
				class="form-control" id="pass" placeholder="Enter Password"
				name="password">
		</div>
		<button class="btn btn-primary" id="btnLogin">로그인</button>
	</form>
</div>