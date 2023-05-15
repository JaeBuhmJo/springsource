<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>BOARD READ</h1>
	<form action="" method="post">
		<div>
			<label for="name">name</label> 
			<input type="text" name="name" password="name" value="${boardDTO.name }"/>
		</div>
		<div>
			<label for="password">password</label> 
			<input type="password" name="password" password="password" value="${boardDTO.password }"/>
		</div>
		<div>
			<label for="title">title</label> 
			<input type="text" name="title" password="title" value="${boardDTO.title }"/>
		</div>
		<div>
			<label for="id">content</label> 
			<textarea rows="5" cols="30">${boardDTO.content }</textarea>
		</div>
		<div>
			<button type="submit">등록</button>
		</div>
	</form>
</body>
</html>