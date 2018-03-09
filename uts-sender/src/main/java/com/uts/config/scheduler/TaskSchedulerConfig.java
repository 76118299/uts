package com.uts.config.scheduler;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
@Configurable
@EnableScheduling
public class TaskSchedulerConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar str) {
        str.setScheduler(taskScherduler());
    }
    @Bean(destroyMethod = "shutdown")
    public Executor taskScherduler(){
        return Executors.newScheduledThreadPool(100);
    }
}
