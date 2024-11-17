package com.tomar.ToDoList.Service;

import com.tomar.ToDoList.Entity.Task;
import com.tomar.ToDoList.Entity.User;
import com.tomar.ToDoList.Repository.ToDoListRepository;
import com.tomar.ToDoList.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ToDoListService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void saveTask(String username, Task task){
       try {
           toDoListRepository.save(task);
           System.out.println("Heyyyy");
           User userInDb = userRepository.findByUsername(username);
           userInDb.getTasks().add(task);
           userRepository.save(userInDb);
       }catch (Exception e){
           throw new RuntimeException("Failed to save task for user: " + username, e);
       }
    }

    public List<Task> viewList(String username){
        User user = userRepository.findByUsername(username);

        if(user!=null)  return user.getTasks();

        return null;
    }

    public String setTaskComplete(String taskName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(taskName));

        // Retrieve the task from the database
        Task task = mongoTemplate.findOne(query, Task.class);

        // Check if task exists to avoid duplicating
        if (task != null) {
            // Set the task's completed status to true
            task.setCompleted(true);

            // Save the updated task back to MongoDB, preventing duplication
            mongoTemplate.save(task);
            return taskName + " is successfully completed.";
        } else {
            // Return message if task was not found
            return "Task with title '" + taskName + "' not found.";
        } // Update the first matched task
    }

    public List<Task> viewCompletedTasks() {
        Query query = new Query();
        query.addCriteria(Criteria.where("completed").is(true));
        return mongoTemplate.find(query,Task.class);
    }

    public String deleteTask(String taskName, String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(taskName));
        mongoTemplate.remove(query, Task.class);
        User userInDB = userRepository.findByUsername(username);
        List<Task> list = userInDB.getTasks();
       for(int i=0; i<list.size(); i++){
           if(list.get(i)!=null && list.get(i).getTitle().equals(taskName)) {
               list.remove(i);
               userInDB.setTasks(list);
               userRepository.save(userInDB);
               return taskName + " is deleted";
           }
       }
        return taskName + " not found";

    }
}
