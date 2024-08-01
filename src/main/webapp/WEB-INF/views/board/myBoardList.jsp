<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        .container {
            text-align: center;
            margin-top: 50px;
        }
        .button {
            margin: 10px;
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>내가 쓴 게시물 목록</h2>
    <button class="button" onclick="location.href='${pageContext.request.contextPath}/boardList'">목록으로</button>
</div>
</body>
</html>