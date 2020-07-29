package org.paulopaula.todolist.services;

import org.paulopaula.todolist.persistence.dao.TodoListDao;
import org.paulopaula.todolist.persistence.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
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

    @Transactional
    @Override
    public TodoItem save(TodoItem todoItem) {
        return todoListDao.saveOrUpdate(todoItem);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws Exception {

        //TODO: Need to create classes for exceptions... they can't all be generic exceptions

        TodoItem todoItem = Optional.ofNullable(todoListDao.findById(id))
                .orElseThrow(Exception::new);

        todoListDao.delete(id);
    }

    @Override
    public List<TodoItem> list() {
        return todoListDao.findAll();
    }
}
