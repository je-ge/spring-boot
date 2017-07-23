package com.jege.spring.boot.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:简单邮件发送测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleSendMailTest {

  @Autowired
  private JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String username;

  @Test
  public void testSendSimple() {
    SimpleMailMessage message = new SimpleMailMessage();
    // 在setFrom处必须填写自己的邮箱地址，否则会报
    // Failed messages: com.sun.mail.smtp.SMTPSendFailedException:
    // 501 mail from address must be same as authorization user错误
    message.setFrom(username);
    message.setTo("1272434821@qq.com");
    message.setSubject("标题");
    message.setText("内容部份");
    javaMailSender.send(message);
  }
}