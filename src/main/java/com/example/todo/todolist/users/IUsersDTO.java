package com.example.todo.todolist.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUsersDTO extends JpaRepository<User, UUID> {
}
