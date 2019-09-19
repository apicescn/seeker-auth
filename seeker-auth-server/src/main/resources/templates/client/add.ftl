<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>统一认证中心</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${request.contextPath}/dist/plugins/layui/css/layui.css"/>
    <link href="${request.contextPath}/dist/plugins/layui/css/formSelects-v4.css" rel="stylesheet"/>
    <style>
        .layui-input-block {
            margin-top: 20px !important;
        }
        div[xm-select-skin] .xm-select-title div.xm-select-label>span {
            border: 1px solid rgba(255,255,255,0.15);
        }
        .xm-select-parent .xm-select-title div.xm-select-label>span {
            position: relative;
            padding: 2px 5px;
            background-color: rgba(255,255,255,0.15);
            border-radius: 2px;
            color: #111;
            display: inline-block;
            line-height: 18px;
            height: 18px;
            margin: 2px 5px 2px 0;
            cursor: initial;
            user-select: none;
            font-size: 14px;
            padding-right: 25px;
            -webkit-user-select: none;
        }
    </style>
</head>
<body>
<div>
    <form class="layui-form" id="test">
        <div class="layui-form-item">
            <label class="layui-form-label">客户端ID</label>
            <div class="layui-input-block">
                <input type="text" name="clientId" required lay-verify="required|isExitClientID" placeholder="请输入客户端ID"
                       class="layui-input" id="clientId" style="width:200px"><#--autocomplete="on"-->
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">服务ID</label>
            <div class="layui-input-block" style="width:200px" xm-select-type="1">
                <select name="resourceIds" id="resourceIds"
                        lay-filter="select_resourceIds"
                        xm-select="_resourceIds" xm-select-type="1">
<#--                    <option value=''></option>
                    <option value='xysy-seeker'>xysy-seeker</option>
                    <option value='seeker-web'>seeker-web</option>
                    <option value='seeker-auth-server'>seeker-auth-server</option>-->
                </select>

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密钥</label>
            <div class="layui-input-block">
                <input type="password" name="clientSecret" required lay-verify="required" placeholder="密钥"
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
                <input type="checkbox" name="scope" title="write" value="write" lay-skin="primary">
                <input type="checkbox" name="scope" title="read" value="read" checked lay-skin="primary">
                <#--<input type="checkbox" name="like[dai]" title="发呆" lay-skin="primary">-->
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">授权模式</label>
            <div class="layui-input-block" style="width:200px" xm-select-type="1">
                <select name="authorizedGrantTypes" id="select_authorizedGrantTypes"
                        lay-filter="select_authorizedGrantTypes"
                        xm-select="select_authorizedGrantTypes" xm-select-type="1">
                    <option value=""></option>
                    <option value="password">password</option>
                    <option value="client_credentials">client_credentials</option>
                    <option value="authorization_code">authorization_code</option>
                </select>

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色编码</label>
            <div class="layui-input-block">
                <input type="text" name="authorities" required lay-verify="required" placeholder="角色编码(默认:ADMIN)"
                       class="layui-input" id="authorities" style="width:200px">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">初始超期时间</label>
            <div class="layui-input-block">
                <input type="text" name="accessTokenValidity" required lay-verify="required" placeholder="初始超期时间(单位：毫秒)"
                       class="layui-input" id="accessTokenValidity" style="width:200px">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="form">
                    提交
                </button>
                <button class="layui-btn" id="close">
                    取消
                </button>
            </div>
        </div>
    </form>

    <#--<button id="div">提交</button>-->
</div>
<script src="${request.contextPath}/dist/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${request.contextPath}/dist/plugins/layui/layui.all.js"></script>
<script src="${request.contextPath}/dist/plugins/layui/formSelects-v4.js"></script>
<script type="text/javascript">
    var formSelects = layui.formSelects;
    var exitClientId = false;
</script>
<script type="text/javascript">
    //
    var resultData;
    $(function () {
        var formSelects = layui.formSelects;
        var htmls = '<option value="">请选择</option>'; //全局变量
        $.ajax({
            url: "${request.contextPath}/api/dto/serviceUrl",
            type: "post",
            dataType : "json",
            contentType : "application/json",
            async: false,//这得注意是同步
            success: function (result) {
                resultData = result;
                for(var x in resultData){
                    htmls += '<option value = "' + resultData[x] + '">' + resultData[x]  + '</option>'
                }
                $("#resourceIds").html(htmls);
            }
        });
        formSelects.render('_resourceIds');//需要渲染一下
    })
    /*全选*/
    /*    layui.use(['form','jquery'], function () {
            var form = layui.form;
            var $ = layui.jquery;
            //点击全选, 勾选
            form.on('checkbox(allChoose)', function (data) {
                var child = $(".seach-box input[type='checkbox']");
                child.each(function (index, item) {
                    item.checked = data.elem.checked;
                });
                form.render('checkbox');
            });
        });*/
    layui.use(['form', 'layer', 'jquery', 'table', 'laydate', 'element', 'upload', 'flow'], function () {
        var $$ = layui.jquery;
        var form = layui.form,
            layer = parent.layer == undefined ? layui.layer : parent.layer,
            //layer = layui.layer,
            table = layui.table,
            laydate = layui.laydate,
            element = layui.element,
            upload = layui.upload,
            flow = layui.flow;
        form.render();

        form.on('submit(btn_submit)', function (obj) {
            layer.confirm('确认录入无误吗？', {
                btn: ['确认', '再看看']
            }, function () {
                var getName = JSON.stringify(layui.formSelects.value('select_base_cityname', 'name'));//取值name数组
                layer.msg("你选择的值为：" + getName, {offset: '150px', icon: 1, time: 5000}, function () {
                });
            });
        });

    });
    /*取消*/
    layui.use(['form', 'layedit', 'laydate', 'element', 'jquery'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            $ = layui.jquery;
        $(document).on('click', '#close', function () {
            //layer.msg('hello');
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    });
    layui.use('form', function () {
        var form = layui.form;
        form.verify({
            //判断是否存在clientID
            isExitClientID: function (value, item) {
                var clientId = $("#clientId").val();
                var url = "${request.contextPath}/api/dto/clientId";
                var data = {clientId: clientId};
                $.ajax({
                    type: "get",
                    url: url,
                    async: false,//同步提交。不设置则默认异步，异步的话，最后执行ajax
                    data: data,
                    success: function (ev) {
                        if (ev.success) {
                            layer.tips('客户端ID已存在', '#clientId', {
                                tips: [3, '#0FA6D8'] //还可配置颜色
                            });
                            exitClientId = true;
                            setTimeout(function () {
                                return false;
                            }, 3000);
                        }
                    }
                });
            }
        });
    })
    /*提交*/
    layui.use('form', function () {
        var form = layui.form;
        form.on('submit(form)', function (data) {
            if (!exitClientId) {
                var $ = layui.jquery
                var loading = layer.load(0, {shade: false});

                //获取checkbox[name='scope']的值
                var arr = new Array();
                $("[id='scope']:checkbox").each(function (i) {
                    if ($(this).is(":checked")) {
                        arr[i] = $(this).attr("title");
                    }
                });
                data.field.scope = arr.join(",");//将数组合并成字符串
                var data1 = data.field;
                $.ajax({
                    url: '${request.contextPath}/api/dto/insert',
                    type: 'post',
                    data: data1,
                    success: function (res) {
                        if (res.success) {
                            layer.close(loading);
                            window.parent.location.reload();
                            layer.msg('提交成功');
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }
                    }
                });
                return false;
            } else {
                return false;
            }
        })
    })
</script>
</body>
</html>