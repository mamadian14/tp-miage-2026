package com.acme.todolist.application.port.in;

import com.acme.todolist.domain.TodoItem;

public interface AddTodoItem {
	
	void addTodoItem(TodoItem item);
}
//ce port definit l'operation permettant de ajouter un  nouvel element dans la liste
