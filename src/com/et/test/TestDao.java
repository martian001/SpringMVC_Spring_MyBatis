package com.et.test;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.et.bean.checkingIn.CheckingIn;
import com.et.bean.system.Student;
import com.et.util.CommomUtils;

/**
 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： liangyanjun                                       ☆★
★☆            @time：2015年9月16日下午4:32:15    ☆★
★☆            @version：                                                                          ☆★
★☆            @lastMotifyTime：                                                   ☆★
★☆            @ClassAnnotation：                                                ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class TestDao {

   private static SqlSessionFactory sessionFactory;
   static {
      try {
         String resource = "com/et/test/conf.xml";
         // 加载mybatis的配置文件（它也加载关联的映射文件）
         Reader reader = Resources.getResourceAsReader(resource);
         // 构建sqlSession的工厂
         sessionFactory = new SqlSessionFactoryBuilder().build(reader);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void test11() {
      SqlSession openSession = sessionFactory.openSession();
      openSession.insert("com.et.bean.Student.insert", new Student(CommomUtils.getUUID(), "a哈哈", 11));
      openSession.commit();
      openSession.close();
   }

   @Test
   public void test_getAll() {
      SqlSession openSession = sessionFactory.openSession();
      List<CheckingIn> list = openSession.selectList("com.et.mapper.checkingIn.CheckingInMapper.getAll");
      for (CheckingIn checkingIn : list) {
         System.out.println(checkingIn);
      }
      openSession.close();
   }

   @Test
   public void test_getById() {
      SqlSession openSession = sessionFactory.openSession();
      String id = "1A97927751314E71B4D3CFFFA1B80175";
      CheckingIn checkingIn = openSession.selectOne("com.et.mapper.checkingIn.CheckingInMapper.getById", id);
      System.out.println(checkingIn);
   }

   @Test
   public void test_deleteById() {
      SqlSession openSession = sessionFactory.openSession();
      openSession.delete("com.et.mapper.checkingIn.CheckingInMapper.deleteById", "A6190C6221E9415AB1D8196597E4E9EE");
      openSession.commit();
      openSession.close();
   }
   @Test
   public void test_update() {
      SqlSession openSession = sessionFactory.openSession();
      CheckingIn checkingIn=new CheckingIn();
      checkingIn.setId("F78C9C703AA0466C83F1AEC7576928BE");
      checkingIn.setEndDate(new Date());
      checkingIn.setEndDate(new Date());
      checkingIn.setHour(33.2);
      checkingIn.setOverHour(33.3);
      checkingIn.setMonth(33);
      checkingIn.setUserId("33");
      checkingIn.setStatus(33);
      checkingIn.setOverTime(33);
      checkingIn.setOverHour(33);
      openSession.update("com.et.mapper.checkingIn.CheckingInMapper.update", checkingIn);
      openSession.commit();
      openSession.close();
   }
   @Test
   public void test_insert() {
      SqlSession openSession = sessionFactory.openSession();
      CheckingIn checkingIn=new CheckingIn();
      checkingIn.setEndDate(new Date());
      checkingIn.setEndDate(new Date());
      checkingIn.setHour(44.4);
      checkingIn.setOverHour(44.4);
      checkingIn.setMonth(44);
      checkingIn.setUserId("44");
      checkingIn.setStatus(44);
      checkingIn.setOverTime(44);
      openSession.insert("com.et.mapper.checkingIn.CheckingInMapper.insert", checkingIn);
      openSession.commit();
      openSession.close();
   }
}
