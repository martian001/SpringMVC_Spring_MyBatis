package code.genarate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.et.util.CommomUtils;
import com.et.util.DateUtils;
import com.et.util.FileUtils;

import freemarker.template.TemplateException;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年6月6日上午9:46:00 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class CodeGenerateMachine {
   @Test
   public void codeGenerate() throws SQLException {
      try {
         String srcPath = "C:/Users/Administrator/git/SpringMVC_Spring_MyBatis/src/";
         String mapperPath = "C:/Users/Administrator/git/SpringMVC_Spring_MyBatis/mapper/";
         List<String> tempLates = new ArrayList<>();
         tempLates.add("MapperTempLate.ftl-Mapper");
         tempLates.add("ServiceTempLate.ftl-Service");
         tempLates.add("ServiceImplTempLate.ftl-Service");
         tempLates.add("BeanTempLate.ftl-Bean");
         tempLates.add("ActionTempLate.ftl-Action");
         tempLates.add("MapperXmlTempLate.ftl-Mapper");

         List<Map<String, Object>> list = new ArrayList<>();
         String tableName = "ORG_ASSETS_COOPERATION_INFO";// 数据库表名
         String name = MapperXmlTempLateUtil.jdbcToJavaName2(tableName);// 类名
         String lowercaseBeanName = MapperXmlTempLateUtil.startLowerCase(name);// 类名小写
         String dateTime = DateUtils.getCurrentDateTime();// 当前时间
         String version = "1.0";// 版本号
         String classAnnotation = "机构管理平台-资产合作信息";// 类注释
         String packageModule = "org";// 模块
         String packageRoot = "com/et/";// 包根路径

         for (String tempLate : tempLates) {
            Map<String, Object> map = new HashMap<String, Object>();
            String packageLayer = tempLate.substring(tempLate.lastIndexOf("-") + 1);
            tempLate = tempLate.substring(0, tempLate.lastIndexOf("-"));
            map.put("tempLate", tempLate);
            if ("ServiceImplTempLate.ftl".equals(tempLate)) {
               map.put("className", name + packageLayer + "Impl");
            } else {
               map.put("className", name + packageLayer);
            }
            map.put("beanName", name);
            map.put("lowercaseBeanName", lowercaseBeanName);
            map.put("dateTime", dateTime);
            map.put("version", version);
            map.put("classAnnotation", classAnnotation);
            map.put("packageModule", packageModule);// 包-模块
            map.put("packageLayer", packageLayer.toLowerCase());// 包，层级（action,service,mapper）
            if ("ActionTempLate.ftl".equals(tempLate)) {
               map.put("packageLayer", "web." + packageLayer.toLowerCase());
            }
            map.put("tableName", tableName);
            list.add(map);
         }
         for (Map<String, Object> m : list) {
            String ftlPath = (String) m.get("tempLate");
            String tempLate = (String) m.get("tempLate");
            if ("MapperXmlTempLate.ftl".equals(tempLate)) {
               m.put("fieldMaps", MapperXmlTempLateUtil.getMetaData(tableName));
            }
            String templateParsing = CommomUtils.templateParsing(m, ftlPath);
            System.out.println(templateParsing);
            String folderPath = srcPath + packageRoot + m.get("packageLayer").toString().replace(".", File.separator) + File.separator
                  + m.get("packageModule").toString() + File.separator;
            if ("ServiceImplTempLate.ftl".equals(tempLate)) {
               folderPath = srcPath + packageRoot + m.get("packageLayer").toString().replace(".", File.separator) + File.separator + m.get("packageModule").toString()
                     + "/impl/";
            }
            String fileName = (m.get("className").toString()) + ".java";
            if ("BeanTempLate.ftl".equals(tempLate)) {
               fileName = (m.get("beanName").toString()) + ".java";
            }
            if ("MapperXmlTempLate.ftl".equals(tempLate)) {
               folderPath = mapperPath + m.get("packageModule").toString() + File.separator;
               fileName = (m.get("beanName").toString()) + "Mapper.xml";
            }
            System.out.println(folderPath);
            System.out.println(fileName);
            FileUtils.textToFile(templateParsing, folderPath, fileName);
         }
      } catch (TemplateException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
