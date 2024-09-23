<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
    <style>
        /* 전체 레이아웃 */
        .container {
            display: flex;
            flex-direction: row;
            margin-top: 100px;
        }
    /* 오른쪽 메인 컨텐츠 */
        .main-content {
            flex-grow: 1;
            padding: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .form-group button {
            padding: 10px 20px;
            background-color: #0073e6;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #005bb5;
        }
    </style>
    <script type="text/javascript">
        function showAlert(message) {
            alert(message);
        }
        function confirmDelete(){
            if(confirm("정말로 탈퇴하시겠습니까?")){
                window.location.href = '/delete';
            }else{
                window.location.href = '/mypage';
            }
        }
    </script>
</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
    <div class="main-content">
        <h2>My page</h2>
        <form action="/edit" method="post">
            <div class="textbox">
                <label>username</label>
                <input type="text" placeholder="Username" name="username" value="${user.username}" readonly>
            </div>
            <div class="textbox">
                <label>password</label>
                <input type="password" placeholder="Password" name="password" value="${user.password}">
            </div>
            <div class="textbox">
                <label>email</label>
                <input type="email" placeholder="Email" name="email" value="${user.email}" readonly>
            </div>
            <button type="submit" class="btn" onclick="location.href='/'">수정</button>
            <button type="button" class="btn" onclick=confirmDelete()>회원탈퇴</button>
        </form>
        <c:if test="${not empty message}">
            <script type="text/javascript">
                showAlert("${message}");
            </script>
        </c:if>
    </div>
</div>
</body>
</html>
