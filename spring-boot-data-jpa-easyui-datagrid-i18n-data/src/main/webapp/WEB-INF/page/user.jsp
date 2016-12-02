<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="title"/></title>
<%@include file="/WEB-INF/page/common.jsp"%>
<script type="text/javascript">
	// 页面加载完毕之后才能写jQuery的代码

	$(function() {
		// 声明并缓存easyui组件
		var userDatagrid = $("#userDatagrid");
		var userDialog = $("#userDialog");
		var userForm = $("#userForm");
		var userSearchForm = $("#userSearchForm");

		// 表单的添加方法
		userForm.form({
			url : "/user/save",
			onSubmit : function() {
				// 在表单提交前，做一下验证
				return userForm.form("validate");
			},
			//data是后台save方法返回的json字符串
			success : function(data) {
				// 需要自己把字符串转变成json对象，easyiui没有提供转换
				data = $.parseJSON(data);
				// 判断保存是否成功
				if (data.meta.success) {
					// 成功就关掉对话框
					userDialog.dialog("close");
					//重新加载最新的数据
					userDatagrid.datagrid("reload");
				} else {
					$.messager.alert('<spring:message code="errorMessage"/>', data.meta.message, 'error');
				}
			}
		});

		// 创建操作data-url的json对象，把页面所有linkbutton组件的操作都统一添加到此对象上面
		var urlObjectUser = {
			addUser : function() {
				// 清空对话框里面的表单内容，防止原来的数据有缓存
				userForm.form("clear");
				// 打开对话框，修改标题，然后居中
				userDialog.dialog("open").dialog("setTitle", '<spring:message code="addUser"/>');
			},
			updateUser : function() {
				// 获取选中行数据
				var selectedRow = userDatagrid.datagrid("getSelected");
				// 判断是否选中行
				if (!selectedRow) {
					$.messager.alert('<spring:message code="operationMessage"/>', '<spring:message code="editSelete"/>', "info");
					return;
				}
				// 清空对话框里面的表单内容
				userForm.form("clear");
				//修改的时候才查询上级null数据
				$('#parentCombotree').combotree({
					url : '${ctx}/user/getTreeByParent'
				});
				// 使用easyui的form组件load方法，只要是相同的名称，会自动回显数据
				userForm.form("load", selectedRow);
				// 打开对话框
				userDialog.dialog("open").dialog("setTitle", '<spring:message code="editUser"/>');
			},
			removeUser : function() {
				// 获取选中行数据
				var row = userDatagrid.datagrid("getSelected");
				// 判断是否选中行
				if (!row) {
					$.messager.alert('<spring:message code="operationMessage"/>', '<spring:message code="deleteSelete"/>', "info");
					return;
				}
				$.get("/user/delete?id=" + row.id, function(data) {
					if (data.meta.success) {//删除成功
						userDatagrid.datagrid("reload");
					} else {
						$.messager.alert('<spring:message code="errorMessage"/>', data.meta.message, 'error');
					}
				}, 'json');
			},
			reloadUser : function() {//调用重新加载数据的方法
				userDatagrid.datagrid("reload");
			},
			saveUser : function() {//提交表单
				userForm.submit();
			},
			cancelUser : function() {//关闭对话框
				userDialog.dialog("close");
			},
			searchUser : function() {//简单搜索
				userDatagrid.datagrid("load", {
					q : $("input[name=q]").val()
				});
			}
		};

		// 对页面所有linkbutton组件，统一监听
		$("a[data-url]").on("click", function() {
			// 获取linkbutton的data-url信息 
			var url = $(this).data("url");
			//如果此目标方法是存在的并且linkbutton组件没有被禁用，才可以点击
			if (urlObjectUser[url] && !$(this).linkbutton('options').disabled) {
				//调用动态的方法
				urlObjectUser[url]();
			}
		});
	});
</script>
</head>
<body>
	<!-- 数据表格组件 -->
	<table id="userDatagrid" class="easyui-datagrid" url="/user/json" title="<spring:message code='title'/>" fit="true" border="false"
		fitColumns="true" singleSelect="true" pagination="true" rownumbers="true" toolbar="#userDatagridToolbar">
		<thead>
			<tr>
				<th data-options="field:'id'"><spring:message code='id'/></th>
				<th data-options="field:'name',width:10"><spring:message code='name'/></th>
				<th data-options="field:'age',width:10"><spring:message code='age'/></th>
			</tr>
		</thead>
	</table>
	<!-- 数据表格组件工具栏 -->
	<div class="easyui-layout" fit="true">
		<div id="userDatagridToolbar" region="north" border="false"
			style="border-bottom: 1px solid #ddd; height: 32px; padding: 2px 5px; background: #fafafa;">
			<div style="float: left;">
				<a data-url="addUser" href="javascript:void(0)" class="easyui-linkbutton c1" iconCls="icon-add"><spring:message code='add'/></a> <a
					data-url="updateUser" href="javascript:void(0)" class="easyui-linkbutton c2" iconCls="icon-edit"><spring:message code='edit'/></a> <a
					data-url="removeUser" href="javascript:void(0)" class="easyui-linkbutton c3" iconCls="icon-remove"><spring:message code='remove'/></a>
				<a data-url="reloadUser" href="javascript:void(0)" class="easyui-linkbutton c4" iconCls="icon-reload"><spring:message code='reload'/></a>
			</div>
			<div style="float: right">
				<form method="post">
					<spring:message code='q'/>：<input name="q" size="10" /> <a data-url="searchUser" href="javascript:void(0)"
						class="easyui-linkbutton c5" iconCls="icon-search"><spring:message code='search'/></a>
				</form>
			</div>
		</div>
	</div>
	<!-- 添加/编辑用户对话框 -->
	<div id="userDialog" class="easyui-dialog" style="width: 360px; height: 260px; padding: 10px 20px"
		title="管理用户对话框" data-options="closed:true,modal:true,buttons:'#userDialogButtons',resizable:true">
		<form id="userForm" method="post">
			<input type="hidden" name="id" />
			<div class="ftitle"><spring:message code='userinfo'/></div>
			<table align="center">
				<tr>
					<td><spring:message code='name'/>:</td>
					<td><input class='easyui-validatebox' required="true" type='text' name='name'></input></td>
				</tr>
				<tr>
					<td><spring:message code='age'/>:</td>
					<td><input class='easyui-numberbox' required="true" min="20" max="80" precision="0" type='text'
						name='age'></input></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 对话框按钮组件 -->
	<div id="userDialogButtons">
		<a data-url="saveUser" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"
			style="width: 90px"><spring:message code='save'/></a> <a data-url="cancelUser" href="javascript:void(0)" class="easyui-linkbutton c7"
			iconCls="icon-cancel" style="width: 90px"><spring:message code='cancel'/></a>
	</div>
</body>
</html>