package br.edu.unipampa.app.application.service;

import br.edu.unipampa.app.domain.Task;
import br.edu.unipampa.app.infrastructure.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task) {
        return this.taskRepository.save(task);
    }

    public List<Task> listMyTasks() {
        return this.taskRepository.findAll();
    }

    public Task taskById(Long id) {
        return this.taskRepository.getOne(id);
    }

    public void deleteTask(Long id) {
        this.taskRepository.deleteById(id);
    }

    public Task updateTask(Task task) {
        return this.taskRepository.saveAndFlush(task);
    }

}
