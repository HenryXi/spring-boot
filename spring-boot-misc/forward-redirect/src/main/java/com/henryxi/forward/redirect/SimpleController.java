package com.henryxi.forward.redirect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

@Controller
@EnableAutoConfiguration
public class SimpleController {

    @RequestMapping("/index-page")
    public String index(@PathParam("name") String name, ModelMap modelMap) {
        modelMap.put("name",name);
        return "index";
    }

    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:index-page";
    }

    @RequestMapping("/forward")
    public String forward() {
        return "forward:index-page";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}