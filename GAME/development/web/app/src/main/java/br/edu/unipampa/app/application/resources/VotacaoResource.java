package br.edu.unipampa.app.application.resources;

import br.edu.unipampa.app.application.service.VotacaoService;
import br.edu.unipampa.app.domain.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("votos")
public class VotacaoResource {

    @Autowired
    private VotacaoService votacaoService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Voto voto) {
        System.out.println(voto);
        Voto novoVoto = this.votacaoService.recebeNovo(voto);

//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
//                path("/{id}").buildAndExpand(novoVoto.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
