package com.github.springrecap;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoService service;

    @PostMapping
    public void addToDo(@RequestBody ToDo newToDo) {
        service.add(newToDo);
        System.out.println("ToDo added");
    }

    @GetMapping
    public List<ToDo> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<ToDo> getToDoById(@PathVariable String id){
        return service.getToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDo editToDo(@PathVariable String id,
                         @RequestBody ToDo updatedToDo) {
        updatedToDo = updatedToDo.withId(id);
        service.editToDo(updatedToDo);
        return updatedToDo;
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id){
        service.deleteToDo(id);
    }
}
