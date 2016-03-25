package com.henry.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Types;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class BatchUpdateController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/batchUpdate", method = RequestMethod.GET)
    public String batchUpdate() {
        Map<String, Object> map = jdbcTemplate.queryForMap("", new Object[]{1}, new int[]{Types.INTEGER});
        return map.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchUpdateController.class, args);
    }

}
