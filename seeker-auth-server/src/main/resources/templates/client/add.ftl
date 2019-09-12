<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>统一认证中心</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${request.contextPath}/dist/plugins/layui/css/layui.css"/>
    <style>
        .layui-input-block{
            margin-top: 20px!important;
        }
    </style>
</head>
<body>
<div>
<form class="layui-form" id="test">
    <div class="layui-form-item">
        <label class="layui-form-label">客户端ID</label>
        <div class="layui-input-block">
            <input type="text" name="title" required  lay-verify="required" placeholder="请输入客户端ID"
                    class="layui-input" id="clientId" style="width:200px"><#--autocomplete="on"-->
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">服务ID</label>
        <div class="layui-input-block">
            <input type="text" name="title" required  lay-verify="required" placeholder="服务ID"
                   class="layui-input" id="resourceIds" style="width:200px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密钥</label>
        <div class="layui-input-block">
            <input type="text" name="title" required  lay-verify="required" placeholder="密钥"
                   class="layui-input" id="clientSecret" style="width:200px">
        </div>
    </div>
<#--    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="on" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">辅助文字</div>
    </div>-->
    <#--<div class="layui-form-item">
        <label class="layui-form-label">选择框</label>
        <div class="layui-input-block">
            <select name="quiz" lay-search>
                <option value="">请选择</option>
                <optgroup label="城市记忆">
                    <option value="你工作的第一个城市">你工作的第一个城市？</option>
                </optgroup>
                <optgroup label="学生时代">
                    <option value="你的工号">你的工号？</option>
                    <option value="你最喜欢的老师">你最喜欢的老师？</option>
                </optgroup>
            </select>
        </div>
    </div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">访问域</label>
        <div class="layui-input-block">
            <input type="checkbox" name="like[write]" title="write" value="write" lay-skin="primary">
            <input type="checkbox" name="like[read]" title="read" value="read" checked lay-skin="primary">
            <#--<input type="checkbox" name="like[dai]" title="发呆" lay-skin="primary">-->
        </div>
    </div>
    <div class="layui-form-item">
    <label class="layui-form-label">oauth授权类型</label>
    <div class="layui-input-block" style="width:200px">
        <select name="quiz" lay-search >
            <option value="">请选择</option>
            <option value="client_credentials">client_credentials</option>
            <option value="password">password</option>
           <#-- <optgroup label="请选择">
                <option value="client_credentials">client_credentials</option>
                <option value="password">password</option>
            </optgroup>-->
          <#--  <optgroup label="学生时代">
                <option value="password">password</option>
                <option value="你最喜欢的老师">你最喜欢的老师？</option>
            </optgroup>-->
        </select>
    </div>
</div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色编码</label>
        <div class="layui-input-block">
            <input type="text" name="title" required  lay-verify="required" placeholder="角色编码"
                   class="layui-input" id="authorities" style="width:200px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">初始超期时间</label>
        <div class="layui-input-block">
            <input type="text" name="title" required  lay-verify="required" placeholder="初始超期时间"
                   class="layui-input" id="accessTokenValidity" style="width:200px">
        </div>
    </div>
    <#--<div class="layui-form-item">
        <label class="layui-form-label">开关</label>
        <div class="layui-input-block">
            <input type="checkbox" name="switch" lay-skin="switch" lay-text="开启|关闭">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男">
            <input type="radio" name="sex" value="女" title="女" checked>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label" >文本域</label>
        <div class="layui-input-block">
            <textarea lay-verify="required" name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>-->
</form>
<#--<button id="div">提交</button>-->
</div>
<script src="${request.contextPath}/dist/plugins/layui/layui.all.js"></script>
<script>

</script>
</body>
</html>