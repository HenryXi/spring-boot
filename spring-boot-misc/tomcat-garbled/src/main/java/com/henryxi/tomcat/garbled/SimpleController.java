package com.henryxi.tomcat.garbled;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SimpleController {

    @RequestMapping(value = "/http-get", method = RequestMethod.GET)
    @ResponseBody
    public String testHttpGet(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address) {
        User user = new User(name, address);
        return user.toString();
    }

    @RequestMapping(value = "/http-post-application-json", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String testHttpPostApplicationJson(@RequestBody User user) {
        return user.toString();
    }

    @RequestMapping(value = "/http-post-form-urlencoded", method = RequestMethod.POST)
    @ResponseBody
    public String testHttpPostFormUrlEncode(@ModelAttribute User user) {
        return user.toString();
    }
}
