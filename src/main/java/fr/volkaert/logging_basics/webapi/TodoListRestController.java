package fr.volkaert.logging_basics.webapi;

import fr.volkaert.logging_basics.model.TodoList;
import fr.volkaert.logging_basics.service.TodoListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/todolists")
public class TodoListRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoListRestController.class);

    @Autowired
    private TodoListService service;

    private Random random = new Random();

    @GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TodoList>> getAllTodoLists() {
        List<TodoList> todoLists = service.getAllTodoLists();
        return todoLists != null ? ResponseEntity.ok(todoLists) : ResponseEntity.notFound().build();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoList> getTodoList(@PathVariable Long id) {
        TodoList todoList = service.geTodoList(id);
        return todoList != null ? ResponseEntity.ok(todoList) : ResponseEntity.notFound().build();
    }

    private void pause(long pauseInMillis) {
        try { Thread.sleep(pauseInMillis);} catch (Exception ex) {}
    }
}
