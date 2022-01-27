<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>필요한 정보들을 입력하세요</h1>
<form method ="post" action="${pageContext.request.contextPath}/user/join">
id 입력<input type="text" name="id"><br>
password 입력<input type="password" name="password"><br>
이름 <input type="text" name ="name"><br>
전화번호 <input type="tel" name="phone"><br>
이메일 <input type="email" name="email"><br>
<input type="submit" value="회원가입">
<input type="reset" value="재작성">
</form>
</body>
</html>