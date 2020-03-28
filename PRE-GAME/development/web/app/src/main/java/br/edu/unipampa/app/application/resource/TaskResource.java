package br.edu.unipampa.app.application.resource;

import br.edu.unipampa.app.application.service.TaskService;
import br.edu.unipampa.app.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("tarefas")
public class TaskResource {


    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(this.taskService.listMyTasks());
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Task task) {
        Task newTask = this.taskService.addTask(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(newTask.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        this.taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Task task = this.taskService.taskById(id);

        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(task);
    }

    @PutMapping
    public ResponseEntity<Void> updateTask(@RequestBody Task task) {
        this.taskService.updateTask(task);

        return ResponseEntity.noContent().build();
    }

}
