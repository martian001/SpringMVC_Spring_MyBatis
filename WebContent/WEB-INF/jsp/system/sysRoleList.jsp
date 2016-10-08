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
		$.jgrid.defaults.styleUI = "Bootstrap";
		$("#table_list").jqGrid({
			url : "${ctx}sysRoleController/list.do",
			datatype : "json",
			mtype : "POST",
			caption : "系统角色列表",
			height : 450,
			autowidth : true,
			shrinkToFit : true,
			multiselect : true,
			multiboxonly : true,
			viewrecords : true,
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
				index : "roleName"
			}, {
				name : "roleCode",
				index : "roleCode",
				sortable : false
			}, {
				name : "status",
				index : "status",
				formatter : formatterUseStatus,
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
			}
		});
		$("#table_list").jqGrid("navGrid", "#pager_list", {
			edit : false,
			add : false,
			del : false,
			search : false,
			refresh : true
		});

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
							searchList('#table_list');
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
			var jqGridIds = $("#table_list")
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
			var selObj = $("#table_list").jqGrid('getRowData', jqGridIds[0]);
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
							<div class="ibox-content">

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
												onclick="searchList('#table_list')">
												<i class="fa fa-check"></i> <span class="bold">查询</span>
											</button>
											<button class="btn btn-warning " type="reset">
												<i class="fa fa-warning"></i> <span class="bold">重置</span>
											</button>
											<a class="btn btn-outline btn-primary"
												onclick="openAddOrEditSysRole(1)">增加</a> <a
												class="btn btn-outline btn-primary"
												onclick="openAddOrEditSysRole(2)">编辑</a>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<div class="jqGrid_wrapper">
									<table id="table_list"></table>
									<div id="pager_list"></div>
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
	<!-- 需要延迟加载的资源 -->
	<%@ include file="../../delayLoadCommon.jsp"%>
</body>
</html>