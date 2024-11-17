package com.tomar.ToDoList.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@Document
public class Task {

    @Id
    private Long id;

    private String title;

    private String description;

    private boolean completed;

}
