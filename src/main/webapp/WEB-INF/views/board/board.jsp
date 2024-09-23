<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <script>
    function toggleReplies(commentId){
        var replySection = document.getElementById("replies-"+commentId);
        if(replySection.style.display === "none"){
            replySection.style.display = "block";
        }else{
            replySection.style.display = "none";
        }
    }
    </script>
</head>
<body>
<div class="container">
    <div class="content-box">
            <h2>${board.title}</h2>
            <div class="post-content">
                <p>${board.content}</p>
            </div>

            <c:if test="${not empty myBoard}">
                <button class="btn" onclick="location.href='/editBoard/${board.id}'">수정하기</button>
                <button class="btn" onclick="location.href='/deleteBoard/${board.id}'">삭제하기</button>
            </c:if>
            <button class="btn" onclick="location.href='/boardList'">목록으로</button>
        </div>
<!-- 댓글 섹션 -->
    <div class="comment-section">
        <h3>댓글</h3>
        <c:forEach var="comment" items="${comments}">
            <div class="comment-box">
                <p><strong>${comment.userId}</strong>: ${comment.content} <span>${comment.time}</span></p>
                <form action="/like/${board.id}/${comment.id}" method="post" style="display: inline;">
                    <button type="submit" class="like-button">좋아요 (${comment.likeCount})</button>
                </form>
                <div class="comment-buttons">
                    <c:if test="${user.id eq comment.userId}">
                        <button class="btn" onclick="location.href='/editComment/${board.id}/${comment.id}'">수정하기</button>
                        <button class="btn" onclick="location.href='/deleteComment/${board.id}/${comment.id}'">삭제하기</button>
                    </c:if>
                    <button class="btn" onclick="toggleReplies(${comment.id})">답글보기</button>
                </div>
            </div>

            <!-- 답글 섹션 -->
            <div id="replies-${comment.id}" class="reply-comment" style="display:none;">
                <c:forEach var="reply" items="${comments}">
                    <c:if test="${reply.parentId == comment.id}">
                        <div class="comment-box">
                            <p><strong>${reply.userId}</strong>: ${reply.content} <span>${reply.time}</span></p>
                            <form action="/like/${board.id}/${reply.id}" method="post" style="display: inline;">
                                <button type="submit" class="like-button">좋아요 (${reply.likeCount})</button>
                            </form>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:forEach>

        <!-- 댓글 작성 -->
        <h4>댓글 작성</h4>
        <form action="/board/${board.id}" method="post">
            <textarea name="content" placeholder="댓글을 입력하세요..." required></textarea>
            <button type="submit" class="btn">댓글 작성</button>
        </form>
    </div>
</div>
</body>
</html>