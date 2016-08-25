package com.henryxi.resource;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class SimpleController {
    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping("/WEB-INF-file")
    @ResponseBody
    public String testProperties() throws IOException {
        String content = IOUtils.toString(resourceLoader.getResource("/WEB-INF/target_file.txt").getInputStream());
        return "the content of resources:" + content;
    }
}
