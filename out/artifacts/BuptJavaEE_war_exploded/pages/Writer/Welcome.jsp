<%--
  Created by IntelliJ IDEA.
  User: tigers
  Date: 2021/7/26
  Time: 10:09 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>CodePen - Airplanes.</title>
    <link rel="stylesheet" href="../../static/css/Welcome-style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<div class="content">
    <div class="loading">Loading</div>
    <div class="trigger"></div>
    <div class="section">

        <h1>写在前面的话</h1>
        <h3>如何进行一次愉快的协同写作？</h3>
        <p>You've probably forgotten what these are.</p>
        <!-- 		<div class="phonetic">/ ˈɛərˌpleɪn /</div> -->
        <div class="scroll-cta">Scroll</div>
    </div>

    <div class="section right">
        <h2>这有点像飞机的一次远航...</h2>
    </div>

    <div class="ground-container">
        <div class="parallax ground"></div>
        <div class="section right">
            <h2>..想象飞机离开地面</h2>
            <p>正如协作时不同的思维飞入云端.</p>
        </div>

        <div class="section">
            <h2>别急，让思维飞一会</h2>
            <p>这是奇妙而美好的!</p>
        </div>

        <div class="section right">
            <h2>别忘了团队的力量</h2>
            <p>你们可是坐在同一架飞机上!</p>
        </div>
        <div class="parallax clouds"></div>
    </div>

    <div class="blueprint">
        <svg width="100%" height="100%" viewbox="0 0 100 100">
            <line id="line-length" x1="10" y1="80" x2="90" y2="80" stroke-width="0.5"></line>
            <path id="line-wingspan" d="M10 50, L40 35, M60 35 L90 50" stroke-width="0.5"></path>
            <circle id="circle-phalange" cx="60" cy="60" r="15" fill="transparent" stroke-width="0.5"></circle>
        </svg>
        <div class="section dark ">
            <h2>让我们来看看本质的东西吧.</h2>
            <p>协同写作是指由两个或两个以上的人共同创作作品的过程。协同写作集中了多人的智慧，可以减少写作时间、提高作品质量...</p>
        </div>
        <div class="section dark length">
            <h2>信息生产者也成了信息传播者和信息接收者.</h2>
            <p>多从团队中获取灵感，能让你不会那么早就江郎才尽，产出更多内容.</p>
        </div>
        <div class="section dark wingspan">
            <h2>明确要做什么.</h2>
            <p>这是你们的航向，决定你们将要去哪儿.</p>
        </div>
        <div class="section dark phalange">
            <h2>注意沟通交流</h2>
            <p>这是你们的机翼，确保你们的航程平稳而顺利</p>
        </div>
        <div class="section dark">
            <h2>思维的火花</h2>
            <p>那是你们最好的引擎</p>
        </div>
        <!-- 		<div class="section"></div> -->
    </div>
    <div class="sunset">
        <div class="section"></div>
        <div class="section end">
            <h2>好了.</h2>
            <br/>
            <h1><a href="My.jsp">起航吧.</a></h1>
            <ul class="credits">
                <li>Plane model by <a href="https://poly.google.com/view/8ciDd9k8wha" target="_blank">Google</a></li>
                <li>Animated using <a href="https://greensock.com/scrolltrigger/" target="_blank">GSAP ScrollTrigger</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- partial -->
<script src='https://codepen.io/ste-vg/pen/zBVakw.js'></script>
<script src='https://unpkg.com/three@0.117.1/build/three.min.js'></script>
<script src='https://unpkg.com/three@0.117.1/examples/js/loaders/OBJLoader.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.3.1/gsap.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.3.1/ScrollTrigger.min.js'></script>
<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/16327/DrawSVGPlugin3.min.js'></script><script  src="../../static/script/Welcome-script.js"></script>

</body>
</html>
