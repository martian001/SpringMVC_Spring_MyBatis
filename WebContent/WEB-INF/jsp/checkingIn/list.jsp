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

<link href="${ctx}css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<script src="${ctx}js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx}js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="${ctx}js/plugins/bootstrap-table/bootstrap-table-export.js"></script>
<script src="${ctx}js/plugins/bootstrap-table/jquery.base64.js"></script>
<script src="${ctx}js/plugins/bootstrap-table/tableExport.js"></script>
<script src="${ctx}js/plugins/bootstrap-table/bootstrap-table-utils.js"></script>

<title>Insert title here</title>
<script type="text/javascript">
	function formatterStatus(value, row, element) {
		if (value == 1) {
			return "未迟到";
		} else if (value == 2) {
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

	function openUploanFile() {
		$('#upload-dialog').dialog('open').dialog('setTitle', "上传文件");
	}
	$(document).ready(function() {
		$('#table1').bootstrapTable({
			method : 'get',
			cache : false,
			//height : 400,
			striped : true,
			pagination : true,
			striped : true,
			showToggle : true,
			//pageSize : 10,
			//pageNumber : 1, 
			showColumns : true,
			showRefresh : true,
			pagination : true,
			//search : true,
			sortable : true,
			showPaginationSwitch : true,//隐藏/显示分页
			queryParams : queryParams,
			rowStyle : rowStyleFn,
			sidePagination : "server", //服务端请求
			silentSort : true,
			//showExport : true,
			//exportTypes : [ 'csv', 'txt', 'xml' ],
			clickToSelect : true,
			url : '${ctx }checkingInAction/checkingInAction_list.do',
			formatNoMatches : function() {
				return '无符合条件的记录';
			}
		});
		/* $(window).resize(function() {
			$('#table1').bootstrapTable('resetView');
		}); */
	});
</script>
</head>
<body class="gray-bg">
 <div class="wrapper wrapper-content animated fadeInRight">
 
  <div class="row">
               <label class="font-noraml">范围选择</label>
                            <div class="input-daterange input-group" id="datepicker">
                                <input type="text" class="input-sm form-control" name="start" value="2014-11-11" />
                                <span class="input-group-addon">到</span>
                                <input type="text" class="input-sm form-control" name="end" value="2014-11-17" />
                            </div>
  </div>
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
           <div class="col-md-8">
            <label class="col-md-1 control-label" for="formGroupInputLarge1">姓名:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" name="realName" id="formGroupInputLarge1" placeholder="姓名">
            </div>
            <label class="col-md-1 control-label" for="formGroupInputLarge2">部门:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" name="deptName" id="formGroupInputLarge2" placeholder="部门">
            </div>
            <label class="col-md-1 control-label" for="formGroupInputLarge4">是否迟到:</label>
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
           <div class="col-md-8">
            <label class="col-md-1 control-label" for="formGroupInputLarge5">回家:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" id="formGroupInputLarge5" placeholder="姓名">
            </div>
            <label class="col-md-1 control-label" for="formGroupInputLarge6">部门:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" id="formGroupInputLarge6" placeholder="部门">
            </div>
            <label class="col-md-1 control-label" for="formGroupInputLarge7">是否迟到:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" id="formGroupInputLarge7" placeholder="是否迟到">
            </div>
            <label class="col-md-1 control-label" for="formGroupInputLarge8">是否迟到:</label>
            <div class="col-md-2">
             <input type="text" class="form-control" id="formGroupInputLarge8" placeholder="是否迟到">
            </div>
           </div>
          </div>
          <br>
          <div class="row">
           <!-- 操作按钮 -->
           <button class="btn btn-primary " type="button" onclick="loadBootstrapTable('#table1')">
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

         <!-- <div style="padding: 5px">
   <table>
    <tr>
     <td width="80" align="right" height="28">姓名：</td>
     <td><input class="easyui-textbox" name="realName" id="realName" /></td>
     <td align="right" height="28">部门：</td>
     <td><input class="easyui-textbox" name="deptName" /></td>
    </tr>
    <tr>
     <td align="right" height="28">日期：</td>
     <td><input class="easyui-datebox" name="startDate" />~<input class="easyui-datebox" name="endDate" /></td>
     <td width="100" align="right" height="28">是否迟到：</td>
     <td><select class="easyui-combobox" name="status" style="width: 124px" panelHeight="auto" editable="false">
       <option value=-1 selected="selected">--请选择--</option>
       <option value=1>未迟到</option>
       <option value=2>迟到</option>
     </select></td>
    </tr>
    <tr>
     <td colspan="4"><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadBootstrapTable('#table1')">查询</a> <a href="#"
      onclick="majaxReset()" class="easyui-linkbutton" style="width: 60px;"
     >重置</a></td>
    </tr>
   </table>
  </div> -->
        </form>

       </div>
      </div>
     </div>
    </div>

    <div class="row">
     <div class="col-sm-12">
      <div class="ibox float-e-margins">
       <div class="ibox-content">
        <table id="table1" data-mobile-responsive="true" data-show-pagination-switch="true">
         <thead>
          <tr>
           <th data-field="id" data-checkbox="true"></th>
           <th data-field="realName" data-sortable="true" align="center">姓名</th>
           <th data-field="deptName" data-sortable="true" align="center">部门</th>
           <th data-field="week" data-sortable="true" align="center">星期</th>
           <th data-field="startDate" data-sortable="true" data-formatter="convertDateTime" align="center">开始打卡时间</th>
           <th data-field="endDate" data-sortable="true" data-formatter="convertDateTime" align="center">结束打卡时间</th>
           <th data-field="status" data-sortable="true" data-formatter="formatterStatus" align="center">是否迟到</th>
           <th data-field="overTime" data-sortable="true" align="center">加班时间(分钟)</th>
          </tr>
         </thead>
        </table>

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




 <%-- <div class="row">
  <div class="row">
   <!--图标按钮 -->
   <div class="ibox-content m-b-sm border-bottom">
    <div class="p-xs">
     <div class="pull-left m-r-md">
      <i class="fa fa-globe text-navy mid-icon"></i>
     </div>
     <h2>欢迎来到H+论坛</h2>
     <span>你可以自由选择你感兴趣的话题。</span>
    </div>
   </div>
  </div>
  <form action="studentAction_list.do" method="post" id="searchFrom" name="searchFrom" class="form-horizontal">
   <!-- 查询条件 -->
   <div style="padding: 5px">
    <div class="row">
     <div class="col-md-8">
      <label class="col-md-1 control-label" for="formGroupInputLarge1">姓名:</label>
      <div class="col-md-2">
       <input type="text" class="form-control" id="formGroupInputLarge1" placeholder="姓名">
      </div>
      <label class="col-md-1 control-label" for="formGroupInputLarge2">部门:</label>
      <div class="col-md-2">
       <input type="text" class="form-control" id="formGroupInputLarge2" placeholder="部门">
      </div>
      <label class="col-md-1 control-label" for="formGroupInputLarge3">是否迟到:</label>
      <div class="col-md-2">
       <input type="text" class="form-control" id="formGroupInputLarge3" placeholder="是否迟到">
      </div>
      <label class="col-md-1 control-label" for="formGroupInputLarge4">是否迟到:</label>
      <div class="col-md-2">
       <input type="text" class="form-control" id="formGroupInputLarge4" placeholder="是否迟到">
      </div>
     </div>
    </div>
    <br>
    <div class="row">
     <div class="col-md-8">
      <label class="col-md-1 control-label" for="formGroupInputLarge5">回家:</label>
      <div class="col-md-2">
       <input type="text" class="form-control" id="formGroupInputLarge5" placeholder="姓名">
      </div>
      <label class="col-md-1 control-label" for="formGroupInputLarge6">部门:</label>
      <div class="col-md-2">
       <input type="text" class="form-control" id="formGroupInputLarge6" placeholder="部门">
      </div>
      <label class="col-md-1 control-label" for="formGroupInputLarge7">是否迟到:</label>
      <div class="col-md-2">
       <input type="text" class="form-control" id="formGroupInputLarge7" placeholder="是否迟到">
      </div>
      <label class="col-md-1 control-label" for="formGroupInputLarge8">是否迟到:</label>
      <div class="col-md-2">
       <input type="text" class="form-control" id="formGroupInputLarge8" placeholder="是否迟到">
      </div>
     </div>
    </div>
   </div>
   <!-- <div style="padding: 5px">
   <table>
    <tr>
     <td width="80" align="right" height="28">姓名：</td>
     <td><input class="easyui-textbox" name="realName" id="realName" /></td>
     <td align="right" height="28">部门：</td>
     <td><input class="easyui-textbox" name="deptName" /></td>
    </tr>
    <tr>
     <td align="right" height="28">日期：</td>
     <td><input class="easyui-datebox" name="startDate" />~<input class="easyui-datebox" name="endDate" /></td>
     <td width="100" align="right" height="28">是否迟到：</td>
     <td><select class="easyui-combobox" name="status" style="width: 124px" panelHeight="auto" editable="false">
       <option value=-1 selected="selected">--请选择--</option>
       <option value=1>未迟到</option>
       <option value=2>迟到</option>
     </select></td>
    </tr>
    <tr>
     <td colspan="4"><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadBootstrapTable('#table1')">查询</a> <a href="#"
      onclick="majaxReset()" class="easyui-linkbutton" style="width: 60px;"
     >重置</a></td>
    </tr>
   </table>
  </div> -->
  </form>
  <!-- 操作按钮 -->
  <!--  <div style="padding-bottom: 5px">
  <a href="#" class="easyui-linkbutton" onclick="delByIds()" iconCls="icon-edit" plain="true">删除</a> <a href="#" class="easyui-linkbutton"
   onclick="openUploanFile()" iconCls="icon-edit" plain="true"
  >上传文件</a>
 </div> -->
  <div class="example-wrap">
   <div class="example">
    <table id="table1" data-mobile-responsive="true" data-show-pagination-switch="true">
     <thead>
      <tr>
       <th data-field="id" data-checkbox="true"></th>
       <th data-field="realName" data-sortable="true" align="center">姓名</th>
       <th data-field="deptName" data-sortable="true" align="center">部门</th>
       <th data-field="week" data-sortable="true" align="center">星期</th>
       <th data-field="startDate" data-sortable="true" data-formatter="convertDateTime" align="center">开始打卡时间</th>
       <th data-field="endDate" data-sortable="true" data-formatter="convertDateTime" align="center">结束打卡时间</th>
       <th data-field="status" data-sortable="true" data-formatter="formatterStatus" align="center">是否迟到</th>
       <th data-field="overTime" data-sortable="true" align="center">加班时间(分钟)</th>
      </tr>
     </thead>
    </table>
   </div>
   <!-- End Example Pagination -->
  </div>
  <div>
   <div style="float: left; font:">迟到:</div>
   <div style="background-color: #F7642C; width: 20px; height: 20px; float: left;"></div>
   <div style="float: left;">，晚上加班:</div>
   <div style="background-color: #03AF15; width: 20px; height: 20px; float: left;"></div>
   <div style="float: left;">，周六日加班:</div>
   <div style="background-color: #00ADE7; width: 20px; height: 20px; float: left;"></div>
  </div>

  <div id="upload-dialog" class="easyui-dialog" buttons="#upFileinfo" style="width: 650px; height: 150px; padding: 10px 20px;" closed="true">
  <form id="fileUploadForm" name="fileUploadForm" method="post" enctype="multipart/form-data">
   <table>
    <tr>
     <td><span class="cus_red">*</span>文件：</td>
     <td><input class="easyui-filebox offlineMeetingInput" name="file" id="offlineMeetingFiles" style="width: 425px"></td>
    </tr>
   </table>
  </form>
 </div>
 <div id="upFileinfo">
  <a id="saveFile" href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFileForm('#fileUploadForm','${ctx }checkingInAction/initExlData.do')"
   iconCls="icon-ok"
  >保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#upload-dialog').dialog('close')">关闭</a>
 </div>
  <br />
 </div> --%>

<script src="${ctx}js/plugins/datapicker/bootstrap-datepicker.js"></script>
<!-- 需要延迟加载的资源 -->
<%@ include file="../../delayLoadCommon.jsp"%>
</body>
</html>