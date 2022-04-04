package com.demo.quartzexample.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println(new Date() + " - Hello");
    }
}
