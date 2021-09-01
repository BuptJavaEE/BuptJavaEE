<%--
  Created by IntelliJ IDEA.
  User: tigers
  Date: 2021/7/26
  Time: 10:09 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
    <!--  基础路径-->
    <base href="../../">
    <!--  页面样式导入-->
    <link rel="stylesheet" href="static/css/style-welcome.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <style>
        img {
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
        }
    </style>
</head>
<body>
<!-- partial:index.partial.html -->
<!-- google fonts lato -->
<link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>


<a href="pages/Writer/Welcome.jsp#fake" class="enter">Hello</a>
<div class="page">
    <div class="main">



        <!--  Content of page -->
        <header class="clearfix">
            <div class='container'>
                <div class="intro">
                    <a href="pages/Writer/My.jsp"><h1>让创作更加美好</h1></a>
                    <p></p>
                </div>
            </div>
        </header>


        <section class="words">
            <div class="container">
                <h3>协同写作是指由两个或两个以上的人共同创作作品的过程;</h3>

                <p>——百度百科</p>
            </div>
        </section>

        <section  class="clearfix"></section>

        <section class="words">
            <div class="container">
                <h3>协同写作演练过程</h3>

                <p>在真实的协作环境里，每位贡献者都有相同的权限增加、编辑及移除条文。写作的过程成了循环的任务，每次改变都会促使他人做更多改变。当大家有特定目标时，写作会比较容易，也容易使写作作品聚焦；但若没目标或目标很模糊的话，写作就会很难进行，也容易使写作作品难以聚焦。 </p>

                <h2>好的沟通方法非常重要</h2>

                <p>尤其是有不同意见的时候，好的沟通可促进协同写作，并提升写作品质。不好的沟通方法，则可能阻碍意见的交流，甚至推迟、阻碍协同写作的进度，与最终写作产品的品质。 </p>

            </div>
        </section>

        <section  class="clearfix"></section>

        <section class="words">
            <div class="container">
                <p>協同寫作或合作寫作是指由多人一起，而非個人單獨完成的寫作計畫。有些計畫由一位編者或編輯團隊監督，但也有很多計劃是沒人負責的。若能有一位以上的編輯來監督，那麼在格式或內容上，該作品比較有機會採用統一格式，而令前後文較容易形成一致性。若沒有特定的負責人，那麼該作品，在格式或內容上，則較有可能是自由的、不受拘束的呈現。 </p>


            </div>
        </section>
        <!-- / Content of page -->


    </div>
</div>
<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script  src="static/script/script-welcome.js"></script>

</body>
</html>