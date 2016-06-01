package com.henryxi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface UserRepository extends MongoRepository<User, String> {
    List<User> findByAge(Long age);
}
