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
	$(document)
			.ready(
					function() {
						$.jgrid.defaults.styleUI = "Bootstrap";
						$("#table_list")
								.jqGrid(
										{
											url : "${ctx}sysUserController/list.do",
											datatype : "json",
											mtype : "POST",
											caption : "系统用户列表",
											height : 450,
											autowidth : true,
											shrinkToFit : true,
											multiselect : true,
											multiboxonly : true,
											viewrecords : true,
											postData : $
													.serializeObject($("#searchFrom")),
											rowNum : 10,
											rowList : [ 10, 20, 30 ],
											pager : "#pager_list",
											colNames : [ "id", "用户名", "姓名",
													"手机", "工号", "职位", "部门",
													"状态", "操作" ],
											colModel : [
													{
														name : "id",
														index : "id",
														hidden : true
													},
													{
														name : "userName",
														index : "userName"
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
													},
													{
														name : "operate",
														index : "operate",
														sortable : false,
														formatter : function(
																cellvalue,
																options, row) {
															return '<a onclick=openTab("${ctx }sysUserController/toAddOrUpdate.do?id='
																	+ row.id
																	+ '","编辑用户","toUpdate.do") >编辑</a>'
														}
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
					});
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
												<label class="col-md-1 control-label" for="userName">用户名:</label>
												<div class="col-md-2">
													<input type="text" class="form-control" name="userName"
														id="userName" placeholder="用户名">
												</div>
												<label class="col-md-1 control-label" for="realName">姓名:</label>
												<div class="col-md-2">
													<input type="text" class="form-control" name="realName"
														id="realName" placeholder="姓名">
												</div>
												<label class="col-md-2 control-label" for="status">状态:</label>
												<div class="col-md-2">
													<select class="form-control" name="status" id="status">
														<option value=-1 selected="selected">--请选择--</option>
														<option value=1>有效</option>
														<option value=2>无效</option>
													</select>
												</div>
											</div>
										</div>
										<br>
										<div class="row">
											<div class="col-md-12">
												<label class="col-md-1 control-label" for="memberId">工号:</label>
												<div class="col-md-2">
													<input type="text" class="form-control" name="memberId"
														id="memberId" placeholder="工号">
												</div>
												<label class="col-md-1 control-label" for="deptName">部门:</label>
												<div class="col-md-2">
													<input type="text" class="form-control" name="deptName"
														id="deptName" placeholder="部门">
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
												onclick="openTab('${ctx}sysUserController/toAddOrUpdate.do','增加用户','toSystemAdd.do')">增加</a>
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

	<!-- 需要延迟加载的资源 -->
	<%@ include file="../../delayLoadCommon.jsp"%>
</body>
</html>