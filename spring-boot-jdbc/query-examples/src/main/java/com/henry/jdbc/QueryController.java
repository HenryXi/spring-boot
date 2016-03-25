package com.henry.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class QueryController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/query-RowCallbackHandler", method = RequestMethod.GET)
    public String queryEntityRowCallbackHandler() {
        final User user = new User();
        jdbcTemplate.query("SELECT * FROM tb_user WHERE id = ?",
                new Object[]{1},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setComment(rs.getString("comment"));
                    }
                });
        return user.toString();
    }

    @RequestMapping(value = "/query-PreparedStatementSetter", method = RequestMethod.GET)
    public String queryEntityPreparedStatementSetter() {
        final User user = new User();
        jdbcTemplate.query("select * from tb_user WHERE id>?",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws
                            SQLException {
                        preparedStatement.setInt(1, 1);
                    }
                },
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setComment(rs.getString("comment"));
                    }
                });
        return user.toString();
    }

    @RequestMapping(value = "/query-RowMapper", method = RequestMethod.GET)
    public String queryListRowMapper() {
        List<User> users = jdbcTemplate.query("SELECT * FROM tb_user WHERE id > ?",
                new Object[]{1}, new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int index) throws SQLException {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setComment(rs.getString("comment"));
                        return user;
                    }
                });
        return users.toString();
    }

    @RequestMapping(value = "/query-list-ResultSetExtractor", method = RequestMethod.GET)
    public String queryListResultSetExtractor() {
        List<User> users = jdbcTemplate.query("select * from tb_user", new ResultSetExtractor<List<User>>() {
            public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<User> list = new ArrayList<User>();
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setComment(rs.getString("comment"));
                    list.add(user);
                }
                return list;
            }
        });
        return users.toString();
    }



    @RequestMapping(value = "/queryForList", method = RequestMethod.GET)
    public String queryForList() {
        List<Integer> usersId = jdbcTemplate.queryForList("select id from tb_user WHERE id>?", new Object[]{1}, Integer.class);
        return usersId.toString();
    }

    @RequestMapping(value = "/queryForList-withArgType", method = RequestMethod.GET)
    public String queryForListWithArgType() {
        List<String> usernames = jdbcTemplate.queryForList("select id from tb_user WHERE id>?", new Object[]{1}, new int[]{Types.INTEGER}, String.class);
        return usernames.toString();
    }

    @RequestMapping(value = "/queryForList-Map", method = RequestMethod.GET)
    public String queryForListInMap() {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from tb_user WHERE id>?", new Object[]{1});
        return mapList.toString();
    }

    @RequestMapping(value = "/queryForList-Map-withArgType", method = RequestMethod.GET)
    public String queryForListInMapWithArgType() {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from tb_user WHERE id>?", new Object[]{1}, new int[]{Types.INTEGER});
        return mapList.toString();
    }

    @RequestMapping(value = "/queryForMap", method = RequestMethod.GET)
    public String queryForMap() {
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from tb_user WHERE id=?", new Object[]{1});
        return map.toString();
    }

    @RequestMapping(value = "/queryForMap-withArgType", method = RequestMethod.GET)
    public String queryForMapWithArgType() {
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from tb_user WHERE id=?", new Object[]{1}, new int[]{Types.INTEGER});
        return map.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(QueryController.class, args);
    }
}
