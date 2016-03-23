package com.henry.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@EnableAutoConfiguration
public class SimpleController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        jdbcTemplate.update("INSERT INTO tb_user (username, comment) VALUES (?,?)", new Object[]{"username1", "comment1"});
        return "insert completes!";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete() {
        jdbcTemplate.update("DELETE FROM tb_user where id>?", 100);
        return "delete completes!";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update() {
        jdbcTemplate.update("UPDATE tb_user SET username=?, comment=? ", new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, "update_username");
                ps.setString(2, "update_comment");
            }
        });
        return "update completes!";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query() {
        String name = jdbcTemplate.queryForObject("SELECT username FROM tb_user where id = ?", String.class, 100);
        return "username " + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}