package com.jege.spring.boot.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.jege.spring.boot.data.jpa.entity.User;
import com.jege.spring.boot.data.jpa.repository.UserRepository;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:动态修改定时任务cron参数
 */
@Component
public class DynamicScheduledTask implements SchedulingConfigurer {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  private static final String DEFAULT_CRON = "0/5 * * * * ?";
  private String cron = DEFAULT_CRON;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.addTriggerTask(new Runnable() {
      @Override
      public void run() {
	if (!cron.equals(DEFAULT_CRON)) {
	  User user = new User("je_ge", 20);
	  userRepository.save(user);
	}
	// 定时任务的业务逻辑
	System.out.println("动态修改定时任务cron参数，当前时间：" + dateFormat.format(new Date()));
      }
    }, new Trigger() {
      @Override
      public Date nextExecutionTime(TriggerContext triggerContext) {
	// 定时任务触发，可修改定时任务的执行周期
	CronTrigger trigger = new CronTrigger(cron);
	Date nextExecDate = trigger.nextExecutionTime(triggerContext);
	return nextExecDate;
      }
    });
  }

  public void setCron(String cron) {
    this.cron = cron;
  }
}
