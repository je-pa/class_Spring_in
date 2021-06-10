<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link rel="stylesheet" href="/res/css/board/detail.css">
    <div><a href="#" onclick="goBack();">돌아가기</a></div>
    <c:if test="${sessionScope.loginUser.iuser == requestScope.data.iuser}">
        <div>
            <a href="/board/writeMod?iboard=${param.iboard}">수정</a>
            <a href="/board/delBoard?iboard=${param.iboard}">삭제</a>

        </div>
    </c:if>
    <h1>${requestScope.data.title}</h1>
    <div>번호 : ${requestScope.data.iboard } <i id="favIcon" class="far fa-kiss-wink-heart"></i></div>
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
    <div id="modal" class="displayNone">
        <div class="modal_content">
            <form id="cmtModFrm" action="#">
                <input type="hidden" id="icmt">
                <input type="text" id="modCmt">
            </form>
            <input type="button" value="댓글 수정" onclick="modAjax();">
            <input type="button" value="취소" onclick="closeModModal();">
        </div>
    </div>
    <div id="cmtList" data-loginuserpk="${sessionScope.loginUser.iuser}" data-iboard="${param.iboard}"></div>

