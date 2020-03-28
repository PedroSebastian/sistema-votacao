package br.edu.unipampa.app.application.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("hello")
public class HelloResource {

    public static final SseEmitter notifier = new SseEmitter((60 * 10000L));

    @RequestMapping("stream")
    public SseEmitter enableNotifier() {
        return notifier;
    }

    @PostMapping
    public ResponseEntity<Void> receive(@RequestBody String message) {
        System.out.println(message);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}