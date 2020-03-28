package br.edu.unipampa.app.application.resources;

import br.edu.unipampa.app.application.service.ReuniaoSSE;
import br.edu.unipampa.app.application.service.ReuniaoService;
import br.edu.unipampa.app.domain.ItemDePauta;
import br.edu.unipampa.app.domain.Membro;
import br.edu.unipampa.app.domain.Reuniao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Pedro Sebastian on 09/05/2018.
 */
@RestController
@RequestMapping("reunioes")
public class ReuniaoResource {

    @Autowired
    private ReuniaoService reuniaoService;

    private final CopyOnWriteArrayList<ReuniaoSSE> reunioes = new CopyOnWriteArrayList<>();

    @GetMapping()
    public ResponseEntity<List<Reuniao>> todas() {
        return ResponseEntity.status(HttpStatus.OK).body(this.reuniaoService.reunioesAbertas());
    }

    @RequestMapping(value = "/{id}/membros/{token}", produces = MediaType.TEXT_EVENT_STREAM_VALUE, method = RequestMethod.GET)
    public SseEmitter conectaNaReuniao(@PathVariable("id") Long id, @PathVariable("token") String token) {
        try {
            Membro membro = this.reuniaoService.buscaMembroPelo(token);

            Reuniao reuniao = this.reuniaoService.pegaPeloId(id);

            SseEmitter emitter = new SseEmitter(60 * 10000L);
            ReuniaoSSE reuniaoSSE = new ReuniaoSSE(reuniao.getId(), membro, emitter);

            this.reunioes.add(reuniaoSSE);

            emitter.onCompletion(() -> {
                this.reunioes.remove(reuniaoSSE);
            });

            emitter.onTimeout(() -> {
                this.reunioes.remove(reuniaoSSE);
            });

            return emitter;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<Void> receive(@RequestBody String message) {
        System.out.println(message);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @EventListener
    public void onSendItemDePauta(ItemDePauta item) {
        List<ReuniaoSSE> deadEmitters = new ArrayList<>();
        this.reunioes.forEach(reuniao -> {
            if (reuniao.getReuniaoId() == item.getReuniao().getId()) {
                try {
                    reuniao.getEmitter().send(item, MediaType.APPLICATION_JSON);
                    reuniao.getEmitter().onError(error -> {
                        try {
                            reuniao.getEmitter().send(item, MediaType.APPLICATION_JSON);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    deadEmitters.add(reuniao);
                    e.printStackTrace();
                }
            }
        });

        this.reunioes.removeAll(deadEmitters);
    }

    public CopyOnWriteArrayList<ReuniaoSSE> getReunioes() {
        return reunioes;
    }

}
