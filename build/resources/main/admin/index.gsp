<!doctype html>
<html>
<head>
    <meta name="layout" content="admin_layout"/>
</head>
<body>
    <div class="col-lg-4 col-lg-offset-4 login-form">
        <h2 align="center">Admin Login</h2>

        <g:form action="authenticate">
            <div class="form-group">
                <g:textField name="username" class="form-control" placeholder="Username" required="requried" />
            </div>
            <div class="form-group">
                <g:passwordField name="password" class="form-control" placeholder="Password" required="requried" />
            </div>
            <g:submitButton name="login" value="Login" class="btn btn-block btn-primary" />
        </g:form>

        <br />

        <g:link url="/">
            <i class="fa fa-long-arrow-left"></i> Back to Map
        </g:link>
    </div>

    <asset:javascript src="app/login.js" />
</body>
</html>
