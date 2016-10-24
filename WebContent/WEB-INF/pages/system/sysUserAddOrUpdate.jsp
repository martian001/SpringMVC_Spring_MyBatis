<%@page import="com.et.bean.system.SysUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../../common.jsp"%>
<%SysUser updateUser=(SysUser)request.getAttribute("sysUser"); %>
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#sysUserForm").validate({
			rules : {
				<% if(updateUser==null){%>
				userName : {
					required : true,
				 	checkLoginName : true,
					checkUserNameIsExist : true
				},
				phone : {
					required : true,
					isMobile : true,
					checkPhoneIsExist : true
				},<%}%>
				realName : {
					required : true,
				 	maxlength :20
				},
				status : {
					required : true
				},
				memberId : {
					required : true,
					maxlength :20
				},
				deptName : {
					required : true,
					maxlength :20
				},
				mail : {
					required : true,
					email : true
				}
				<% if(updateUser==null){%>
				,
				pwd : {
					required : true,
					isPwd : true
				}
				<%}%>
			},
			submitHandler : function(form) {
				$.ajax({
					url : "${ctx}sysUserController/addOrUpdate.do",
					type : "POST",
					data : $("#sysUserForm").serialize(),
					async : false,
					success : function(result) { //表单提交后更新页面显示的数据
						var ret = eval("(" + result + ")");
						if (ret && ret.header["success"]) {
							layer.confirm(ret.header["msg"], {
								icon : 6,
								btn : [ '是' ]
							//按钮
							}, function() {
								refreshTab("${ctx}sysUserController/toList.do","${ctx}sysUserController/toList.do");
								<% if(updateUser==null){%>
								closeTab();
								<%}else{%>
								refreshPage();
								<%}%>
							});
						} else {
							layer.alert(ret.header["msg"], {
								icon : 5
							});
						}
					}
				});
			}
		})
	});
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
								 <c:if test="${sysUser eq null}"><h5>增加</h5></c:if>
                                 <c:if test="${not empty sysUser.id}"><h5>编辑</h5></c:if>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a> <a class="close-link"> <i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">
						       <form role="form" id="sysUserForm" class="form-horizontal" action="#" method="post">
						        <c:if test="${not empty sysUser}">
						        <input type="hidden" name="id" id="id" value="${sysUser.id }">
						        </c:if>
						        <div class="form-group">
						         <label for="userName" class="col-sm-2 control-label"><span class="requiredSty">*</span>用户名:</label>
						         <div class="col-sm-4">
						          <input type="text" class="form-control" value="${sysUser.userName }" id="userName" name="userName" maxlength="20"
						           <c:if test="${not empty sysUser.id}">readonly="readonly"</c:if> placeholder="用户名"
						          >
						         </div>
						         <label for="phone" class="col-sm-2 control-label"><span class="requiredSty">*</span>手机号码:</label>
						         <div class="col-sm-4">
						          <input type="text" class="form-control" value="${sysUser.phone }" maxlength="20" id="phone" name="phone" <c:if test="${not empty sysUser.id}">readonly="readonly"</c:if>
						           placeholder="手机号"
						          >
						         </div>
						        </div>
						        <div class="form-group">
						         <label for="realName" class="col-sm-2 control-label"><span class="requiredSty">*</span>真实姓名:</label>
						         <div class="col-sm-4">
						          <input type="text" class="form-control" value="${sysUser.realName }" id="realName" name="realName" placeholder="真实姓名" maxlength="20">
						         </div>
						         <label for="status" class="col-sm-2 control-label"><span class="requiredSty">*</span>状态:</label>
						         <div class="col-sm-4">
						          <select class="form-control" name="status" id="status">
						           <option value=1 <c:if test="${sysUser.status==1 }">selected="selected"</c:if>>有效</option>
						           <option value=2 <c:if test="${sysUser.status==2 }">selected="selected"</c:if>>无效</option>
						          </select>
						         </div>
						        </div>
						        <div class="form-group">
						         <label for="memberId" class="col-sm-2 control-label"><span class="requiredSty">*</span>工号:</label>
						         <div class="col-sm-4">
						          <input type="text" class="form-control" value="${sysUser.memberId }" id="memberId" name="memberId" placeholder="工号" maxlength="20">
						         </div>
						         <label for="deptName" class="col-sm-2 control-label"><span class="requiredSty">*</span>部门:</label>
						         <div class="col-sm-4">
						          <input type="text" class="form-control" value="${sysUser.deptName }" id="deptName" name="deptName" placeholder="部门" maxlength="30">
						         </div>
						        </div>
						        <div class="form-group">
						         <label for="mail" class="col-sm-2 control-label"><span class="requiredSty">*</span>邮箱:</label>
						         <div class="col-sm-4">
						          <input type="text" class="form-control" value="${sysUser.mail }" id="mail" name="mail" placeholder="邮箱" maxlength="30">
						         </div>
						         <c:if test="${sysUser eq null}">
						         <label for="pwd" class="col-sm-2 control-label"><span class="requiredSty">*</span>密码:</label>
						         <div class="col-sm-4">
						          <input type="password" class="form-control" id="pwd" name="pwd" placeholder="密码"  maxlength="30">
						         </div>
						        </c:if>
						        </div>
						        <div class="form-group">
						         <label for="remark" class="col-sm-2 control-label">备注:</label>
						         <div class="col-sm-10">
						          <textarea rows="1" cols="10" class="form-control" name="remark" id="remark" placeholder="备注"  maxlength="200">${sysUser.remark}</textarea>
						         </div>
						        </div>
						        <div class="form-group forum-info">
						         <input type="submit" class="btn btn-primary an_bk" value="提交" name="commit">
						         <input type="reset" class="btn btn-w-m btn-white cz_an" value="重置" name="commit">
						        </div>
						       </form>

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