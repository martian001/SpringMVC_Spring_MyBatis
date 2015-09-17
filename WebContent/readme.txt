1.重定向redirectAction

2.方法命名list,add,addUI,delete,edit,editUI

3.action命名user_list,user_add...

3.逻辑页面list,add,toList,edit

4.集合可以保存在ActionContext.getContext().put("userList",userList),在jsp通过iterate value="#userList"访问

5.删除带id:<s:a action="user_delete?%{id}"/>

6.修改的时候要先获取持久化对象，修改持久化对象的值在进行update，切记不要new，因为new就是新的对象了

6.return confirm('是否要删除')

7.添加和修改合并：<s:form action="userAction_%{id==null?'add':'update'}"></s:form>

8.添加学生的时候，先根据添加选择的班里id从数据库中获取班级对象，再把学生的班级对象赋值，最后调用保存save方法

8.在StudentAction为了方便可以这个classId字段，方便修改（回显）的添加(获取班级对象)学生。

9.在关联查询中因为懒加载而导致异常的解决方案：在web.xml中配置spring用于解决懒加载的过滤器（OpenSessionViewFilter类）过滤条件为*.action,记住一定要有struts2的过滤器之前

10.重构action，抽取BaseAction基类,把所有的service的声明和实现的ModelDrenver放到BaseAction，利用泛型和反射给model实例化