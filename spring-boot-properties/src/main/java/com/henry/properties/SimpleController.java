package com.henry.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@ComponentScan(value = "com.henry.xi.properties")
public class SimpleController {
    @Autowired
    private DefaultProperties defaultProperties;

    @Autowired
    private SpecialProperties specialProperties;

    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public String getProperties() {
        return defaultProperties.toString() + "<br>" + specialProperties.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}