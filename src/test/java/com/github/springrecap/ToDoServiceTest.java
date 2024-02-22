package com.github.springrecap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ToDoServiceTest {
    private final ToDoRepo repo = mock(ToDoRepo.class);
    ToDoService service = new ToDoService(repo);
//    @Test
//    void testAdd() {
//        //GIVEN
//        ToDo expected = new ToDo("1", "einkaufen", Status.OPEN);
//        when(repo.save(any())).thenReturn(expected);
//        //WHEN
//        ToDo actual = new ToDo("1", "einkaufen", Status.OPEN);
//        //THEN
//        service.add(actual);
//        verify(repo).save(any());
//    }

    @Test
    void testGetAll_whenInitiallyUsed_returnEmptyList(){
        //GIVEN
        List<ToDo> expected = List.of();
        when(repo.findAll()).thenReturn(List.of());
        //WHEN
        List<ToDo> actual = service.getAll();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getToDoById() {
        //GIVEN
        Optional<ToDo> expected = Optional.of(new ToDo("1", "einkaufen", Status.OPEN));
        when(repo.findById("1")).thenReturn(expected);
        //WHEN
        Optional<ToDo> actual = service.getToDoById("1");
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void editToDo() {
        //GIVEN
        ToDo expected = new ToDo("1", "einkaufen", Status.OPEN);
        when(repo.save(any())).thenReturn(expected);
        //WHEN
        ToDo actual = service.editToDo(new ToDo("1", "einkaufen", Status.OPEN));
        //THEN
        assertEquals(expected,actual);
    }
}