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
    <style>
      .prev,.next{

          background-color: #ffeafd;
          width: 200px;
          height: 50px;
          font-size: 25px;

      }
      .prev{

      }
      .next{
          float: right;
      }

        #content{
            margin: 50px;
        }
    </style>
</head>
<body>

<a href="showChapterList?url=${baseUrl}">返回章节列表</a>

<h2 align="center">${chapterDetail.title}</h2>
<hr>
<div id="content">
    <c:if test="${isSuccess}" >
        ${chapterDetail.content}

        <hr>

            <a href="showNovelDetail?url=${chapterDetail.prev}&baseUrl=${baseUrl}" class="prev">上一章</a>
            <a href="showNovelDetail?url=${chapterDetail.next}&baseUrl=${baseUrl}" class="next">下一章</a>

    </c:if>
    <c:if test="!${isSuccess}">
        出错了耶
    </c:if>
</div>



</body>
</html>
