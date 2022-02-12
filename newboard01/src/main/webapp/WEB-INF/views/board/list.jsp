<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function dislpayDiv(number){ // 새로운 글을 작성하는 함수
	let value = (document.getElementById("userinfo")) //사용자에 대한 정보를 받아온다.
	// 회원일 경우 글 작성 가능, write라는 id를 가진 태그를 보여주고 add라는 id를 가진 태그를 숨긴다.
	document.getElementById("write").style.display='block';
	document.getElementById("add").style.display='none';	
		}
		
function reject(){ // 글 작성 취소
	document.getElementById("write").style.display='none';
	document.getElementById("add").style.display='block';	
}

</script>

<c:choose>
	<c:when test="${empty authUser}">
		<h3 id="userinfo">사용자 : 비회원</h3>
		<a href="${pageContext.request.contextPath}/user/login">로그인</a><br>
		<a href="${pageContext.request.contextPath}/user/join">회원가입</a>
	</c:when>
	<c:otherwise>
		<h3 id="userinfo">사용자 : ${authUser.id}</h3>
		<a href="${pageContext.request.contextPath}/user/update">회원정보수정</a><br>
		<a href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
	</c:otherwise>
</c:choose>		

<table border="1">
<tr><th>글번호</th><th>작성자</th><th>글제목</th><th>조회수</th><th>댓글수</th><th>작성 날짜</th></tr>  
		<c:forEach items="${map.list}" var="vo" varStatus="status">
		 <tr><td>${vo.boardNo}</td>
		 <td>${vo.id}</td>
		 <td>
		 <c:choose>
		 	<c:when test="${not empty authUser}">
		 		<a href="${pageContext.servletContext.contextPath}/board/view/${vo.boardNo}?page=${map.currentPage}&kwd=${map.kwd}&value=${map.value}" 
		 		style="text-decoration-line:none">${vo.title}</a>
		 	</c:when>
		 	<c:otherwise>
		 		<a href="${pageContext.servletContext.contextPath}/user/login" style="text-decoration-line:none">${vo.title}</a>
	 		</c:otherwise>
	 	</c:choose>	
		 </td>			
		 <td>${vo.readCount}</td>
		 <td>${vo.replyCount}</td>
		 <td>${vo.writeDate}</td></tr>
		 </c:forEach> 
</table>
<!-- paging 처리 -->
			<c:if test = "${map.startPage!=1}">
			<a href="${pageContext.servletContext.contextPath}/board?page=${map.startPage-5}&kwd=${map.kwd}&value=${map.value}&arrow=arrow">◀</a>
			</c:if>
			<c:forEach  begin="${map.startPage}" end="${map.endPage}"  step="1" var="page">
				<c:choose>
					<c:when test="${map.currentPage==page}">
						<a href="${pageContext.servletContext.contextPath}/board?page=${page}&kwd=${map.kwd}&value=${map.value}"> ${page}</a>
					</c:when>
					<c:otherwise>
					<a href="${pageContext.servletContext.contextPath}/board?&page=${page}&kwd=${map.kwd}&value=${map.value}">${page}</a>
					</c:otherwise>	
				</c:choose>		
			</c:forEach>
			<c:if test = "${map.endPage!=Math.ceil(map.totalList.size()/10)}">
			<a href="${pageContext.servletContext.contextPath}/board?page=${map.startPage+5}&kwd=${map.kwd}&value=${map.value}&arrow=arrow">▶</a>
			</c:if>

<h3>총 글의 개수 : ${map.totalList.size()}</h3>
<form method=get action="${pageContext.request.contextPath}/board"> 
<select name="kwd">
    <option value="" disabled>검색</option>
    <option value="title">제목</option>
    <option value="content">내용</option>
</select>
<input name = "value" placeholder="글자를 입력하세요">
</form>

<c:if test="${not empty authUser}">
<button id ="add" onclick="dislpayDiv(1);"> 게시판 작성</button>
</c:if>
<div id="write"  style="display:none">
<h1>글을 작성하세요</h1>
<form method = "post" action="${pageContext.request.contextPath}/board/write"> <!-- write로 request mapping을 받는다. -->
아이디 : <input name="id" value="${authUser.id}" readonly><br>
글 제목 : <input name="title" type="text" required maxlength="20" placeholder="제목은 최대 20자까지 작성 가능합니다."><br>
글의 내용 : <br>
<textarea name="content" rows="10" cols="40" required maxlength="300"
placeholder="텍스트는 최대 300까지 작성 가능합니다."></textarea>
 <br>
<!-- 이미지 : <input type="file" name="file" > -->
<input type="submit" value="등록" onclick="alert('글이 추가되었습니다.')">
<input type="reset" value="재작성">
<input type="submit" value="작성취소" onclick="reject();">
</form>
</div>

</body>
</html>