# Spring boot scheduled tasks example
In this page I will show you how to use scheduling tasks with Spring Boot. As we all known there is no
config file in Spring Boot. We use annotations to config scheduling tasks. This example also works well
in Spring.

**project structure**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─spring
│  │              └─boot
│  │                  └─scheduling
│  │                      └─tasks
│  │                              Application.java
│  │                              ScheduledTasks.java
│  │
│  └─resources
└─test
    └─java
```
**pom**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>1.3.3.RELEASE</version>
</dependency>
```
**Application**
```java
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}
```
**ScheduledTasks**
```java
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
```
the output of this demo like following
```
Report time every 5s:10:02:13
Report time every 5s after last task success:10:02:13
Report time every 5s:10:02:18
Wait 10s report time:10:02:23
Report time every 5s:10:02:23
Report time every 5s after last task success:10:02:23
Wait 10s report time:10:02:28
Report time every 5s:10:02:28
Wait 10s report time:10:02:33
Report time every 5s:10:02:33
Report time every 5s after last task success:10:02:33
Wait 10s report time:10:02:38
Report time every 5s:10:02:38
Wait 10s report time:10:02:43
Report time every 5s:10:02:43
Report time every 5s after last task success:10:02:43
Wait 10s report time:10:02:48
Report time every 5s:10:02:48
Wait 10s report time:10:02:53
Report time every 5s:10:02:53
Report time every 5s after last task success:10:02:53
Wait 10s report time:10:02:58
Report time every 5s:10:02:58
Wait 10s report time:10:03:03
Report time every 5s:10:03:03
Report time every 5s after last task success:10:03:03
```
* `@Scheduled(fixedRate = 5000)` on `reportTimeEvery5s` method means this method will executed every 5 seconds.
* `@Scheduled(fixedDelay = 5000)` on `reportTimeEvery5sAfterLastTaskSuccess` method means this method will executed after last
    task execute successfully 5 seconds. if Last task is still running the new task won't executed.
* `@Scheduled(initialDelay = 10000, fixedRate = 5000)` on `wait10sReportTime` method means this method will "wait" 10 seconds
then begin executing every 5 seconds.
* `@Scheduled(cron="*/5 * * * * MON-FRI")` on `reportTimeByCron` method means this method will executed like corn
expression described.
