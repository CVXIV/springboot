<!DOCTYPE html>
<html lang="zh">
<#assign basePath=request.contextPath>
<head lang="zh">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <script src="${basePath}/js/jquery-1.11.0.min.js"></script>
    <script src="${basePath}/js/bootstrap.min.js"></script>
</head>

<body background="${basePath}/img/back.jpg">
<form method="post" action="${basePath}/login/checkUser">
    <div class="container">
        <div class="form row">
            <div class="form-horizontal col-md-offset-3">
                <h3 class="form-title">LOGIN</h3>
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="Username" name="username" autofocus="autofocus"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="Password" name="password"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success col-lg-12" name="submit">登录</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>