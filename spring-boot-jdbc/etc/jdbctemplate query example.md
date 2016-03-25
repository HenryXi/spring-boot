# JdbcTemplate query examples
There are many powerful query methods in JdbcTemplate. They are flexible enough to do complex query tasks.
In this tutorial we will show you how to use query method. In order to get the result quickly I use Spring
Boot(SpringMVC) you can access different address to invoke different query method.

**project structure**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henry
│  │          └─jdbc
│  │                  QueryController.java
│  │                  User.java
│  │
│  └─resources
│          application.properties
│
└─test
    └─java              
```
**init database(PostgreSQL)**
```
CREATE TABLE public.tb_user
(
  id SERIAL PRIMARY KEY NOT NULL,
  username VARCHAR(20) NOT NULL,
  comment VARCHAR(500)
);
INSERT INTO tb_user (username, comment) VALUES ('username', 'comments');
```
JdbcTemplate CRUD examples click [here](http://www.henryxi.com/jdbctemplate-examples-in-spring-boot)

Install PostgreSQL and configure click [here](http://www.henryxi.com/install-and-configure-postgresql-on-linux)

**pom.xml file**
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>1.3.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>4.2.4.RELEASE</version>
</dependency>
<dependency>
    <groupId>postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>9.1-901.jdbc4</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
    <version>1.3.2.RELEASE</version>
</dependency>
```

**java class**

QueryController
```
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
```
User.java is an entity
```
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
Run the main method and request different address you can see how JdbcTemplate works. Want know how to
build and run a Spring Boot project click [here](http://www.henryxi.com/build-a-restful-spring-project-in-1-minute).