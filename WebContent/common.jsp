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
<!-- easyui -->
<link rel="stylesheet" type="text/css" href="${ctx }js/plug-in/jquery-easyui-1.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx }js/plug-in/jquery-easyui-1.4.5/themes/icon.css">
<script type="text/javascript" src="${ctx }js/plug-in/jquery-easyui-1.4.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }js/plug-in/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }js/plug-in/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
<!-- 页面打印 -->
<script type="text/javascript" src="${ctx }js/plug-in/jquery.jqprint.js"></script>

<script type="text/javascript" src="${ctx }js/common.js" ></script>
<!--  <script src="js/jquery.min.js?v=2.1.4"></script> -->
 <script src="js/bootstrap.min.js?v=3.3.6"></script>

