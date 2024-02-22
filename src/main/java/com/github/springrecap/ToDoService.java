package com.github.springrecap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepo repo;

    public void add(ToDo newToDo) {
        repo.save(newToDo.withId((UUID.randomUUID().toString())));
    }

    public List<ToDo> getAll() {
        return repo.findAll();
    }

    public Optional<ToDo> getToDoById(String id) {
        return repo.findById(id);
    }


    public ToDo editToDo(ToDo updatedToDo) {
        return repo.save(updatedToDo);
    }

    public void deleteToDo(String id) {
        repo.deleteById(id);
    }
}
