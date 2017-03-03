<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2017/3/2/002
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>${novelName}</title>
    <style>
        a{
            text-decoration: none;
        }
        table{
            border: 1px solid darkcyan;
        }
        #title{
            text-align: center;

        }
    </style>
</head>
<body>
<div>
    <a href="index">返回首页</a>
</div>
<table >
    <h2 id="title">${novelName}</h2>

    <c:forEach items="${chapterList}" var="chapter" varStatus="index">
        <c:if test="${index.count% 5 == 0}">
            <tr>
        </c:if>

        <c:if test="${index.count %5 != 0}">
            <td>
                <a href="showNovelDetail?url=${chapter.url}&baseUrl=${baseUrl}">${chapter.title}</a>
            </td>

        </c:if>

        <c:if test="${index.count% 5 == 0}">
            </tr>

        </c:if>


    </c:forEach>


</table>

</body>
</html>
