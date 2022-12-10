<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container p-5">
	<h3>게시판(${count})</h3>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boards.content}" var="b">
				<tr>
					<td>${b.num}</td>
					<td><a href="/board/view/${b.num}">${b.title}[${b.replycnt}]</a></td>
					<td>${b.user.username}</td>
					<td>${b.hitcount}</td>
					<td>${b.regdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="d-flex justify-content-between mt-5 mr-auto">
		<ul class="pagination">
			<c:if test="${boards.first==false}">
				<il class="page-item"> <a class="page-link"
					href="?page=${boards.number-1}&field=${param.field}&word=${param.word}">이전</a></il>
			</c:if>
			<c:if test="${boards.last==false}">
				<il class="page-item"> <a class="page-link"
					href="?page=${boards.number+1}&field=${param.field}&word=${param.word}">다음</a></il>
			</c:if>
		</ul>

		<div>
			<form class="form-inline" action="/board/list" method="get">
				<select name="field" class="form-control mr-sm-1">
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select> <input type="text" name="word" class="form-control"
					placeholder="Search">
				<button class="btn btn-secondary">Search</button>
			</form>
		</div>
	</div>



</div>