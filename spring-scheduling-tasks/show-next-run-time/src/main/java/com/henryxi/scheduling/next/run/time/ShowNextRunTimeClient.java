package com.henryxi.scheduling.next.run.time;

import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.Date;

public class ShowNextRunTimeClient {
    public static void main(String[] args) {
        String cronExpr = "*/5 * * * * *";
        CronSequenceGenerator generator = new CronSequenceGenerator(cronExpr);
        System.out.println("now:\t\t\t" + new Date());
        Date next = generator.next(new Date());
        System.out.println("next run time:\t" + next);
    }
}
