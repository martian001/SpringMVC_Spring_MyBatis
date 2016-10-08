package code.genarate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.et.util.CommonUtil;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年7月1日上午8:53:41 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class MapperXmlTempLateUtil {
   private final static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
   private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
   private static Connection conn = null;

   public static final Map<String, String> TYPE_MAP = new HashMap<String, String>() {
      private static final long serialVersionUID = 1L;
      {
         put("BIGINT", "long");
         put("BINARY", "byte[]");
         put("BIT", "java.lang.Boolean");
         put("BLOB", "java.lang.String");
         put("CLOB", "java.lang.String");
         put("DATE", "java.util.Date");
         put("DECIMAL", "java.math.BigDecimal");
         put("DOUBLE", "java.lang.Double");
         put("FLOAT", "java.lang.Double");
         put("INTEGER", "java.lang.Integer");
         put("JAVA_OBJECT", "java.lang.Object");
         put("LONGVARBINARY", "byte[]");
         put("LONGVARCHAR", "java.lang.String");
         put("OTHER", "java.lang.Object");
         put("REAL", "java.lang.Float");
         put("SMALLINT", "java.lang.Integer");
         put("TIME", "java.sql.Time");
         put("TIMESTAMP", "java.lang.String");
         put("TINYINT", "java.lang.Bute");
         put("VARBINARY", "byte[]");
         put("VARCHAR", "java.lang.String");
         put("VARCHAR2", "java.lang.String");
         put("NUMBER", "java.lang.Integer");
      }
   };

   static {// 静态代码块.
      try {
         Class.forName(DRIVER);
         conn = DriverManager.getConnection(URL, "oadb", "Ab123456");// url,用户名和密码
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * 获取数据库元数据.
    * @throws Exception
    */
   @Test
   public void testMetaData() throws Exception {
      Map<String, Object> m = new HashMap<String, Object>();
      String tableName = "T_SMS_VALIDATE_CODE_INFO";
      m.put("tableName", tableName);
      m.put("beanName", jdbcToJavaName2(tableName));
      m.put("packageModule", "system");
      m.put("lowercaseBeanName", jdbcToJavaName(tableName));

      List<Map<String, String>> maps = getMetaData(tableName);
      // for (Map<String, String> fieldMap : maps) {
      // String jdbcName = fieldMap.get("jdbcName");
      // String jdbcType = fieldMap.get("jdbcType");
      // String javaName = fieldMap.get("javaName");
      // String javaType = fieldMap.get("javaType");
      // System.out.println(javaName + "(" + javaType + ") -----------" + jdbcName + "(" + jdbcType + ")");
      // }
      m.put("fieldMaps", maps);
      String templateParsing = CommonUtil.templateParsing(m, "MapperXmlTempLate2.ftl");
      System.out.println(templateParsing);
   }

   /**
    * 获取元数据集合
    *@author:liangyanjun
    *@time:2016年7月1日上午11:53:54
    *@param tableName
    *@return
    *@throws SQLException
    */
   public static List<Map<String, String>> getMetaData(String tableName) throws SQLException {
      List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + tableName);
      ResultSetMetaData rsmd = rs.getMetaData();// 从查询结果集中获取结果集元数据
      int count = rsmd.getColumnCount();// 获取表中字段总数.
      for (int i = 1; i <= count; i++) {// mySql从0开始，oracle从1开始
         String jdbcName = rsmd.getColumnName(i);// 表的字段名.
         String jdbcType = rsmd.getColumnTypeName(i);// jdbc字段类型
         String javaType = TYPE_MAP.get(jdbcType);// java字段类型
         String javaName = jdbcToJavaName(jdbcName);// java字段名
         rs.next();
         Map<String, String> fieldMap = new HashMap<>();
         fieldMap.put("jdbcName", jdbcName);
         fieldMap.put("jdbcType", jdbcType);
         fieldMap.put("javaName", javaName.toString());
         fieldMap.put("javaType", javaType);
         fieldMap.put("javaSimpleType", javaType.substring(javaType.lastIndexOf(".")+1));
         maps.add(fieldMap);
      }
      return maps;
   }

   public static String jdbcToJavaName(String jdbcName) {
      StringBuffer javaName = new StringBuffer();
      String[] words = jdbcName.toLowerCase().split("_");// 表的字段名转成小写并且分割成单词
      for (int j = 0; j < words.length; j++) {
         String word = words[j];// 获取单词
         if (j > 0) {
            javaName.append(startUpperCase(word));
            continue;
         }
         javaName.append(word);
      }
      return javaName.toString();
   }
   public static String jdbcToJavaName2(String jdbcName) {
      StringBuffer javaName = new StringBuffer();
      String[] words = jdbcName.toLowerCase().split("_");// 表的字段名转成小写并且分割成单词
      for (int j = 0; j < words.length; j++) {
         String word = words[j];// 获取单词
         javaName.append(startUpperCase(word));
      }
      return javaName.toString();
   }

   /**
    * 首字母大写方法
    *@author:liangyanjun
    *@time:2016年7月1日上午9:23:55
    *@param name
    *@return
    */
   public static String startUpperCase(String name) {
      name = name.substring(0, 1).toUpperCase() + name.substring(1);
      return name;
   }
   public static String startLowerCase(String name) {
      name = name.substring(0, 1).toLowerCase() + name.substring(1);
      return name;
   }

}