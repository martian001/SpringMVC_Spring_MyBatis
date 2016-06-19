<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common.jsp"%>
<title>Insert title here</title>
<script type="text/javascript">
  //
  function formatterStatus(val, row) {
   	if (val == 1) {
   		return "未迟到";
   	} else if (val == 2) {
   		return "迟到";
   	} else {
   		return "未知";
   	}
  }
  function formatterWeek(val, row) {
   	if (val == 1) {
   		return "星期日";
   	} else if (val == 2) {
   		return "星期一";
   	} else if (val == 3) {
   		return "星期二";
   	}  else if (val == 4) {
   		return "星期三";
   	}  else if (val == 5) {
   		return "星期四";
   	}  else if (val == 6) {
   		return "星期五";
   	}  else if (val == 7) {
   		return "星期六";
   	}  else {
   		return "未知";
   	}
  }
  function delByIds() {
		var rows = $('#grid').datagrid('getSelections');
		if (rows.length <= 0) {
			$.messager.alert("操作提示", "请选择数据", "error");
			return;
		}
		var ids = "";
		for (var i = 0; i < rows.length; i++) {
			if (i == 0) {
				ids += rows[i].id;
			} else {
				ids += "," + rows[i].id;
			}
		}
		$.messager.confirm('温馨提示','是否确认删除?',function(r){
		    if (r){
     		$.ajax({
     				url :'${ctx }checkingInAction/delByIds.do?ids='+ids,
     				type : "POST",
     				async : false,
     				success : function(data) {
     					var ret = eval("(" + data + ")");
     					if (ret && ret.header["success"]) {
     						$.messager.alert("操作提示", "删除成功", "info");
          					$("#grid").datagrid("clearChecked");
          					$("#grid").datagrid('reload');
     					}else{
     						$.messager.alert('操作提示', ret.header["msg"], 'error');
     					}
     				}
     		})
		    }
	    });	

	}
	var bangdingLink = {
		formatOperate : function(value, row, index) {
			var va = "<a href='studentAction_editUI.do?id="+row.id+"''> <font color='blue'>编辑</font></a>";
			 va = va+"|<a href='studentAction_delete.do?id="+row.id+"'> <font color='blue'>删除</font></a>";
			return va;
		}
	}
	function openUploanFile(){
		$('#upload-dialog').dialog('open').dialog('setTitle', "上传文件");
	}
	function compute() {//计算函数
           var rows = $('#grid').datagrid('getRows')//获取当前的数据行
           var overTimetal =0;//
           for (var i = 0; i < rows.length; i++) {
        	   if (rows[i].overTime!=null) {
        	   	overTimetal += rows[i].overTime;
			   }
           }
           //新增一行显示统计信息
           $('#grid').datagrid('appendRow', { realName: '<b>统计：</b>', overTime: overTimetal});
       }
	$(document).ready(function() {
	    $('#grid').datagrid({  
	    	  onLoadSuccess:function(index,row){   
	    		  compute();
	    	  },
			  rowStyler:function(index,row){    
			      if (row.status==2){    
			          return 'background-color:#F7642C';    
			      }    
			      if (row.overTime>0){    
			          return 'background-color:#03AF15';    
			      }    
			      if (row.week==1||row.week==7){    
			          return 'background-color:#00ADE7';    
			      }    
			  } 
	    }) ;
	});
</script>
</head>
<body>
 <!--图标按钮 -->
 <div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
  <form action="studentAction_list.do" method="post" id="searchFrom" name="searchFrom">
   <!-- 查询条件 -->
   <div style="padding: 5px">
    <table>
     <tr>
      <td width="80" align="right" height="28">姓名：</td>
      <td><input class="easyui-textbox" name="realName" /></td>
      <td  align="right" height="28">部门：</td>
      <td><input class="easyui-textbox" name="deptName" /></td>
     </tr>
     <tr>
      <td align="right" height="28">日期：</td>
      <td><input class="easyui-datebox" name="startDate" />~<input class="easyui-datebox" name="endDate" /></td>
      <td width="100" align="right" height="28">是否迟到：</td>
        <td>
        <select class="easyui-combobox" name="status" style="width: 124px" panelHeight="auto" editable="false">
          <option value=-1 selected="selected">--请选择--</option>
          <option value=1>未迟到</option>
          <option value=2>迟到</option>
        </select>
        </td>
     </tr>
     <tr>
      <td colspan="4"><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="ajaxSearchPage('#grid','#searchFrom')">查询</a> <a href="#"
       onclick="majaxReset()" class="easyui-linkbutton" style="width: 60px;"
      >重置</a></td>
     </tr>
    </table>
   </div>
  </form>

  <!-- 操作按钮 -->
  <div style="padding-bottom: 5px">
   <a href="#" class="easyui-linkbutton" onclick="delByIds()" iconCls="icon-edit" plain="true">删除</a>
   <a href="#" class="easyui-linkbutton" onclick="openUploanFile()" iconCls="icon-edit" plain="true">上传文件</a>
  </div>

 </div>
 <table id="grid" title="考勤列表" class="easyui-datagrid" style="height: 500px; width: auto;"
  data-options="
		    url: '${ctx }checkingInAction/checkingInAction_list.do',
		    method: 'POST',
		    rownumbers: true,
		    singleSelect: false,
		    pagination: true,
		    sortOrder:'asc',
		    remoteSort:false,
		    toolbar: '#toolbar',
		    idField: 'id',
		    fitColumns:true"
 >
  <thead>
   <tr>
    <th data-options="field:'id',checkbox:true"></th>
    <th data-options="field:'realName',sortable:true" align="center" halign="center">姓名</th>
    <th data-options="field:'deptName',sortable:true" align="center" halign="center">部门</th>
    <th data-options="field:'week',sortable:true" align="center" halign="center">星期</th>
    <th data-options="field:'startDate',sortable:true,formatter:convertDateTime" align="center" halign="center">开始打卡时间</th>
    <th data-options="field:'endDate',sortable:true,formatter:convertDateTime" align="center" halign="center">结束打卡时间</th>
    <th data-options="field:'status',formatter:formatterStatus,sortable:true" align="center" halign="center">是否迟到</th>
    <th data-options="field:'overTime',sortable:true" align="center" halign="center">加班时间(分钟)</th>
   <!--  <th data-options="field:'cz',formatter:bangdingLink.formatOperate" align="center" halign="center">操作</th> -->
   </tr>
  </thead>
 </table>
 <div>
 <div style="float: left;font: ">迟到:</div><div style="background-color: #F7642C;width: 20px;height: 20px;float:left;"></div>
      <div style="float: left;"> 晚上加班:</div><div style="background-color: #03AF15;width: 20px;height: 20px;float:left;"></div>
       <div style="float: left;"> 周六日加班:</div><div style="background-color: #00ADE7;width: 20px;height: 20px;float:left;"></div>
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
      <a id="saveFile" href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFileForm('#fileUploadForm','${ctx }checkingInAction/initExlData.do')" iconCls="icon-ok">保存</a> <a href="javascript:void(0)"
       class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#upload-dialog').dialog('close')"
      >关闭</a>
     </div>
 <br />
</body>
</html>