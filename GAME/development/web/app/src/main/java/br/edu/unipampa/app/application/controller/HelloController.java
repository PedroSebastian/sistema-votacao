package br.edu.unipampa.app.application.controller;

import br.edu.unipampa.app.application.resources.HelloResource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Named
@RequestScoped
public class HelloController {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendMessage() {
        CompletableFuture.supplyAsync(() -> {
            try {
                return this.message;
            } catch (Exception e) {
                return e.getMessage();
            }
        }).whenCompleteAsync((result, throwable) -> {
            try {
                HelloResource.notifier.send(result);
                this.message = "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).exceptionally(ex -> {
            try {
                HelloResource.notifier.send(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "erro";
        });
    }

}