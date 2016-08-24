package com.henryxi.downlaod;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@EnableAutoConfiguration
public class DownloadController {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response) {
        try {
            DefaultResourceLoader loader = new DefaultResourceLoader();
            InputStream is = loader.getResource("classpath:target_file.txt").getInputStream();
            IOUtils.copy(is, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename='target_file.exe'");
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DownloadController.class, args);
    }
}
