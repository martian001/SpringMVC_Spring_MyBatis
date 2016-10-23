<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../../common.jsp"%>
<title>系统操作日志</title>
<script type="text/javascript">
	$(document).ready(function() {
		$.jgrid.defaults.styleUI = "Bootstrap";
		$("#table_list").jqGrid({
			url : "${ctx}sysLogInfoController/list.do",
			datatype : "json",
			mtype : "POST",
			caption : "系统操作日志列表",
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
			colNames : [ "操作人", "操作类型", "操作模块", "IP地址", "操作时间", "操作内容" ],
			colModel : [ {
				name : "operatorName",
				index : "operatorName",
				width : 35,
				sortable : false
			}, {
				name : "type",
				index : "type",
				formatter : formatterType,
				width : 35,
				sortable : false
			}, {
				name : "moduel",
				index : "moduel",
				width : 35,
				formatter : formatterModuel,
				sortable : false
			}, {
				name : "ipAddress",
				index : "ipAddress",
				width : 35,
				sortable : false
			}, {
				name : "creatorDate",
				index : "creatorDate",
				width : 40,
				formatter : 'date',
				formatoptions : {
					srcformat : 'Y-m-d H:i:s',
					newformat : 'Y-m-d H:i:s'
				},
				sortable : false
			} , {
				name : "content",
				index : "content",
				width : 300,
				sortable : false
			}],
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
	//格式化操作类型
	function formatterType(cellvalue, options, rowObject) {
		if (cellvalue == 1) {
			return "添加";
		} else if (cellvalue == 2) {
			return "删除";
		} else if (cellvalue == 3) {
			return "修改";
		} else if (cellvalue == 4) {
			return "登录";
		} else if (cellvalue == 5) {
			return "登出";
		} else if (cellvalue == 6) {
			return "下载文件";
		} else if (cellvalue == 7) {
			return "上传文件";
		} else {
			return "未知";
		}
	}
	//格式化操作模块
	function formatterModuel(cellvalue, options, rowObject) {
		if (cellvalue == 1) {
			return "系统模块";
		} else {
			return "未知";
		}
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
        <form action="#" method="post" id="searchFrom" name="searchFrom" class="form-horizontal">
         <!-- 查询条件 -->
         <div style="padding: 5px">
          <div class="row">
           <div class="col-md-12">
            <label class="col-md-1 control-label" for="operatorName">操作人:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" name="operatorName" placeholder="操作人">
            </div>
            <label class="col-md-2 control-label" for="type">操作类型:</label>
            <div class="col-md-2">
             <select class="form-control" name="type">
              <option value=-1 selected="selected">--请选择--</option>
              <option value=1>添加</option>
              <option value=2>删除</option>
              <option value=3>修改</option>
              <option value=4>登录</option>
              <option value=5>登出</option>
              <option value=6>下载文件</option>
              <option value=7>上传文件</option>
             </select>
            </div>
            <label class="col-md-2 control-label" for="moduel">操作模块:</label>
            <div class="col-md-2">
             <select class="form-control" name="moduel">
              <option value=-1 selected="selected">--请选择--</option>
              <option value=1>系统</option>
             </select>
            </div>
           </div>
          </div>
          <br>
          <div class="row">
           <div class="col-md-12">
            <label class="col-md-1 control-label" for=creatorDate>操作时间:</label>
            <div class="col-md-2">
             <input type="text" class="form-control input-sm laydate-icon layer-date" name="creatorDate" id="creatorDate"
              onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" placeholder="开始日期"
             >
            </div>
            <div class="col-md-2">
             <input type="text" class="form-control input-sm laydate-icon layer-date" name="creatorDateEnd" id="creatorDateEnd"
              onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" placeholder="结束日期"
             >
            </div>
           </div>
          </div>
          <br>
          <div class="row">
           <!-- 操作按钮 -->
           <button class="btn btn-primary " type="button" onclick="searchList('#table_list')">
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