<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 50px;
            margin-left: 300px;
        }
        table {
            width: 80%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .pagination a {
            margin: 0 5px;
            padding: 5px 10px;
            text-decoration: none;
            color: black;
        }
        .pagination a.active {
            background-color: #4CAF50;
            color: white;
            border: 1px solid #4CAF50;
        }
    </style>
    <script type="text/javascript">
        function showAlert(message) {
            alert(message);
        }
    </script>
</head>
<body>
<div class="container">
    <h2>게시물 목록</h2>
    <a href="/boardList?sort=like&searchKeyword=${pageRequest.searchKeyword}">좋아요순</a>
    <a href="/boardList?sort=read&searchKeyword=${pageRequest.searchKeyword}">조회순</a>
    <a href="/boardList?sort=time&searchKeyword=${pageRequest.searchKeyword}">최신순</a>
    <br><br>
    <div>
        <form action="/boardList" method="get">
            <input type="text" name="searchKeyword" placeholder="검색어 입력" value="${pageRequest.searchKeyword}">
            <button type="submit">검색</button>
        </form>
    </div>
    <table class="board-table">
        <thead>
        <tr>
            <th>제목</th>
            <th>작성자</th>
            <th>작성 시간</th>
            <th>조회수</th>
            <th>좋아요</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${pageResponse.data}">
            <tr>
                <td><a href="/board/${board.id}">${board.title}</a></td>
                <td>${board.author}</td>
                <td>${board.time}</td>
                <td>${board.readCount}</td>
                <td>${board.likeCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br><br>
    <div class="pagination">
        <c:if test="${pageResponse.startPage > 1}">
               <a href="/boardList?page=${pageResponse.startPage-1}&size=10&sort=${pageRequest.sort}&searchKeyword=${pageRequest.searchKeyword}">이전</a>
        </c:if>
        <c:forEach var="i" begin="${pageResponse.startPage}" end="${pageResponse.endPage}">
            <a href="/boardList?page=${i}&size=10&sort=${pageRequest.sort}&searchKeyword=${pageRequest.searchKeyword}"
               class="${i == pageResponse.currentPage ? 'active' : ''}">${i}</a>
        </c:forEach>
        <c:if test="${pageResponse.endPage < pageResponse.totalPages}">
            <a href="/boardList?page=${pageResponse.endPage+1}&size=10&sort=${pageRequest.sort}&searchKeyword=${pageRequest.searchKeyword}">다음</a>
        </c:if>
    </div>
    <br><br>
    <button class="button" onclick="location.href='/addBoard'">게시글 등록</button>
    <button class="button" onclick="location.href='/'">홈으로</button>
    <c:if test="${not empty successMessage}">
        <script type="text/javascript">
            showAlert("${successMessage}");
        </script>
    </c:if>
</div>
</body>
</html>