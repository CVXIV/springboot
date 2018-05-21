<!DOCTYPE html>
<html>
<#assign basePath=request.contextPath>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <![endif]-->
    <title>出错了</title>
    <!-- BOOTSTRAP STYLE SHEET -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet" />
    <!-- CUSTOM STYLES -->
    <link href="${basePath}/css/error-style.css" rel="stylesheet" />

</head>
<body>
<div class="header-div">
    <div class="overlay">

        <div class="container">
            <div class="row text-center">
                <div class="col-md-4">
                    <img src="${basePath}/img/logo.jpg" alt="" />
                </div>
                <div class="col-md-8 ">
                    <h1>We Found Error <strong>${status} ! </strong></h1>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- HEADER END -->
<section class="section-text">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h3>Possible Causes Of this Error</h3>
                <ul>
                    <li>${error}
                    </li>
                </ul>
            </div>
            <div class="col-md-3">
                <div class="alert alert-info">
                    In case of emergency you can call us at
                    <strong>13838385438</strong> and reach us at our headquarter located  :
                    <strong>EARTH-CHINA-SHANGHAI</strong>
                </div>
            </div>
            <div class="col-md-3 text-center">
                <h2>Quick Links</h2>
                <hr />
                <a href="${basePath}/login/loginPage" class="btn btn-info">Navigate to Home Page</a>
                <hr />
            </div>
        </div>
    </div>
</section>
<!-- TEXT SECTION END -->
<div class="header-div">
    <div class="overlay">
        <div class="container">
            <div class="row text-center">
                <div class="col-md-12">
                    <h1>Sorry,  <strong>For Inconvenience </strong></h1>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- HEADER DIV REPLICATE END -->
<footer>
    © cvxiv.smart.com
</footer>
<!-- FOOTER SECTION END -->
<!-- REQUIRED SCRIPTS FILES -->
<!-- CORE JQUERY FILE -->
<script src="${basePath}/js/jquery-1.11.0.min.js"></script>
<!-- REQUIRED BOOTSTRAP SCRIPTS -->
<script src="${basePath}/js/bootstrap.min.js"></script>

</body>
</html>