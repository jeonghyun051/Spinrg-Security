<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div class="m-2">
		<form class="form-inline d-flex justify-content-end" action="/post/search">
		 <!-- <input type="hidden" name="cmd" value="search" />
			<input type="hidden" name="page" value="0" />  --> 
	
			<input type="text" name="title" class="form-control mr-sm-2" placeholder="Search">			
			<button class="btn btn-primary m-1">검색</button>
		</form>
	</div>

	<c:forEach var="post" items="${posts.content}">
		<div class="card">
			<div class="card-body">
				<div class="d-flex justify-content-between">
					<h4 class="card-title">${post.title}</h4>
					<div>작성자:${post.user.username}</div>
				</div>
				<a href="/post/${post.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
		<br>
	</c:forEach>


	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${posts.first}">
				<li class="page-item disabled"><a class="page-link">Previous</a></li>

			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="search?title=${title}&page=${posts.number-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${posts.last}">
				<li class="page-item disabled"><a class="page-link">Next</a></li>

			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="search?title=${title}&page=${posts.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>

	</ul>
</div>

<%@ include file="../layout/footer.jsp"%>