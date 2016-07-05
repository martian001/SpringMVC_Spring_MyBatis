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
package com.qfang.xk.aom.server.${packageModule};

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.achievo.framework.thrift.client.BaseClientFactory;
import com.achievo.framework.thrift.client.ThriftBinaryClientFactory;
import com.achievo.framework.thrift.exception.ThriftException;
import com.qfang.xk.aom.rpc.${packageModule}.${beanName};
import com.qfang.xk.aom.rpc.${packageModule}.${beanName}Service;
import com.xlkfinance.bms.common.util.DateUtils;

public class ${beanName}ServiceTest {
   private String serverIp = "127.0.0.1";
   private int serverPort = 19090;
   private String basePackage = "com.qfang.xk.aom.rpc";
   private ${beanName}Service.Client client;

   public BaseClientFactory getFactory(String serviceModuel, String serviceName) {
      String service = new StringBuffer().append(basePackage).append(".").append(serviceModuel).append(".").append(serviceName).toString();
      BaseClientFactory clientFactory = new ThriftBinaryClientFactory(serverIp, serverPort, 20000, service);
      return clientFactory;
   }

   @Before
   public void init() {
      BaseClientFactory clientFactory = getFactory("orgSystem", "${beanName}Service");
      try {
         client = (${beanName}Service.Client) clientFactory.getClient();
      } catch (ThriftException e) {
         e.printStackTrace();
         fail(e.getMessage());
      }
   }

   @Test
   public void test_insert() throws Exception {
      ${beanName} ${lowercaseBeanName} = new ${beanName}();
        <#-- 遍历字段集合--> 
		<#list fieldMaps as map>
		    <#-- 如果java类型是字符串，则检查字符串是否为空 ，如果是数字，则检查是否大于零--> 
		    <#if (map.javaType == "java.lang.String"||map.javaType == "java.util.Date")>
		      ${lowercaseBeanName}.set${map.javaName?cap_first}("${map.javaName}");
		    <#else> 
		      ${lowercaseBeanName}.set${map.javaName?cap_first}(1);
		    </#if>
		</#list>
      client.insert(${lowercaseBeanName});
   }
   @Test
   public void test_update() throws Exception {
      ${beanName} ${lowercaseBeanName} = new ${beanName}();
      	<#-- 遍历字段集合--> 
		<#list fieldMaps as map>
		    <#-- 如果java类型是字符串，则检查字符串是否为空 ，如果是数字，则检查是否大于零--> 
		    <#if (map.javaType == "java.lang.String"||map.javaType == "java.util.Date")>
		      ${lowercaseBeanName}.set${map.javaName?cap_first}("${map.javaName}");
		    <#else> 
		      ${lowercaseBeanName}.set${map.javaName?cap_first}(1);
		    </#if>
		</#list>
      client.update(${lowercaseBeanName});
   }

   @Test
   public void test_getAll() throws Exception {
      ${beanName} ${lowercaseBeanName} = new ${beanName}();
      List<${beanName}> list = client.getAll(${lowercaseBeanName});
      for (${beanName} obj : list) {
         System.out.println(obj);
      }
   }

   @Test
   public void test_getById() throws Exception {
      ${beanName} obj = client.getById(9);
      System.out.println(obj);
   }

   @Test
   public void test_deleteById() throws Exception {
      client.deleteById(8);
   }

   @Test
   public void test_deleteByIds() throws Exception {
      List<Integer> pids = new ArrayList<Integer>();
      pids.add(6);
      pids.add(7);
      client.deleteByIds(pids);
   }
}
