<!DOCTYPE html>
<html lang="zh">
<#assign basePath=request.contextPath>
<head lang="zh">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <script src="${basePath}/js/jquery-1.11.0.min.js"></script>
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        #autoCode,#email {
            width: 200px;
            margin: 0 auto;
        }
    </style>
    <title>验证码测试</title>
</head>

<body>
<div class="container">
    <div class="col-lg">
        <div class="input-group">
            <input id="email" type="text" class="form-control" name="email" placeholder="邮箱地址" />
            <button class="btn btn-default"  type="button" id="getCode">获取验证码</button>
        </div>
        <div class="input-group">
            <input id="autoCode" type="text" class="form-control" name="authCode" placeholder="输入验证码" />
            <button class="btn btn-default"  type="button" id="login">登录</button>
        </div>
    </div>
<div
<!-- /.row -->
<script type="text/javascript">
    $(function() {
        $("#getCode").bind("click",function () {
            $.ajax({
                url: "getAuthCode",
                type: "get",
                data:{"email":$("#email").val()},
                dataType: "json",
                success: function(data) {
                    alert(data);
                }
            });
        });
        $("#login").bind("click",function () {
            $.ajax({
                url: "checkAuthCode",
                type: "get",
                data:{"code":$("#autoCode").val(),"email":$("#email").val()},
                dataType: "json",
                success: function(data) {
                    alert(data);
                }
            });
        });
    });
</script>
</body>

</html>