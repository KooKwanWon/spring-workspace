<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous" />


<style>
h1 {
	margin-top: 70px;
}

.form-group {
	margin: 20px 0;
}
</style>
</head>
<body>
	<div class="container">
		<h1>게시물 정보</h1>

		<form>
			<div class="form-group">
				<label for="title">Title</label> <input type="text" name="title"
					id="title" class="form-control" readonly value="${vo.title}" />
			</div>

			<div class="form-group">
				<label for="content">Content</label>
				<textarea name="content" id="content" cols="30" rows="10"
					class="form-control" style="resize: none" readonly>${vo.content}
          </textarea>
				<img src="${vo.url}" />
			</div>

			<div class="form-group">
				<label for="writer">Writer</label> <input type="text" id="writer"
					name="writer" class="form-control" value="${vo.writer}" readonly />
			</div>
	
			<sec:authentication property="principal" var="info" />
			<sec:authorize access="hasRole('ROLE_MEMBER')">
				<a class="btn btn-outline-warning" href="/board/update?no=${vo.no}">수정</a>
				<a class="btn btn-outline-danger" href="/board/delete?no=${vo.no}">삭제</a>
			</sec:authorize>
		</form>
	</div>
</body>
</html>
