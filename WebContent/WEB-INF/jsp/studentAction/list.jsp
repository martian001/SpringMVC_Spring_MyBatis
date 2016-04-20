<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common.jsp"%>
<title>Insert title here</title>
<script type="text/javascript">
	var bangdingLink = {
		formatOperate : function(value, row, index) {
			var va = "<a href='studentAction_editUI.do?id="+row.id+"''> <font color='blue'>编辑</font></a>";
			 va = va+"|<a href='studentAction_delete.do?id="+row.id+"'> <font color='blue'>删除</font></a>";
			return va;
		}
	}
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
      <td><input class="easyui-textbox" name="name" /></td>
      <td width="100" align="right" height="28">年龄：</td>
      <td><input class="easyui-textbox" style="width: 220px" name="age" /></td>
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
   <a href="studentAction_addUI.do" class="easyui-linkbutton" iconCls="icon-edit" plain="true">添加</a>
  </div>

 </div>
 <table id="grid" title="学生列表" class="easyui-datagrid" style="height: 500px; width: auto;"
  data-options="
		    url: 'studentAction_list.do',
		    method: 'POST',
		    rownumbers: true,
		    singleSelect: true,
		    pagination: true,
		    sortOrder:'asc',
		    remoteSort:false,
		    toolbar: '#toolbar',
		    idField: 'pid',
		    fitColumns:true"
 >
  <thead>
   <tr>
    <th data-options="field:'pid',checkbox:true"></th>
    <th data-options="field:'name',sortable:true,width:30" align="center" halign="center">姓名</th>
    <th data-options="field:'age',sortable:true,width:30" align="center" halign="center">年龄</th>
    <th data-options="field:'cz',formatter:bangdingLink.formatOperate" align="center" halign="center">操作</th>
   </tr>
  </thead>
 </table>
 <br />
</body>
</html>