package com.acme.todolist.adapter.rest;

import com.acme.todolist.adapters.rest_api.TodoListController;
import com.acme.todolist.application.port.in.AddTodoItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoListController.class)
class TodoListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddTodoItem addTodoItem; // 🔥 OBLIGATOIRE

    @Test
    void should_create_todo_and_return_201() throws Exception {

        String json = """
        {
          "id": "1",
          "time": "2020-02-27T10:31:43Z",
          "content": "Faire les courses"
        }
        """;

        mockMvc.perform(post("/todos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());
    }
}