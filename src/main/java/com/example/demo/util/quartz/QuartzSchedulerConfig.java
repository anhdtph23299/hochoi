package com.example.demo.util.quartz;


import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzSchedulerConfig {

//    @Autowired
//    private Scheduler scheduler;
//
//    @PostConstruct
//    public void startScheduler() throws Exception {
//        // Define job and tie it to our MyJob class
//        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
//                .withIdentity("myJob", "group1")
//                .build();
//
//        // Trigger the job to run now, and then every 10 seconds
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("myTrigger", "group1")
////                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 17 * * ?"))
//                .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
//                .build();
//
//        scheduler.scheduleJob(jobDetail, trigger);
//        scheduler.start();
//    }
//
//    @PreDestroy
//    public void stopScheduler() throws Exception {
//        scheduler.shutdown();
//    }
}
