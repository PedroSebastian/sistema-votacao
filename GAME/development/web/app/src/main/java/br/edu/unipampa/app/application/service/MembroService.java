package br.edu.unipampa.app.application.service;

import br.edu.unipampa.app.domain.Membro;
import br.edu.unipampa.app.infrastructure.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private ReuniaoService reuniaoService;

    public Membro pegaMembroPelo(String token, Long reuniaoId) {
        return this.membroRepository.getMembroByTokenAndReuniaoId(this.reuniaoService.pegaPeloId(reuniaoId), token);
    }

}
