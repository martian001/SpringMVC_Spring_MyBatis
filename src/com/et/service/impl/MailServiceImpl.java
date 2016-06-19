package com.et.service.impl;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.Mail;
import com.et.mapper.system.MailMapper;
import com.et.service.MailService;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年4月19日下午4:42:36 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年4月19日下午4:42:36 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Service
public class MailServiceImpl extends BaseServiceImpl<Mail> implements MailService {
   @Resource
   private MailMapper mailMapper;

   @Autowired
   private JavaMailSenderImpl javaMailSenderImpl;

   @PostConstruct
   public void init() {
      System.out.println("初始化");
      setBaseDao(mailMapper);
   }

   @Override
   public void send() {
      System.out.println("发送邮箱");
      try {
         SimpleMailMessage smm = new SimpleMailMessage();
         // // 设定邮件参数
         // smm.setFrom(javaMailSenderImpl.getUsername());
         // smm.setTo("995396426@qq.com");
         // smm.setSubject("Hello world");
         // smm.setText("Hello world via spring mail sender");
         // // 发送邮件
         // javaMailSenderImpl.send(smm);
         // 使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
         MimeMessage msg = javaMailSenderImpl.createMimeMessage();
         // 创建MimeMessageHelper对象，处理MimeMessage的辅助类
         MimeMessageHelper helper = new MimeMessageHelper(msg, true);
         // 使用辅助类MimeMessage设定参数
         helper.setFrom(javaMailSenderImpl.getUsername());
         helper.setTo("995396426@qq.com");
         helper.setSubject("Hello Attachment");
         helper.setText("This is a mail with attachment");
         // 加载文件资源，作为附件
         // ClassPathResource file = new ClassPathResource("Chrysanthemum.jpg");
         // 加入附件
         helper.addAttachment("111.jpg", new File("E:/111.jpg"));
         // 发送邮件
         javaMailSenderImpl.send(helper.getMimeMessage());
      } catch (Exception e) {
         e.printStackTrace();
         // TODO: handle exception
      }
   }

}
