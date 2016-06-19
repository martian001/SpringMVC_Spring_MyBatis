<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录</title>
<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
<!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
</script>

</head>
<body class="gray-bg">

 <div class="middle-box text-center loginscreen  animated fadeInDown">
  <div>
   <div>

    <h1 class="logo-name">H+</h1>

   </div>
   <h3>欢迎使用 H+</h3>

   <form class="m-t" role="form" action="http://www.zi-han.net/theme/hplus/index.html">
    <div class="form-group">
     <input type="email" class="form-control" placeholder="用户名" required="">
    </div>
    <div class="form-group">
     <input type="password" class="form-control" placeholder="密码" required="">
    </div>
    <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


    <p class="text-muted text-center">
     <a href="login.jsp#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
    </p>

   </form>
  </div>
 </div>
</body>
</html>