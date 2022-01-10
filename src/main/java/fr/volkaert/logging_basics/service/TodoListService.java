package fr.volkaert.logging_basics.service;


import fr.volkaert.logging_basics.model.TodoList;
import fr.volkaert.logging_basics.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TodoListService {

    @Autowired
    private TodoListRepository repository;

    public List<TodoList> getAllTodoLists() {
        return repository.findAll();
    }

    public TodoList geTodoList(Long id) {
        return repository.findById(id).orElse(null);
    }

    public TodoList saveTodoList(TodoList todoList) {
        return repository.save(todoList);
    }

    public void deleteTodoList(Long id) {
        repository.deleteById(id);
    }
}
