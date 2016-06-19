package code.genarate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.et.util.CommomUtils;
import com.et.util.DateUtils;

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
   public void codeGenerate() {
      try {
         String srcPath = "C:/Users/Administrator/git/SpringMVC_Spring_MyBatis/src/";
         List<String> list = new ArrayList<>();
         list.add("DaoTempLate.ftl");
         list.add("DaoImplTempLate.ftl");
         list.add("ServiceTempLate.ftl");
         list.add("ServiceImplTempLate.ftl");
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("dateTime", DateUtils.getCurrentDateTime());
         map.put("version", "1.0");
         map.put("classAnnotation", "测试");
         map.put("beanName", "Pruduct");
         map.put("lowercaseBeanName", "pruduct");
         map.put("packagePath", "com.et.pruduct");
         for (String ftlPath : list) {
            String templateParsing = CommomUtils.templateParsing(map, ftlPath);
            String folderPath = srcPath + (map.get("packagePath").toString()).replace(".", "/");
            String fileName = (map.get("beanName").toString()) + ".java";
            System.out.println(folderPath);
            System.out.println(fileName);
            System.out.println(templateParsing);
            // FileUtils.textToFile(templateParsing, folderPath, fileName);
         }
      } catch (TemplateException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
