<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<title>H+ 后台主题UI框架 - 登录</title>
<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min.css" rel="stylesheet">
<link href="css/login.min.css" rel="stylesheet">
<!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
<script>
	if (window.top !== window.self) {
		window.top.location = window.location
	};
</script>

</head>
<%-- <body class="gray-bg">

 <div class="middle-box text-center loginscreen  animated fadeInDown">
  <div>
   <div>

    <h1 class="logo-name">H+</h1>

   </div>
   <h3>欢迎使用 H+</h3>

   <form class="m-t" role="form" action="${ctx}login.do" method="post">
    <div class="form-group">
     <input type="text" name="userName" class="form-control" placeholder="用户名" required="">
    </div>
    <div class="form-group">
     <input type="password" name="password" class="form-control" placeholder="密码" required="">
    </div>
    <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
    <c:if test="${msg!=null }">
      <div class="alert alert-danger">${msg }</div>
    </c:if>
    <p class="text-muted text-center">
     <a href="login.jsp#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
    </p>

   </form>
  </div>
 </div>
</body> --%>
<body class="signin">
 <div class="signinpanel">
  <div class="row">
   <div class="col-sm-7">
    <div class="signin-info">
     <div class="logopanel m-b">
      <h1>[ H+ ]</h1>
     </div>
     <div class="m-b"></div>
     <h4>
      欢迎使用 <strong>H+ 后台主题UI框架</strong>
     </h4>
     <ul class="m-b">
      <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
      <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
      <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
      <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
      <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
     </ul>
     <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
    </div>
   </div>
   <div class="col-sm-5">
    <form method="post" action="${ctx}login.do">
     <h4 class="no-margins">登录：</h4>
     <p class="m-t-md">登录到H+后台主题UI框架</p>
     <input type="text" name="userName" class="form-control uname" required="" placeholder="用户名" /> <input type="password" name="password" required="" class="form-control pword m-b" placeholder="密码" />
     <c:if test="${msg!=null }">
      <div class="alert alert-danger">${msg }</div>
     </c:if>
      <a href="#">忘记密码了？</a>
     <button class="btn btn-success btn-block">登录</button>
    </form>
   </div>
  </div>
  <div class="signup-footer">
   <div class="pull-left">&copy; 2015 All Rights Reserved. H+</div>
  </div>
 </div>
</body>
</html>