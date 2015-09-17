<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#name").change(function() {
			var $this = $(this);
			var nameVal = $this.val();
			if (nameVal != "") {
				$this.nextAll("font").remove();
				var url = "studentAction_nameIsExist.do";
				var agrs = {
					"name" : nameVal,
					"date" : new Date()
				};
				$.post(url, agrs, function(date) {
					if (date == "1") {
						$this.after("<font color='red'>已存在</font>");
					} else if (date == "0") {
						$this.after("<font color='greed'>可用</font>");
					} else {
						alert("系统错误");
					}

				})
			} else {
				alert("名字不能为空");
				$nameInput.focus();
			}
		})
	})
</script>
</head>
<body>
	<form action="studentAction_add.do" method="post">
		名字：<input type="text" id="name" name="name" /><br /> 年龄：<input
			type="text" name="age" /><br /> <input type="submit" value="保存" />
	</form>
</body>
</html>