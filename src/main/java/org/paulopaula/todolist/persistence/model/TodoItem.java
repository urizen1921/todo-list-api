package org.paulopaula.todolist.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class TodoItem extends AbstractModel {

    private String task;
    private boolean isComplete = false;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "task='" + task + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }
}
