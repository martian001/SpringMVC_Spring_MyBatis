package com.et.web.action.checkingIn;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.base.BaseAction;
import com.et.bean.checkingIn.CheckingIn;
import com.et.bean.checkingIn.CheckingInIndex;
import com.et.bean.checkingIn.CheckingInRecord;
import com.et.bean.system.BizFile;
import com.et.bean.system.SysUser;
import com.et.service.checkingIn.CheckingInRecordService;
import com.et.service.checkingIn.CheckingInService;
import com.et.service.system.SysUserService;
import com.et.util.CommonUtil;
import com.et.util.DateUtils;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午11:49:55 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Controller
@RequestMapping("/checkingInAction")
public class CheckingInAction extends BaseAction {
   private final String PATH = "checkingIn";
   @Resource
   private SysUserService sysUserService;
   @Resource
   private CheckingInService checkingInService;
   @Resource
   private CheckingInRecordService checkingInRecordService;

   @RequestMapping("/to_checkingInAction_list.do")
   public String to_list() {
      return PATH + "/list";
   }
   @RequestMapping("/to_jqgrid_list.do")
   public String to_jqgrid_list() {
      return PATH + "/jqgrid_list";
   }

   @RequestMapping("/checkingInAction_jqgrid_list.do")
   public void jqgrid_list(CheckingInIndex checkingInIndex, HttpServletResponse response) {
      Map<String, Object> resultMap = new HashMap<String, Object>();
      List<CheckingInIndex> list = checkingInService.queryCheckingInIndex(checkingInIndex);
      int total = checkingInService.getCheckingInIndexTotal(checkingInIndex);
      // 输出
      resultMap.put("rows", list);
      resultMap.put("total", total/checkingInIndex.getRows());
      resultMap.put("records", total);
      outputJson(resultMap, response);
   }
   @RequestMapping("/checkingInAction_list.do")
   public void list(CheckingInIndex checkingInIndex, HttpServletResponse response) {
      Map<String, Object> resultMap = new HashMap<String, Object>();
      List<CheckingInIndex> list = checkingInService.queryCheckingInIndex(checkingInIndex);
      int total = checkingInService.getCheckingInIndexTotal(checkingInIndex);
      // 输出
      resultMap.put("rows", list);
      resultMap.put("total", total);
      outputJson(resultMap, response);
   }

   @RequestMapping("/initExlData.do")
   public void initExlData(HttpServletRequest request, HttpServletResponse response) {
      try {
         Map<String, Object> fileMap = getFileMap(request, response, "", "chackingIn");
         BizFile bizFile = (BizFile) fileMap.get("bizFile");
         File file=new File(CommonUtil.getRootPath()+bizFile.getFileUrl());
         jxl.Workbook readwb = Workbook.getWorkbook(file);
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
         // 获取指定单元格的对象引用
         for (int i = 1; i < rsRows; i++) {
            String checkingInDate = readsheet.getCell(readsheet.findCell("日期时间").getColumn(), i).getContents();
            String realName = readsheet.getCell(readsheet.findCell("姓名").getColumn(), i).getContents();
            String deptName = readsheet.getCell(readsheet.findCell("部门").getColumn(), i).getContents();
            System.out.println(realName + "," + checkingInDate + "," + deptName);
            String userId;
            SysUser sysUser = new SysUser();
            sysUser.setRealName(realName);
            sysUser.setDeptName(deptName);
            sysUser.setPage(-1);
            // 根据部门名称和用户姓名查询是否已存在，不存在添加
            List<SysUser> users = sysUserService.findPage(sysUser);
            if (users == null || users.isEmpty()) {
               sysUserService.insert(sysUser);
               userId = sysUser.getId();
            } else {
               userId = users.get(0).getId();
            }
            if (userId == null) {
               System.out.println();
            }
            Date stringToDate = DateUtils.stringToDate(checkingInDate);
            // 保存考勤记录
            CheckingInRecord checkingInRecord = new CheckingInRecord();
            checkingInRecord.setCheckingInDate(stringToDate);
            checkingInRecord.setMonth(stringToDate.getMonth());
            checkingInRecord.setUserId(userId);
            checkingInRecordService.insert(checkingInRecord);
         }
         readwb.close();

         CheckingInIndex checkingInIndexQuery = new CheckingInIndex();
         checkingInIndexQuery.setPage(-1);
         List<CheckingInIndex> checkingInIndexs = checkingInService.queryHandleCheckingIn(checkingInIndexQuery);
         for (CheckingInIndex checkingInIndex : checkingInIndexs) {
            String startDateStr = checkingInIndex.getStartDate();
            String tempDate = startDateStr.substring(0, 11);
            Date startDate = DateUtils.stringToDate(startDateStr);
            Date endDate = DateUtils.stringToDate(checkingInIndex.getEndDate());
            int hours = startDate.getHours();
            int minutes = startDate.getMinutes();
            int overTime = 0;
            int minutesDifference = 0;
            boolean isLate;
            Date lateDate = DateUtils.stringToDate(tempDate + " 9:30:00");
            if ((hours < 9) || (hours < 10 && minutes <= 10)) {
               startDate = DateUtils.stringToDate(tempDate + " 9:00:00");
               minutesDifference = DateUtils.minutesDifference(startDate, endDate);
               overTime = minutesDifference - 540;
            } else if ((hours < 10 && minutes > 10)) {
               startDate = DateUtils.stringToDate(tempDate + " 9:30:00");
               minutesDifference = DateUtils.minutesDifference(startDate, endDate);
               overTime = minutesDifference - 540;
            } else if ((hours >= 12 && hours <= 14) || (hours <= 14 && minutes <= 10)) {
               startDate = DateUtils.stringToDate(tempDate + " 14:00:00");
               minutesDifference = DateUtils.minutesDifference(startDate, endDate);
               overTime = minutesDifference - 240;
            } else {
               startDate = DateUtils.stringToDate(tempDate + " 14:00:00");
               lateDate = DateUtils.stringToDate(tempDate + " 14:10:00");
            }
            isLate = DateUtils.secondsDifference(startDate, lateDate) == 0;

            CheckingIn checkingIn = new CheckingIn();
            if (isLate) {
               checkingIn.setStatus(2);
            } else {
               checkingIn.setStatus(1);
            }
            if (overTime >= 120) {
               checkingIn.setOverTime(overTime);
            }
            checkingIn.setUserId(checkingInIndex.getUserId());
            checkingIn.setStartDate(DateUtils.stringToDate(startDateStr));
            checkingIn.setEndDate(endDate);
            System.out.println(checkingIn);
            checkingInService.insert(checkingIn);
         }
         fillReturnJson(response, true, "提交成功");
      } catch (Exception e) {
         fillReturnJson(response, false, "提交失败");
         e.printStackTrace();
      }
   }

   @RequestMapping("/delByIds.do")
   public void delByIds(String ids, HttpServletResponse response) {
      if (StringUtils.isEmpty(ids)) {
         return;
      }
      List<String> asList = Arrays.asList(ids.split(","));
      checkingInService.deleteByIds(asList);
      fillReturnJson(response, true, "提交成功");
   }
}
