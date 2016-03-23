package com.henry.xi.xml.properties.configuration.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Properties;

@Controller
@Configuration
@PropertySource("classpath:system.properties")
public class SimpleController {
    @Autowired
    private Environment env;

    @RequestMapping("/test-configuration-annotation")
    @ResponseBody
    public String testProperties() {
        return "serverAddress: " + env.getProperty("server.address") +
                " serverPort: " + env.getProperty("server.port");
    }
}
