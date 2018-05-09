package com.henryxi.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    private static Logger logger = LoggerFactory.getLogger(AppController.class);

    @RequestMapping(value = "/testLog4j2", method = RequestMethod.GET)
    @ResponseBody
    public String testLog4j2() {
        String content = "hello spring boot log4j2.";
        logger.info("this is info log!");
        logger.error("this is error log!");
        logger.warn("this is warm log!");
        return content;
    }
}
