package com.henryxi.spring.boot.scheduling.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportTimeEvery5s() {
        System.out.println("Report time every 5s:" + dateFormat.format(new Date()));
    }

    @Scheduled(fixedDelay = 5000)
    public void reportTimeEvery5sAfterLastTaskSuccess() throws InterruptedException {
        System.out.println("Report time every 5s after last task success:" + dateFormat.format(new Date()));
        Thread.sleep(5000L);
    }

    @Scheduled(initialDelay = 10000, fixedRate = 5000)
    public void wait10sReportTime() {
        System.out.println("Wait 10s report time:" + dateFormat.format(new Date()));
    }

    @Scheduled(cron="*/5 * * * * MON-FRI")
    public void reportTimeByCron() {
        System.out.println("Report time by cron:" + dateFormat.format(new Date()));
    }
}
