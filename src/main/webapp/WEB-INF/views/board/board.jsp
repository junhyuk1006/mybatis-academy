<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                <c:if test = "${comment.parentId == 0}">
                    <div class="comment">
                        <p style="color: white"><strong> ${comment.userId} -> </strong>${comment.content} -> ${comment.time} ->
                         <form action="/like/${board.id}/${comment.id}" method="post" style="display: inline;">
                            <button type="submit" style="background:none; border:none; color:white;">
                                <img src="/images/like.png" alt="Like" style="width:25px; height:25px;">${comment.likeCount}
                            </button>
                         </form>
                         <div>${comment.parentId}</div>
                        </p>
                        <div class="comment-buttons">
                            <c:if test="${user.id eq comment.userId}">
                                <button type="button" class="btn" onclick="location.href='/editComment/${board.id}/${comment.id}'">수정하기</button>
                                <button type="button" class="btn" onclick="location.href='/deleteComment/${board.id}/${comment.id}'">삭제하기</button>
                            </c:if>
                            <button type="button" class="btn" onclick="location.href='/commentComment/${board.id}/${comment.id}'">답글달기</button>
                            <button type="button" class="btn" onclick="toggleReplies(${comment.id})">답글보기</button>
                        </div>
                    </div>

                    <div id="replies-${comment.id}" class="reply-comment" style="display:none;">
                        <c:forEach var="reply" items="${comments}">
                            <c:if test = "${reply.parentId == comment.id}">
                                <div class="comment">
                                    <p style="color: white"><strong> ↳ ${reply.userId} -> </strong>${reply.content} -> ${reply.time} ->
                                     <form action="/like/${board.id}/${reply.id}" method="post" style="display: inline;">
                                        <button type="submit" style="background:none; border:none; color:white;">
                                            <img src="/images/like.png" alt="Like" style="width:25px; height:25px;">${reply.likeCount}
                                        </button>
                                     </form>
                                    </p>
                                    <div class="comment-buttons">
                                        <c:if test="${user.id eq reply.userId}">
                                            <button type="button" class="btn" onclick="location.href='/editComment/${board.id}/${reply.id}'">수정하기</button>
                                            <button type="button" class="btn" onclick="location.href='/deleteComment/${board.id}/${reply.id}'">삭제하기</button>
                                        </c:if>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>
            </c:forEach>
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