package com.tomar.ToDoList.Controller;

import com.tomar.ToDoList.Entity.User;
import com.tomar.ToDoList.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/viewUsers")
    public List<User> viewUsers(){
       return userService.viewUsers();
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("updateUser/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable String username, @RequestBody User user){
       return userService.updateUserDetails(username, user);
    }

}
