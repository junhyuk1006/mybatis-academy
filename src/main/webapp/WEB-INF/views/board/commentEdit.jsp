<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>댓글 수정</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div>
    <h4 style="color:white">댓글 수정</h4>
    <form action="/editComment/${boardId}/${comment.id}" method="post">
        <div class="textbox">
            <textarea name="content" placeholder="댓글을 입력하세요..." required>${comment.content}</textarea>
        </div>
        <button type="submit" class="btn">댓글 작성</button>
    </form>
</div>
</body>
</html>