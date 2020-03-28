package br.edu.unipampa.app.application.controller;

import br.edu.unipampa.app.application.service.TaskService;
import br.edu.unipampa.app.domain.Task;
import br.edu.unipampa.app.infrastructure.JSF.FacesMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class TaskBean {

    @Autowired
    private TaskService taskService;

    private Task task;
    private Task selectedTask;
    private List<Task> tasks;

    @PostConstruct
    private void init() {
        this.task = new Task();
    }

    public void saveTask() {
        try {
            this.taskService.addTask(task);
            this.init();

            FacesMessageUtil.addInfoMessage("Sua tarefa foi cadastrada com sucesso.");
        } catch (Exception exception) {
            FacesMessageUtil.addErrorMessage("Oopss! Algo deu errado, contate o administrador do sistema.");
        }
    }

    public void selectedTask() {

    }

    public void listAllTasks() {
        this.tasks =  this.taskService.listMyTasks();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
