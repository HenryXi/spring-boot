package com.henryxi.mongo.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QueryClient implements CommandLineRunner {
    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(QueryClient.class, args);
    }

    public void run(String... strings) throws Exception {
        // save data
        User henry = new User("Henry", 27);
        mongoTemplate.save(henry);
        Address beijing = new Address("China", "Beijing");
        Address shanghai = new Address("China", "Shanghai");
        User justin = new User("Justin", 28, beijing);
        User mathew = new User("Mathew", 23, shanghai);
        User charles = new User("Charles", 32, beijing);
        List<User> users = new ArrayList<User>();
        users.add(justin);
        users.add(mathew);
        users.add(charles);
        mongoTemplate.insert(users, User.class);

        //delete data
        String justinId = justin.getId();
        justin.setId("123456");
        mongoTemplate.remove(justin);//delete fail when change the id
        Query queryMathew = new Query();
        queryMathew.addCriteria(Criteria.where("name").is("Mathew"));
        mongoTemplate.remove(queryMathew, User.class);
        justin.setId(justinId);
        mongoTemplate.remove(justin);

        //query data
        Query queryHenry = new Query();
        queryHenry.addCriteria(Criteria.where("name").is("Henry"));
        List<User> usersInDB = mongoTemplate.find(queryHenry, User.class);
        System.out.println(usersInDB.get(0));
        String id = usersInDB.get(0).getId();
        User user = mongoTemplate.findById(id, User.class);
        System.out.println(user);

        //update data
        Query queryJustin = new Query();
        Update updateHenry = new Update().set("name", "new Henry");
        User oldHenry = mongoTemplate.findAndModify(queryHenry, updateHenry, User.class);//return old user object
        System.out.println(oldHenry);
        queryJustin.addCriteria(Criteria.where("name").is("Charles"));
        Update updateCharles = new Update().set("name", "new Charles");
        FindAndModifyOptions returnNew = new FindAndModifyOptions().returnNew(true);
        User newCharles = mongoTemplate.findAndModify(queryJustin, updateCharles, returnNew, User.class);//return new user object
        System.out.println(newCharles);
    }
}
