package com.henryxi.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class SimpleController {

    @Autowired
    private ApplicationEventPublisher applicationContext;

    @RequestMapping(value = "/publishEvent", method = RequestMethod.GET)
    @ResponseBody
    public String publishEvent() {
        applicationContext.publishEvent(new MyEvent("luck data"));
        return "Hello Spring Boot";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}