package com.henry.xml.properties.property.placeholder.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    @Value("${server.address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;
    @RequestMapping("/test-property-placeholder-configurer")
    @ResponseBody
    public String testProperties(){
        return "serverAddress: " + serverAddress + " serverPort: " + serverPort;
    }
}
