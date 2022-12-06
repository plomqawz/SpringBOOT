<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
</head>
<body>
	<sec:authorize access="isAnonymous()"> <!-- 인증 되었는지 안되었는지 물어봄. -->
		<a href="/login">로그인</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<a href="/logout">로그아웃</a><br/>
		principal : <sec:authentication property="principal"/><br/> <!-- 권한을 물어봄. -->
		사용자 ID : <sec:authentication property="principal.username"/><br/>
		사용자 이름 : <sec:authentication property="principal.member.username"/><br/>
		권한 : <sec:authentication property="principal.member.authList"/><br/>
		Member : <sec:authentication property="principal.member"/><br/>
	</sec:authorize>

</body>
</html>