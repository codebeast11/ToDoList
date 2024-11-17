package com.tomar.ToDoList.Controller;

import com.tomar.ToDoList.Entity.Task;
import com.tomar.ToDoList.Entity.User;
import com.tomar.ToDoList.Service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoListService toDoListService;

    @GetMapping("/viewList/{username}")
    public List<Task> viewList(@PathVariable String username){
       return toDoListService.viewList(username);
    }

    @PostMapping("/addTask/{username}")
    public String addTask(@RequestBody Task task, @PathVariable String username){
        toDoListService.saveTask(username, task);
        return task.getTitle() + " successfully added for " + username;
    }

    @PutMapping("/complete/{username}/{taskName}")
    public String setTaskComplete(@PathVariable String username, @PathVariable String taskName){
       return toDoListService.setTaskComplete(taskName);
    }

    @GetMapping("/completedTasks")
    public List<Task> viewCompletedTasks(){
     return toDoListService.viewCompletedTasks();
    }

    @DeleteMapping("/deleteTask/{username}/{taskName}")
    public String deleteTask(@PathVariable String taskName, @PathVariable String username) {
       return toDoListService.deleteTask(taskName, username);
    }
}
