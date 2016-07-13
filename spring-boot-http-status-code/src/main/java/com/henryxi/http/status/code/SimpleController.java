package com.henryxi.http.status.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@EnableAutoConfiguration
public class SimpleController {

    @RequestMapping(value = "/http-status-404", method = RequestMethod.GET)
    public String httpStatus404(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return "404";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}