/**
 * Created by yj-mbp on 15/11/1.
 */
$(function(){
    $('#packageInfoList_dg').datagrid({
        toolbar: '#pacakgeInfo_dg_toolbar',
        singleSelect: true,
        rownumbers: true,
        fit: true,
        fitColumns: true
    });
});

var operations = {create:0,edit:1,remove:2};
var op;
function newPackageAttr(){
    $('#packageInfo_save_dialog').dialog('open').dialog('setTitle','添加属性');
    $('#role_info_form').form('clear');
    op = operations.create;
}

function editPackageAttr(){
    if(!params()['attrSelected'])
        alert("请选择要修改的属性");
    $('#packageInfo_save_dialog').dialog('open').dialog('setTitle','编辑属性');
    $('#role_info_form').form('clear');
    op = operations.edit;
}

function deletePackageAttr(){
    $.messager.confirm('Confirm', '请确认是否删除该属性？', function (n) {
        if (n) {
            op = operations.remove;
            attrsLoader.deleteAttr(params()['attrSelected']);
        }
    });
}

function getReady(){
    var params = new Object();
    //catalogId是全局变量 保存当前选中的节点的id
    //同时packageId和catalogId对于数据包是一致的
    params['packageId'] = catalogId;
    loadPackageInfo(params);
    loadDirectorInfo(params);
    loadPackageData(params);

    //绑定属性列表的选中事件
    $('#packageInfoList_dg').datagrid({
        onSelect: function(index, record){
            params['attrSelected'] = record.attributeName;
            $("input[name='attributeName']").prop('value', record.attributeName);
            $("input[name='attributeValue']").prop('value', record.attributeValue);
        }
    });

    $("#package_data").treegrid({
        onClickRow:function(row){
            params['dataSelected'] = row;
            console.log(row);
        }
    });
    attrsLoader.packageId = catalogId;
    attrsLoader.loadAttr();
    return function () {
        return params;
    }
}

//加载数据包信息
function loadPackageInfo(params){
        $("#pPackages").combobox({
            url:'/package/loadPackageInfo?id='+params['packageId'],
            valueField:"id",
            textField:"name",
            onSelect:function(rec){
                //上游数据包
                var upstreamIds = params['upstreamIds'];
                if(upstreamIds){
                    upstreamIds.push(rec.id);
                }else{
                    upstreamIds = new Array();
                    upstreamIds.push(rec.id);
                }
                params['upstreamIds'] = upstreamIds;
            }
        })

}

//加载负责人信息
function loadDirectorInfo(params){
        $("#packageDirectors").combobox({
            url:'/package/loadPackageUser',
            valueField:'id',
            textField:'userName',
            onSelect:function(rec){
                params['directorId'] = rec.id;
            }
        });
}

//加载数据包的数据信息
function loadPackageData(params){
    $("#package_data").treegrid({
        url:"/package/getPackageData?packageId="+params['packageId']
    })
}

//保存选中的上游数据包和负责人
function savePackageInfo(){
    var data = {
        packageId:params()['packageId'],
        upstreamIds:JSON.stringify(params()['upstreamIds']),
        directorId:params()['directorId']
    };
    console.log(data);
    if(data.directorId == null){
        $.messager.show({title: '操作结果', msg: '请至少选择一个负责人'});
    }else{
        $.get("/package/savePackageInfo",data);
        $.messager.show({title: '操作结果', msg: '操作成功'});
    }
}

//属性操作
attrsLoader = {
    attrs:new Object(),
    dataJson:new Array(),
    packageId:"",
    loadAttr:function(){
        //清空数据表
        var item = $('#packageInfoList_dg').datagrid('getRows');
        if (item) {
            for (var i = item.length - 1; i >= 0; i--) {
                var index = $('#packageInfoList_dg').datagrid('getRowIndex', item[i]);
                $('#packageInfoList_dg').datagrid('deleteRow', index);
            }
        }

        $.get("/package/getAttr",{packageId:this.packageId},function(result){
            var result = eval(result);
            attrsLoader.attrs = result;
            for(var key in attrsLoader.attrs){
                var obj = {"attributeName":key,"attributeValue":attrsLoader.attrs[key]};
                attrsLoader.dataJson.push(obj);
            }
            attrsLoader.display();
        });
    },
    addAttr:function(key, value){
        if(attrsLoader.attrs == null)
            attrsLoader.attrs = new Object();
        attrsLoader.attrs[key] = value;
        attrsLoader.updateAttr();
    },
    deleteAttr:function(key){
        delete attrsLoader.attrs[key];
        attrsLoader.updateAttr()
    },
    updateAttr:function(){
        $.get('/package/saveAttr',{attrStr:JSON.stringify(attrsLoader.attrs),packageId:attrsLoader.packageId});
        $.messager.show({title: '操作结果', msg: '操作成功'});

        $("#package_info_form").form('clear');
        attrsLoader.loadAttr();
    },
    display:function(){
        $("#packageInfoList_dg").datagrid('loadData',attrsLoader.dataJson);
    }
}

function saveAttribute(){
    var key = $("input[name='attributeName']")[0].value;
    var value = $("input[name='attributeValue']")[0].value;
    switch (op){
        case operations.create:
            attrsLoader.addAttr(key, value);
            break;
        case operations.edit:
            attrsLoader.addAttr(key, value);
            break;
        case operations.remove:
            attrsLoader.deleteAttr(key);
            break;
    }
    $('#packageInfo_save_dialog').dialog('close');
}


//数据项操作
function newPackageData(){
    $("#package_data_name").show();
    $("#package_data_type").show();
    $("#package_data_value").hide();
    $('#packageData_dialog').dialog('open').dialog('setTitle','添加数据项');
    $('#packageData_form').form('clear');
    op = operations.create;
}

function editPackageData(){
    var row = params()['dataSelected'];
    if(!row){
        alert("请选择要修改的数据项");
        return;
    }

    $("#package_data_name").show();
    $("#package_data_type").show();
    $("#package_data_value").hide();
    $("input[name='name']").prop('value',row.name);
    $("input[name='type']").prop('value',row.type);

    $('#packageData_dialog').dialog('open').dialog('setTitle','修改数据项');
    op = operations.edit;
}

function deletePackageData(){
    var row = params()['dataSelected'];
    if(row && !row.isLeaf){
        alert("请从叶子节点开始删除");
        return;
    }
    $.messager.confirm('Confirm', '请确认是否删除该属性？', function (n) {
        if (n) {
            op = operations.remove;
            dataOperator.remove();
        }
    });
}

function savePackageData(){
    var name = $("#packageData_form input[name='name']").val();
    var type = $("#packageData_form input[name='type']").val();
    var value = $("#packageData_form input[name='value']").val();
    switch (op){
        case operations.create:
            dataOperator.insert(name, type, value);
            break;
        case operations.edit:
            dataOperator.edit(name, type);
            break;
    }
    $('#packageData_dialog').dialog('close');
}

dataOperator = {
    tree:$("#package_data"),
    insert:function(name,type,value){
        var selected = params()['dataSelected'];
        var pid;
        if(!selected){
            pid = 0;//创建根节点
        } else{
            pid = selected.id;
        }
        $.get("/package/savePackageData",{name:name, type:type, packageId:params()['packageId'], pid:pid},function(result){
            loadPackageData(params());
            $.messager.show({title: '操作结果', msg: '操作成功'});
            clearSelection();
        });
    },
    edit:function(name,type){
        var row = params()['dataSelected'];
        if(!row){
            alert("请选择要修改的数据项");
            return;
        }
        var id = row.id;
        var pid = row.parentId;
        var packageId = row.packageId;
        $.get("/package/editPackageData", {id:id, name:name, type:type, packageId:packageId, parentId:pid},
            function (result) {
                loadPackageData(params());
                $.messager.show({title: '操作结果', msg: '操作成功'});
                clearSelection();
            });
    },
    remove:function(){
        var row = params()['dataSelected'];
        if(!row){
            alert("请选择要删除的数据项");
            return;
        }
        var id = row.id;
        $.get("/package/deletePackageData", {id:id},function(result){
            loadPackageData(params());
            $.messager.show({title: '操作结果', msg: '操作成功'});
            clearSelection();
        });
    }
};

function clearSelection(){
    delete params()['dataSelected'];
}