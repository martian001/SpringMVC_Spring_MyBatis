<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h2>学生管理</h2>
  <a href="studentAction_addUI.do">添加</a>
 <br />
 <table border="1" cellspacing="0" cellpadding="0">
  <tr>
   <th width="85" scope="col">id</th>
   <th width="94" scope="col">name</th>
   <th width="97" scope="col">age</th>
   <th width="118" scope="col">&nbsp;</th>
  </tr>
  <c:forEach items="${requestScope.studentList }" var="student">
   <tr>
    <td>${student.id }</td>
    <td>${student.name }</td>
    <td>${student.age }</td>
    <td><a href="studentAction_delete.do?id=${student.id }"> delete</a>&nbsp;&nbsp; <a href="studentAction_editUI.do?id=${student.id }">edit</a></td>
   </tr>
  </c:forEach>
 </table>
 <br />
</body>
</html>