package com.acme.todolist.adapters.rest_api;

import com.acme.todolist.application.port.in.AddTodoItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.acme.todolist.application.port.in.GetTodoItems;
import com.acme.todolist.domain.TodoItem;

/**
 * Le controlleur Spring MVC qui expose les endpoints REST
 * 
 * @author bflorat
 *
 */
@RestController
public class TodoListController {
	
	
	private GetTodoItems getTodoItemsQuery;
	private AddTodoItem addTodoItem;

	public void TodoController(AddTodoItem addTodoItem) {
		this.addTodoItem = addTodoItem;
	}

    public TodoListController(AddTodoItem addTodoItem) {
        this.addTodoItem = addTodoItem;
    }

    @PostMapping("/todos")
	@ResponseStatus(HttpStatus.CREATED)
	public void createTodo(@RequestBody TodoItem item) {
		addTodoItem.addTodoItem(item);
	}
	
	
}
