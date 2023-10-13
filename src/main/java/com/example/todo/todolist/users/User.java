package com.example.todo.todolist.users;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="tb_user")

public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(unique = true)
   private String name;
    private  String username;
   private String password;

   @CreationTimestamp
    private LocalDateTime createdAt;
}
