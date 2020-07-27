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


}
