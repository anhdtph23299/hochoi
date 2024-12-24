package com.example.demo.util.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Logic của job
        System.out.println("Executing MyJob");
//        try {
//            context.getScheduler().deleteJob(context.getJobDetail().getKey());
//        } catch (SchedulerException e) {
//            throw new RuntimeException(e);
//        }
    }
}
