<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <div class="login-box">
        <h2>Join</h2>
            <div class="textbox">
                <label>username</label>
                <input type="text" placeholder="Username" name="username" value="${user.username}" readonly>
            </div>
            <div class="textbox">
                <label>password</label>
                <input type="password" placeholder="Password" name="password" value="${user.password}" readonly>
            </div>
            <div class="textbox">
                <label>email</label>
                <input type="email" placeholder="Email" name="email" value="${user.email}" readonly>
            </div>
            <button type="submit" class="btn" onclick="location.href='${pageContext.request.contextPath}/home'">홈으로</button>
            <button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/edit'">회원정보 수정</button>
            <button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/delete'">회원삭제</button>
        </form>
    </div>
</div>
</body>
</html>
