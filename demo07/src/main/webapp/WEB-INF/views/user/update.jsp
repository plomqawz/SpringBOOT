<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container mt-5 mb-5">
	<h3>회원정보수정</h3>
	<br />
	<div class="form-group">
		<input type="hidden" value="${member.id}" id="id"> <label
			for="username">아이디:</label> <input type="text" class="form-control"
			id="username" placeholder="Enter username" name="username"
			value="${member.username}">
	</div>

	<div class="form-group">
		<label for="password">새 비밀번호:</label> <input type="password"
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
			name="email" value="${member.email}">
	</div>

	<div align="right">
		<button class="btn btn-primary" id="btnUpdate">수정하기</button>
		<button class="btn btn-danger" id="btnDelete">탈퇴하기</button>
	</div>
</div>
</body>
<script>
	// 수정버튼
	$("#btnUpdate").click(function() {
		if ($("#password").val() == "") {
			alert("새 비밀번호를 입력하세요.");
			$("#pwd").focus();
			return false;
		}

		if ($("#password").val() != $("#pass_check").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#pass_check").focus();
			return false;
		}

		var data = {
			"id" : $("#id").val(),
			"username" : $("#username").val(),
			"password" : $("#password").val(),
			"email" : $("#email").val()
		}

		$.ajax({
			type : 'PUT',
			url : "/userupdate",
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(data)
		}).done(function(resp) {
			if (resp == "success") {
				alert("회원정보가 수정되었습니다.")
				location.href = "/login"
			} else if (resp == "fail") {
				alert("사용중인 아이디입니다.")
				$("#username").val("")
			}
		}).fail(function(e) {
			alert("수정실패 : " + e)
		})
	}) //btnUpdate
	
	//탈퇴버튼
	$("#btnDelete").click(function() {
		if (!confirm("정말 탈퇴하시겠습니까?")) {
			return false;
		}
		$.ajax({
			type : "DELETE",
			url : "/userdelete/"+${member.id},
			success : function(resp) {
				if (resp == "success") {
					alert("탈퇴되었습니다.");
					location.href = "/list"
				}
			},
			error : function(e) {
				alert("탈퇴실패 : " + e)
			}
		})
	})//btnDelete
</script>
</html>