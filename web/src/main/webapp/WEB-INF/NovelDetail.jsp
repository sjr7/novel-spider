<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2017/3/2/002
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>${chapterDetail.title}</title>
</head>
<body>
<div>
<a href="showChapterList?url=${baseUrl}">返回章节列表</a>

</div>
<c:if test="${isSuccess}">
    ${chapterDetail.content}

    <div>
        <a href="showNovelDetail?url=${chapterDetail.prev}&baseUrl=${baseUrl}">上一章</a>
    <a href="showNovelDetail?url=${chapterDetail.next}&baseUrl=${baseUrl}">下一章</a>
    </div>
</c:if>
<c:if test="!${isSuccess}">
    出错了耶
</c:if>


</body>
</html>
