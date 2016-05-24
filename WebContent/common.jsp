<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- easyui -->
<link rel="stylesheet" type="text/css" href="js/plug-in/jquery-easyui-1.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/plug-in/jquery-easyui-1.4.5/themes/icon.css">
<script type="text/javascript" src="js/plug-in/jquery-easyui-1.4.5/jquery.min.js"></script>
<script type="text/javascript" src="js/plug-in/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/plug-in/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
<!-- 页面打印 -->
<script type="text/javascript" src="js/plug-in/jquery.jqprint.js"></script>

<script type="text/javascript" src="js/plug-in/jquery-1.7.2.js"></script>

<script type="text/javascript" src="js/common.js"></script>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
