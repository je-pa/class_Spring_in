<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>리스트</h1>
<div>
    <form action="list" id="frm">
        <input type="hidden1" name="page" value="${empty param.page?1:param.page}">
        <select name="recordCnt">
            <c:forEach begin="5" end="20" step="5" var="cnt">
                <c:choose>
                    <c:when test="${cnt eq param.recordCnt}">
                        <option selected>${cnt}</option>
                    </c:when>
                    <c:otherwise>
                        <option>${cnt}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </select>
    </form>
</div>
<table>
    <tr><th>no</th><th>title</th><th>작성자</th><th>작성일</th></tr>

    <c:forEach var="item" items="${requestScope.list }">
        <tr class = "record" onclick = "moveToDetail(${item.iboard})">
            <td>${pageScope.item.iboard}</td>
            <td>
                <c:choose>
                    <c:when test="${param.searchType eq 1 || param.searchType eq 2 }">
                        ${item.title.replace(param.searchText,'<mark>'+=param.searchText+='</mark>')}
                    </c:when>
                    <c:otherwise>
                        ${item.title}
                    </c:otherwise>
                </c:choose>
                <!--좋아요-->
                <c:if test = "${not empty sessionScope.loginUser && item.isfav eq 1}">
                    <i class="fas fa-kiss-wink-heart"></i>
                </c:if>

            </td>
            <c:choose>
                <c:when test="${empty item.profileImg }">
                    <c:set var="img" value="/res/img/noprofile.jpg"/>
                </c:when>
                <c:otherwise>
                    <c:set var="img" value="/img/${item.iuser }/${item.profileImg }"/>
                </c:otherwise>
            </c:choose>
            <td>
                <c:choose>
                    <c:when test="${param.searchType eq 4}">
                        ${item.writerNm.replace(param.searchText,'<mark>'+=param.searchText+='</mark>')}
                    </c:when>
                    <c:otherwise>
                        ${item.writerNm}
                    </c:otherwise>
                </c:choose>
                <img src ="${img}" class="profileImg">
            </td>
            <td>${item.regdt}</td>
        </tr>
    </c:forEach>

</table>
<c:forEach var="page" begin="1" end="${requestScope.maxPage}">
    <c:choose>
        <c:when test="${param.page eq page || (empty param.page && page eq 1)}">
            <span class = "selected">${page}</span>
        </c:when>
        <c:otherwise>
            <span><a href="list?page=${page}&recordCnt=${param.recordCnt==null?5:param.recordCnt}">${page}</a> </span>
        </c:otherwise>
    </c:choose>

</c:forEach>

