<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/resources/style/login.css">
</head>
<body>

<hr/>

<form action="/join" method="POST" class="loginForm">
		<h2>회원가입</h2>
		<div class="idForm">
			<input type="text" placeholder="Username" name="username" class="id"/>
		</div>
		<div class="passForm">
			<input type="password" placeholder="Password" name="password" class="pw"/>
		</div>
		<div class="passForm">
			<input type="email" placeholder="Email" name="email" class="pw"/>
		</div>

		<button class="btn">회원가입</button>

		<div class="bottomText">
			이미 회원가입이 되셨나요? <a href="joinForm">Login</a>
		</div>
	</form>

<!-- <form action="/join" method="POST">
	<input type="text" placeholder="Username" name="username" /><br/>
	<input type="password" placeholder="Password" name="password" /><br/>
	<input type="email" placeholder="Email" name="email" /><br/>
	<button>회원가입</button>
</form>

이미 회원가입이 되셨나요? <a href="/loginForm">로그인</a>
 -->

</body>
</html>