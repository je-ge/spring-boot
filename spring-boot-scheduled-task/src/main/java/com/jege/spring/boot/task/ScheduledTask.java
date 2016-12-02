package com.jege.spring.boot.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:从配置文件加载任务信息
 */
@Component
public class ScheduledTask {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(fixedDelayString = "${jobs.fixedDelay}")
  public void getTask1() {
    System.out.println("任务1，当前时间：" + dateFormat.format(new Date()));
  }

  @Scheduled(cron = "${jobs.cron}")
  public void getTask2() {
    System.out.println("任务2，当前时间：" + dateFormat.format(new Date()));
  }
}
