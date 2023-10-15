package com.example.todo.todolist.tasks;

import com.example.todo.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TasksDTO tasksDTO;
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        System.out.println(userId);
        taskModel.setUserId((UUID)userId);

        //Validating the dates
        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndsAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inicio/ termino deve ser maior que a atual");
        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndsAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inicio deve menor que a data de termino");
    }
        var task =  this.tasksDTO.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var userId = request.getAttribute("userId");
        return this.tasksDTO.findByUserId((UUID)userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
    var task = this.tasksDTO.findById(id).orElse(null);
        if (task==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não existe");
        }
        var userId = request.getAttribute("userId");
        if (!task.getUserId().equals(userId)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Essa tarefa não pertence ao Usuário");
        }
        Utils.copyNonNullProperties(taskModel, task);

    return ResponseEntity.ok().body(this.tasksDTO.save(task));
    }
}
