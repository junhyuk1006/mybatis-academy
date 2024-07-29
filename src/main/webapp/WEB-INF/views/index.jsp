<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <script>
        function showLoginForm(){
            document.getElementById('login-form').style.display = 'block';
            document.getElementById('join-form').style.display = 'none';
            document.getElementById('form-title').innerText = 'Login';
        }

        function showJoinForm(){
            document.getElementById('join-form').style.display = 'block';
            document.getElementById('login-form').style.display = 'none';
            document.getElementById('form-title').innerText = 'Join';
        }

        function handelJoinResponse(){
            showLoginForm();
            alert("회원가입에 성공하였습니다!!");
        }

    </script>
</head>
<body>
<div class="container">
    <div class="login-box">
        <h2 id="form-title">Login</h2>
        <form id="login-form" action="${pageContext.request.contextPath}/home" method="post" style="display: block">
            <div class="textbox">
                <input type="text" placeholder="Username" name="username" required>
            </div>
            <div class="textbox">
                <input type="password" placeholder="Password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
            <button type="button" class="btn" onclick="showJoinForm()">Join</button>
        </form>
        <form id="join-form" action="${pageContext.request.contextPath}" method="post" style="display: none;">
            <div class="textbox">
                <input type="text" placeholder="Username" name="username" value="${user.username}" required>
            </div>
            <div class="textbox">
                <input type="password" placeholder="Password" name="password" value="${user.password}" required>
            </div>
            <div class="textbox">
                <input type="email" placeholder="Email" name="email" value="${user.email}" required>
            </div>
            <button type="submit" class="btn" onclick="handelJoinResponse()">Join</button>
            <button type="button" class="btn" onclick="showLoginForm()">Back to Login</button>
        </form>
    </div>
</div>
</body>
</html>