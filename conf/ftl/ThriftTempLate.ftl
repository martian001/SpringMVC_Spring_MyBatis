/*
 *${classAnnotation}
 *${dateTime}
 **/
struct ${className}{
    <#-- 遍历字段集合--> 
	<#list fieldMaps as map>
		 <#-- 如果java类型是字符串，则检查字符串是否为空 ，如果是数字，则检查是否大于零--> 
		<#if (map.javaType == "java.lang.String"||map.javaType == "java.util.Date")>
	${map_index+1}: string ${map.javaName};//
		 <#elseif (map.javaType == "java.lang.Double")> 
	${map_index+1}: double ${map.javaName};//
	     <#else> 
	${map_index+1}: i32 ${map.javaName};//
		 </#if>
	</#list>
}
/*${classAnnotation}Service*/
service ${className}Service{
    //根据条件查询所有${classAnnotation}
	list<${className}> getAll(1:${className} ${lowercaseBeanName});
	//查询${classAnnotation}
	${className} getById(1:i32 pid);
	//新增${classAnnotation}
	i32 insert(1:${className} ${lowercaseBeanName});
	//修改${classAnnotation}
	i32 update(1:${className} ${lowercaseBeanName});
	//删除${classAnnotation}
	i32 deleteById(1:i32 pid);
	//批量删除${classAnnotation}
	i32 deleteByIds(1:list<i32> pids);
}
	