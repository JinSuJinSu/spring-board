<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인을 하세요</h1>

 <form method="post" action="${pageContext.servletContext.contextPath}/user/auth">
아이디 : <input name="id" type="text"><br>
비밀번호 : <input name="password" type="password"><br>
<input type="submit" value="로그인">
</form>
<input type="button" value="회원가입" onclick="location.href='${pageContext.servletContext.contextPath}/user/join'">

</body>
</html>