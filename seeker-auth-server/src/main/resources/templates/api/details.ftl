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

        <div class="layui-form-item ">
            <div class="layui-inline drag-mc-pane">
                <ui>
                    <li>
                        <label class="layui-form-label drag-mc-pane" draggable="false">ID:</label>
                        <div class="layui-input-inline drag-mc-pane">
                            <input type="text"  id="id" autocomplete="off" class="layui-input" style="border-style: none; width: 200px">
                        </div>
                    </li>
                    <li>
                        <label class="layui-form-label drag-mc-pane" draggable="false">服务ID号:</label>
                        <div class="layui-input-inline drag-mc-pane">
                            <input type="text"  id="serviceId" autocomplete="off" class="layui-input" style="border-style: none; width: 200px">
                        </div>
                    </li>
                    <li>
                        <label class="layui-form-label drag-mc-pane" draggable="false">请求类型:</label>
                        <div class="layui-input-inline drag-mc-pane">
                            <input type="text"  id="method" autocomplete="off" class="layui-input" style="border-style: none; width: 200px">
                        </div>
                    </li>
                    <li>
                        <label class="layui-form-label drag-mc-pane" draggable="false">服务描述:</label>
                        <div class="layui-input-inline drag-mc-pane">
                            <input type="text"  id="description" autocomplete="off" class="layui-input" style="border-style: none; width: 200px">
                        </div>
                    </li>
                </ui>
            </div>
            <div class="layui-inline drag-mc-pane">
                <ui>
                    <li>
                        <label class="layui-form-label drag-mc-pane" draggable="false">名称:</label>
                        <div class="layui-input-inline drag-mc-pane">
                            <input type="text"  id="name" autocomplete="off" class="layui-input" style="border-style: none; width: 200px">
                        </div>
                    </li>
                    <li>
                        <label class="layui-form-label drag-mc-pane" draggable="false">标签:</label>
                        <div class="layui-input-inline drag-mc-pane">
                            <input type="text"  id="label" autocomplete="off" class="layui-input" style="border-style: none; width: 200px">
                        </div>
                    </li>
                    <li>
                        <label class="layui-form-label drag-mc-pane" draggable="false">url地址:</label>
                        <div class="layui-input-inline drag-mc-pane">
                            <input type="text"  id="url" autocomplete="off" class="layui-input" style="border-style: none; width: 200px">
                        </div>
                    </li>
                    <li>
                        <label class="layui-form-label drag-mc-pane" draggable="false"></label>
                        <div class="layui-input-inline drag-mc-pane">
                            <input type="text"  autocomplete="off" class="layui-input" style="border-style: none; width: 200px">
                        </div>
                    </li>
                </ui>
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
    $(function(){
        var parent_json = eval('(' + parent.json + ')');
        var _json = eval('(' + parent_json + ')');
        var data = _json.data
        $("#id").val(data.id);
        $("#name").val(data.name);
        $("#serviceId").val(data.serviceId);

        $("#label").val(data.label);
        $("#method").val(data.method);
        $("#url").val(data.url);

        $("#description").val(data.description);

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

</script>
</body>
</html>