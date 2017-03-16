<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <g:layoutTitle default="Weather Map Admin"/>
    </title>

    <asset:stylesheet src="application.css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <asset:javascript src="application.js"/>
    <asset:javascript src="lib/angular.min.js" />
    <asset:javascript src="lib/angular-resource.min.js" />

    <g:layoutHead/>
</head>
<body class="admin">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Maryland Weather App</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <g:if test="${session.admin}">
                        <li class="active"><g:link action="logout">
                            Logout <i class="fa fa-sign-out"></i>
                        </g:link></li>
                    </g:if>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <g:layoutBody/>
            </div>
        </div>
    </div>
</body>
</html>
