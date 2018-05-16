<!DOCTYPE html>
<html>
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <title>登录成功</title>
</head>

<body>
<p>登陆成功</p>
<p><strong id="result"></strong></p>
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
            $("#result").html("断开连接");
        };
        webSocket.onopen=function (evnt) {
            $("#result").html(evnt.data);
        };
        webSocket.onerror=function () {
            $("#result").html("连接出错");
        };
        webSocket.onmessage=function (evnt) {
            $("#result").html(evnt.data);
        };
    });
</script>
</body>

</html>