<%--
  Created by IntelliJ IDEA.
  User: tigers
  Date: 2021/7/26
  Time: 10:19 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>CodePen - Parallax Photo Carousel</title>
    <!--  基础路径-->
    <base href="../../">
    <!--  导入css基础设定的包-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel="stylesheet" href="static/css/style-roll.css">
    <!--    vue-基础包导入-->
    <script src="static/vue/vue.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
    <!--    vue-resource 包导入-->
    <script src="https://cdn.bootcdn.net/ajax/libs/vue-resource/1.5.3/vue-resource.js"></script>
</head>
<body>
<!-- partial:index.partial.html -->
<%--导航栏--%>
<%@include file="headleader.jsp"%>
<!--  滚动展示文章-->
<divr class="stage" id="show-blogs">
    <p>{{test}}</p>
    <br/>
    <br/>
    <h1 align="center">文章总览</h1>
    <!--    搜索功能-->
    <input type="text" v-model="search" placeholder="搜索">

    <!--    <blog class="single-blog" v-for="blog in filterblogs">-->
    <!--        <h2 v-rainbow>{{blog.title}}</h2>-->
    <!--    </blog>-->
    <divr class="contain">
        <divr class="ring">

            <divr class="img" >  <blog class="single-blog" v-for="blog in filterblogs.slice(0,1)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>
            <divr class="img" >  <blog class="single-blog" v-for="blog in filterblogs.slice(1,2)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>
            <divr class="img" >  <blog class="single-blog" v-for="blog in filterblogs.slice(2,3)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>
            <divr class="img" >  <blog class="single-blog" v-for="blog in filterblogs.slice(3,4)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>
            <divr class="img" >  <blog class="single-blog" v-for="blog in filterblogs.slice(4,5)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>
            <divr class="img" >  <blog class="single-blog" v-for="blog in filterblogs.slice(5,6)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>
            <divr class="img">  <blog class="single-blog" v-for="blog in filterblogs.slice(6,7)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>
            <divr class="img">  <blog class="single-blog" v-for="blog in filterblogs.slice(7,8)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>
            <divr class="img">  <blog class="single-blog" v-for="blog in filterblogs.slice(8,9)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>
            <divr class="img">  <blog class="single-blog" v-for="blog in filterblogs.slice(9,10)">
                <h2 v-rainbow>{{blog.title}}</h2>
                <br/>
                <article>{{blog.content|snippet}}</article>
            </blog>
            </divr>



        </divr>
    </divr>
</divr>


<!--vue实现交互功能-->

<script type="text/javascript">
    //文章获取
    var vm = new Vue({
        el:'#show-blogs',
        data:{
            test:"connect-success",
            blogs:[],
            search:""
        },
        created(){
            this.$http.get("showblogsevlet")
                .then(function (data) {
                    this.blogs = data.body.slice(0,10);
                    console.log(this.blogs);
                })
        },
        computed:{
            filterblogs:function () {
                return this.blogs.filter((blog)=>{
                    return blog.title.match(this.search)
                })
            }
        }

    })

    //    自定义指令 改变样式
    //    颜色随机
    Vue.directive('rainbow',{
        bind(el,binding,vnode){
            el.style.color="#"+Math.random().toString(16).slice(2,8);
        }

    })
    //宽度调节
    Vue.directive('theme',{
        bind(el,binding,vnode){
            if (binding.value=='wide'){
                el.style.maxWidth="1260px"
            }else if(binding.value=='narrow'){
                el.style.maxWidth="560px"
            }
        }
    })

    //    自定义过滤器

    //    防止内容过长
     Vue.filter("snippet",function(value){
    if(value)
        return value.slice(0,100)+"..."
     })
</script>

<!-- 引入动画文件 -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.1/gsap.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/zepto/1.2.0/zepto.min.js'></script>
<script  src="static/script/script-roll.js"></script>

<!--最后微调一下各个格式-->
<style>
    h2{
        font-size: larger;
    }
    #show-blogs{
        margin: 0 auto;
    }
    blog{
        padding: 20px;
        margin: 20px 0;
        box-sizing: border-box;
    }
</style>

</body>
</html>

