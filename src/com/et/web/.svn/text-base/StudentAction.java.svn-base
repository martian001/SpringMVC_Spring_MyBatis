package com.et.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.et.bean.Student;
import com.et.service.StudentService;

/**
 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年11月26日 下午8:09:39      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
@Controller
public class StudentAction {
   @Resource
   private StudentService studentService;

   @RequestMapping("/studentAction_list.do")
   public String list(ModelMap map) {
      List<Student> studentList = studentService.getAll();
      map.put("studentList", studentList);
      return "studentAction/list";
   }

   @RequestMapping("/studentAction_delete.do")
   public String delete(Long id) {
      Student student = studentService.getById(id);
      studentService.delete(student);
      return "forward:studentAction_list.do";
   }

   @RequestMapping("/studentAction_addUI.do")
   public String addUI() {
      return "studentAction/addOrEditUI";
   }

   @RequestMapping("/studentAction_editUI.do")
   public String editUI(ModelMap map, Long id) {
      Student student = studentService.getById(id);
      map.put("student", student);
      return "studentAction/addOrEditUI";
   }

   @RequestMapping("/studentAction_nameIsExist.do")
   public @ResponseBody String nameIsExist(String name) {
      return studentService.nameIsExist(name) ? "1" : "0";
   }

   @RequestMapping("/studentAction_saveOrUpdate.do")
   public String saveOrUpdate(Long id, String name, int age) {
      studentService.saveOrUpdate(new Student(id, name, age));
      return "forward:studentAction_list.do";
   }

   public void setStudentService(StudentService studentService) {
      this.studentService = studentService;
      System.out.println("StudentAction.enclosing_method()");
   }
}
