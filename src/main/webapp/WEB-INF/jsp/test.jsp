<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="requestURL">${pageContext.request.requestURL}</c:set>
<c:set var="url">${fn:replace(requestURL,pageContext.request.requestURI,pageContext.request.contextPath)}</c:set>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h3>WOW</h3>
<table border="1" style="width: 300px">
    <tr>
        <th>No</th>
        <th>Name</th>
        <th>Age</th>
        <th>Address</th>
    </tr>
    <c:forEach var="mylist" items="${list}" varStatus="mycount">
        <tr>
            <td>${mycount.count}</td>
            <td>${mylist.name}</td>
            <td>${mylist.age}</td>
            <td>${mylist.address}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4">
            <input type="button" value="第一頁" onclick="goPage('1');">
            <input type="button" value="上一頁" onclick="goPage('${page-1}');">
            <input type="button" value="下一頁" onclick="goPage('${page+1}');">
            <input type="button" value="最後一頁" onclick="goPage('${pageCount}');">
            <br>
            現在是第 ${page} 頁，全部共有 ${pageCount} 頁
            全部資料有 ${totalSize} 筆，每一頁有 ${pageSize} 筆
        </td>
    </tr>
    <tr>
        <td colspan="4">
    <c:set value="${userList}" var="userPageList" />

    <c:choose>
        <c:when test="${userPageList.firstPage}">
            <span>Prev</span>
        </c:when>
        <c:otherwise>
            <c:url value="/love/prev" var="url" />
            <a href='<c:out value="${url}" />'>Prev</a>
        </c:otherwise>
    </c:choose>
    <c:forEach begin="1" end="${userPageList.pageCount}" step="1"  varStatus="tagStatus">
        <c:choose>
            <c:when test="${(userPageList.page + 1) == tagStatus.index}">
                <span>${tagStatus.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/love/${tagStatus.index}" var="url" />
                <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:choose>
        <c:when test="${userPageList.lastPage}">
            <span>Next</span>
        </c:when>
        <c:otherwise>
            <c:url value="/love/next" var="url" />
            <a href='<c:out value="${url}" />'>Next</a>
        </c:otherwise>
    </c:choose>
        </td>
    </tr>
</table>
</body>
</html>

<script>
    function goPage(page){
        console.log("page:"+page);

    }
</script>