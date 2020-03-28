package br.edu.unipampa.app.application.resources;

import br.edu.unipampa.app.application.service.MembroService;
import br.edu.unipampa.app.domain.Membro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("membros")
public class MembroResource {

    @Autowired
    private MembroService membroService;

    @GetMapping("/{token}/reunioes/{id}")
    public ResponseEntity<Membro> pegaPelo(@PathVariable("token") String token, @PathVariable("id") Long id) {
        Membro membro = this.membroService.pegaMembroPelo(token, id);

        if (membro != null) {
            return new ResponseEntity<Membro>(membro, HttpStatus.OK);
        } else {
            return new ResponseEntity<Membro>((Membro) null, HttpStatus.NOT_FOUND);
        }
    }

}
