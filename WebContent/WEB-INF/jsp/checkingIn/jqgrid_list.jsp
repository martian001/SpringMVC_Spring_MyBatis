<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../../common.jsp"%>
<link href="${ctx }css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">
<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/locale/bootstrap-table-zh-CN.min.js"></script>
 -->
<link href="${ctx}css/plugins/jsTree/style.min.css" rel="stylesheet">
<link href="${ctx}css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="${ctx}css/animate.min.css" rel="stylesheet">
<link href="${ctx}css/style.min862f.css?v=4.1.0" rel="stylesheet">
<!-- jqgrid-->
<link href="${ctx}css/plugins/jqgrid/ui.jqgridffe4.css?0820" rel="stylesheet">
<script src="${ctx}js/plugins/bootstrap-table/bootstrap-table-utils.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function formatterStatus(cellvalue, options, rowObject) {
		if (cellvalue == 1) {
			return "未迟到";
		} else if (cellvalue == 2) {
			return "迟到";
		} else {
			return "未知";
		}
	}

	function rowStyleFn(row) {
		var style = {};
		if (row.status == 2) {
			style.css = {
				"background-color" : "#F7642C"
			}
		} else if (row.overTime > 0) {
			style.css = {
				"background-color" : "#03AF15"
			}
		} else if (row.week == "星期六" || row.week == "星期日") {
			style.css = {
				"background-color" : "#00ADE7"
			}
		}
		return style;
	}

	function searchList(tableId) { // 配置参数
		$(tableId).jqGrid('setGridParam', {
			datatype : 'json',
			postData : $.serializeObject($("#searchFrom")), //发送数据  
			page : 1
		}).trigger("reloadGrid"); //重新载入
	}
	$(document)
			.ready(
					function() {
						$.jgrid.defaults.styleUI = "Bootstrap";
						$("#table_list_2")
								.jqGrid(
										{
											url : "${ctx}checkingInAction/checkingInAction_jqgrid_list.do",
											datatype : "json",
											mtype : "POST",
											height : 450,
											autowidth : true,
											shrinkToFit : true,
											postData : $
													.serializeObject($("#searchFrom")),
											rowNum : 10,
											rowList : [ 10, 20, 30 ],
											colNames : [ "姓名", "部门", "星期",
													"开始打卡时间", "结束打卡时间", "是否迟到",
													"加班时间(分钟)" ],
											colModel : [ {
												name : "realName",
												index : "realName",
												editable : true
											}, {
												name : "deptName",
												index : "deptName",
												editable : true
											}, {
												name : "week",
												index : "week",
												editable : true
											}, {
												name : "startDate",
												index : "startDate",
												editable : true,
												formatter : "date",
												formatoptions : {
													srcformat : 'Y-m-d H:i:s',
													newformat : 'Y-m-d H:i:s'
												}
											}, {
												name : "endDate",
												index : "endDate",
												editable : true,
												formatter : "date",
												formatoptions : {
													srcformat : 'Y-m-d H:i:s',
													newformat : 'Y-m-d H:i:s'
												}
											}, {
												name : "status",
												index : "status",
												formatter : formatterStatus,
												editable : true
											}, {
												name : "overTime",
												index : "overTime",
												editable : true
											} ],
											pager : "#pager_list_2",
											viewrecords : true,
											//caption : "jqGrid 示例2",
											add : true,
											edit : true,
											addtext : "Add",
											edittext : "Edit",
											hidegrid : false,
											gridComplete: function(){ //列表生成后,给某一列绑定操作 例如删除操作
									    		var ids = $("#list").jqGrid('getDataIDs');
									    		for(var i=0;i < ids.length;i++){
									    			showInfo = "<a href='##' onclick=\"showInfo('"+ids[i]+"');\">"+$("#list").jqGrid('getRowData',ids[i]).id+"</a>"; 
									    			$("#list").jqGrid('setRowData',ids[i],{act:showInfo});
									    		}
									    },
										//loadonce : true,//如果为ture则数据只从服务器端抓取一次，之后所有操作都是在客户端执行，翻页功能会被禁用
										//sortable:true
										});
						 $("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
							edit : true,
							add : true,
							del : true,
							refresh : true,
							search : true
						}, {
							height : 200,
							reloadAfterSubmit : true
						});
						$(window).bind("resize", function() {
							var width = $(".jqGrid_wrapper").width();
							$("#table_list_2").setGridWidth(width)
						}) 
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

        <form action="studentAction_list.do" method="post" id="searchFrom" name="searchFrom" class="form-horizontal">
         <!-- 查询条件 -->
         <div style="padding: 5px">
          <div class="row">
           <div class="col-md-12">
            <label class="col-md-1 control-label" for="formGroupInputLarge1">姓名:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" name="realName" id="formGroupInputLarge1" placeholder="姓名">
            </div>
            <label class="col-md-1 control-label" for="formGroupInputLarge2">部门:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" name="deptName" id="formGroupInputLarge2" placeholder="部门">
            </div>
            <label class="col-md-2 control-label" for="formGroupInputLarge4">是否迟到:</label>
            <div class="col-md-2">
             <select class="form-control" name="status" id="formGroupInputLarge4">
              <option value=-1 selected="selected">--请选择--</option>
              <option value=1>未迟到</option>
              <option value=2>迟到</option>
             </select>
            </div>
           </div>
          </div>
          <br>
          <div class="row">
           <div class="col-md-12">
            <label class="col-md-1 control-label" for="formGroupInputLarge5">回家:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" id="formGroupInputLarge5" placeholder="姓名">
            </div>
            <label class="col-md-1 control-label" for="formGroupInputLarge6">部门:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" id="formGroupInputLarge6" placeholder="部门">
            </div>
            <label class="col-md-2 control-label" for="formGroupInputLarge7">是否迟到:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" id="formGroupInputLarge7" placeholder="是否迟到">
            </div>
           </div>
          </div>
          <br>
          <div class="row">
           <!-- 操作按钮 -->
           <button class="btn btn-primary " type="button" onclick="searchList('#table_list_2')">
            <i class="fa fa-check"></i> <span class="bold">查询</span>
           </button>
           <button class="btn btn-warning " type="reset">
            <i class="fa fa-warning"></i> <span class="bold">重置</span>
           </button>
           <button class="btn btn-success " type="button" onclick="openUploanFile()">
            <i class="fa fa-upload"></i>&nbsp;&nbsp;<span class="bold">上传文件</span>
           </button>
           <button class="btn btn-warning " type="button" onclick="delByIds()">
            <i class="fa fa-warning"></i> <span class="bold">删除</span>
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
      <div class="ibox float-e-margins">
       <div class="ibox-content">
        <div class="jqGrid_wrapper">
         <table id="table_list_2"></table>
         <div id="pager_list_2"></div>
        </div>

       </div>
      </div>
     </div>
     <div class="col-sm-12">
      <div style="float: left; font:">迟到:</div>
      <div style="background-color: #F7642C; width: 20px; height: 20px; float: left;"></div>
      <div style="float: left;">，晚上加班:</div>
      <div style="background-color: #03AF15; width: 20px; height: 20px; float: left;"></div>
      <div style="float: left;">，周六日加班:</div>
      <div style="background-color: #00ADE7; width: 20px; height: 20px; float: left;"></div>
     </div>
    </div>

   </div>
  </div>
 </div>

 <script src="${ctx}js/plugins/jqgrid/i18n/grid.locale-cnffe4.js?0820"></script>
 <script src="${ctx}js/plugins/jqgrid/jquery.jqGrid.minffe4.js?0820"></script>
 <!-- 需要延迟加载的资源 -->
 <%@ include file="../../delayLoadCommon.jsp"%>
</body>
</html>