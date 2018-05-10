# Spring get next run time by cron expression
I always use cron expression to execute scheduling task. I want to know when the task will be executed. I use `CronSequenceGenerator`
to calculate the next run time which is a class of **Spring Framework**. The example code is here.
```java
public class ShowNextRunTimeClient {
    public static void main(String[] args) {
        String cronExpr = "*/5 * * * * *";
        CronSequenceGenerator generator = new CronSequenceGenerator(cronExpr);
        System.out.println("now time:\t\t" + new Date());
        Date next = generator.next(new Date());
        System.out.println("next run time:\t" + next);
    }
}
```
output
```
now time:		Thu May 10 16:24:20 CST 2018
next run time:	Thu May 10 16:24:25 CST 2018
```

EOF