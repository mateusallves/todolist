package com.example.todo.todolist.users;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersDTO extends JpaRepository<UserModel, UUID> {
   UserModel findByUsername(String username);
}