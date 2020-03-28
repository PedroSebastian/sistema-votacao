package br.edu.unipampa.app.application.service;

import br.edu.unipampa.app.domain.Membro;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class ReuniaoSSE {

    private Long reuniaoId;
    private Membro membro;
    private SseEmitter emitter;

    public ReuniaoSSE(Long reuniaoId, Membro membro, SseEmitter emitter) {
        this.reuniaoId = reuniaoId;
        this.membro = membro;
        this.emitter = emitter;
    }

    public Long getReuniaoId() {
        return reuniaoId;
    }

    public Membro getMembro() {
        return membro;
    }

    public SseEmitter getEmitter() {
        return emitter;
    }

}
