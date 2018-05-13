<!DOCTYPE html>
<html>
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <title>跨域测试</title>
</head>

<body>
<button id="test">测试</button>
<p id="result"></p>
<script type="text/javascript" src="${basePath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
    $(function() {
        $("#test").on("click", function() {
            $.ajax({
                url: "restful",
                type: "get",
                data:{
                    "id":"1"
                },
                dataType: "json",
                success: function(data) {
                    $("#result").html(data.birthday);
                }
            });
        });
    });
</script>
</body>

</html>