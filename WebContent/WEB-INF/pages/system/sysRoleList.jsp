<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../../common.jsp"%>
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		sysRoleModalListener();
		initUserByRoleList();
		initSysUserList();
		initRoleList();
		initRolePermissionList();
		initNotGrantRolePermissionList();
		$("#sysRoleForm").validate({
			rules : {
				roleName : {
					required : true,
					maxlength : 20
				},
				status : {
					required : true
				},
				roleCode : {
					required : true,
					maxlength : 20
				},
				roleDesc : {
					maxlength : 200
				}
			},
			submitHandler : function(form) {
				$.ajax({
					url : "${ctx}sysRoleController/addOrUpdate.do",
					type : "POST",
					data : $("#sysRoleForm").serialize(),
					async : false,
					success : function(result) { //表单提交后更新页面显示的数据
						var ret = eval("(" + result + ")");
						if (ret && ret.header["success"]) {
							layer.alert(ret.header["msg"], {
								icon : 6
							});
							searchList('#role_table_list');
							$('#sysRoleModal').modal('hide');
						} else {
							layer.alert(ret.header["msg"], {
								icon : 5
							});
						}
					}
				});
			}
		})
	});
	function openAddOrEditSysRole(openType) {
		if (openType == 2) {//编辑
			var jqGridIds = $("#role_table_list")
					.jqGrid('getGridParam', 'selarrrow');
			if (jqGridIds.length == 0) {
				layer.alert('请选择数据', {
					icon : 0
				});
				return;
			} else if (jqGridIds.length > 1) {
				layer.alert('只能选择一条数据，不能多选重置', {
					icon : 0
				});
				return;
			}
			var selObj = $("#role_table_list").jqGrid('getRowData', jqGridIds[0]);
			var id = selObj.id;
			var roleName = selObj.roleName;
			var status = selObj.status;
			var roleCode = selObj.roleCode;
			var roleDesc = selObj.roleDesc;
			$("#sysRoleId").val(id);
			$("#roleName").val(roleName);
			$("#status").val(status);
			$("#roleCode").val(roleCode);
			$("#roleDesc").val(roleDesc);
		}
		$('#sysRoleModal').modal('show');
	}
	//对话框监听器
	function sysRoleModalListener() {
		$('#sysRoleModal').on('hide.bs.modal', function () {
			$("#sysRoleId").val("");
			$("#roleName").val("");
			$("#status").val("1");
			$("#roleCode").val("");
			$("#roleDesc").val("");
		});
	}
	function initRoleList(){
		$.jgrid.defaults.styleUI = "Bootstrap";
		$("#role_table_list").jqGrid({
			url : "${ctx}sysRoleController/list.do",
			datatype : "json",
			mtype : "POST",
			caption : "系统角色列表",
			height : 200,
			autowidth : true,
			shrinkToFit : true,
			multiselect : true,
			viewrecords : false,
			multiboxonly : true,
			postData : $.serializeObject($("#searchFrom")),
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : "#pager_list",
			colNames : [ "id", "角色名称", "角色编码", "状态", "从属角色ID", "角色描述" ],
			colModel : [ {
				name : "id",
				index : "id",
				hidden : true
			}, {
				name : "roleName",
				index : "roleName",
				sortable : false
			}, {
				name : "roleCode",
				index : "roleCode",
				sortable : false
			}, {
				name : "status",
				index : "status",
				formatter : formatterUseStatus,
				unformat : unformatUseStatus,
				sortable : false
			}, {
				name : "parentId",
				index : "parentId",
				sortable : false
			}, {
				name : "roleDesc",
				index : "roleDesc",
				sortable : false
			} ],
			gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			},
			onSelectRow : function(id) {
				//选择角色的时候根据角色加载用户列表数据
				var roleId = $("#role_table_list").jqGrid('getRowData', id).id;
				$("#user_by_role_table_list").jqGrid('setGridParam', {
					url : '${ctx}sysUserController/roleUserList.do',
					datatype : 'json',
					postData : {
						"roleList[0].id" : roleId
					}, //发送数据  
					page : 1
				}).trigger("reloadGrid"); //重新载入
				//选择角色的时候根据角色加载已拥有权限列表数据
				$("#permission_by_role_table_list1").jqGrid('setGridParam', {
					url : '${ctx}sysPermissionController/rolePermissionList.do',
					datatype : 'json',
					postData : {
						"roleList[0].id" : roleId,"isGrant":true
					}, //发送数据  
					page : 1
				}).trigger("reloadGrid"); //重新载入
				//选择角色的时候根据角色加载未拥有权限列表数据
				$("#permission_by_role_table_list2").jqGrid('setGridParam', {
					url : '${ctx}sysPermissionController/rolePermissionList.do',
					datatype : 'json',
					postData : {
						"roleList[0].id" : roleId,"isGrant":false
					}, //发送数据  
					page : 1
				}).trigger("reloadGrid"); //重新载入
			},
		});
		$("#role_table_list").jqGrid("navGrid", "#pager_list", {
			edit : false,
			add : false,
			del : false,
			search : false,
			refresh : true
		});
	}
	function initUserByRoleList(){
		$.jgrid.defaults.styleUI = "Bootstrap";
		$("#user_by_role_table_list").jqGrid({
						    url : "#",
							datatype : "json",
							mtype : "POST",
							caption : "该角色下用户列表",
							height : 200,
							autowidth : true,
							shrinkToFit : true,
							multiselect : true,
							viewrecords : false,
							postData : $
									.serializeObject($("#searchFrom")),
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							pager : "#user_by_role_pager_list",
							colNames : [ "id", "用户名", "姓名",
									"手机", "工号", "职位", "部门",
									"状态"],
							colModel : [
									{
										name : "id",
										index : "id",
										hidden : true
									},
									{
										name : "userName",
										index : "userName",
										sortable : false
									},
									{
										name : "realName",
										index : "realName",
										sortable : false
									},
									{
										name : "phone",
										index : "phone",
										sortable : false
									},
									{
										name : "memberId",
										index : "memberId",
										sortable : false
									},
									{
										name : "jobTitle",
										index : "jobTitle",
										sortable : false
									},
									{
										name : "deptName",
										index : "deptName",
										sortable : false
									},
									{
										name : "status",
										index : "status",
										formatter : formatterUseStatus,
										sortable : false
									}],
							gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
							}
						});
		$("#user_by_role_table_list").jqGrid("navGrid", "#user_by_role_pager_list", {
			edit : false,
			add : false,
			del : false,
			search : false,
			refresh : true
		});
	}
	function initSysUserList(){
		$.jgrid.defaults.styleUI = "Bootstrap";
		$("#sysUser_table_list").jqGrid({
						    url : "#",
							datatype : "json",
							mtype : "POST",
							caption : "系统用户列表",
							height : 200,
							autowidth : true,
							shrinkToFit : true,
							multiselect : true,
							viewrecords : false,
							postData : $
									.serializeObject($("#sysUserSearchFrom")),
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							pager : "sysUser_pager_list",
							colNames : [ "id", "用户名", "姓名",
									"手机", "工号", "职位", "部门",
									"状态"],
							colModel : [
									{
										name : "id",
										index : "id",
										hidden : true
									},
									{
										name : "userName",
										index : "userName",
										width : 100,
										sortable : false
									},
									{
										name : "realName",
										index : "realName",
										width : 100,
										sortable : false
									},
									{
										name : "phone",
										index : "phone",
										width : 100,
										sortable : false
									},
									{
										name : "memberId",
										index : "memberId",
										width : 100,
										sortable : false
									},
									{
										name : "jobTitle",
										index : "jobTitle",
										width : 100,
										sortable : false
									},
									{
										name : "deptName",
										index : "deptName",
										width : 100,
										sortable : false
									},
									{
										name : "status",
										index : "status",
										width : 100,
										formatter : formatterUseStatus,
										sortable : false
									}],
							gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
							}
						});
		$("#sysUser_table_list").jqGrid("navGrid", "#sysUser_pager_list", {
			edit : false,
			add : false,
			del : false,
			search : false,
			refresh : true
		});
	}
	function initRolePermissionList(){
		$.jgrid.defaults.styleUI = "Bootstrap";
		$("#permission_by_role_table_list1").jqGrid({
						    url : "#",
							datatype : "json",
							mtype : "POST",
							caption : "该角色已拥有权限列表",
							height : 200,
							autowidth : true,
							shrinkToFit : true,
							multiselect : true,
							viewrecords : false,
							postData : $
									.serializeObject($("#sysUserSearchFrom")),
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							pager : "permission_by_role_pager_list1",
							colNames : [ "id", "权限名称", "权限代码", "权限类型", "状态", "权限描述"],
							colModel : [ {
								name : "id",
								index : "id",
								hidden : true
							}, {
								name : "permisName",
								index : "permisName",
								sortable : false
							}, {
								name : "permisCode",
								index : "permisCode",
								sortable : false
							}, {
								name : "permisType",
								index : "permisType",
								formatter : formatterPermisType,
								unformat : unformatPermisType,
								sortable : false
							}, {
								name : "status",
								index : "status",
								formatter : formatterUseStatus,
								unformat : unformatUseStatus,
								sortable : false
							}, {
								name : "permisDesc",
								index : "permisDesc",
								sortable : false
							} ],
							gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
							}
						});
		$("#permission_by_role_table_list1").jqGrid("navGrid", "#permission_by_role_pager_list1", {
			edit : false,
			add : false,
			del : false,
			search : false,
			refresh : true
		});
	}
	function initNotGrantRolePermissionList(){
		$.jgrid.defaults.styleUI = "Bootstrap";
		$("#permission_by_role_table_list2").jqGrid({
						    url : "#",
							datatype : "json",
							mtype : "POST",
							caption : "该角色未拥有权限列表",
							height : 200,
							autowidth : true,
							shrinkToFit : true,
							multiselect : true,
							viewrecords : false,
							postData : $
									.serializeObject($("#sysUserSearchFrom")),
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							pager : "permission_by_role_pager_list2",
							colNames : [ "id", "权限名称", "权限代码", "权限类型", "状态", "权限描述"],
							colModel : [ {
								name : "id",
								index : "id",
								hidden : true
							}, {
								name : "permisName",
								index : "permisName",
								sortable : false
							}, {
								name : "permisCode",
								index : "permisCode",
								sortable : false
							}, {
								name : "permisType",
								index : "permisType",
								formatter : formatterPermisType,
								unformat : unformatPermisType,
								sortable : false
							}, {
								name : "status",
								index : "status",
								formatter : formatterUseStatus,
								unformat : unformatUseStatus,
								sortable : false
							}, {
								name : "permisDesc",
								index : "permisDesc",
								sortable : false
							} ],
							gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
							}
						});
		$("#permission_by_role_table_list2").jqGrid("navGrid", "#permission_by_role_pager_list2", {
			edit : false,
			add : false,
			del : false,
			search : false,
			refresh : true
		});
	}
	function openAddUserByRole(){
		var jqGridIds = $("#role_table_list")
		.jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length == 0) {
			layer.alert('请选择数据', {
				icon : 0
			});
			return;
		} else if (jqGridIds.length > 1) {
			layer.alert('只能选择一条数据，不能多选重置', {
				icon : 0
			});
			return;
		}
		var selObj = $("#role_table_list").jqGrid('getRowData', jqGridIds[0]);
		$("#sysUser_table_list").jqGrid('setGridParam', {
			url : '${ctx}sysUserController/list.do',
			datatype : 'json',
			postData : {
			}, //发送数据  
			page : 1
		}).trigger("reloadGrid"); //重新载入
		$('#sysUserModal').modal('show');
	}
	function saveSysUserRole(){
		var jqGridIds = $("#role_table_list").jqGrid('getGridParam', 'selarrrow');
		var roleId = $("#role_table_list").jqGrid('getRowData', jqGridIds[0]).id;
		jqGridIds=$("#sysUser_table_list").jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length == 0) {
			layer.alert('请选择数据', {
				icon : 0
			});
			return;
		} 
		var userIds="";
		for (var i = 0; i < jqGridIds.length; i++) {
			var userId = $("#sysUser_table_list").jqGrid('getRowData', jqGridIds[i]).id;
			userIds=userIds+userId+",";
		}
		$.ajax({
			url : "${ctx}sysUserRoleController/saveSysUserRole.do",
			type : "POST",
			data : {"userIdStrs":userIds,"roleId":roleId},
			async : false,
			success : function(result) { //表单提交后更新页面显示的数据
				var ret = eval("(" + result + ")");
				if (ret && ret.header["success"]) {
					layer.alert(ret.header["msg"], {
						icon : 6
					});
					searchList('#user_by_role_table_list');
					$('#sysUserModal').modal('hide');
				} else {
					layer.alert(ret.header["msg"], {
						icon : 5
					});
				}
			}
		});
	}
	function removeSysUserRole(){
		var jqGridIds = $("#role_table_list").jqGrid('getGridParam', 'selarrrow');
		jqGridIds=$("#role_table_list").jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length == 0) {
			layer.alert('请选择角色', {
				icon : 0
			});
			return;
		} 
		var roleId = $("#role_table_list").jqGrid('getRowData', jqGridIds[0]).id;
		jqGridIds=$("#user_by_role_table_list").jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length == 0) {
			layer.alert('请选择用户', {
				icon : 0
			});
			return;
		} 
		var userIds="";
		for (var i = 0; i < jqGridIds.length; i++) {
			var userId = $("#user_by_role_table_list").jqGrid('getRowData', jqGridIds[i]).id;
			userIds=userIds+userId+",";
		}
		$.ajax({
			url : "${ctx}sysUserRoleController/removeSysUserRole.do",
			type : "POST",
			data : {"userIdStrs":userIds,"roleId":roleId},
			async : false,
			success : function(result) { //表单提交后更新页面显示的数据
				var ret = eval("(" + result + ")");
				if (ret && ret.header["success"]) {
					layer.alert(ret.header["msg"], {
						icon : 6
					});
					searchList('#user_by_role_table_list');
					$('#sysUserModal').modal('hide');
				} else {
					layer.alert(ret.header["msg"], {
						icon : 5
					});
				}
			}
		});
	}
	function addpermissionByRole(){
		var jqGridIds = $("#role_table_list").jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length == 0) {
			layer.alert('请选择角色', {
				icon : 0
			});
			return;
		} 
		var roleId = $("#role_table_list").jqGrid('getRowData', jqGridIds[0]).id;
		jqGridIds=$("#permission_by_role_table_list2").jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length == 0) {
			layer.alert('请选择数据', {
				icon : 0
			});
			return;
		} 
		var permisIds="";
		for (var i = 0; i < jqGridIds.length; i++) {
			var permisId = $("#permission_by_role_table_list2").jqGrid('getRowData', jqGridIds[i]).id;
			permisIds=permisIds+permisId+",";
		}
		$.ajax({
			url : "${ctx}sysRolePermissionController/addSysRolePermission.do",
			type : "POST",
			data : {"permisStrs":permisIds,"roleId":roleId},
			async : false,
			success : function(result) { //表单提交后更新页面显示的数据
				var ret = eval("(" + result + ")");
				if (ret && ret.header["success"]) {
					layer.alert(ret.header["msg"], {
						icon : 6
					});
					searchList('#permission_by_role_table_list1');
					searchList('#permission_by_role_table_list2');
				} else {
					layer.alert(ret.header["msg"], {
						icon : 5
					});
				}
			}
		});
	}
	function removePermissionByRole(){
		var jqGridIds = $("#role_table_list").jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length == 0) {
			layer.alert('请选择角色', {
				icon : 0
			});
			return;
		} 
		var roleId = $("#role_table_list").jqGrid('getRowData', jqGridIds[0]).id;
		jqGridIds=$("#permission_by_role_table_list1").jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length == 0) {
			layer.alert('请选择数据', {
				icon : 0
			});
			return;
		} 
		var permisIds="";
		for (var i = 0; i < jqGridIds.length; i++) {
			var permisId = $("#permission_by_role_table_list1").jqGrid('getRowData', jqGridIds[i]).id;
			permisIds=permisIds+permisId+",";
		}
		$.ajax({
			url : "${ctx}sysRolePermissionController/removeSysRolePermission.do",
			type : "POST",
			data : {"permisStrs":permisIds,"roleId":roleId},
			async : false,
			success : function(result) { //表单提交后更新页面显示的数据
				var ret = eval("(" + result + ")");
				if (ret && ret.header["success"]) {
					layer.alert(ret.header["msg"], {
						icon : 6
					});
					searchList('#permission_by_role_table_list1');
					searchList('#permission_by_role_table_list2');
				} else {
					layer.alert(ret.header["msg"], {
						icon : 5
					});
				}
			}
		});
	}
	//该角色已拥有(未拥有)权限列表搜索
	function searchPermissionByRoleTableList(obj,gridId,isGrant){
		var permisName=obj.value;
		var jqGridIds = $("#role_table_list").jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length==0) {
			return;
		}
		var roleId = $("#role_table_list").jqGrid('getRowData', jqGridIds[0]).id;
		$("#"+gridId).jqGrid('setGridParam', {
			url : '${ctx}sysPermissionController/rolePermissionList.do',
			datatype : 'json',
			postData : {
				"roleList[0].id" : roleId,"isGrant":isGrant,"permisName":permisName
			}, //发送数据  
			page : 1
		}).trigger("reloadGrid"); //重新载入
	}
	//该角色下用户列表搜索
	function searChuserByRoleTableList(obj){
		var realName=obj.value;
		var jqGridIds = $("#role_table_list").jqGrid('getGridParam', 'selarrrow');
		if (jqGridIds.length==0) {
			return;
		}
		var roleId = $("#role_table_list").jqGrid('getRowData', jqGridIds[0]).id;
		$("#user_by_role_table_list").jqGrid('setGridParam', {
			url : '${ctx}sysUserController/roleUserList.do',
			datatype : 'json',
			postData : {
				"roleList[0].id" : roleId,"realName":realName
			}, //发送数据  
			page : 1
		}).trigger("reloadGrid"); //重新载入
	}
	//用户列表搜索
	function searChSysUserTableList(obj){
		var realName=obj.value;
		$("#sysUser_table_list").jqGrid('setGridParam', {
			url : '${ctx}sysUserController/list.do',
			datatype : 'json',
			postData : {
				"realName":realName
			}, //发送数据  
			page : 1
		}).trigger("reloadGrid"); //重新载入
	}
</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>搜索</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a> <a class="close-link"> <i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content" style="display: none;">

								<form action="#" method="post" id="searchFrom" name="searchFrom"
									class="form-horizontal">
									<!-- 查询条件 -->
									<div style="padding: 5px">
										<div class="row">
											<div class="col-md-12">
												<label class="col-md-1 control-label" for="roleName">角色名称:</label>
												<div class="col-md-2">
													<input type="text" class="form-control" name="roleName"
														placeholder="角色名称">
												</div>
												<label class="col-md-1 control-label" for="roleCode">角色编码:</label>
												<div class="col-md-2">
													<input type="text" class="form-control" name="roleCode"
														placeholder="角色编码">
												</div>
												<label class="col-md-2 control-label" for="status">状态:</label>
												<div class="col-md-2">
													<select class="form-control" name="status">
														<option value=-1 selected="selected">--请选择--</option>
														<option value=1>有效</option>
														<option value=2>无效</option>
													</select>
												</div>
											</div>
										</div>
										<br>
										<div class="row">
											<!-- 操作按钮 -->
											<button class="btn btn-primary " type="button"
												onclick="searchList('#role_table_list')">
												<i class="fa fa-check"></i> <span class="bold">查询</span>
											</button>
											<button class="btn btn-warning " type="reset">
												<i class="fa fa-warning"></i> <span class="bold">重置</span>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
					  <div class="col-sm-6">
							<div class="ibox-content">
							<a class="btn btn-outline btn-primary" onclick="openAddOrEditSysRole(1)">增加</a> 
							<a class="btn btn-outline btn-warning" onclick="openAddOrEditSysRole(2)">编辑</a>
							<a class="btn btn-outline btn-primary" onclick="openAddUserByRole()">添加用户</a>
								<div class="jqGrid_wrapper">
									<table id="role_table_list"></table>
									<div id="pager_list"></div>
								</div>
							</div>
					  </div>
					  <div class="col-sm-6">
					      <div class="ibox-content">
					        <a class="btn btn-outline btn-danger" onclick="removePermissionByRole()">取消该权限</a>
                            <div style="float: right;">搜索：<input type="text" name="permisName" placeholder="权限名称" onchange="searchPermissionByRoleTableList(this,'permission_by_role_table_list1',true)"></div>
								<div class="jqGrid_wrapper">
									<table id="permission_by_role_table_list1"></table>
									<div id="permission_by_role_pager_list1"></div>
								</div>
							</div>
					  </div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
					  <div class="col-sm-6">
							<div class="ibox-content">
							<a class="btn btn-outline btn-danger" onclick="removeSysUserRole()">取消该用户</a>
                            <div style="float: right;">搜索：<input type="text" name="realName" placeholder="姓名" onchange="searChuserByRoleTableList(this)"></div>
								<div class="jqGrid_wrapper">
									<table id="user_by_role_table_list"></table>
									<div id="user_by_role_pager_list"></div>
								</div>
							</div>
					  </div>
					  <div class="col-sm-6">
					     <div class="ibox-content">
							<a class="btn btn-outline btn-primary" onclick="addpermissionByRole()">附加该权限</a>
                             <div style="float: right;">搜索：<input type="text" name="permisName" placeholder="权限名称" onchange="searchPermissionByRoleTableList(this,'permission_by_role_table_list2',false)"></div>
								<div class="jqGrid_wrapper">
									<table id="permission_by_role_table_list2"></table>
									<div id="permission_by_role_pager_list2"></div>
								</div>
							</div>
					  </div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="modal fade" id="sysRoleModal" tabindex="-1" role="dialog"
		aria-labelledby="sysRoleModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content animated flipInY">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="sysRoleModalLabel">新增角色</h4>
				</div>
				<form id="sysRoleForm" name="sysRoleForm" method="post"
					class="form-horizontal">
					<input type="hidden" name="id" id="sysRoleId">
					<div class="modal-body">
						<div class="form-group">
							<label for="roleName" class="col-sm-2 control-label"><span
								class="requiredSty">*</span>角色名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="roleName"
									name="roleName" maxlength="20" placeholder="角色名称">
							</div>
							<label for="roleCode" class="col-sm-2 control-label"><span
								class="requiredSty">*</span>角色代码:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" maxlength="20"
									id="roleCode" name="roleCode" placeholder="角色代码">
							</div>
						</div>
						<div class="form-group">
							<label for="status" class="col-sm-2 control-label"><span
								class="requiredSty">*</span>状态:</label>
							<div class="col-sm-4">
								<select class="form-control" name="status" id="status">
									<option value=1>有效</option>
									<option value=2>无效</option>
								</select>
							</div>
							<label for="parentId" class="col-sm-2 control-label"><span
								class="requiredSty">*</span>从属角色:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" maxlength="20"
									id="parentId" name="parentId" placeholder="从属角色">
							</div>
						</div>
						<div class="form-group">
							<label for="roleDesc" class="col-sm-2 control-label">角色描述:</label>
							<div class="col-sm-10">
								<textarea rows="1" cols="10" class="form-control"
									name="roleDesc" id="roleDesc" placeholder="角色描述"
									maxlength="200"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="sysUserModal" tabindex="-1" role="dialog"
		aria-labelledby="sysUserModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog" style="width: 800px;">
			<div class="modal-content animated flipInY">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="sysUserModalLabel">系统用户</h4>
				</div>
					<div class="modal-body">
					  <a class="btn btn-outline btn-primary" onclick="saveSysUserRole()">保存</a>
                    <div style="float: right;">搜索：<input type="text" name="realName" placeholder="姓名" onchange="searChSysUserTableList(this)"></div>
					  <div class="jqGrid_wrapper">
									<table id="sysUser_table_list"></table>
									<div id="sysUser_pager_list"></div>
					  </div>
					</div>
			</div>
		</div>
	</div>
	<!-- 需要延迟加载的资源 -->
	<%@ include file="../../delayLoadCommon.jsp"%>
</body>
</html>