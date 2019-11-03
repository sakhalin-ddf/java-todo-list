package com.todo.service;

import com.todo.model.Task;
import com.todo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Task create(String name) {
        Task task = new Task(name);

        repository.save(task);

        return task;
    }

    @Transactional
    public Task update(int id, String name, String status) {
        Task task = repository.findById(id);

        if (task == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Task not found");
        }

        if (name != null) {
            task.setName(name);
        }

        if (status != null) {
            task.setStatus(Task.Status.valueOf(status));
        }

        repository.save(task);

        return task;
    }

    @Transactional
    public void delete(int id) {
        Task task = repository.findById(id);

        if (task == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Task not found");
        }

        repository.delete(task);
    }
}