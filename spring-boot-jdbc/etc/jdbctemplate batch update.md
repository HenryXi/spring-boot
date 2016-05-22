# JdbcTemplate batch update example
JdbcTemplate provide many batch update functions. When you want update or insert many records you can 
use ``batchUpdate``. It is more elegant and faster than update(insert) records one by one. In this 
blog we will show you how to use ``batchUpdate`` in JdbcTemplate.

**Init database**
```sql
CREATE TABLE public.tb_user
(
  id SERIAL PRIMARY KEY NOT NULL,
  username VARCHAR(20) NOT NULL,
  comment VARCHAR(500)
);
```
**Entity code**
```java
public class User {
    private int id;
    private String username;
    private String comment;

    //getter and setter

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
```

**batchUpdate functions**

* batchUpdate(String... sql)

    ```java
    public String batchUpdateMultipleSQL() {
        int[] affects = jdbcTemplate.batchUpdate(
                "INSERT INTO tb_user (username,comment) VALUES ('username_batch1','comment_batch1')",
                "INSERT INTO tb_user (username,comment) VALUES ('username_batch2','comment_batch2')");
        return "Batch update affect rows: " + Arrays.toString(affects);
    }
    ```
* batchUpdate(String sql, BatchPreparedStatementSetter pss)

    ```java
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
    ```
* batchUpdate(String sql, Collection<T> batchArgs, int batchSize, ParameterizedPreparedStatementSetter<T> pss)
    
    ```java
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
    ```
    I found this ``batchUpdate`` function return int[][] instead of int[]. After viewing source I got
    the answer. If you are interested, click [here](http://www.henryxi.com/why-batchupdate-return-int-instead-of-int). 
* batchUpdate(String sql, List<Object[]> batchArgs)

    ```java
    public String batchUpdateObjectList() {
        List<Object[]> parametersList = new ArrayList<Object[]>();
        parametersList.add(new Object[]{"username_batch1", "comment_batch1"});
        parametersList.add(new Object[]{"username_batch2", "comment_batch2"});
        parametersList.add(new Object[]{"username_batch3", "comment_batch3"});
        int[] affects = jdbcTemplate.batchUpdate("INSERT INTO tb_user (username,comment) VALUES (?,?)", parametersList);
        return "Batch update affect rows: " + Arrays.toString(affects);
    }
    ```
* batchUpdate(String sql, List<Object[]> batchArgs, int[] argTypes)

    ```java
    public String batchUpdateWithArgType() {
        List<Object[]> parametersList = new ArrayList<Object[]>();
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR};
        parametersList.add(new Object[]{"username_batch1", "comment_batch1"});
        parametersList.add(new Object[]{"username_batch2", "comment_batch2"});
        parametersList.add(new Object[]{"username_batch3", "comment_batch3"});
        int[] affects = jdbcTemplate.batchUpdate("INSERT INTO tb_user (username,comment) VALUES (?,?)", parametersList, argTypes);
        return "Batch update affect rows: " + Arrays.toString(affects);
    }
    ```
