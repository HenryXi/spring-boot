package com.henry.xml.properties.util.tag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Properties;

@Controller
public class SimpleController {
    @Resource(name = "configProperties")
    private Properties properties;

    @RequestMapping("/test-util-tag")
    @ResponseBody
    public String testProperties() {
        return "serverAddress: " + properties.getProperty("server.address") +
                " serverPort: " + properties.getProperty("server.port");
    }
}
