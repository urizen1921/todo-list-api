package org.paulopaula.todolist.controller;

import org.paulopaula.todolist.persistence.model.TodoItem;
import org.paulopaula.todolist.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todoitem")
public class TodoItemController {

    private TodoItemService todoItemService;

    @Autowired
    public void setTodoItemService(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<TodoItem>> listTodoItems() {
        System.out.println("Hello");

        //TODO: conversion from todoItem to todoItemDTO

        List<TodoItem> todoItems = todoItemService.list();
        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addTodoItem(@Valid @RequestBody TodoItem todoItem, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if(bindingResult.hasErrors() || todoItem.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TodoItem savedItem = todoItemService.save(todoItem);

        //TODO: conversion from todoItemDTO to todoItem

        UriComponents uriComponents = uriComponentsBuilder.path("/api/todoitem/" + savedItem.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<TodoItem> deleteTodoItem(@PathVariable Integer id) {

        //TODO: write exceptions properly and all cases
        System.out.println(id);
        try {
            todoItemService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<TodoItem> editTodoItem(@Valid @RequestBody TodoItem todoItem, BindingResult bindingResult, @PathVariable Integer id) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (todoItem.getId() != null && !todoItem.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (todoItemService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        todoItemService.save(todoItem);
        return new ResponseEntity<>(HttpStatus.OK);


    }


}
