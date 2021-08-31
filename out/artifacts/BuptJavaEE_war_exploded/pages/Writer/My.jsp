<%@ page import="pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: tigers
  Date: 2021/7/26
  Time: 9:37 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生首页</title>
    <!--    基础路径-->
    <base href="../../">
    <!--提示表单样式CSS  -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,300i,500,500i,700" rel="stylesheet"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.4.1/css/simple-line-icons.min.css'><link rel="stylesheet" href="static/css/chat_remainder.css">
    <!--    vue-基础包导入-->
    <script src="static/vue/vue.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
    <!--    vue-resource 包导入-->
    <script src="https://cdn.bootcdn.net/ajax/libs/vue-resource/1.5.3/vue-resource.js"></script>
    <%--bootstrap导入    --%>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <%--左右布局样式导入    --%>
    <link rel="stylesheet" href="static/css/My-box.css" >
    <style>
        <%--基础个人信息美化--%>
        .userHead {
            background: white;
            border: 3px solid #ddd;
            border-radius: 4px;
            padding: 5px;
            float: left;
        }
        .userNickname {

            padding: 5px;
        }
        .butt {
            width: 100px;
            height: 30px;
            background-color: white;
            color: black;
            align-self: center;
            border: 2px solid #555555;
        }
        .butt:hover {
            background-color: #555555;
            color: white;
        }
        /*文章搜索美化*/
        form {
            position: relative;
            width: 300px;
            margin: 0 auto;
        }

        input, button {
            border: none;
            outline: none;
        }

        input {
            width: 100%;
            height: 42px;
            padding-left: 13px;
        }
        .button {
            height: 42px;
            width: 42px;
            cursor: pointer;
            position: absolute;
        }

        /*搜索框1*/
        .bar1 input {
            border: 2px solid #8c8c8c;
            border-radius: 5px;
            /*background: #F9F0DA;*/
            color: #9E9C9C;
        }
        .bar1 button {
            top: 0;
            right: 0;
            background: #8c8c8c;
            border-radius: 0 5px 5px 0;
        }
        .bar1 button:before {
            content: "🔍";
            font-family: FontAwesome;
            font-size: 16px;
            color: #F9F0DA;
        }
        /*消息提示美化*/
        .message {
            color: #ecf0f1;
            background: #8c8c8c;
            width: 100%;
            top: -10px;
            padding: 0 10px;
        }

        .status-primary {
            background: #e1f5fe;
            color: #0288d1;
        }
        /*.status-primary:before, .status-primary:after {*/
        /*    background: #0288d1;*/
        /*}*/

        .status-secondary {
            background: #ede7f6;
            color: #311b92;
        }
        /*.status-secondary:before, .status-secondary:after {*/
        /*    background: #512da8;*/
        /*}*/

        .status-info {
            background: #e0f2f1;
            color: #00796b;
        }
        /*.status-info:before, .status-info:after {*/
        /*    background: #00796b;*/
        /*}*/

        .status-success {
            background: #e7f6d5;
            color: #689f38;
        }
        /*.status-success:before, .status-success:after {*/
        /*    background: #689f38;*/
        /*}*/

        .status-error {
            background:  #ffdde0;
            color: #d32f2f;
        }
        /*.status-error:before, .status-error:after {*/
        /*    background: #d32f2f;*/
        /*}*/

    </style>
</head>
<body>
    <%@include file="headleader.jsp"%>
    <%--从session中获得user    --%>

    <%
        User loginUser=(User)request.getSession().getAttribute("User");
    %>
<%--个人信息展示端--%>
    <nav>
        <br/>
        <br/>
        <p class="message">个人信息</p>
        <img class="userHead" src="static/img/头像.jpeg" width="100" height="100">
        <p class="userNickname"><span class="glyphicon glyphicon-user"></span> <%=loginUser.getNickname()%></p>
        <span class="label label-default">账号用户名:<%=loginUser.getUsername()%></span>
        <br/>
        <a href="pages/User/login.html">
        <button class="butt">登出</button>
        </a>
    </nav>
<%--文章搜索展示端--%>
    <main>
        <br/>
        <br/>
        <p class="message">我的文章</p>
        <div id="show_blogs" style="overflow-y: scroll">
            <%-- 搜索栏     --%>
                <div class="search bar1">
                    <form>
                        <input type="text" class="search" v-model="search" placeholder="搜索">
                        <button disabled="disabled" class="button" type="submit"></button>
                    </form>
                </div>

            <%-- 具体博客           --%>
                <blog class="single-blog" v-for="blog in filterblogs">
                    <div class="blog">
                    <h2>{{blog.title}}</h2>
                    <br/>
                    <article>{{blog.body|snippet}}</article>
                        <button class="butt" v-on:click="goComment(blog.title,blog.body)"><span class="glyphicon glyphicon-file"></span>查看</button>
                    </div>
                </blog>
        </div>
    </main>
<%--消息推送端--%>
    <aside>
        <br/>
        <br/>
        <p class="message">消息提示</p>
            <section class="alerts" style="width: 100%">
                <div class="alert status-primary">有人对您的文章提出了建议，点我<a>查看建议</a></div>
                <div class="alert status-secondary">xx申请加入您的xx项目与您一起写作<a>查看申请</a></div>
                <div class="alert status-info">xx正在写作xx文章，一起来吗？<a>点我写作</a></div>
                <div class="alert status-success">您的协同协作申请已经通过</div>
                <div class="alert status-error">您的协同写作申请被拒绝了</div>
            </section>
    </aside>




<%--信息处理模块--%>
<script>
    var messages;
    function goComment(title,body,permission){
        console.log("点击事件触发")
        commentblog={title:title,content:body,permission:permission}
        localStorage.setItem('blog',JSON.stringify(commentblog))
        window.location.href="pages/Student/Comment.jsp"
    }
    $(function(){
        $.getJSON("shownewsservlet",function (data) {
            //1.给用户引导；固定 2。给用户提示信息
            $.each(data,function (i,message) {
                if (message.type=="comment") {
                    //用户的message（type:信息类型）
                    //个人主页，收到提示  提示他去评论对应作品
                    var str = " <li>\n" +
                        "                            <div>\n" +
                        "                               "+"<time><span  class=\"label label-info\">作品互评</span></time>"+"您已经被分配到评论文章{"+message.title + "}点击按钮进行评论 \n"+"<br/>"+
                        "<button class=\"btn btn-info btn-lg\" onclick='goComment(\""+message.title+"\",\""+message.content+"\",\""+"true"+"\")'><span class=\"glyphicon glyphicon-pencil\"></span>评论</button>"+
                        "                            </div>\n" +
                        "       </li>";
                    $(".messages").append(str);
                }else if (message.type=="commentfinished"){
                    //个人主页，收到提示 我的作品已经被人评论完了
                    var str = " <li>\n" +
                        "                            <div>\n" +
                        "                               "+"<time><span  class=\"label label-success\">作品互评</span></time>"+"您的文章{"+message.title + "}已经互评完毕，点击按钮查看结果 \n"+"<br/>"+
                        "<button class=\"btn btn-success btn-lg\" onclick='goComment(\""+message.title+"\",\""+message.content+"\",\""+"false"+"\")'><span class=\"glyphicon glyphicon-search\"></span>查看</button>"+
                        "                            </div>\n" +
                        "       </li>";
                    $(".messages").append(str);
                }else if (message.type=="group"){
                    //个人主页，收到提示 小组消息 上面那种组合方式真的垃圾下面换种好康的
                    var strVar="";
                    strVar += "<li>";
                    strVar += "      <div>";
                    strVar += "          <time><span  class=\"label label-default\">小组消息<\/span><\/time> ";
                    strVar += "<span class=\"glyphicon glyphicon-user\">"+message.content;
                    strVar += "<time><span class=\"glyphicon glyphicon-plus\">附加消息:"+message.extraInfo;
                    strVar += "      <\/div>";
                    strVar += "<\/li>";
                    $(".messages").append(strVar);
                }else if (message.type=="chatroom") {
                    //个人主页，收到提示 小组消息 上面那种组合方式真的垃圾下面换种好康的
                    var strVar = "";
                    strVar += "<li>";
                    strVar += "      <div>";
                    strVar += "          <time><span  class=\"label label-primary\">聊天提醒<\/span><\/time> ";
                    strVar += "<span class=\"glyphicon glyphicon-comment\">" + message.content;
                    strVar += "<time><span class=\"glyphicon glyphicon-plus\">附加消息:" + message.extraInfo;
                    strVar += "      <\/div>";
                    strVar += "<\/li>";
                    $(".messages").append(strVar);
                }
            })
        }).then( function () {
            "use strict";

            // define variables
            var items = document.querySelectorAll(".timeline li");

            // check if an element is in viewport
            // http://stackoverflow.com/questions/123999/how-to-tell-if-a-dom-element-is-visible-in-the-current-viewport
            function isElementInViewport(el) {
                var rect = el.getBoundingClientRect();
                return (
                    rect.top >= 0 &&
                    rect.left >= 0 &&
                    rect.bottom <=
                    (window.innerHeight || document.documentElement.clientHeight) &&
                    rect.right <= (window.innerWidth || document.documentElement.clientWidth)
                );
            }

            function callbackFunc() {
                for (var i = 0; i < items.length; i++) {
                    if (isElementInViewport(items[i])) {
                        items[i].classList.add("in-view");
                    }
                }
            }

            // listen for events
            window.addEventListener("load", callbackFunc);
            window.addEventListener("resize", callbackFunc);
            window.addEventListener("scroll", callbackFunc);
        })
    })
</script>

<%--Vue查找模块--%>
    <script>
        var vm = new Vue({
            el:'#show_blogs',
            data:{
                blogs:[],
                search:"",
                test:"success"
            },
            created(){
                this.$http.get('https://jsonplaceholder.typicode.com/posts/').then(function(data){
                    this.blogs = data.body.slice(0,10);
                    console.log(this.blogs);
                })
            },
            methods: {
                goComment(title, body) {
                    commentblog = {title: title, content: body}
                    localStorage.setItem('blog', JSON.stringify(commentblog))
                    window.location.href = "pages/Writer/Comment.jsp"
                }
            },
            computed:{
              filterblogs:function(){
                  return this.blogs.filter((blog)=>{
                      return blog.title.match(this.search);
                  })
              }
            }
        })
        Vue.filter("snippet",function(value){
            return value.slice(0,100)+"..."
        })
    </script>
    <style>
        /*个人文章美化*/
        h2{
            font-size: larger;
        }
        #show_blogs{
            margin: 0 auto;
        }
        .blog{
            /*经测试 该颜色会和背景颜色叠加导致较差的效果*/
            /*background: #8c8c8c;*/
            width: 98%;
            padding: 20px;
            margin: 20px 0;
            border: 3px solid #ddd;
            border-radius: 4px;
            box-shadow: 5px 5px 5px #333333;
        }
    </style>
    <!-- 提示表单样式js动态消失-->
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="static/script/chat_remainder.js"></script>
    <script src="https://eqcn.ajz.miesnfu.com/wp-content/plugins/wp-3d-pony/live2dw/lib/L2Dwidget.min.js"></script>
    <!--小帅哥： https://unpkg.com/live2d-widget-model-chitose@1.0.5/assets/chitose.model.json-->
    <!--萌娘：https://unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json-->
    <!--小可爱（女）：https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json-->
    <!--小可爱（男）：https://unpkg.com/live2d-widget-model-haruto@1.0.5/assets/haruto.model.json-->
    <!--初音：https://unpkg.com/live2d-widget-model-miku@1.0.5/assets/miku.model.json-->
    <!-- 上边的不同链接显示的是不同的小人，这个可以根据需要来选择 下边的初始化部分，可以修改宽高来修改小人的大小，或者是鼠标移动到小人上的透明度，也可以修改小人在页面出现的位置。 -->
    <script>
        /*https://unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json*/
        L2Dwidget.init({
            "model": {
                jsonPath:
                    "https://unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json",
                "scale": 1
            }, "display": {
                "position": "right", "width": 210, "height": 250,
                "hOffset": 0, "vOffset": -20
            }, "mobile": {"show": true, "scale": 0.5},
            "react": {"opacityDefault": 0.8, "opacityOnHover": 0.1}
        });
    </script>
</body>

</html>