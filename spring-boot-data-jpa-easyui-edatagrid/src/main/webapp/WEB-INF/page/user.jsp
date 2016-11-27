<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<%@include file="/WEB-INF/page/common.jsp"%>
<!-- 额外添加的jquery.edatagrid.js -->
<script type="text/javascript" src="${ctx}/static/easyui/jquery.edatagrid.js"></script>
<script type="text/javascript">
	// 页面加载完毕之后才能写jQuery的代码
	$(function() {
		$('#userDatagrid').edatagrid({
			url : '/user/json',
			saveUrl : '/user/save',
			updateUrl : '/user/save',
			destroyUrl : '/user/delete',
			onError : function(index, data) {
				$.messager.alert('错误提示', data.msg, 'error');
			}
		});
	});
</script>
</head>
<body>
	<!-- 数据表格组件 -->
	<table id="userDatagrid" title="用户管理" fit="true" border="false" fitColumns="true" singleSelect="true"
		pagination="true" rownumbers="true" toolbar="#userDatagridToolbar"
		data-options="onSave:function(){
		  $('#userDatagrid').edatagrid('reload');
		},
		destroyMsg:{
			norecord:{// 在没有记录选择的时候执行
				title:'警告',
				msg:'没有选择要删除的行!!!'
		},
			confirm:{// 在选择一行的时候执行		
			  title:'确定',
				msg:'你真的要删除吗?'
			}
		}">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true">编号</th>
				<th field="name" width="50" sortable="true" editor="{type:'validatebox',options:{required:true}}">名称</th>
				<th field="age" width="50" sortable="true"
					editor="{type:'numberbox',options:{required:true,min:20,max:80,precision:0}}">年龄</th>
			</tr>
		</thead>
	</table>
	<!-- 数据表格组件工具栏 -->
	<div class="easyui-layout" fit="true">
		<div id="userDatagridToolbar" region="north" border="false"
			style="border-bottom: 1px solid #ddd; height: 32px; padding: 2px 5px; background: #fafafa;">
			<div style="float: left;">
				<a href="javascript:;" onclick="javascript:$('#userDatagrid').edatagrid('addRow')"
					class="easyui-linkbutton c1" iconCls="icon-add">添加</a> <a href="javascript:;"
					onclick="javascript:$('#userDatagrid').edatagrid('saveRow')" class="easyui-linkbutton c2"
					iconCls="icon-save">保存</a> <a href="javascript:;"
					onclick="javascript:$('#userDatagrid').edatagrid('destroyRow')" class="easyui-linkbutton c3"
					iconCls="icon-remove">删除</a> <a href="javascript:;"
					onclick="javascript:$('#userDatagrid').edatagrid('cancelRow')" class="easyui-linkbutton c4"
					iconCls="icon-cancel">取消</a><a href="javascript:;"
					onclick="javascript:$('#userDatagrid').edatagrid('reload')" class="easyui-linkbutton c5"
					iconCls="icon-reload">刷新</a>
			</div>
			<div style="float: right">
				<form method="post">
					关键字：<input name="q" size="10" /> <a href="javascript:;"
						onclick="javascript:$('#userDatagrid').edatagrid('load', {q : $('input[name=q]').val()});"
						class="easyui-linkbutton c5" iconCls="icon-search">搜索</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>