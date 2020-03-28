package br.edu.unipampa.app.application.resource;

import br.edu.unipampa.app.domain.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

//    @GetMapping("hello")
//    public ResponseEntity<Task> hello() {
//        return new ResponseEntity<Task>(new Task((long) 1, "RPV", "Olá Mundo!"), HttpStatus.OK);
//    }

}
