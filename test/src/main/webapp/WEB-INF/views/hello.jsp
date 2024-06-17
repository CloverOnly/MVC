<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<%
		request.setCharacterEncoding("utf-8");
	%>
<!DOCTYPE html>
<html>
<head>
<script src="${contextPath}/js/scriptTest.js" type="text/javascript"></script>
<meta charset="utf-8">

<title>Insert title here</title>
</head>
<body>
<!--<h2>Hello World</h2>-->
<!--<hr>-->
<!--현재 날짜와 시간은 <%=java.time.LocalDateTime.now() %>-->
<!--<hr>-->
<!--메시지: ${msg }-->

안녕하세요<br>
<h2>${message}</h2>
<img width = 200 height=200 src="${contextPath}/img/jp.jpg"/><br/>
<input type="button" name="테스트" value="테스트" onClick="test();">

</body>
</html>