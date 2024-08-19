<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <div class="login-box">
        <h2>게시글</h2>
        <div class="textbox">
            <label for="title">제목</label>
            <div id="title" style="color: white">${board.title}</div>
        </div>
        <div class="textbox">
            <label for="content">내용</label>
            <div id="content" style="color: white">${board.content}</div>
        </div>
        <c:if test="${not empty myBoard}">
            <button type="button" class="btn" onclick="location.href='/editBoard/${board.id}'">수정하기</button>
            <button type="button" class="btn" onclick="location.href='/deleteBoard/${board.id}'">삭제하기</button>
        </c:if>
        <button type="button" class="btn" onclick="location.href='/boardList'">목록으로</button>
    </div>
    <div class="comment-section">
        <h3 style="color:white">댓글</h3>
            <c:forEach var="comment" items="${comments}">
                <div class="comment">
                    <p style="color: white"><strong> ${comment.userId} -> </strong>${comment.content} -> ${comment.time} ->
                     <form action="/like/${board.id}/${comment.id}" method="post" style="display: inline;">
                        <button type="submit" style="background:none; border:none; color:white;">
                            <img src="/images/like.png" alt="Like" style="width:16px; height:16px;">
                            <span>${comment.likeCount}</span>
                        </button>
                     </form>
                    </p>
                    <c:if test="${user.id eq comment.userId}">
                        <button type="button" class="btn" onclick="location.href='/deleteComment/${board.id}/${comment.id}'">삭제하기</button>
                    </c:if>
                    <button type="button" class="btn">답글 달기</button>
                </div>
            </c:forEach>
        <div class="reply">
            <blockquote>
                    <p style="color: white"><strong>사용자2</strong>: → 이건 대댓글입니다.</p>
                    <button type="button" class="btn">답글 달기</button>
            </blockquote>
        </div>
        <h4 style="color:white">댓글 작성</h4>
        <form action="/board/${board.id}" method="post">
            <div class="textbox">
                <textarea name="content" placeholder="댓글을 입력하세요..." required></textarea>
            </div>
            <button type="submit" class="btn">댓글 작성</button>
           </form>
    </div>
</div>
</body>
</html>