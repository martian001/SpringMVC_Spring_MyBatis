<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.et.mapper.${packageModule}.${beanName}Mapper">
	<resultMap id="${lowercaseBeanName}Map" type="${lowercaseBeanName}">
		<#list fieldMaps as map>
		<id property="${map.javaName}" column="${map.jdbcName}" />
		</#list>
	</resultMap>
	<sql id="column_list">
        <#list fieldMaps as map>${map.jdbcName}<#if map_has_next>,</#if></#list>
	</sql>
	<!-- 查询所有 -->
	<select id="getAll" resultMap="${lowercaseBeanName}Map">
		SELECT
		<include refid="column_list" />
		FROM ${tableName}
		<where>
			<trim>
		<#-- 遍历字段集合--> 
		<#list fieldMaps as map>
		     <#-- 如果java类型是字符串，则检查字符串是否为空 ，如果是数字，则检查是否大于零--> 
		    <#if (map.javaType == "java.lang.String"||map.javaType == "java.util.Date")>
		      <if test="${map.javaName} != null and ${map.javaName} !=''">
	          <#if (map_index!=fieldMaps?size && map_index!=0)>AND </#if>${map.jdbcName}=${r'#{'}${map.javaName}${r'}'}
	          </if>
		    <#else> 
		      <if test="${map.javaName} != null and ${map.javaName} >0">
	          <#if (map_index!=fieldMaps?size && map_index!=0)>AND </#if>${map.jdbcName}=${r'#{'}${map.javaName}${r'}'}
	          </if>
		    </#if>
		</#list>
			</trim>
		</where>
	</select>
	<!-- 根据id获取 -->
	<select id="getById" resultMap="${lowercaseBeanName}Map">
		SELECT <include refid="column_list" /> FROM ${tableName} WHERE ID=${r'#{id}'}
	</select>
	<!-- 插入一条数据 -->
	<insert id="insert" parameterType="${lowercaseBeanName}">
		<selectKey keyProperty="id" resultType="String" order="BEFORE">
			SELECT SYS_GUID() FROM DUAL
		</selectKey>
		INSERT INTO ${tableName}
		<trim prefix="(" suffix=")" suffixOverrides=",">
		    <#-- 遍历字段集合--> 
			<#list fieldMaps as map>
			<#-- 如果java类型是字符串，则检查字符串是否为空 ，如果是数字，则检查是否大于零--> 
			<#if (map.javaType == "java.lang.String"||map.javaType == "java.util.Date")>
		    <if test="${map.javaName} != null and ${map.javaName} !=''">
		    <#else> 
		    <if test="${map.javaName} != null and ${map.javaName} >0">
		    </#if>
				${map.jdbcName},
			</if>
		    </#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides=",">
		    <#-- 遍历字段集合--> 
			<#list fieldMaps as map>
			<#-- 如果java类型是字符串，则检查字符串是否为空 ，如果是数字，则检查是否大于零--> 
			<#if (map.javaType == "java.lang.String"||map.javaType == "java.util.Date")>
		    <if test="${map.javaName} != null and ${map.javaName} !=''">
		    <#else> 
		    <if test="${map.javaName} != null and ${map.javaName} >0">
		    </#if>
		     <#-- 如果jdbc类型是日期，则加入类型说明 --> 
			<#if (map.jdbcType == "DATE"||map.jdbcType == "TIMESTAMP")>
		    ${r'#{'}${map.javaName},jdbcType= ${map.jdbcType},javaType= ${map.javaType}${r'}'},
		    <#else> 
		    ${r'#{'}${map.javaName}${r'}'},
		    </#if>
			</if>
		    </#list>
		</trim>
	</insert>
	<!-- 根据id删除数据 -->
	<delete id="deleteById" parameterType="${lowercaseBeanName}">
		<![CDATA[DELETE FROM ${tableName} WHERE ID=${r'#{id}'}]]>
	</delete>
	<!-- 根据id更新数据 -->
		<update id="update" parameterType="${lowercaseBeanName}">
		UPDATE ${tableName}
		<set>
		    <#-- 遍历字段集合--> 
			<#list fieldMaps as map>
			<#-- 如果java类型是字符串，则检查字符串是否为空 ，如果是数字，则检查是否大于零--> 
			<#if (map.javaType == "java.lang.String"||map.javaType == "java.util.Date")>
		    <if test="${map.javaName} != null and ${map.javaName} !=''">
		    <#else> 
		    <if test="${map.javaName} != null and ${map.javaName} >0">
		    </#if>
		    <#-- 如果jdbc类型是日期，则加入类型说明 --> 
			<#if (map.jdbcType == "DATE"||map.jdbcType == "TIMESTAMP")>
		     ${map.jdbcName}=${r'#{'}${map.javaName},jdbcType= ${map.jdbcType},javaType= ${map.javaType}${r'}'},
		    <#else> 
		     ${map.jdbcName}=${r'#{'}${map.javaName}${r'}'},
		    </#if>
			</if>
		    </#list>
		</set>
		WHERE ID = ${r'#{id}'}
	</update>
	<!-- 根据传入多个id删除数据 -->
	<delete id="deleteByIds" parameterType="java.util.List">
		DELETE FROM ${tableName} WHERE ID in
		<foreach collection="list" item="item" index="index" open="("
			separator="," close=")">
			${r'#{item}'}
		</foreach>
	</delete>
</mapper>
