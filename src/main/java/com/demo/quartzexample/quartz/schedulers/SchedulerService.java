package com.demo.quartzexample.quartz.schedulers;

import com.demo.quartzexample.quartz.info.TimerInfo;
import com.demo.quartzexample.quartz.jobs.HelloJob;
import com.demo.quartzexample.quartz.util.TimerUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Service
public class SchedulerService {

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void runHelloService(){
        final TimerInfo info = new TimerInfo();
        info.setTotalFireCount(5);
        info.setRepeatIntervalMs(2000);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("TEST CALL BACK");
        schedule(HelloJob.class , info);
    }

    private void schedule(final Class<? extends Job> jobClass, final TimerInfo info){
        final JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass,info);
        final Trigger trigger = TimerUtils.buildTrigger(jobClass,info);
        try {
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            log.error(e.getMessage(),e);
        }
    }

    @PostConstruct
    private void init(){
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.error(e.getMessage(),e);
        }
    }

    @PreDestroy
    private void preDestroy(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error(e.getMessage(),e);
        }
    }
}
