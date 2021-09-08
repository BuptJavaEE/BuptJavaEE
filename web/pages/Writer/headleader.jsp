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
<%--    <script type="text/javascript">--%>
<%--        //定义获取词语下标--%>
<%--        var a_idx = 0;--%>
<%--        jQuery(document).ready(function($) {--%>
<%--            //点击body时触发事件--%>
<%--            $("body").click(function(e) {--%>
<%--                //需要显示的词语  多加一个上签，增加用户抽中好签的概率--%>
<%--                var a = new Array("上上签","上签", "中签", "下签","下下签", "上签");--%>
<%--                //设置词语给span标签--%>
<%--                var $i = $("<span/>").text(a[a_idx]);--%>
<%--                //随机生成 代表抽签--%>
<%--                a_idx = (a_idx + Math.floor(Math.random() * 10))% a.length;--%>
<%--                //获取鼠标指针的位置，分别相对于文档的左和右边缘。--%>
<%--                //获取x和y的指针坐标--%>
<%--                var x = e.pageX, y = e.pageY;--%>
<%--                //在鼠标的指针的位置给$i定义的span标签添加css样式--%>
<%--                $i.css({"z-index" : 999999999999999999999999999999999999999999999999999999999999999999999,--%>
<%--                    "top" : y - 20,--%>
<%--                    "left" : x,--%>
<%--                    "position" : "absolute",--%>
<%--                    "font-weight" : "bold",--%>
<%--                    "opacity":0.7--%>
<%--                    // "color" : ""--%>
<%--                });--%>
<%--                //在body的鼠标上方添加这个标签--%>
<%--                $("body").append($i);--%>
<%--                //animate() 方法执行 CSS 属性集的自定义动画。--%>
<%--                //该方法通过CSS样式将元素从一个状态改变为另一个状态。CSS属性值是逐渐改变的，这样就可以创建动画效果。--%>
<%--                //详情请看http://www.w3school.com.cn/jquery/effect_animate.asp--%>
<%--                $i.animate({--%>
<%--                    //将原来的位置向上移动180--%>
<%--                    "top" : y - 180,--%>
<%--                    "opacity" : 0--%>
<%--                    //1500动画的速度--%>
<%--                }, 1500, function() {--%>
<%--                    //时间到了自动删除--%>
<%--                    $i.remove();--%>
<%--                });--%>
<%--            });--%>
<%--        })--%>
<%--        ;--%>
<%--    </script>--%>
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
                <li><a href="http://10.28.225.233:8080/BuptJavaEE_war_exploded/pages/Writer/My.jsp">我的&nbsp;</a ></li>
                <li><a href="http://10.28.225.233:9001?name=<%=StudentUser.getUsername()%>">协同写作</a ></li>
                <li><a href="http://10.28.225.233:8080/BuptJavaEE_war_exploded/pages/Writer/Findblog.jsp">查找文章</a ></li>
                <li><a href="http://10.28.225.233:8082/teacherpage?name=<%=StudentUser.getUsername()%>">发布任务</a ></li>
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
