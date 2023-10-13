package com.example.todo.todolist.tasks;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskModel {
   private UUID id;
   private String description;
   private String title;
   private LocalDateTime startAt;
   private LocalDateTime endsAt;
   private  String priority;
   private LocalDateTime createdAt;

   private UUID userId;

}
