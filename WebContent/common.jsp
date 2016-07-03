<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
   String path = request.getContextPath();
   String ctx = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   request.setAttribute("ctx", ctx);
%>
<script type="text/javascript" src="${ctx }js/jquery.min.js?v=2.1.4"></script>

<!-- easyui -->
<link rel="stylesheet" type="text/css" href="${ctx }js/plug-in/jquery-easyui-1.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx }js/plug-in/jquery-easyui-1.4.5/themes/icon.css">
<script type="text/javascript" src="${ctx }js/plug-in/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }js/plug-in/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
<!-- 页面打印 -->
<script type="text/javascript" src="${ctx }js/plug-in/jquery.jqprint.js"></script>

<script type="text/javascript" src="${ctx }js/common.js" ></script>
<!-- bootstrap -->
<script src="${ctx }js/bootstrap.min.js?v=3.3.6"></script>

<!-- bootstrap表格 分页 -->
<%-- <script src="${ctx }js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx }js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<link href="${ctx }css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<script src="${ctx }js/demo/bootstrap-table-demo.min.js"></script>
 --%>
