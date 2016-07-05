package com.qfang.xk.aom.server.${packageModule}.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.mybatis.mapper.BaseMapper;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：${dateTime} <br>
 * ★☆ @version：  ${version}<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： ${classAnnotation}<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@MapperScan("${lowercaseBeanName}Mapper")
public interface ${className}<T, PK> extends BaseMapper<T, PK>{
   /**
    *根据条件查询所有
    *@author:liangyanjun
    *@time:${dateTime}
    */
   public List<${beanName}> getAll(${beanName} ${lowercaseBeanName});

   /**
    *根据id查询
    *@author:liangyanjun
    *@time:${dateTime}
    */
   public ${beanName} getById(int pid);

   /**
    *插入一条数据
    *@author:liangyanjun
    *@time:${dateTime}
    */
   public int insert(${beanName} ${lowercaseBeanName});

   /**
    *根据id更新数据
    *@author:liangyanjun
    *@time:${dateTime}
    */
   public int update(${beanName} ${lowercaseBeanName});

   /**
    *根据id删除数据
    *@author:liangyanjun
    *@time:${dateTime}
    */
   public int deleteById(int pid);

   /**
    *根据id集合删除
    *@author:liangyanjun
    *@time:${dateTime}
    */
   public int deleteByIds(List<Integer> pids);
}
