package com.henryxi.tomcat.garbled;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class SimpleController {

    @RequestMapping(value = "/http-get", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String testHttpGet(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address) throws UnsupportedEncodingException {
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        address = new String(address.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("name:" + name + ",address" + address);
        User user = new User(name, address);
        return user.toString();
    }

    @RequestMapping(value = "/http-post-application-json", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String testHttpPostApplicationJson(@RequestBody User user) {
        System.out.println(user.toString());
        return user.toString();
    }

    @RequestMapping(value = "/http-post-form-urlencoded", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String testHttpPostFormUrlEncode(@ModelAttribute User user) throws UnsupportedEncodingException {
        user.setName(new String(user.getName().getBytes("ISO-8859-1"),"UTF-8"));
        user.setAddress(new String(user.getAddress().getBytes("ISO-8859-1"),"UTF-8"));
        System.out.println(user.toString());
        return user.toString();
    }
}
