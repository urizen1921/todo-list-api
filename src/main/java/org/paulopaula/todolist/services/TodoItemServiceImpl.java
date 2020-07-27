package org.paulopaula.todolist.services;

import org.paulopaula.todolist.persistence.dao.TodoListDao;
import org.paulopaula.todolist.persistence.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TodoItemServiceImpl implements TodoItemService{

    private TodoListDao todoListDao;

    @Autowired
    public void setTodoListDao(TodoListDao todoListDao) {
        this.todoListDao = todoListDao;
    }

    @Override
    public TodoItem get(Integer id) {
        return todoListDao.findById(id);
    }

    @Override
    public TodoItem save(TodoItem todoItem) {
        return todoListDao.saveOrUpdate(todoItem);
    }

    @Override
    public void delete(Integer id) {
        todoListDao.delete(id);
    }

    @Override
    public List<TodoItem> list() {
        return todoListDao.findAll();
    }
}
