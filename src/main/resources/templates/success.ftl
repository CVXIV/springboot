<!DOCTYPE html>
<html>
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <title>登录成功</title>
</head>

<body>
<p>连接状态：<strong id="status"></strong></p>
<p>对话信息：<strong id="result"></strong></p>
<button type="button" id="logout">注销</button>
<button type="button" id="download">下载</button>
<script type="text/javascript" src="${basePath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
    $(function() {
        var address="ws://"+window.location.host+"/webSocketPush";
        var webSocket;
        if ('WebSocket' in window) {
            webSocket = new WebSocket(address);
        } else if ('MozWebSocket' in window) {
            webSocket = new MozWebSocket(address);
        }
        webSocket.onclose=function () {
            $("#status").html("连接已断开");
        };
        webSocket.onopen=function (evnt) {
            $("#status").html("已成功连接");
            $("#result").html(evnt.data);
        };
        webSocket.onerror=function () {
            $("#result").html("连接出错");
        };
        webSocket.onmessage=function (evnt) {
            $("#result").html(evnt.data);
        };

        $("#logout").bind("click",function () {
            window.location="${basePath}/login/logout";
        });

        $("#download").bind("click",function () {
            window.location="${basePath}/file/download";
        });
    });
</script>
</body>

</html>