package com.example.todoapp.controller;

import com.example.todoapp.entity.ToDo;
import com.example.todoapp.repository.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@ResponseBody
@RequestMapping("/api")
@CrossOrigin("*")
public class ToDoController {

    @Autowired
    ToDoRepo toDoRepo;


    @GetMapping("/todos")
    public List<ToDo> getAllTodos(){
        return toDoRepo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @PostMapping("/todos")
    public ToDo createTodo(@RequestBody ToDo toDo) {
        toDo.setStatus(false);
        return toDoRepo.save(toDo);
    }

    @GetMapping(value="/todos/{id}")
    public ResponseEntity<ToDo> getTodoById(@PathVariable("id") Integer id) {
        return toDoRepo.findById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ToDo postToDo(@RequestBody ToDo toDo) {
        return toDoRepo.save(toDo);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable("id") Integer id, @RequestBody ToDo toDo) {
           return toDoRepo.findById(id)
            .map(toDoData -> {
                    toDoData.setTitle(toDo.getTitle());
                    toDoData.setDescription((toDo.getDescription()));
                    toDoData.setStatus(toDo.isStatus());
                    ToDo updatedToDo = toDoRepo.save(toDoData);
                    return ResponseEntity.ok().body(updatedToDo);
                }).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable("id") Integer id) {
        return toDoRepo.findById(id)
                .map(toDo -> {
                    toDoRepo.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
