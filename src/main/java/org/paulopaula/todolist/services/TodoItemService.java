package org.paulopaula.todolist.services;

import org.paulopaula.todolist.persistence.model.TodoItem;

import java.util.List;

public interface TodoItemService {

    TodoItem get(Integer id);

    TodoItem save(TodoItem todoItem);

    void delete(Integer id) throws Exception;

    List<TodoItem> list();
}
