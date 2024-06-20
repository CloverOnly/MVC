<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입창</title>
<style>
    .text_center { text-align: center; }
    .error { color: red; }
</style>
<script>
function validateForm() {
    var id = document.forms["memberForm"]["id"].value;
    var pwd = document.forms["memberForm"]["pwd"].value;
    var name = document.forms["memberForm"]["name"].value;
    var email = document.forms["memberForm"]["email"].value;

    if (id.length < 3 || id.length > 11) {
        alert("아이디는 최소 3자에서 최대 11자여야 합니다.");
        return false;
    }
    if (pwd.length < 3 || pwd.length > 11) {
        alert("비밀번호는 최소 3자에서 최대 11자여야 합니다.");
        return false;
    }
    if (name.length < 2 || name.length > 11) {
        alert("이름은 최소 2자에서 최대 11자여야 합니다.");
        return false;
    }
    if (!validateEmail(email)) {
        alert("유효한 이메일 주소를 입력해주세요.");
        return false;
    }
    return true;
}

function validateEmail(email) {
    var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}
</script>
</head>
<body>
    <form name="memberForm" method="post" action="${contextPath}/member/addMember.do" onsubmit="return validateForm()">
        <h1 class="text_center">회원 가입창</h1>
        <table align="center">
            <tr>
                <td width="200"><p align="right">아이디</p></td>
                <td width="400">
                    <input type="text" name="id">
                    <c:if test="${not empty fieldErrors['id']}">
                        <span class="error">${fieldErrors['id'][0].defaultMessage}</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td width="200"><p align="right">비밀번호</p></td>
                <td width="400">
                    <input type="password" name="pwd">
                    <c:if test="${not empty fieldErrors['pwd']}">
                        <span class="error">${fieldErrors['pwd'][0].defaultMessage}</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td width="200"><p align="right">이름</p></td>
                <td width="400">
                    <input type="text" name="name">
                    <c:if test="${not empty fieldErrors['name']}">
                        <span class="error">${fieldErrors['name'][0].defaultMessage}</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td width="200"><p align="right">이메일</p></td>
                <td width="400">
                    <input type="text" name="email">
                    <c:if test="${not empty fieldErrors['email']}">
                        <span class="error">${fieldErrors['email'][0].defaultMessage}</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td width="200"><p>&nbsp;</p></td>
                <td width="400"><input type="submit" value="가입하기"> <input type="reset" value="다시입력"></td>
            </tr>
        </table>
    </form>
</body>
</html>
