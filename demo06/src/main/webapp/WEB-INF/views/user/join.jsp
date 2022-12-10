<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container mt-5">
	<h3>JOIN</h3>
		<div class="form-group">
			<label for="username">아이디:</label> <input type="text"
				class="form-control" id="username" placeholder="Enter username"
				name="username">
		</div>
		
		<div class="form-group">
			<label for="password">비밀번호:</label> <input type="password"
				class="form-control" id="password" placeholder="Enter Password"
				name="password">
		</div>
		<div class="form-group">
			<label for="pass_check">비밀번호 확인:</label> <input type="password"
				class="form-control" id="pass_check"
				placeholder="Enter Password_Check" name="pass_check">
		</div>

		<div class="form-group">
			<label for="email">이메일:</label> <input type="text"
				class="form-control" id="email" placeholder="Enter email"
				name="email">
		</div>

		<button class="btn btn-primary" id="btnJoin">회원가입</button>
	
</div>
<script>
$("#btnJoin").click(function(){
	
	if($("#id").val()==""){
		alert("아이디를 입력하세요.");
		$("#id").focus();
		return false;
	}
	
	if($("#password").val()==""){
		alert("비밀번호를 입력하세요.");
		$("#pwd").focus();
		return false;
	}
	
	if($("#password").val() != $("#pass_check").val()){
		alert("비밀번호가 일치하지 않습니다.");
		$("#pass_check").focus();
		return false;
	}
	
	if($("#username").val()==""){
		alert("이름을 입력하세요.");
		$("#username").focus();
		return false;
	}
	
	if($("#email").val()==""){
		alert("이메일을 입력하세요.");
		$("#email").focus();
		return false;
	}
	
	var data={
			"username" : $("#username").val(),
			"password" : $("#password").val(),
			"email" : $("#email").val()
	}
	
	$.ajax({
		type : 'post',
		url : "/register",
		contentType : "application/json;charset=utf-8",
		data : JSON.stringify(data)
	})
	.done(function(resp){
		if(resp=="success"){
		alert("회원가입을 축하합니다.")
		location.href="/login"
		}else if(resp=="fail"){
			alert("사용중인 아이디입니다.")
			$("#id").val("")
		}
	})
	.fail(function(e){
		alert("회원가입 실패 : "+e)
	})
}) //btnJoin
</script>