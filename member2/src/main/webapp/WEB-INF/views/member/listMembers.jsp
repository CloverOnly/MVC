<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	String sessionId=(String)session.getAttribute("sessionId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
<table border="1" align="center" width="80%">
    <tr align="center" bgcolor="lightgreen">
        <td><b>아이디</b></td>
        <td><b>비밀번호</b></td>
        <td><b>이름</b></td>
        <td><b>이메일</b></td>
        <td><b>가입일</b></td>
        <td><b>삭제</b></td>
		<td><b>수정</b></td>
    </tr>
    
    <c:forEach var="member" items="${membersList}">
        <tr align="center">
            <td>${member.id}</td>
            <td>${member.pwd}</td>
            <td>${member.name}</td>
            <td>${member.email}</td>
            <td>${member.joinDate}</td>
            <td><a href="${contextPath}/member/removeMember.do?id=${member.id}">
                삭제하기
            </a></td>
			<td><a href="${contextPath}/member/modMember.do?id=${member.id}">
			    수정하기
			</a></td>
        </tr>
    </c:forEach>
</table>
<!--<a href="${contextPath}/member/memberForm.do"><h1 style="text-align:center">회원가입</h1></a>-->
<!--<a href="${contextPath }/member/loginForm.do"><h1 style="text-align:center">정보수정</h1></a>-->

<c:choose>
			    <c:when test="${empty sessionScope.member}">
			        <a class="imc d-flex flex-column align-items-center" href="${contextPath }/member/loginForm.do">    
			            <h1 style="text-align:center">로그인</h1>
			        </a>
			        <a class="imc d-flex flex-column align-items-center" href="${contextPath}/member/memberForm.do">			          
			            <h1 style="text-align:center">회원가입</h1>
			        </a>
			        	        
			    </c:when>

			    <c:otherwise>
			        <h1 style="text-align:center">${member.id}님</h1>
					
					<a class="imc d-flex flex-column align-items-center" href="${contextPath }/member/logout.do">    
						<h1 style="text-align:center">로그아웃</h1>
					</a>
			        
					<a href="${contextPath }/member/modMember.do"><h1 style="text-align:center">회원수정</h1></a>
				</c:otherwise>
			</c:choose>
</body>
</html>
