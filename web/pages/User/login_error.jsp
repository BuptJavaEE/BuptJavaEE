<%--
  Created by IntelliJ IDEA.
  User: LuoSue
  Date: 2021/9/6
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>诶？好像出错了......</title>
</head>
<body>
<p style="text-indent: 2em; margin-top: 30px;">
    您输入的密码或用户名有误，系统将在 <span id="time">5</span> 秒钟后自动跳转至登录页面，如果未能跳转，<a href="http://localhost:8080/BuptJavaEE_war_exploded/pages/User/login.html" title="点击访问">请点击</a>。</p>
<script type="text/javascript">
    delayURL();
    function delayURL() {
        var delay = document.getElementById("time").innerHTML;
        var t = setTimeout("delayURL()", 1000);
        if (delay > 0) {
            delay--;
            document.getElementById("time").innerHTML = delay;
        } else {
            clearTimeout(t);
            window.location.href = "http://localhost:8080/BuptJavaEE_war_exploded/pages/User/login.html";
        }
    }
</script>
</body>
</html>
