<%@ page import="pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: tigers
  Date: 2021/7/26
  Time: 9:27 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--    基础路径-->
    <base href="../../">
    <!--    引入bootstrap相应样式文件-->
    <link rel="stylesheet" href="static/css/bootstrap.css">
    <%--引入jquery    --%>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<!-- 导航栏区域   -->
<%
    User StudentUser=(User)request.getSession().getAttribute("User");
%>
<abc class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a href=" " class="navbar-brand">协同写作平台</a >
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="http://localhost:8080/BuptJavaEE_war_exploded/pages/Writer/My.jsp">我的&nbsp;</a ></li>
                <li><a href="http://47.94.108.20:9001?name=<%=StudentUser.getUsername()%>">协同写作</a ></li>
                <li><a href="http://localhost:8080/BuptJavaEE_war_exploded/pages/Writer/Findblog.jsp">查找文章</a ></li>
                <li><a href="http://47.94.108.20:8080/BuptCreationEE/pages/Student/ChatRoom.jsp">聊天室</a ></li>
            </ul>
        </div>
    </div>
</abc>
<%--动态显示高亮--%>
<script>
    $('.nav').find('a').each(function () {
        if (this.href == document.location.href || document.location.href.search(this.href) >= 0) {
            $(this).parent().addClass('active'); // this.className = 'active';
        }
    });
    $('.nav').find('a').each(function () {
        if (this.href == document.location.host+"/BuptCreationEE/pages/Student/My.jsp") {
            $(this).append("<span class=\"badge\">6</span>");
        }
    });
</script>
</body>
</html>
