package com.kaiburr.task.service;

import com.kaiburr.task.model.Task;
import com.kaiburr.task.model.TaskExecution;
import com.kaiburr.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public Task addTask(Task task) {
        if (task.getCommand().toLowerCase().contains("rm") || task.getCommand().toLowerCase().contains("shutdown")) {
            throw new IllegalArgumentException("Unsafe command!");
        }
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public List<Task> searchTasksByName(String name) {
        return taskRepository.findByNameContainingIgnoreCase(name);
    }

    public Task executeTask(String id) throws Exception {
        Task task = taskRepository.findById(id).orElseThrow(() -> new Exception("Task not found"));
        TaskExecution execution = new TaskExecution();
        execution.setStartTime(new Date());

        StringBuilder output = new StringBuilder();
        ProcessBuilder builder = new ProcessBuilder("sh", "-c", task.getCommand());
        Process process = builder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        process.waitFor();
        execution.setEndTime(new Date());
        execution.setOutput(output.toString());

        task.getTaskExecutions().add(execution);
        return taskRepository.save(task);
    }
}