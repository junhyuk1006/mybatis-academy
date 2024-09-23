<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <title>Your Website</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sidebarStyles.css">
</head>
<div class="sidebar">
    <h2>내 계정</h2>
    <ul>
        <li><a href="/mypage">프로필</a></li>
        <li><a href="/account">계정 관리</a></li>
    </ul>

    <h3>Jobs</h3>
    <ul>
        <li><a href="/resume">이력서 관리</a></li>
        <li><a href="/jobs">구직 내역 관리</a></li>
        <li><a href="/interest">관심 포지션</a></li>
        <li><a href="/myBoardList">내가 쓴 게시물</a></li>
    </ul>
</div>