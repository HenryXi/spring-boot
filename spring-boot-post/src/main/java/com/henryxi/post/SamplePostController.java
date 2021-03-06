package com.henryxi.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class SamplePostController extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String index() {
        return "post";
    }

    @RequestMapping(path = "/post-xml", method = RequestMethod.POST)
    @ResponseBody
    public String postXml(@RequestBody User user) throws IOException {
        return user.toString();
    }

    @RequestMapping(path = "/post-json", method = RequestMethod.POST)
    @ResponseBody
    public String postJson(User user) {
        return user.toString();
    }

    @RequestMapping(path="/nginx-post")
    @ResponseBody
    public Map<String, Object> testNginxPost(HttpServletRequest request, @RequestBody Map<String, Object> mapResult) {
        return mapResult;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(SamplePostController.class, args);
    }
}
