package com.et.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.et.bean.Json;


/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年4月20日下午6:43:30 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class BaseAction {
   /**
    * 获取服务端的地址
    * 例如：http://localhost:8080
    *@author:liangyanjun
    *@time:2016年4月7日上午11:36:39
    *@param request
    *@return
    */
   protected String getServerUrl(HttpServletRequest request) {
      StringBuffer requestURL = request.getRequestURL();
      String contextPathFullUrl = requestURL.substring(0, requestURL.indexOf(request.getServletPath()));
      String serverUrl = contextPathFullUrl.substring(0, contextPathFullUrl.indexOf(request.getContextPath()));
      return serverUrl;
   }

   /**
    * 
    *@author:liangyanjun
    *@time:2016年4月20日下午6:45:23
    *@param returnMap
    *@param response
    */
   public void outputJson(Map<String, Object> returnMap, HttpServletResponse response) {
      try {
         String retrunJson = JSON.toJSONString(returnMap);
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.write(retrunJson);
         out.flush();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   protected void fillReturnJson(HttpServletResponse response,boolean isSucc,String msg) {
      com.et.bean.Json j = new Json();
      j.getHeader().put("success", isSucc);
      j.getHeader().put("msg", msg);
      outputJson(j, response);
   }
   protected void outputJson(Object jsonObj, HttpServletResponse response) {
      // 兼容IE浏览器
      response.setContentType("text/html;charset=utf-8");
      response.setHeader("Cache-Control", "no-cache");
      try {
         PrintWriter pw = response.getWriter();
         // 将Java对象转换为JSON字符串
         String gsonStr = new ObjectMapper().writeValueAsString(jsonObj);
         // 输出JSON字符串
         pw.print(gsonStr);
         pw.flush();
         pw.close();
      } catch (IOException e) {
         System.out.println("输出GSON出错：" + e);
      }
   }
}
