<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>login</h1>
	<form action="" method="post">
		<div>
			<label for="id">id</label> 
			<input type="text" name="id" id="id" />
		</div>
		<div>
			<label for="password">password</label> 
			<input type="password" name="password" password="password" />
		</div>
		<div>
			<button type="submit">로그인</button>
		</div>
	</form>
	<h3>${registerDTO.id }${registerDTO.password }${registerDTO.name}${registerDTO.email }</h3>
</body>
</html>