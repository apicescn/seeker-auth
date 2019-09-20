<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>统一认证中心</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${request.contextPath}/dist/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${request.contextPath}/dist/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${request.contextPath}/dist/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${request.contextPath}/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${request.contextPath}/dist/css/skins/all-skins.min.css">
    <#--table分页样式-->
    <link rel="stylesheet" href="${request.contextPath}/dist/plugins/datatables/jquery.dataTables.css"/>

    <link rel="stylesheet" href="${request.contextPath}/dist/plugins/layui/css/layui.css"/>

    <!--http://aimodu.org:7777/admin/index_iframe.html?q=audio&search=#-->
    <style type="text/css">
        html {
            overflow: hidden;
        }
    </style>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${request.contextPath}/dist/plugins/ie9/html5shiv.min.js"></script>
    <script src="${request.contextPath}/dist/plugins/ie9/respond.min.js"></script>
    <![endif]-->
    <style>
        .dataTables_info {
            display: none;
        }

        .dataTables_wrapper.no-footer .dataTables_scrollBody {
            border-bottom: 1px solid #f1f1f1 !important;
        }

        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: left !important;
        }
    </style>
</head>
<body>
<div class="box-body">
    <div class="clearfix">
        <form class="form-horizontal">
            <input id="api_list_repeatApply" name="api_list_repeatApply" type="reset" style="display:none;"/>
            <div class="form-group clearfix">
                <#--<label class="col-md-1  control-label" style="float: left">Api名称</label>-->
                <div class="col-md-2">
                    <input type="text" class="input-sm form-control" id="api_name" name="api_name"
                           placeholder="请输入Api名称...">
                </div>
                <div class="col-md-2">
                    <select class="input-sm form-control" id="service_name" placeholder="请选择服务...">
                        <option value="">请选择服务...</option>
                        　　　　
                        <option>选择A</option>
                        　　　　
                        <option>选择B</option>
                        　　　　
                        <option>选择C</option>
                        　　</select>
                </div>
                <#--<label class="col-md-1  control-label">用户名</label>
                <div class="col-md-2">
                    <input type="text" class="input-sm form-control" id="user_list_true_name" name="user_list_true_name"
                           placeholder="请输入用户名...">
                </div>-->
                <button type="button" onclick="api_list_query();" class="btn btn-sm btn-primary"><i
                            class="fa fa-search"></i>搜索
                </button>
                <button id="delete" type="button" onclick="api_list_delete('##');" class="btn btn-sm btn-danger"><i
                            class="fa fa-remove"></i>删除
                </button>
                <button type="button" onclick="api_list_reset();" class="btn btn-sm btn-default">重置</button>
                <button type="button" class="btn btn-sm btn-success" style="float: right; margin-right: 20px"
                        onclick="api_refresh();">
                    <i class="fa fa-refresh"></i>刷新
                </button>
            </div>
        </form>
    </div>
    <table id="api_tab" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th ><input type="checkbox" title="全选"/></th>
            <th>ID</th>
            <th width="10%">标签</th>
            <th>名称</th>
            <th>服务ID号</th>
            <th>url地址</th>
            <th>请求类型</th>
            <th>API服务描述</th>
            <th>操作</th>
        </tr>
        </thead>
    </table>
</div>

<!-- jQuery 2.2.3 -->
<script src="${request.contextPath}/dist/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${request.contextPath}/dist/plugins/datatables/jquery.dataTables.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${request.contextPath}/dist/bootstrap/js/bootstrap.min.js"></script>
<!-- Slimscroll -->
<script src="${request.contextPath}/dist/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${request.contextPath}/dist/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${request.contextPath}/dist/js/app.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${request.contextPath}/dist/js/demo.js"></script>

<!--tabs-->
<script src="${request.contextPath}/dist/js/app_iframe.js"></script>
<script src="${request.contextPath}/dist/plugins/layui/layui.all.js"></script>

<script type="text/javascript">
    var api_tab;
    var client_param;
    var jsonData;
    // language=JQuery-CSS
    var resultData;//动态获取下拉列表数据
    $(function () {
        var htmls = '<option value="">请选择服务...</option>'; //全局变量
        $.ajax({
            url: "${request.contextPath}/api/dto/serviceName",
            type: "post",
            dataType: "json",
            contentType: "application/json",
            async: false,//这得注意是同步
            success: function (result) {
                resultData = result;
                for (var x in resultData) {
                    htmls += '<option value = "' + resultData[x] + '">' + resultData[x] + '</option>'
                }
                $("#service_name").html(htmls);
            }
        });
        //不显示
        var url = "${request.contextPath}/api/api/list";
        api_list_setParm();
        api_tab = $("#api_tab").DataTable({
            "fnDrawCallback": function () {
            },
            "dom": '<"top"i>rt<"bottom"flp><"clear">',
            "ordering": false,//是否排序
            "processing": true,
            "searching": false,
            "serverSide": true,   //启用服务器端分页
            "order": [[5, "asc"]],//默认排序字段
            "bInfo": true,
            "bAutoWidth": false,
            "scrollX": true,
            "scrollCollapse": false,
            /*fixedColumns:   {
                leftColumns: 0,
                rightColumns: 1
            },*/
            "language": {

                "sProcessing": "正在加载中......",

                "sLengthMenu": "每页显示 _MENU_ 条记录",

                "sZeroRecords": "对不起，查询不到相关数据！",

                "sEmptyTable": "表中无数据存在！",

                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",

                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",

                "oPaginate": {

                    "sFirst": "首页",

                    "sPrevious": "上一页",

                    "sNext": "下一页",

                    "sLast": "末页",

                },

            },
            "ajax": {
                "url": url, "data": client_param, "type": "post",
                "dataSrc": function (json) {
                    var data = json.data == null ? '' : json.data;
                    return data;
                }
            },
            "columns": [
                {"data": "id"},
                {"data": "id"},
                {
                    "data": "label",
                    render: function (data, type, row, meta) {
                        if (type === 'display') {
                            if (data.length > 30) {
                                return '<span title="' + data + '">' + data.substr(0, 29) + '...</span>';
                            } else {
                                return data;
                            }
                        }
                        return data;
                    }
                },
                {
                    "data": "name",
                    render: function (data, type, row, meta) {
                        //type 的值  dispaly sort filter
                        //代表，是显示类型的时候判断值的长度是否超过8，如果是则截取
                        //这里只处理了类型是显示的，过滤和排序返回原始数据
                        if (type === 'display') {
                            if (data.length > 15) {
                                return '<span title="' + data + '">' + data.substr(0, 14) + '...</span>';
                            } else {
                                return data/*'<span title="' + data + '>' + data + '</span>'*/;
                            }
                        }
                        return data;
                    }
                },
                {"data": "serviceId"},
                {
                    "data": "url",
                    render: function (data, type, row, meta) {
                        if (type === 'display') {
                            if (data.length > 15) {
                                return '<span title="' + data + '">' + data.substr(0, 14) + '...</span>';
                            } else {
                                return data;
                            }
                        }
                        return data;
                    }
                },
                {"data": "method"},
                {
                    "data": "description",
                    render: function (data, type, row, meta) {
                        if (type === 'display') {
                            if (data.length > 15) {
                                return '<span title="' + data + '">' + data.substr(0, 14) + '...</span>';
                            } else {
                                return data;
                            }
                        }
                        return data;
                    }
                },
                {"data": "id"}
            ]
            ,
            "columnDefs": [
                {
                    targets: 0,
                    data: null,
                    orderable: false,
                    render: function (data) {
                        return '<input type="checkbox" class="userCheckbox" value="' + data + '"/>';
                    }
                },
                {
                    "targets": -1,
                    "data": null,
                    orderable: false,
                    "render": function (data) {
                        var data = "'" + data + "'";
                        var btn1 = '<a class="btn btn-xs btn-warning"  target="modal" modal="hg" onclick="api_details(' + data + ');"><i class="fa fa-edit"></i>详情</a> &nbsp;';
                        /*var btn2 = '<a class="btn btn-xs btn-danger"  target="modal" modal="hg" onclick="api_list_delete(' + data + ')"><i class="fa fa-remove"></i>删除</a> &nbsp;';*/
                        return btn1;
                    }
                }
            ]
        }).on('preXhr.dt', function (e, settings, data) {
        }).on('xhr.dt', function (e, settings, json, xhr) {
        });
    });

    //搜索框内容重置
    function api_list_reset() {
        $("input[name='api_list_repeatApply']").click();
    }

    //api刷新
    function api_refresh() {
        var index1 = layer.load(1, {shade: false}); //0代表加载的风格，支持0-2
        var options = {
            url: '${request.contextPath}/api/api/refresh/auto',
            type: 'post',
            dataType: 'text',
            success: function (res) {
                console.log('刷新成功')
                window.setTimeout(function () {
                    layer.close(index1);
                }, 500);
            }
        };
        $.ajax(options);
    }

    /*查看详情*/
    function api_details(obj) {
        var options = {
            url: '${request.contextPath}/api/api/id?id=' + obj,
            type: 'get',
            dataType: 'text',
            success: function (res) {
                jsonData = res
                json = JSON.stringify(jsonData);
                console.log(json)
                layer.open({
                    type: 2, //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层)
                    area: ['700px', '300px'],
                    title: '详情页',
                    zIndex: layer.zIndex,
                    content: '${request.contextPath}/api/details',
                    shade: 0,
                    success: function (layero, index) {
                        //成功
                    }
                });
            }
        };
        $.ajax(options);
    }

    /*根据ClientId获取行数据*/
    function getDataByClientId(id) {
        var options = {
            url: '${request.contextPath}/api/dto/clientId?clientId=' + id,
            type: 'get',
            dataType: 'text',
            success: function (data) {
                jsonData = data
                return jsonData;
            }
        };
        $.ajax(options);
    }

    //删除
    function api_list_delete(param) {
        layer.tips('暂不支持', '#delete');
        return;
    }

    /**
     * 搜索
     */
    function api_list_query() {
        api_list_setParm();
        api_tab.settings()[0].ajax.data = client_param;
        api_tab.ajax.reload();
    }

    //动态拼接参数
    function api_list_setParm() {
        var api_name = $("#api_name").val() == '' ? null : $("#api_name").val();
        var serviceName = $('#service_name option:selected').val();
        client_param = {
            "pageIndex": 1,
            "pageSize": 10
        }
        if (api_name != null) {
            client_param["name"] = api_name
        }
        if (serviceName!='') {
            client_param["serviceId"] = serviceName
        }
    }
</script>
</body>
</html>