package com.et.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.UUID;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年6月6日上午9:50:24 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class CommomUtils {
   
   public static String getRootPath() {
      String classPath = CommomUtils.class.getClassLoader().getResource("/").getPath();
      String rootPath = "";
      // windows下
      if ("\\".equals(File.separator)) {
         // System.out.println("windows");
         rootPath = classPath.substring(1, classPath.indexOf("/WEB-INF/classes"));
         rootPath = rootPath.replace("/", "\\");
      }
      // linux下
      if ("/".equals(File.separator)) {
         // System.out.println("linux");
         rootPath = classPath.substring(0, classPath.indexOf("/WEB-INF/classes"));
         rootPath = rootPath.replace("\\", "/");
      }
      rootPath = rootPath.substring(0, rootPath.lastIndexOf(File.separator)+1);
      return rootPath;
   }
   /**
    * 解析模版
    *@author:liangyanjun
    *@time:2016年6月6日上午9:57:33
    *@param map
    *@param tempPath
    *@return
    *@throws TemplateException
    *@throws IOException
    */
   public static String templateParsing(Map<String, Object> map, String tempPath) throws TemplateException, IOException {
      /* 创建一configuration */
      Configuration cfg = new Configuration();
      // 这里我设置模版的根目录
      String path = CommomUtils.class.getResource("/ftl").getPath();
      cfg.setDirectoryForTemplateLoading(new File(path));
      cfg.setObjectWrapper(new DefaultObjectWrapper());
      /* 而以下代码你通常会在一个应用生命周期中执行多次 */
      /* 获取或创建一个模版 */
      Template temp = cfg.getTemplate(tempPath, "utf-8");
      /* 合并数据模型和模版 */
      Writer out = new StringWriter(2048);
      temp.process(map, out);
      return out.toString();
   }

   public static String getUUID() {
      UUID randomUUID = UUID.randomUUID();
      return randomUUID.toString().replace("-", "");
   }

   public static void main(String[] args) {
      System.out.println(getUUID());
   }
}
