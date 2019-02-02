package com.henryxi.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Async("threadPoolExecutor")
    public void hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":finish!!");
    }
}
