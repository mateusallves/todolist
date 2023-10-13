package com.example.todo.todolist.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUsersDTO userRepository;
    @PostMapping("/")
    public void createUser(@RequestBody User userModel){
        var userCreated = this.userRepository.save(userModel);

    }
}
