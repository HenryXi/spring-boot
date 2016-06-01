package com.henryxi.mongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QueryClient implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(QueryClient.class, args);
    }

    public void run(String... strings) throws Exception {
        // save data
        User henry = new User("Henry", 27);
        userRepository.save(henry);
        Address beijing = new Address("China", "Beijing");
        Address shanghai = new Address("China", "Shanghai");
        User justin = new User("Justin", 28, beijing);
        User mathew = new User("Mathew", 23,shanghai);
        List<User> users = new ArrayList<User>();
        users.add(justin);
        users.add(mathew);
        userRepository.save(users);

        //delete data
        String justinId = justin.getId();
        justin.setId("123456");
        userRepository.delete(justin);//delete fail when change the id
        userRepository.delete(justinId);

        //query data
        List<User> usersInDB = userRepository.findByAge(23L);
        String id = usersInDB.get(0).getId();
        User user = userRepository.findOne(id);
        System.out.println(user);

        //update data
        User updateUser = userRepository.findOne(id);
        updateUser.setName("new name");//update date need query it first
        userRepository.save(updateUser);
        User newUser = userRepository.findOne(id);
        System.out.println(newUser);
    }
}
