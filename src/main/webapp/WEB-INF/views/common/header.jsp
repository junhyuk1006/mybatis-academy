<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <title>Your Website</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/barStyles.css">
</head>
<div class="navbar">
    <a href="/">홈</a>
    <a href="/category1">카테고리 1</a>
    <a href="/category1">카테고리 2</a>
    <a href="/category1">카테고리 3</a>
    <a href="/boardList">게시판</a>
</div>
<c:if test = "${user != null}">
    <div class="user-info-box">
        <div>
            <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA4MzBfMTky%2FMDAxNzI0OTgzNjcwODEz.08SVabCkPDTtQftmoRmslp1GeEWGJkV5FN5niwT3940g.DCIok9hHDJOPO7oGmvK6FU6ERXz3n9Bcmkf9lrJt76gg.JPEG%2Fgenerated_%252838%2529.jpg&type=sc960_832" alt="User Avatar">
            <div class="user-details">
                <p class="user-name">${sessionScope.user.username}님</p>
                <p class="user-email">${sessionScope.user.email}</p>
            </div>
        </div>
        <button class="logout-btn" onclick="location.href='/logout'">로그아웃</button>
        <div class="user-actions">
            <a href="/mypage">내정보</a>
        </div>
    </div>
</c:if>
<c:if test = "${user == null}">
    <div class="user-info-box">
        <button class="logout-btn" onclick="location.href='/login'">로그인</button>
    </div>
</c:if>