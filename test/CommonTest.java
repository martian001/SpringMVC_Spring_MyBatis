import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.Test;

import com.et.util.DateUtils;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月4日下午2:52:48 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class CommonTest {
   @Test
   public void realExl() {
      try {
         // 构建Workbook对象, 只读Workbook对象
         // 直接从本地文件创建Workbook
         InputStream instream = new FileInputStream("D:\\指纹打卡记录=2016年5月=住房金融事业部.xls");
         jxl.Workbook readwb = Workbook.getWorkbook(instream);
         // Sheet的下标是从0开始
         // 获取第一张Sheet表
         Sheet readsheet = readwb.getSheet(0);
         // 获取Sheet表中所包含的总列数
         int rsColumns = readsheet.getColumns();
         if (rsColumns < 3) {
            return;
         }
         // 获取Sheet表中所包含的总行数
         int rsRows = readsheet.getRows();
         if (rsRows < 2) {
            return;
         }
         Map<String, String> WorkOvertime = new TreeMap<>();
         Map<String, String> late = new TreeMap<>();
         // 获取指定单元格的对象引用
         for (int i = 1; i < rsRows; i++) {
            int column = readsheet.findCell("姓名").getColumn();
            String name = readsheet.getCell(column, i).getContents();
            String date = readsheet.getCell(4, i).getContents();
            if ("梁衍君".equals(name)) {
               Integer hour = Integer.valueOf(date.substring(11, 13));
               String tempDate = date.substring(0, 11);// 2016-04-02
               if (hour >= 20 && WorkOvertime.containsKey(tempDate)) {
                  String object = WorkOvertime.get(tempDate);
                  boolean before = DateUtils.stringToDate(object).before(DateUtils.stringToDate(date));
                  if (before) {
                     WorkOvertime.put("下午" + tempDate + name, date);
                  }
               } else if (hour >= 20) {
                  WorkOvertime.put("下午" + tempDate + name, date);
               } else if (hour >= 9 && hour < 18) {
                  late.put("上午" + tempDate + name, date);
               }
            }
         }
         Set<String> keySet = WorkOvertime.keySet();
         int sum=0;
         for (String string : keySet) {
            String date = WorkOvertime.get(string);
            String start = date.substring(0, 11) + "18:00:00";
            int minutesDifference = DateUtils.minutesDifference(DateUtils.stringToDate(start), DateUtils.stringToDate(date));
            System.out.println(string + "  " + date + "  加班时间（分钟）：" + minutesDifference);
            sum=sum+minutesDifference;
         }
         System.out.println("加班天数：" + keySet.size()+"   总时长："+(sum/60)+"小时  "+(sum%60)+"分钟"+"  餐补"+(keySet.size()*25));
         System.out.println("--------------------------------------------");
         Set<String> lateSet = late.keySet();
         for (String key : lateSet) {
            System.out.println(key + "  " + late.get(key));
         }
         System.out.println("迟到天数：" + lateSet.size());
         readwb.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
