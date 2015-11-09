﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
    <script type="text/javascript">

        var userlist = <%=request.getAttribute("userJson")%>;
        var UrlConfig = {
            SysUserList: '<%=request.getContextPath() %>/user/list',
            SysUserPage: '<%=request.getContextPath() %>/user/page',
            SysUserAdd: '<%=request.getContextPath() %>/user/add',
            SysUserUpdate: '<%=request.getContextPath() %>/sys/user/update',
            SysUserDelete: '<%=request.getContextPath() %>/sys/user/delete',
            SysUserResetPassword: '<%=request.getContextPath() %>/user/resetPassword',
            SysRoleList: '<%=request.getContextPath() %>/role/list',
            SysGroupList: '<%=request.getContextPath() %>/group/groupList'
        };
        var url;
        var roles = [
            {roleId:1,roleName:'系统管理员'},
            {roleId:2,roleName:'数据区管理员'},
            {roleId:3,roleName:'工程师'},
            {roleId:4,roleName:'阅览者'}
        ];

        function roleFormatter(value){
            for(var i=0;i<roles.length;i++){
                if(roles[i].roleId==value)
                    return roles[i].roleName;
            }
            return value;
        }
        $(function(){
            var lastIndex;
            $('#tb').datagrid({
                toolbar:[{
                    text:'添加用户',
                    iconCls:'icon-add',
                    handler:function(){
                        $('#tb').datagrid('endEdit',lastIndex);
                        $('#tb').datagrid('appendRow',{
                            userName:'',
                            password:'',
                            roleId:'',
                            groupId:''
                        });
                        lastIndex=$('#tb').datagrid('getRows').length-1;
                        $('#tb').datagrid('selectRow',lastIndex);
                        $('#tb').datagrid('beginEdit',lastIndex);
                    }
                },'-',{
                    text:'删除用户',
                    iconCls:'icon-remove',
                    handler:function(){
                        var row=$('#tb').datagrid('getSelected');
                        if(row){
                            var index=$('#tb').datagrid('getRowIndex',row);
                            $('#tt').datagrid('deleteRow',index);
                        }
                    }
                },'-',{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:function(){
                        $('#tb').datagrid('acceptChanges');
                    }
                },'-',{
                    text:'撤销',
                    iconCls:'icon-undo',
                    handler:function(){
                        $('#tb').datagrid('rejectChanges');
                    }
                }]
            })
        });

        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#dg').datagrid('validateRow', editIndex)){
                var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
                var productname = $(ed.target).combobox('getText');
                $('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
                $('#dg').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickRow(index){
            if (editIndex != index){
                if (endEditing()){
                    $('#dg').datagrid('selectRow', index)
                            .datagrid('beginEdit', index);
                    editIndex = index;
                } else {
                    $('#dg').datagrid('selectRow', editIndex);
                }
            }
        }
        function append(){
            if (endEditing()){
                $('#dg').datagrid('appendRow',row);
                editIndex = $('#dg').datagrid('getRows').length-1;
                $('#dg').datagrid('selectRow', editIndex)
                        .datagrid('beginEdit', editIndex);
            }
        }
        function removeit(){
            if (editIndex == undefined){return}
            $('#dg').datagrid('cancelEdit', editIndex)
                    .datagrid('deleteRow', editIndex);
            editIndex = undefined;
        }
        function accept(){
            if (endEditing()){
                $('#dg').datagrid('acceptChanges');
            }
        }
        function reject(){
            $('#dg').datagrid('rejectChanges');
            editIndex = undefined;
        }

    </script>
    <title>系统管理</title>
</head>
<body>

<div style="margin:20px 0;"></div>
<div id="login_user_info">欢迎你：${currentUser.userName}. <a href="<%=request.getContextPath() %>/logout">退出</a></div>
<div id="p" class="easyui-panel" title="系统管理" iconCls='icon-edit' style="width:800px;height:650px;padding:10px;">
    <div  class="easyui-tabs" style="width:770px;height:600px">
        <div title="用户管理" style="padding:10px">
            <table id="dg" class="easyui-datagrid" style="width:700px;height:540px"
                   data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				data:userlist,
				method: 'get',
				onClickRow: onClickRow,
				rownumbers:true,
				pagination:true
			">
                <thead>
                <tr>
                    <th data-options="field:'userName',width:150,editor:{
                        type:'text',
                        options:{
                            required:true
                        }
                    }">用户名称</th>
                    <th data-options="field:'password',width:150,editor:'button'">重置密码</th>
                    <th data-options="field:'roleId',width:185,
						formatter:function(value,row){
							return row.roleName;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'roleId',
								textField:'roleName',
								method:'get',
								url:UrlConfig.SysRoleList,
								required:true
							}
						}">用户角色</th>

                    <th data-options="field:'groupId',width:185,
						formatter:function(value,row){
							return row.groupName;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'groupId',
								textField:'groupName',
								method:'get',
								url:UrlConfig.SysGroupList,
							}
						}">用户群组</th>
                </tr>
                </thead>
            </table>

            <div id="tb" style="height:auto">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销</a>
            </div>

        </div>
        <div title="群组管理" style="padding:10px"></div>
    </div>
</div>


<%--<div class="easyui-layout" style="width:100%;height:650px;">
    <div data-options="region:'north'" style="height:150px"></div>
    <div data-options="region:'center',title:'系统管理',iconCls:'icon-ok'">
        <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
            <div title="用户管理"  style="padding:10px">
                <table id="userlist" class="easyui-datagrid" title="编辑用户信息",style="width:650px;height:500px" data-options="
                    iconCls: 'icon-edit',
				    singleSelect: true,
				    url: UrlConfig.SysUserList,
				    method:'get',
				    onClickCell: onClickCell">
                    <thead>
                    <tr>
                        <th data-options="field:'itemId',width:50px">序号</th>
                        <th data-options="field:'userName',width:150px,editor:'textbox'">用户名</th>
                        <th data-options="field:'password',width:150px,editor:'textbox'">输入新密码</th>
                        <th data-options="field:'roleId',width:100px,formatter:function(value,row){
                            return row.roleName;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'roleId',
                                textField:'roleName',
                                method:'get',
                                url:UrlConfig.SysRoleList,
                                required:true
                            }
                        }">用户角色</th>
                        <th data-options="field:'groutId',width:150px,formatter:function(value,row){
                            return row.groupName;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'groupId',
                                textField:'groupName',
                                method:'get',
                                url:UrlConfig.SysGroupList,
                                required:true
                            }
                        }">用户角色</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div title="群组管理" style="padding:5px">
               <ul class="easyui-tree" data-options="url:'UrlConfig.SysGroupList',method:'get',animate:true,dnd:true"></ul>
            </div>
        </div>
    </div>
</div>--%>

<%--<script type="text/javascript">

    var UrlConfig = {
        SysUserList: '<%=request.getContextPath() %>/user/list',
        SysUserPage: '<%=request.getContextPath() %>/user/page',
        SysUserAdd: '<%=request.getContextPath() %>/user/add',
        SysUserUpdate: '<%=request.getContextPath() %>/sys/user/update',
        SysUserDelete: '<%=request.getContextPath() %>/sys/user/delete',
        SysUserResetPassword: '<%=request.getContextPath() %>/user/resetPassword',
        SysRoleList: '<%=request.getContextPath() %>/role/listNoRoot',
        SysGroupList: '<%=request.getContextPath() %>/group/groupList'
    };
    var url;

    function newUser(){
        $('#role_save_dialog').dialog('open').dialog('setTitle','添加角色');
        $('#role_info_form').form('clear');
        url = UrlConfig.SysRoleAdd;
    }
    function editUser(){
        var row = $('#rolelist_dg').datagrid('getSelected');

        if (row){
            $('#role_save_dialog').dialog('open').dialog('setTitle','编辑角色');
            $('#role_info_form').form('load',row);
            url = UrlConfig.SysRoleUpdate;
        } else {
            alert("请选择角色");
        }
    }
    function saveUser(){
        $('#role_info_form').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
                result = eval('(' + result + ')');
                if (result.successful) {
                    $('#role_save_dialog').dialog('close');        // close the dialog
                    $('#rolelist_dg').datagrid('reload');    // reload the user data
                    $.messager.show({ title: '操作结果', msg: '操作成功' });
                } else {
                    $.messager.show({ title: '操作结果', msg: result.msg });
                }
            }
        });
    }

    function destroyUser(){
        var row = $('#rolelist_dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','请确认是否删除该角色?',function(r){
                if (r){
                    $.post(UrlConfig.SysRoleDelete, {roleId:row.roleId}, function(result){
                        $.messager.show({ title: '操作结果', msg: '操作成功' });
                        $('#rolelist_dg').datagrid('reload');
                    },'json');
                }
            });
        } else {
            alert('请选择角色');
        }
    }

    function openPriv() {
        var row = $('#rolelist_dg').datagrid('getSelected');
        if (! row) {
            alert('请选择角色');
            return;
        }

        $('#menuWinRoleName').text(row.roleName);
        $('#role_menu_dialog').dialog('open');

        $('#et').tree({
            url: UrlConfig.SysRoleMenu + '?roleId=' + row.roleId,
            animate: true,
            checkbox: true,
            lines: true
        });
    }

    function getChecked(){

        var s = '';
        var nodes = $('#et').tree('getChecked');
        for(var i = 0; i < nodes.length; i++){

            if ($('#et').tree('isLeaf', nodes[i].target)) {
                if (s != '') {
                    s += ',';
                }
                s += nodes[i].id;
            }

        }

        return s;
    }

    function saveRoleMenu() {
        var checkedMenus = getChecked();

        var row = $('#rolelist_dg').datagrid('getSelected');

        var url = UrlConfig.SysRoleSaveMenu;
        var data = {roleId: row.roleId, menuId: checkedMenus};

        $.post(
                url,
                data,
                function(result) {
                    if (result.successful) {
                        $.messager.show({title: '操作结果',msg: '操作成功'});
                        $('#role_menu_dialog').dialog('close');
                    }
                },
                'json');
    }

</script>--%>

</body>
</html>