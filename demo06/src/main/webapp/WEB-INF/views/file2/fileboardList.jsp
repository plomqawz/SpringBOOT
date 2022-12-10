<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container mt-5">
	<h3>FileBoard List(${count})</h3>
	<br />
	<div class="row">
		<c:forEach items="${fboards}" var="fboard">
			<div class="col-4">
				<img class="card-img-top" src="/images/${fboard.fileimage}"
					style="width: 300px; height: 200px">
				<div class="card-body">
					<h4 class="card-title">${fboard.title}</h4>
					<p class="card-text">${fboard.writer}</p>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>