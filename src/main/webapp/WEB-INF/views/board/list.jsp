<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>리스트</h1>
<div>
    <span><a href ="/user/profile">프로필가기</a></span>
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

