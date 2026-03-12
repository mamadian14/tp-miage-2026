package com.acme.todolist;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Le controlleur Spring MVC qui expose les endpoints REST
 * 
 * @author bflorat
 *
 */
@RestController //recevoir les requete HTTP appeler la logique metier
public class TodoListController {

	private static final String LATE = "[LATE!]"; // regle de gestionn RG1
	private TodoItemRepository todoItemRepository;

	public TodoListController(TodoItemRepository todoItemRepository) {
		 // supprimer le constructeur vide,provoaue nullpointerexception
		this.todoItemRepository = todoItemRepository;
	}

	@PostMapping("/todos")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createTodoItem(@RequestBody TodoItem todoItem) {
		this.todoItemRepository.save(todoItem);
	}

	@GetMapping("/todos")
	public List<TodoItem> todoItems() {
		return this.todoItemRepository.findAll().stream() // le controller accède directement au repository.
				.map(item -> new TodoItem(item.getId(), item.getTime(), finalContent(item)))
				.collect(Collectors.toList());

	}

	/**
	 * RG 1 : si l'item a plus de 24h, ajouter dans le contenu une note "[LATE!]"
	 * 
	 * @return liste des items
	 */
	private String finalContent(TodoItem item) { // regle de gestion RG1 si l'item a plus de 24h , ajouter late , donc
		return (Instant.now().isAfter(item.getTime().plus(1, ChronoUnit.DAYS))) ? 
				LATE + item.getContent()
				: item.getContent();
	}

}
