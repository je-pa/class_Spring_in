<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021-06-04
  Time: 오전 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/res/css/boardDetail.css">
    <script defer src="/res/js/BoardDetail.js"></script>
</head>
<body>
    <link rel="stylesheet" href="/res/css/boardDetail.css">
    <div><a href="#" onclick="goBack();">돌아가기</a></div>
    <h1>${requestScope.data.title}</h1>
    <div>번호 : ${requestScope.data.iboard }</div>
    <div>작성일 : ${requestScope.data.regdt}</div>
    <div>작성자 : ${requestScope.data.writerNm}</div>
    <div><c:out value="${requestScope.data.ctnt}"/></div>

    <c:if test="${not empty sessionScope.loginUser}">
        <div>
            <form id="cmtFrm" onsubmit="return false;">
                <input type="text" id="cmt" placeholder="댓글">
                <input type="button" value="댓글달기" onclick="regCmt();">
            </form>
        </div>
    </c:if>
    <div id="cmtList" data-login-user-pk="${sessionScope.loginUser.iuser}" data-iboard="${param.iboard}"></div>


</body>
</html>
