package org.paulopaula.todolist.persistence.dao;

import org.paulopaula.todolist.persistence.model.TodoItem;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTodoItemDao extends GenericJpaDao<TodoItem> implements TodoListDao {

    public JpaTodoItemDao() {
        super(TodoItem.class);
    }
}
