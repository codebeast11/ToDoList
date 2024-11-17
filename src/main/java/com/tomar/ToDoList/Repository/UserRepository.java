package com.tomar.ToDoList.Repository;

import com.tomar.ToDoList.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
