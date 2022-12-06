<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
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
			<c:forEach items="${boards}" var="board">
				<tr>
					<td>${board.num}</td>
					<td><a href="view/${board.num}">${board.title}[${board.replyCnt}]</a></td>
					<td>${board.writer}</td>
					<td>${board.hitcount}</td>
					<td>${board.regdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
