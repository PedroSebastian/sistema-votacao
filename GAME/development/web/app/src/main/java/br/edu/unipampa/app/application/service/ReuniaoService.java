package br.edu.unipampa.app.application.service;

import br.edu.unipampa.app.domain.Membro;
import br.edu.unipampa.app.domain.Reuniao;
import br.edu.unipampa.app.infrastructure.repository.MembroRepository;
import br.edu.unipampa.app.infrastructure.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReuniaoService {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private MembroRepository membroRepository;

    public Reuniao pegaPeloId(Long id) {
        return this.reuniaoRepository.findById(id).get();
    }

    public List<Reuniao> reunioesAbertas() {
        return this.reuniaoRepository.getAllByEstaAbertaTrue();
    }

    public Membro buscaMembroPelo(String token) {
        return this.membroRepository.getMembroByToken(token);
    }

}
