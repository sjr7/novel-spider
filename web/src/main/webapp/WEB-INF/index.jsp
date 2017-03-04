<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2017/3/1/
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html lang="en-us">
<head>
    <meta charset="UTF-8">
    <title>小说爬虫</title>
    <style>
        #keyword, #platformId {
            width: 300px;
            height: 30px;
            border: solid #43d5ff 1px;
            margin: 20px;
            padding-left: 50px;
        }

        #submitButton {
            border: 0.5px solid #43d5ff;
            background-color: #f8f8f2;
            margin-left: 30px;
            height: 50px;
            font-size: 25px;
            border-radius: 4px;
        }

        #tableList {
            border-right: solid 1px cadetblue;
            border-bottom: solid 1px cadetblue;
            margin: 0 auto;

        }

        tr {
            margin-top: 20px;
        }

        td {
            width: 200px;
            text-align: center;
            border-left: solid 1px cadetblue;
            border-top: solid 1px cadetblue;
        }

        a {
            text-decoration: none;
        }

        #tableHead {
            font-size: 20px;
            font-weight: bold;
        }

        #search {
            text-align: center;
        }

    </style>
</head>
<body>
<datalist id="novel-name"></datalist>

<div id="search">
    <label for="keyword"></label>
    <input type="text" id="keyword" name="keyword" placeholder="小说的书名，作者名" data-toggle='autocomplete'
           list="novel-name" oninput="getAutoCompletion()"/>
    <label for="platformId"></label>
    <input type="text" id="platformId" name="platformId" placeholder="小说平台的id，笔下文学为3，看书中为4" data-toggle='autocomplete'>
    <a href="#">
        <button type="button" id="submitButton" value="">点击搜索或按回车键</button>
    </a>
</div>

<div id="content">
    <table id="tableList">
        <thead>
        <tr id="tableHead">
            <td>书 名</td>
            <td>作 者</td>
            <td>最新章节</td>
            <td>更新状态</td>
            <td>更新时间</td>
            <td>网站平台</td>
        </tr>
        </thead>
        <tbody id="chapterList">

        </tbody>
    </table>

</div>

</body>
<script>

    var submitButton = document.getElementById("submitButton");
    var chapterList = document.getElementById("chapterList");
    var novelNameCompletion = document.getElementById("novel-name");
    // 按下回车键时发送查询请求
    submitButton.onclick = getNovelByKeyWordAndPlatformId;

    document.body.onkeydown = function () {
        if (event.keyCode == 13) {
            console.log("回车键被按下，用户搜索");
            getNovelByKeyWordAndPlatformId();
        }
    };

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
                return "看书中";
            case 4:
                return "笔下文学";
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

    function getAutoCompletion() {
        console.log("开始自动补全");
        var xmlHttp = createxmlHttpRequest();
        xmlHttp.open("get", "${pageContext.request.contextPath}/getAutoCompletion?keyword=" +
        document.getElementById("keyword").value, true);
        xmlHttp.send();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var json = JSON.parse(xmlHttp.responseText);
                if (!json.status == false && json.status == true) {
                    for (var i = 0; i < json.data.length; i++) {
                        var option = document.createElement("option");
                        option.innerHTML = json.data[i].name;
                        novelNameCompletion.appendChild(option);
                    }

                }
                else {
                    console.log("都没有数据");
                }

            }
        }
    }


    function getNovelByKeyWordAndPlatformId() {

        var xmlHttp = createxmlHttpRequest();
        xmlHttp.open("post", "${pageContext.request.contextPath}/getNovelByKeywordAndPlatformId", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("keyword=" + document.getElementById("keyword").value + "&platformId=" + document.getElementById("platformId").value);
        console.log(document.getElementById("keyword").value);
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                chapterList.innerHTML = "";
                var json = JSON.parse(xmlHttp.responseText);
                for (var j = 0; j < json.data.length; j++) {
                    var tr = document.createElement("tr");
                    tr.style.height = "30px";               //设置行距
                    var tds = [];
                    for (var a = 0; a < 6; a++) {
                        tds[a] = document.createElement("td");
                    }
                    // 小说的链接
                    var Novelia = document.createElement("a");
                    Novelia.href = "showChapterList?url=" + json.data[j].url + "&novelName=" + json.data[j].name;
                    Novelia.innerHTML = json.data[j].name;
                    tds[0].appendChild(Novelia);                 // 设置小说的链接

                    tds[1].innerHTML = json.data[j].author;           //作者

                    var NovelLastUrl = document.createElement("a");
                    //笔下文学没有最新章节链接url地址
                    var lastUrl = json.data[j].lastUpdateChapterUrl;
                    var end = lastUrl.lastIndexOf("bxwx9.org") + 9;
                    var subStringUrl = lastUrl.substring(0, end);
                    if (subStringUrl === "http://www.bxwx9.org") {
                        NovelLastUrl.href = "#";   //最后更新一章的url
                        NovelLastUrl.innerHTML = json.data[j].lastUpdateChapter;  //最后一章的标题
                        NovelLastUrl.onclick = function () {
                            alert("哥，这本小说自己点进去看最新章节，不开车");
                        };
                    }
                    else {
                        NovelLastUrl.href = "showNovelDetail?url=" + json.data[j].lastUpdateChapterUrl + "&baseUrl=" + json.data[j].url;   //最后更新一章的url
                        NovelLastUrl.innerHTML = json.data[j].lastUpdateChapter;  //最后一章的标题
                    }
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
