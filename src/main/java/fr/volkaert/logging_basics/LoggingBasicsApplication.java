package fr.volkaert.logging_basics;

import fr.volkaert.logging_basics.model.TodoItem;
import fr.volkaert.logging_basics.model.TodoList;
import fr.volkaert.logging_basics.repository.TodoListRepository;
import fr.volkaert.logging_basics.service.TodoListService;
import fr.volkaert.logging_basics.webapi.LoggingBasicsRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication()
public class LoggingBasicsApplication implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TodoListService todoListService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingBasicsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LoggingBasicsApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("LoggingBasicsApplication started");


        LOGGER.info("Initialization of some TodoLists...");

        TodoList todoList1 = new TodoList();
        Set<TodoItem> itemsForList1 = new HashSet<>();
        todoList1.setItems(itemsForList1);
        itemsForList1.add(TodoItem.builder().name("task1").priority(1).build());
        itemsForList1.add(TodoItem.builder().name("task2").priority(1).build());
        todoListService.saveTodoList(todoList1);

        TodoList todoList2 = new TodoList();
        Set<TodoItem> itemsForList2 = new HashSet<>();
        todoList2.setItems(itemsForList2);
        itemsForList2.add(TodoItem.builder().name("task3").priority(2).build());
        itemsForList2.add(TodoItem.builder().name("task4").priority(2).build());
        todoListService.saveTodoList(todoList2);

        List<TodoList> allTodoLists = todoListService.getAllTodoLists();
        LOGGER.info("Found {} TodoLists", allTodoLists.size());
        for (TodoList todoList : allTodoLists) {
            LOGGER.info(todoList.toString());
        }

        LOGGER.info("Initialization of some TodoLists completed");
    }
}
