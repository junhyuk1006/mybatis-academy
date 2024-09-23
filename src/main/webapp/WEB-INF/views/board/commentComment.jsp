<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>답글</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div>
    <h4 style="color:white">답글 등록</h4>
    <form action="/commentComment/${boardId}/${commentId}" method="post">
        <div class="textbox">
            <textarea name="content" placeholder="댓글을 입력하세요..." required></textarea>
        </div>
        <button type="submit" class="btn">답글 작성</button>
    </form>
</div>
</body>
</html>