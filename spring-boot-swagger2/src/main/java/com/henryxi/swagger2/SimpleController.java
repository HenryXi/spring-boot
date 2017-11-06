package com.henryxi.swagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(description = "simple controller")
public class SimpleController {

    @RequestMapping(value = "/get-user-info", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "get user info")
    public User userInfo() {
        User user = new User();
        user.setName("henry");
        user.setAge(27);
        return user;
    }

    @RequestMapping(value = "/post-user-info", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "save user info")
    public User userInfo(@RequestBody User user) {
        System.out.println("user info:" + user);
        return user;
    }
}