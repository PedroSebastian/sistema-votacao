package br.edu.unipampa.app.infrastructure.repository;

import br.edu.unipampa.app.domain.Membro;
import br.edu.unipampa.app.domain.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {

    List<Reuniao> getAllByEstaAbertaTrue();

}
