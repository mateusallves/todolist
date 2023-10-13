package com.example.todo.todolist.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUsersDTO userRepository;
    @PostMapping("/")
    public ResponseEntity create(@RequestBody User userModel){
        var users = this.userRepository.findByUserName(userModel.getName());
        if (users != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body("Usuário Criado");
    }
}
