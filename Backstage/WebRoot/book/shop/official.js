﻿	var datagrid;
    var newTxl;
    var searchWin;
    var editTxl;
$(function(){
	
//表格开始
	 datagrid=$('#datagrid').datagrid({
        title: '积分商城',
        iconCls: 'icon-save',
        methord: 'post',
        url: '../Backstage/news/selMallBackstage.action',
        pageSize: 20,
        pageList:[5,10,15,20],
        frozenColumns: [[
	                { field: 'ck', checkbox: true }
				]],
        columns: [[
					{ field: 'title', title: '标题', width: 150,align:'center' },
					{ field: 'number', title: '剩余份数', width: 150,align:'center'},
					{ field: 'integral', title: '需要积分', width: 150,align:'center'},
					{ field: 'bife', title: '商品简介', width: 150,align:'center'},
					{ field: 'logo', title: '图片', width: 150,align:'center',formatter:function(val){return '<image src="'+val+'" width="50" height = 50/>';}},
				]],
        fit:true,
        pagination: true,
        rownumbers: true,
        fitColumns: true,
        singleSelect: false,
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler:newTxl
        } , '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: deleteTxl
        }, '-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler:edit
        }, '-',  {
            text: '查找',
            iconCls: 'icon-search',
            handler:openSearch
        }]
    });
    //表格结束
    
//add对话框开始
	 newTxl = $('#newTxl').dialog({
        closed: true,
        modal: true,
        toolbar: [{
            text: '保存',
            iconCls: 'icon-save',
            handler:saveTxl
        }, '-', {
            text: '关闭',
            iconCls: 'icon-no',
            handler: function () {
        	newTxl.dialog('close');
            }
        }]
    });
//add对话框结束
	 
	 
	//edit对话框开始
	 editTxl = $('#editTxl').dialog({
	        closed: true,
	        modal: true,
	        toolbar: [{
	            text: '保存',
	            iconCls: 'icon-save',
	            handler:updateTxl
	        }, '-', {
	            text: '关闭',
	            iconCls: 'icon-no',
	            handler: function () {
	        	editTxl.dialog('close');
	            }
	        }]
	    });
	 
	//edit对话框结束
	 

//查找窗口开始
 $('#btn-search,#btn-search-cancel').linkbutton();
   searchWin = $('#search').window({
        closed: true,
        modal: true
    });   
});
//查找窗口结束

//add开始
function newTxl(){
	newTxl.dialog('open');
	}

 function saveTxl(){
	  $('#addForm').form('submit', {
	        url:'../Backstage/news/addMall.action',
	        onSubmit: function () {
	            return $(this).form('validate');
	        },
	        success: function (data) {
	        	$('#addForm').form("clear");
	        	newTxl.dialog('close');
	        	datagrid.datagrid('reload');
	        }
	    });  
  }
//add结束

 //delete开始	
 function deleteTxl(){
	  var rows = datagrid.datagrid('getSelections');
	  var num = rows.length;
	  var ids = [];
	  for (var i = 0; i < rows.length; i++) {
	        ids.push(rows[i].id);
	    }
//批量删除开始
	  if (num >0) { 
		  $.messager.confirm('提示信息', '您确认要删除吗?', function (data) {
	            if (data) {
	                $.ajax({
	                    url: '../Backstage/news/delMallBackstage.action',
	                    type: 'post',
	                    data: {items: ids.join(',')},	  
	                    success: function (data) {
	                    datagrid.datagrid('reload');
	                    }
	                });
	            }
	        });
//批量删除结束
	   } 	
	  else{
		  Msgslide('请先选择要删除的记录!');
	  }
	 
 }

    //delete开始	

//edit开始	
    function edit(){
	var rows = datagrid.datagrid('getSelections');
	var num = rows.length;
    if (num == 0) {
        Msgslide('请选择一条记录进行修改!');
    }
    else if (num > 1) {
        Msgslide('您选择了多条记录,只能选择一条记录进行修改!'); 
    }
    else {
    	var row = datagrid.datagrid('getSelected');
    	  if (row) {
    		 editTxl.dialog('open');
             $("#editForm").form("load", row);
 
    	  }
    }
  
	}
    
    function updateTxl(){
    	$('#editForm').form('submit', {
	        url:'../Backstage/news/addMall.action',	
	        onSubmit: function () {
	            return $(this).form('validate');
	        },
	        success: function (data) {
	        	$('#editForm').form("clear");
	        	editTxl.dialog('close');
	        	datagrid.datagrid('reload');
	        }
	    });  
    	
    }
	//edit开始	
	
	//search开始	
   function openSearch(){
   searchWin.window('open');
   	}
   
   function searchTxl(){
	   var name= $('#name').val();
	   var youxiang= $('#youxiang').val();
	   var phone= $('#phone').val();
	   datagrid=$('#datagrid').datagrid({
	        title: '通讯录',
	        iconCls: 'icon-save',
	        methord: 'post',
	        url: '../Backstage/news/selMallBackstage.action',
	        queryParams: { name: name,youxiang:youxiang},
	        pageSize: 20,
	        pageList:[5,10,15,20],
	        frozenColumns: [[
		                { field: 'ck', checkbox: true }
					]],
	        columns: [[
						{ field: 'title', title: '标题', width: 150,align:'center' },
					{ field: 'number', title: '剩余份数', width: 150,align:'center'},
					{ field: 'integral', title: '需要积分', width: 150,align:'center'},
					{ field: 'bife', title: '商品简介', width: 150,align:'center'},
					{ field: 'logo', title: '图片', width: 150,align:'center',formatter:function(val){return '<image src="'+val+'" width="50" height = 50/>';}},
					]],
	        fit:true,
	        pagination: true,
	        rownumbers: true,
	        fitColumns: true,
	        singleSelect: false,
	        toolbar: [{
	            text: '新增',
	            iconCls: 'icon-add',
	            handler:newTxl
	        } , '-',{
	            text: '删除',
	            iconCls: 'icon-remove',
	            handler: deleteTxl
	        }, '-', {
	            text: '修改',
	            iconCls: 'icon-edit',
	            handler:edit
	        }, '-',  {
	            text: '查找',
	            iconCls: 'icon-search',
	            handler:openSearch
	        }]
	    });
	   
	   searchWin.window('close');
	   $('#searchForm').form("clear");
	  
	   
   }
    //search结束

	//消息提醒
	function Msgslide(msg) {
    $.messager.show({
        title: '提示',
        msg: msg,
        timeout: 3000,
        showType: 'slide'
    });
}
//查询关闭按钮
function closeSearchWindow() {
    searchWin.window('close');
}