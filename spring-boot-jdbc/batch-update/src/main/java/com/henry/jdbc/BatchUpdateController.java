package com.henry.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class BatchUpdateController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/batchUpdate-multipleSQL", method = RequestMethod.GET)
    public String batchUpdateMultipleSQL() {
        int[] affects = jdbcTemplate.batchUpdate(
                "INSERT INTO tb_user (username,comment) VALUES ('username_batch1','comment_batch1')",
                "INSERT INTO tb_user (username,comment) VALUES ('username_batch2','comment_batch2')");
        return "Batch update affect rows: " + Arrays.toString(affects);
    }

    @RequestMapping(value = "/batchUpdate-batchPreparedStatementSetter", method = RequestMethod.GET)
    public String batchUpdateBatchPreparedStatementSetter() {
        final List<User> userList = new ArrayList<User>();
        User user1 = new User("username_batch1", "comment_batch1");
        User user2 = new User("username_batch2", "comment_batch2");
        User user3 = new User("username_batch3", "comment_batch3");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        int[] affects = jdbcTemplate.batchUpdate(
                "INSERT INTO tb_user (username,comment) VALUES (?,?)", new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, userList.get(i).getUsername());
                        ps.setString(2, userList.get(i).getComment());
                    }

                    public int getBatchSize() {
                        return userList.size();
                    }
                });
        return "Batch update affect rows: " + Arrays.toString(affects);
    }

    @RequestMapping(value = "/batchUpdate-objectList", method = RequestMethod.GET)
    public String batchUpdateObjectList() {
        List<Object[]> parametersList = new ArrayList<Object[]>();
        parametersList.add(new Object[]{"username_batch1", "comment_batch1"});
        parametersList.add(new Object[]{"username_batch2", "comment_batch2"});
        parametersList.add(new Object[]{"username_batch3", "comment_batch3"});
        int[] affects = jdbcTemplate.batchUpdate("INSERT INTO tb_user (username,comment) VALUES (?,?)", parametersList);
        return "Batch update affect rows: " + Arrays.toString(affects);
    }

    @RequestMapping(value = "/batchUpdate-objectListWithArgType", method = RequestMethod.GET)
    public String batchUpdateWithArgType() {
        List<Object[]> parametersList = new ArrayList<Object[]>();
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR};
        parametersList.add(new Object[]{"username_batch1", "comment_batch1"});
        parametersList.add(new Object[]{"username_batch2", "comment_batch2"});
        parametersList.add(new Object[]{"username_batch3", "comment_batch3"});
        int[] affects = jdbcTemplate.batchUpdate("INSERT INTO tb_user (username,comment) VALUES (?,?)", parametersList, argTypes);
        return "Batch update affect rows: " + Arrays.toString(affects);
    }

    @RequestMapping(value = "/batchUpdate-ParameterizedPreparedStatementSetter", method = RequestMethod.GET)
    public String batchUpdateParameterizedPreparedStatementSetter() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User("username_batch1", "comment_batch1"));
        userList.add(new User("username_batch2", "comment_batch2"));
        userList.add(new User("username_batch3", "comment_batch3"));
        int[][] affects = jdbcTemplate.batchUpdate("INSERT INTO tb_user (username,comment) VALUES (?,?)", userList, userList.size(), new ParameterizedPreparedStatementSetter<User>() {
            public void setValues(PreparedStatement ps, User user) throws SQLException {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getComment());
            }
        });
        return "Batch update affect rows: " + Arrays.deepToString(affects);
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchUpdateController.class, args);
    }

}
