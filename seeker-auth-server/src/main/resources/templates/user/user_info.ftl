<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>用户登录信息</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <div class="content-wrapper">
        <!-- Main content -->
            <div class="callout callout-info">
                <h4>用户登录信息</h4>
                <a th:text="${user}">${user}</a>
                <#--<a href="${request.contextPath}/logout">登出</a>-->
            </div>
    </div>
</div>
</body>
</html>
