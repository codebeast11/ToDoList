package com.tomar.ToDoList.Entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    String id;

    @Indexed(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    private List<Task> tasks = new ArrayList<>();
}
