package com.kaiburr.task.controller;

import com.kaiburr.task.model.Task;
import com.kaiburr.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) throws Exception {
        return taskService.getTaskById(id).orElseThrow(() -> new Exception("Task not found"));
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/search")
    public List<Task> searchTasksByName(@RequestParam String name) {
        return taskService.searchTasksByName(name);
    }

    @PutMapping("/{id}/execute")
    public Task executeTask(@PathVariable String id) throws Exception {
        return taskService.executeTask(id);
    }
}