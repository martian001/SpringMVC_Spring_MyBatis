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
		sysMenuModalListener();
		$.jgrid.defaults.styleUI = "Bootstrap";
		$("#table_list").jqGrid({
			url : "${ctx}sysMenuController/list.do",
			datatype : "json",
			mtype : "POST",
			caption : "系统菜单列表",
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
			colNames : [ "ID", "父ID", "菜单名称", "菜单图标", "菜单URL", "状态", "显示顺序" ],
			colModel : [ {
				name : "id",
				index : "id",
				sortable : false
			}, {
				name : "parentId",
				index : "parentId",
				hidden : true
			},  {
				name : "menuName",
				index : "menuName",
				sortable : false
			}, {
				name : "iconCls",
				index : "iconCls",
				sortable : false
			}, {
				name : "menuUrl",
				index : "menuUrl",
				sortable : false
			}, {
				name : "status",
				index : "status",
				formatter : formatterUseStatus,
				unformat : unformatUseStatus,
				sortable : false
			}, {
				name : "menuIndex",
				index : "menuIndex",
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

		$("#sysMenuForm").validate({
			rules : {
				parentId : {
					maxlength : 50
				},
				menuName : {
					required : true,
					maxlength : 20
				},
				iconCls : {
					maxlength : 20
				},
				status : {
					required : true
				},
				menuUrl : {
					required : true,
					maxlength : 100
				},
				menuIndex : {
					maxlength : 2,
					required : true,
					isIntGtZero : true
				}
			},
			submitHandler : function(form) {
				$.ajax({
					url : "${ctx}sysMenuController/addOrUpdate.do",
					type : "POST",
					data : $("#sysMenuForm").serialize(),
					async : false,
					success : function(result) { //表单提交后更新页面显示的数据
						var ret = eval("(" + result + ")");
						if (ret && ret.header["success"]) {
							layer.alert(ret.header["msg"], {
								icon : 6
							});
							searchList('#table_list');
							$('#sysMenuModal').modal('hide');
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
	function openAddOrEditSysMenu(openType) {
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
			var parentId = selObj.parentId;
			var menuName = selObj.menuName;
			var iconCls = selObj.iconCls;
			var menuUrl = selObj.menuUrl;
			var status = selObj.status;
			var menuIndex = selObj.menuIndex;
			$("#sysMenuId").val(id);
			$("#parentId").val(parentId);
			$("#menuName").val(menuName);
			$("#iconCls").val(iconCls);
			$("#menuUrl").val(menuUrl);
			$("#status").val(status);
			$("#menuIndex").val(menuIndex);
		}
		$('#sysMenuModal').modal('show');
	}
	
	function deleteSysMenu() {
		var jqGridIds = $("#table_list").jqGrid('getGridParam', 'selarrrow');
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
		layer.confirm('确定删除?', {
			icon : 0,
			btn : [ '是', '否' ]
		//按钮
		}, function() {
			$.ajax({
				url : "${ctx}sysMenuController/delete.do",
				type : "POST",
				data : {
					"id" : id
				},
				async : false,
				success : function(result) { //表单提交后更新页面显示的数据
					var ret = eval("(" + result + ")");
					if (ret && ret.header["success"]) {
						layer.alert(ret.header["msg"], {
							icon : 6
						});
						searchList('#table_list');
					} else {
						layer.alert(ret.header["msg"], {
							icon : 5
						});
					}
				}
			});
		}, function() {
		});
	}
	//对话框监听器
	function sysMenuModalListener() {
		$('#sysMenuModal').on('hide.bs.modal', function() {
			$("#sysMenuId").val("");
			$("#parentId").val("");
			$("#menuName").val("");
			$("#iconCls").val("");
			$("#menuUrl").val("");
			$("#status").val(1);
			$("#menuIndex").val("");
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
												<label class="col-md-1 control-label" for="menuName">菜单名称:</label>
												<div class="col-md-2">
													<input type="text" class="form-control" name="menuName"
														placeholder="菜单名称">
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
											    <a class="btn btn-outline btn-primary" onclick="openAddOrEditSysMenu(1)">增加</a> 
												<a class="btn btn-outline btn-primary" onclick="openAddOrEditSysMenu(2)">编辑</a>
												<a class="btn btn-outline btn-primary" onclick="deleteSysMenu()">删除</a>
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
	<div class="modal fade" id="sysMenuModal" tabindex="-1" menu="dialog"
		aria-labelledby="sysMenuModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content animated flipInY">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="sysMenuModalLabel">新增菜单</h4>
				</div>
				<form id="sysMenuForm" name="sysMenuForm" method="post"
					class="form-horizontal">
					<input type="hidden" name="id" id="sysMenuId">
					<div class="modal-body">
						<div class="form-group">
							<label for="menuName" class="col-sm-2 control-label"><span
								class="requiredSty">*</span>菜单名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="menuName"
									name="menuName" maxlength="20" placeholder="菜单名称">
							</div>
							<label for="menuIndex" class="col-sm-2 control-label"><span
								class="requiredSty">*</span>显示顺序:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" maxlength="20"
									id="menuIndex" name="menuIndex" placeholder="显示顺序">
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
							<label for="menuUrl" class="col-sm-2 control-label"><span
								class="requiredSty">*</span>菜单URL:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" maxlength="100"
									id="menuUrl" name="menuUrl" placeholder="菜单URL">
							</div>
						</div>
						<div class="form-group">
							<label for="parentId" class="col-sm-2 control-label">父级菜单ID:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="parentId"
									name="parentId" maxlength="50" placeholder="父级菜单ID">
							</div>
							<label for="iconCls" class="col-sm-2 control-label">菜单图标:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" maxlength="20"
									id="iconCls" name="iconCls" placeholder="菜单图标">
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