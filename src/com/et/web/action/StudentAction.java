package com.et.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.et.base.BaseAction;
import com.et.bean.Student;
import com.et.service.MailService;
import com.et.service.StudentService;

/**
 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： liangyanjun                                       ☆★
★☆            @time：2015年9月16日下午4:31:54    ☆★
★☆            @version：                                                                          ☆★
★☆            @lastMotifyTime：                                                   ☆★
★☆            @ClassAnnotation：                                                ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
@Controller
public class StudentAction extends BaseAction {
   @Resource
   private StudentService studentService;
   @Resource
   private MailService mailService;

   @RequestMapping("/to_studentAction_list.do")
   public String to_list() {
      return "studentAction/list";
   }

   @RequestMapping("/studentAction_list.do")
   public void list(Student studentQuery, HttpServletResponse response) {
      Map<String, Object> resultMap = new HashMap<String, Object>();
      List<Student> studentList = studentService.findPage(studentQuery);
      int total = studentService.getTotal(studentQuery);
      // 输出
      resultMap.put("rows", studentList);
      resultMap.put("total", total);
      outputJson(resultMap, response);
   }

   @RequestMapping("/studentAction_delete.do")
   public String delete(Long id) {
      studentService.deleteById(id);
      return "forward:to_studentAction_list.do";
   }

   @RequestMapping("/studentAction_addUI.do")
   public String addUI() {
      return "studentAction/addUI";
   }

   @RequestMapping("/studentAction_editUI.do")
   public String editUI(ModelMap map, Long id) {
      Student student = studentService.getById(id);
      map.put("student", student);
      return "studentAction/editUI";
   }

   @RequestMapping("/studentAction_nameIsExist.do")
   public @ResponseBody String nameIsExist(String name) {
      Student student = new Student();
      student.setName(name);
      return studentService.getTotal(student) > 0 ? "1" : "0";
   }

   @RequestMapping("/studentAction_add.do")
   public String add(String name, Integer age) {
      studentService.insert(new Student(null, name, age));
      return "forward:to_studentAction_list.do";
   }

   @RequestMapping("/studentAction_update.do")
   public String update(Long id, String name, Integer age) {
      studentService.update(new Student(id, name, age));
      return "forward:to_studentAction_list.do";
   }

   public void setStudentService(StudentService studentService) {
      this.studentService = studentService;
      System.out.println("StudentAction.enclosing_method()");
   }
}
