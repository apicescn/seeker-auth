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
    </style>
</head>
<body>
<div class="box-body">
    <div class="clearfix">
        <form class="form-horizontal">
            <input id="client_list_repeatApply" name="client_list_repeatApply" type="reset" style="display:none;"/>
            <div class="form-group clearfix">
                <#--<label class="col-md-1  control-label">客户端ID</label>-->
                <div class="col-md-2">
                    <input type="text" class="input-sm form-control" id="client_name" name="client_name"
                           placeholder="请输入客户端ID...">
                </div>
                <#--<label class="col-md-1  control-label">用户名</label>
                <div class="col-md-2">
                    <input type="text" class="input-sm form-control" id="user_list_true_name" name="user_list_true_name"
                           placeholder="请输入用户名...">
                </div>-->
                <button type="button" onclick="client_list_query();" class="btn btn-sm btn-primary"><i
                            class="fa fa-search"></i>搜索
                </button>
                <button type="button" class="btn btn-sm btn-success" onclick="client_list_add();">
                    <i class="fa fa-plus"></i>增加
                </button>
                <button type="button" onclick="client_list_delete('##');" class="btn btn-sm btn-danger"><i
                            class="fa fa-remove"></i>删除
                </button>
                <button type="button" onclick="client_list_reset();" class="btn btn-sm btn-default">重置</button>
            </div>
        </form>
    </div>
    <table id="client_tab" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th><input type="checkbox" title="全选"/></th>
            <th>客户端ID</th>
            <th>服务ID集合</th>
            <th>密钥</th>
            <th>访问域</th>
            <th>oauth授权类型</th>
            <th>角色编码</th>
            <th>初始超期时间</th>
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
    var client_tab;
    var client_param;
    var jsonData;
    // language=JQuery-CSS
    $(function () {
        //不显示
        var url = "${request.contextPath}/api/dto/clientList";
        client_list_setParm();
        client_tab = $("#client_tab").DataTable({
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
                {"data": "clientId"},
                {"data": "clientId"},
                {"data": "resourceIds"},
                {
                    "data": "clientSecret",
                    render: function (data, type, row, meta) {
                        console.log(data)
                        //type 的值  dispaly sort filter
                        //代表，是显示类型的时候判断值的长度是否超过8，如果是则截取
                        //这里只处理了类型是显示的，过滤和排序返回原始数据
                        data = '*************';
                        if (type === 'display') {
                            if (data.length > 8) {
                                return '<span title="' + data + '">' + data.substr(0, 7) + '...</span>';
                            } else {
                                return '<span title="' + data + '>' + data + '</span>';
                            }
                        }
                        return data;
                    }
                },
                {"data": "scope"},
                {"data": "authorizedGrantTypes"},
                {"data": "authorities"},
                {"data": "accessTokenValidity"},
                {"data": "clientId"}
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
             /*   {
                    targets: 3,
                    data: null,
                    orderable: false,
                    render: function (data) {
                        return '*****';
                    }
                },*/
                {
                    "targets": -1,
                    "data": null,
                    orderable: false,
                    "render": function (data) {
                        var data = "'" + data + "'";
                        var btn1 = '<a class="btn btn-xs btn-warning"  target="modal" modal="hg" onclick="client_list_edit(' + data + ');"><i class="fa fa-edit"></i>修改</a> &nbsp;';
                        var btn2 = '<a class="btn btn-xs btn-danger"  target="modal" modal="hg" onclick="client_list_delete(' + data + ')"><i class="fa fa-remove"></i>删除</a> &nbsp;';
                        return btn1 + btn2;
                    }
                }
            ]
        }).on('preXhr.dt', function (e, settings, data) {
            // 在这里就可以对发送的数据进行重写
            // console.log( e )
            // console.log( setting )
            // console.log( data )
            // console.dir( data.order[0].column )
        }).on('xhr.dt', function (e, settings, json, xhr) {
        });
    });

    //搜索框内容重置
    function client_list_reset() {
        $("input[name='client_list_repeatApply']").click();
    }

    //增加
    function client_list_add() {
        layer.open({
            type: 2, //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层)
            area: ['400px', '600px'],
            title: '新增',
            content: '${request.contextPath}/client/addClient',
            shade: 0,
            /*  btn: ['提交', '取消']
              ,btn1: function(index, layero){
                  var kk=$("#username").val();
                  alert(kk);
              },
              btn2: function(index, layero){
                  layer.closeAll();
              },
              cancel: function(layero,index){
                  layer.closeAll();
              }
  */
        });
    }

    /*编辑*/
    function client_list_edit(obj) {
        var options = {
            url: '${request.contextPath}/api/dto/clientId?clientId=' + obj,
            type: 'get',
            dataType: 'text',
            success: function (res) {
                jsonData = res
                json = JSON.stringify(jsonData);
                console.log(json)
                layer.open({
                    type: 2, //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层)
                    area: ['400px', '600px'],
                    title: '编辑',
                    zIndex: layer.zIndex,
                    content: '${request.contextPath}/client/editClient',
                    shade: 0,
                    success: function (layero, index) {
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
    function client_list_delete(param) {
        var href = "/";
        var title = "<p>警告！ 所选取的数据将会被删除！</p>";
        var cb;
        if (param == "##") {
            var checkNum = $('input:checkbox[class="userCheckbox"]:checked').length;
            var checkVal = [];
            if (checkNum == 0) {
                layer.msg('请选择数据', {time: 3000, icon: 6});
                return;
            }
            $.each($('input:checkbox[class="userCheckbox"]:checked'), function () {
                checkVal.push($(this).val());
            });
            cb = "user_list_delete_data('" + checkVal + "');";
        } else {
            //cb = "client_list_delete_one_data('" + param + "');";
            client_list_delete_one_data(param);
        }
        //$("#smModal").attr("action", href).attr("callback", cb).find(".modal-body").html(title).end().modal("show");
        //$("#smModal").modal("show");
    }

    function user_list_delete_data(checkVal) {

        var options = {
            url: '/admin/user/delete?checkVal=' + checkVal,
            type: 'post',
            dataType: 'text',
            success: function (data) {
                if (data > 0) {
                    client_tab.draw(false);
                    alertMsg("<p>成功删除" + data + "条记录</p>", "success");
                } else {
                    alertMsg("<p>删除失败</p>", "danger");
                }
            }
        };
        $.ajax(options);
    }

    //单个删除
    function client_list_delete_one_data(id) {
        layer.confirm('确认删除此条数据？', {
            btn: ['确认', '取消']
        }, function () {
            var options = {
                url: '${request.contextPath}/api/dto/delete?clientId=' + id,
                type: 'get',
                dataType: 'text',
                success: function (success) {
                    client_tab.draw(false);
                    layer.msg('删除成功', {time: 2000, icon: 6});
                }
            };
            $.ajax(options);
        });

    }

    //搜索
    function client_list_query() {
        client_list_setParm();
        client_tab.settings()[0].ajax.data = client_param;
        client_tab.ajax.reload();
    }
    //动态拼接参数
    function client_list_setParm() {
        var client_name = $("#client_name").val() == '' ? null : $("#client_name").val();
        console.log(client_name)
        client_param = {
            "pageIndex": 1,
            "pageSize": 10
        }
        if (client_name != null) {
            client_param["clientId"] = client_name
        }
        /*client_param = client_name == null ? {
            "pageIndex": 1,
            "pageSize": 10
        } : {
            "clientId": client_name,
            "pageIndex": 1,
            "pageSize": 10
        };*/
    }
</script>
</body>
</html>