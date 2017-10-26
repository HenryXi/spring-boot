package com.henryxi.json.nullfield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

@Controller
@EnableAutoConfiguration
public class SimpleController {

    @RequestMapping("/user-info")
    @ResponseBody
    public User userInfo() {
        User user = new User();
        user.setName("henry");
        user.setAge(27);
        return user;
    }

    @RequestMapping("/user-info-without-null-field")
    @ResponseBody
    public UserIgnoreNullField userInfoWithoutNullField() {
        UserIgnoreNullField user = new UserIgnoreNullField();
        user.setName("henry");
        user.setAge(27);
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}