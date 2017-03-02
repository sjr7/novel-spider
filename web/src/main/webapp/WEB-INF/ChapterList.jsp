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
    <title></title>
</head>
<body>
<div>
    <a href="index">返回首页</a>
</div>
<table>
   <tr >小说列表</tr>
            <c:forEach items="${chapterList}" var="chapter">
    <tr>
        <td>
            <a href="showNovelDetail?url=${chapter.url}&baseUrl=${baseUrl}">${chapter.title}</a>
        </td>
    </tr>
            </c:forEach>



</table>

</body>
</html>
