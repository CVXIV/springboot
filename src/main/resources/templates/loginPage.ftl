<!DOCTYPE html>
<html lang="zh">
<#assign basePath=request.contextPath>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">-->
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.css">
    <link rel="stylesheet" href="${basePath}/css/form-elements.css">
    <link rel="stylesheet" href="${basePath}/css/style.css">

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="${basePath}/img/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="${basePath}/img/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="${basePath}/img/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${basePath}/img/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${basePath}/img/apple-touch-icon-57-precomposed.png">
</head>
<script type="text/javascript">
</script>
<body id="main">
<div class="top-content">
    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>StringBoot</strong>登陆界面</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>请输入用户名和密码:</h3>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-lock"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" method="post" action="${basePath}/login/checkUser" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="username">用户名</label>
                                <input type="text" name="username" placeholder="用户名..."
                                       class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="password">密码</label>
                                <input type="password" name="password" placeholder="密码..."
                                       class="form-password form-control" id="form-password">
                            </div>
                            <button type="submit" class="btn">登陆!</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 social-login">
                    <h3>...or login with:</h3>
                    <div class="social-login-buttons">
                        <a class="btn btn-link-2" href="#">
                            <i class="fa fa-facebook"></i> Facebook
                        </a>
                        <a class="btn btn-link-2" href="#">
                            <i class="fa fa-twitter"></i> Twitter
                        </a>
                        <a class="btn btn-link-2" href="#">
                            <i class="fa fa-google-plus"></i> Google Plus
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/js/jquery-1.11.0.min.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>
<script src="${basePath}/js/jquery.backstretch.js"></script>
<script src="${basePath}/js/scripts.js"></script>

<!--[if lt IE 10]>
<script src="${basePath}/js/placeholder.js"></script>
<![endif]-->
<script type="text/javascript">
    jQuery(document).ready(function() {
        $("#main").backstretch([
            "${basePath}/img/1.jpg",
            "${basePath}/img/2.jpg",
            "${basePath}/img/3.jpg"
        ], {duration: 3000, fade: 750});
    });
</script>
</body>
</html>