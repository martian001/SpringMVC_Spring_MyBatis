package com.xlkfinance.bms.server.aom.${packageModule}.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.achievo.framework.annotation.ThriftService;
import com.qfang.xk.aom.rpc.${packageModule}.${beanName};
import com.qfang.xk.aom.rpc.${packageModule}.${beanName}Service.Iface;
import com.qfang.xk.aom.server.system.mapper.${beanName}Mapper;
import com.xlkfinance.bms.rpc.common.ThriftServiceException;

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
@Service("${lowercaseBeanName}ServiceImpl")
@ThriftService(service = "com.qfang.xk.aom.rpc.${packageModule}.${beanName}Service")
public class ${beanName}ServiceImpl implements Iface {
   private Logger logger = LoggerFactory.getLogger(${beanName}ServiceImpl.class);

   @Resource(name = "${lowercaseBeanName}Mapper")
   private ${beanName}Mapper ${lowercaseBeanName}Mapper;

   /**
    *根据条件查询所有
    *@author:liangyanjun
    *@time:${dateTime}
    */
   @Override
   public List<${beanName}> getAll(${beanName} ${lowercaseBeanName}) throws ThriftServiceException, TException {
      return ${lowercaseBeanName}Mapper.getAll(${lowercaseBeanName});
   }

   /**
    *根据id查询
    *@author:liangyanjun
    *@time:${dateTime}
    */
   @Override
   public ${beanName} getById(int pid) throws ThriftServiceException, TException {
      ${beanName} ${lowercaseBeanName} = ${lowercaseBeanName}Mapper.getById(pid);
      if (${lowercaseBeanName}==null) {
         return new ${beanName}();
      }
      return ${lowercaseBeanName};
   }

   /**
    *插入一条数据
    *@author:liangyanjun
    *@time:${dateTime}
    */
   @Override
   public int insert(${beanName} ${lowercaseBeanName}) throws ThriftServiceException, TException {
      return ${lowercaseBeanName}Mapper.insert(${lowercaseBeanName});
   }

   /**
    *根据id更新数据
    *@author:liangyanjun
    *@time:${dateTime}
    */
   @Override
   public int update(${beanName} ${lowercaseBeanName}) throws ThriftServiceException, TException {
      return ${lowercaseBeanName}Mapper.update(${lowercaseBeanName});
   }

   /**
    *根据id删除数据
    *@author:liangyanjun
    *@time:${dateTime}
    */
   @Override
   public int deleteById(int pid) throws ThriftServiceException, TException {
      return ${lowercaseBeanName}Mapper.deleteById(pid);
   }

   /**
    *根据id集合删除
    *@author:liangyanjun
    *@time:${dateTime}
    */
   @Override
   public int deleteByIds(List<Integer> pids) throws ThriftServiceException, TException {
      return ${lowercaseBeanName}Mapper.deleteByIds(pids);
   }

}
