package com.tomar.ToDoList.Service;

import com.tomar.ToDoList.Entity.Task;
import com.tomar.ToDoList.Entity.User;
import com.tomar.ToDoList.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> viewUsers(){
       return userRepository.findAll();
    }

    public ResponseEntity<Object> updateUserDetails(String username, User user) {
         User userInDb = userRepository.findByUsername(username);
//        Query query = new Query();
//        query.addCriteria(Criteria.where("username").is(username));
//        User userInDb = mongoTemplate.findOne(query, User.class);
//        System.out.println(userInDb);
         if(userInDb!=null){
             userInDb.setUsername(user.getUsername());
             userInDb.setPassword(user.getPassword());
             saveUser(userInDb);
             return new ResponseEntity<>(HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
