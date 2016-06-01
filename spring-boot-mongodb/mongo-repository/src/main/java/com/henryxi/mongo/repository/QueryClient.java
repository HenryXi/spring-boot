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

        List<User> usersInDB = userRepository.findByName("Henry");

        System.out.println(usersInDB.toString());
    }
}
