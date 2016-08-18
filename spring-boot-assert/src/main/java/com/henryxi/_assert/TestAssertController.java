package com.henryxi._assert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
@EnableAutoConfiguration
public class TestAssertController {

    @RequestMapping(value = "/test-java-assert", method = RequestMethod.GET)
    public String testJavaAssert(HttpServletResponse response) {
        try {
            int randomInt = new Random().nextInt(10);
            System.out.println(randomInt);
            assert randomInt > 5 : "smaller than 5";
            return "result: " + randomInt;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "/test-spring-assert", method = RequestMethod.GET)
    public String testSpringAssert(HttpServletResponse response) {
        try {
            int randomInt = new Random().nextInt(10);
            Assert.isTrue(randomInt > 5, "smaller than 5");
            Assert.notNull(null);
            return "result: " + randomInt;
        } catch (Exception e) {
            return "error:" + e.getMessage();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TestAssertController.class, args);
    }
}