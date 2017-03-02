<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2jsonArray17/3/1/jsonArrayjsonArray1
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>小说爬虫</title>
    <style>
        #keyword {
            width: 300px;
            height: 30px;
            border: solid #43d5ff 1px;
            margin: 30px;

        }

        #platformId {
            width: 300px;
            height: 30px;
            border: solid #43d5ff 1px;
            margin-left: 30px;
        }

        #submitButton {
            border: solid cornflowerblue 1px;
            background-color: #43d5ff;
            margin-left: 30px;
            font-size: 20px;
        }

        #chapterList {
            border: solid cornflowerblue 1px;
            margin: 0 auto;
        }
    </style>
</head>
<body>


<span>小说的关键字，作者名</span>
<label for="keyword">
    <input type="text" id="keyword" name="keyword"/>
</label>
<span>小说平台的id</span>
<label for="platformId">
    <input type="text" id="platformId" name="platformId">
</label>
<input id="submitButton" value="搜索下"/>


<div id="content">
    <table id="chapterList">
        <tr>
            <td>书 名</td>
            <td>作 者</td>
            <td>最新章节</td>
            <td>更新状态</td>
            <td>更新时间</td>
            <td>网站平台</td>

        </tr>
    </table>

</div>

</body>
<script>


    Object.prototype.length = function () {
        var count = 0;
        for (var length in this) {
            count++;
        }
        return count;
    };

    function formatDate(number) {
        var date = new Date(number);
        Y = date.getFullYear() + '-';
        M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        D = date.getDate() + ' ';
        return Y + M + D;

    }

    function getStatus(platformId) {
        switch (platformId) {
            case 1:
                return "完结";
            case 2:
                return "连载中";
            default :
                return "未知状态";
        }
    }

    function getPlatform(statusId) {
        switch (statusId) {
            case 3 :
                return "笔下文学";
            case 4:
                return "看书中";
            default :
                return "无法判断";
        }
    }

    function createxmlHttpRequest() {

        if (window.ActiveXObject) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        } else if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        }
        return xmlHttp;
    }

    var submitButton = document.getElementById("submitButton");
    var chapterList = document.getElementById("chapterList");

    submitButton.onclick = function () {
        var xmlHttp = createxmlHttpRequest();
        xmlHttp.open("post", "${pageContext.request.contextPath}/getNovelByKeywordAndPlatformId", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("keyword=" + document.getElementById("keyword").value + "&platformId=" + document.getElementById("platformId").value);
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                console.log("请求成功");
                var json = JSON.parse(xmlHttp.responseText);
                for (var j = 0; j < json.data.length; j++) {
                    var tr = document.createElement("tr");
                    var tds = new Array();
                    for (var a = 0; a < 6; a++) {
                        tds[a] = document.createElement("td");
                    }
                    // 小说的链接
                    var Novelia = document.createElement("a");
                    Novelia.href = "showChapterList?url="+json.data[j].url;
                    Novelia.innerHTML = json.data[j].name;
                    tds[0].appendChild(Novelia);                 // 设置小说的链接

                    tds[1].innerHTML = json.data[j].author;           //作者
                    var NovelLastUrl = document.createElement("a");
                    NovelLastUrl.href = json.data[j].lastUpdateChapterUrl;   //最后更新一章的url
                    NovelLastUrl.innerHTML = json.data[j].lastUpdateChapter;  //最后一章的标题
                    tds[2].appendChild(NovelLastUrl);
                    tds[3].innerHTML = getStatus(json.data[j].status);             //状态
                    tds[4].innerHTML = formatDate(json.data[j].lastUpdateTime);       //最后一章更新的时间
                    tds[5].innerHTML = getPlatform(json.data[j].platformId);        //平台的id
                    for (var x = 0; x < tds.length; x++) {
                        tr.appendChild(tds[x]);
                    }
                    chapterList.appendChild(tr);
                }


            }

        }


    }


</script>
</html>
