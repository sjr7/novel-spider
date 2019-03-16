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
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${novelName}</title>
    <style>
        a {
            text-decoration: none;
        }

        table {
            border: 1px solid darkcyan;
        }

        #title {
            text-align: center;

        }

        td {
            padding: 10px;
            border: 1px solid darkcyan;
            text-align: center;
        }

        a:hover {
            border-bottom: 1px solid ivory;
        }

        table {
            position: relative;
            margin: 0 auto;
        }

        #backtop a {
            /* back to top button */
            position: fixed;
            bottom: 0px; /* 小按钮到浏览器底边的距离 */
            right: 50px; /* 小按钮到浏览器右边框的距离 */
            color: #333; /* 小按钮中文字的颜色 */
            z-index: 1000;
            background: #cfcfcf; /* 小按钮底色 */
            padding: 10px; /* 小按钮中文字到按钮边缘的距离 */
            border-radius: 4px; /* 小按钮圆角的弯曲程度（半径）*/
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            font-weight: normal; /* 小按钮中文字的粗细 */
            text-decoration: none !important;
        }

        #backtop a:hover {
            /* 小按钮上有鼠标悬停时 */
            background: #333; /* 小按钮的底色 */
            color: #fff; /* 文字颜色 */
        }

    </style>
</head>
<body>
<div>
    <a href="index">返回首页</a>

</div>
<div id="backtop"><a href="#">TOP</a></div>
<table>
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
