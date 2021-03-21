<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/resources/style/login.css">
<title>블로그</title>
</head>
<body>

	<form action="/login" method="POST" class="loginForm">
		<h2>Login</h2>
		<div class="idForm">
			<input type="text" placeholder="Username" name="username" class="id"/>
		</div>
		<div class="passForm">
			<input type="password" placeholder="Password" name="password" class="pw"/>
		</div>

		<button class="btn">LOG IN</button>

		<div class="bottomText">
			Don't you have ID? <a href="#">sign up</a>
		</div>
		<div style="margin: 8px">
			<a href="/oauth2/authorization/naver"><img src="http://localhost:8080/resources/images/login_naver.jpg"></a>
		</div>
		<div style="margin: 8px">
			<a href="/oauth2/authorization/google"><img src="http://localhost:8080/resources/images/login_google.jpg"></a>
		</div>
		<div style="margin: 8px">
			<a href="/oauth2/authorization/facebook"><img src="http://localhost:8080/resources/images/login_facebook.jpg"></a>
		</div>
		<div style="margin: 8px">
			<a href="/oauth2/authorization/kakao"><img src="http://localhost:8080/resources/images/login_kakao.jpg"></a>
		</div>
	</form>
	
	
	<!-- 	아직 회원가입이 안되셨나요?
	<a href="/joinForm">회원가입</a>
	<br />
<form action="/login" method="POST">
		<input type="text" placeholder="Username" name="username" /> 
		<input type="password" placeholder="Password" name="password" />
		<button>로그인</button>
	</form>

	<a href="/oauth2/authorization/google">구글 로그인</a>
	<a href="/oauth2/authorization/facebook">페이스북 로그인</a>
	<a href="/oauth2/authorization/naver">네이버 로그인</a>
	<a href="/oauth2/authorization/kakao">카카오 로그인</a>

	<a href="12"><img src="http://localhost:8080/resources/images/123.jpg"></a> -->

</body>
</html>