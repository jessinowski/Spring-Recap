package com.github.springrecap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ToDoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ToDoRepo repo;

    @Test
    void addToDo() throws Exception {
        //GIVEN
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                                                
                                    {
                                        "description": "einkaufen",
                                        "status": "OPEN"
                                    }
                                                                
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAll() throws Exception {
        //GIVEN
        List<ToDo> todos = List.of();
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(todos.toString()));
    }

    @Test
    void getToDoById() throws Exception {
        //GIVEN
        ToDo todo = new ToDo("1", "einkaufen", Status.OPEN);
        repo.save(todo);
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""                                
                        {
                             "id": "1",
                             "description": "einkaufen",
                             "status": "OPEN"
                         }
                        """));
    }

    @Test
    void editToDo() throws Exception {
        //GIVEN
//        ToDo todo = new ToDo("1", "einkaufen", Status.OPEN);
//        repo.save(todo);
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                  {
                                       "id": "1",
                                       "description": "bouldern",
                                       "status": "OPEN"
                                   }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                             "id": "1",
                             "description": "bouldern",
                             "status": "OPEN"
                         }
                        """));
    }

    @Test
    void deleteToDo() throws Exception {
        //GIVEN
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}