<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.et.mapper.${packageModule}.${beanName}Mapper">
    <resultMap id="${lowercaseBeanName}Map" type="${lowercaseBeanName}">
		<id property="id" column="ID" />
	</resultMap>
	<!-- 查询所有 -->
	<select id="getAll" resultMap="${lowercaseBeanName}Map">
		<![CDATA[SELECT * FROM ${table} ]]>
	</select>
	<!-- 根据条件分页查询  -->
	<select id="findPage" resultMap="${lowercaseBeanName}Map" parameterType="${lowercaseBeanName}">
	<if test="page != -1">
	SELECT D.* FROM (SELECT T.*, ROWNUM RN FROM (
	</if>
    SELECT * FROM ${table} A
		<where>
			<trim>
				<if test="id!=null and id !=''">
					AND A.ID = #{id}
				</if>
			</trim>
		</where>
		<if test="page != -1">
		 ) T WHERE ROWNUM <=#{page}*#{rows} ) D WHERE D.RN > ((#{page}-1)*#{rows}) 
		</if>
	</select>
	<!-- 根据条件查询数量 -->
	<select id="getTotal" resultType="Integer" parameterType="${lowercaseBeanName}">
	<![CDATA[
	SELECT COUNT(1) FROM
			(SELECT * FROM ${table} A
	]]>
		<where>
			<!-- 用来处理如果where后面是以and开头的条件,那么可以自动去掉and -->
			<trim>
				<if test="id!=null and id !=''">
					AND A.ID = #{id}
				</if>
			</trim>
		</where>
		) T
	</select>
	<select id="getById" resultMap="${lowercaseBeanName}Map">
		<![CDATA[SELECT * FROM ${table} WHERE ID=#{id}]]>
	</select>
	<insert id="insert" parameterType="${lowercaseBeanName}" useGeneratedKeys="true" keyProperty="id">
	   <selectKey keyProperty="id" resultType="String" order="BEFORE">
        SELECT SYS_GUID() FROM DUAL
       </selectKey>
		INSERT INTO ${table}
		<trim prefix="(" suffix=")" suffixOverrides=",">
			  ID,
	          <if test="userName !=null and userName !=''">
	                USER_NAME,
	          </if>
         </trim>
         <trim prefix="values (" suffix=")" suffixOverrides=",">
			  #{id},
	          <if test="userName !=null and userName !=''">
	               #{userName} ,
	          </if>
         </trim>
	</insert>
	<delete id="deleteById" parameterType="${lowercaseBeanName}">
		<![CDATA[DELETE FROM ${table} WHERE ID=#{id}]]>
	</delete>
	<update id="update" parameterType="${lowercaseBeanName}">
		 UPDATE ${table} 
		 <set>
	        <if test="userName !=null and userName !=''">
	         USER_NAME=#{userName} ,
	        </if>
         </set> 
         where id=#{id}
	</update>
</mapper>
