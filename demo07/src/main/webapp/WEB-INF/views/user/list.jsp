<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container p-5">
	<h3>회원목록(${count})</h3>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>회원번호</th>
				<th>ID</th>
				<th>Email</th>
				<th>권한</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users.content}" var="u">
				<tr>
					<td>${u.id}</td>
					<td><a href="/user/view/${u.id}">${u.username}</a></td>
					<td>${u.email}</td>
					<td>${u.role}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="d-flex justify-content-between mt-5 mr-auto">
		<ul class="pagination">
			<c:if test="${users.first==false}">
				<il class="page-item"> <a class="page-link"
					href="?page=${users.number-1}&field=${param.field}&word=${param.word}">이전</a></il>
			</c:if>
			<c:if test="${users.last==false}">
				<il class="page-item"> <a class="page-link"
					href="?page=${users.number+1}&field=${param.field}&word=${param.word}">다음</a></il>
			</c:if>
		</ul>

		<div>
			<form class="form-inline" action="/list" method="get">
				<select name="field" class="form-control mr-sm-1">
					<option value="username">아이디</option>
					<option value="email">이메일</option>
				</select> <input type="text" name="word" class="form-control"
					placeholder="Search">
				<button class="btn btn-secondary">Search</button>
			</form>
		</div>
	</div>

</div>