package com.tomar.ToDoList.Repository;

import com.tomar.ToDoList.Entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoListRepository extends MongoRepository<Task, Long> {

    Task findByTitle(String taskName);
}
