<#assign queryString=request.queryString!""/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>统一认证中心</title>
    <meta name="description" content="Seeker Cloud Oauth Login Page">
    <!--CSRF TOKEN-->
<#--<meta name="_csrf" content="${_csrf.token}"/>-->
    <!-- default header name is X-CSRF-TOKEN -->
<#--<meta name="_csrf_header" content="${_csrf.headerName}"/>-->
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${request.contextPath}/dist/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${request.contextPath}/dist/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${request.contextPath}/dist/ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${request.contextPath}/dist/AdminLTE/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${request.contextPath}/dist/plugins/iCheck/square/blue.css">
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        Seeker Cloud Oauth
    </div>
<#if queryString?contains("error")>
    <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        用户名或密码错误。
    </div>
</#if>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">统一认证中心</p>

        <form action="${request.contextPath}/login" method="POST">
            <div class="form-group has-feedback">
                <input name="username" type="text" class="form-control" placeholder="用户名">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input name="password" type="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                <#--<div class="checkbox icheck">-->
                        <#--<label>-->
                            <#--<input type="checkbox"> 记住我-->
                        <#--</label>-->
                    <#--</div>-->
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
        </form>

    <#--<a href="#">忘记密码</a><br>-->
    <#--<a href="register.html" class="text-center">注册</a>-->

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery-->
<script src="${request.contextPath}/dist/jquery/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${request.contextPath}/dist/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="${request.contextPath}/dist/plugins/iCheck/icheck.min.js"></script>
<#--<script>-->
<#--    $(function () {-->
<#--        $('input').iCheck({-->
<#--            checkboxClass: 'icheckbox_square-blue',-->
<#--            radioClass: 'iradio_square-blue',-->
<#--            increaseArea: '20%' // optional-->
<#--        });-->
<#--    });-->
<#--</script>-->
</body>
</html>
