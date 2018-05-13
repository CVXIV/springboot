<!DOCTYPE html>
<html lang="zh">
<#setting date_format="yyyy-MM-dd HH:mm:ss">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="../static/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <h2>${msg.username}</h2>
    <h2>${msg.birthday?date}</h2>
</div>
</body>
</html>