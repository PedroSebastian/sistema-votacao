package br.edu.unipampa.app.infrastructure.repository;

import br.edu.unipampa.app.domain.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    Votacao findByItemDePautaId(Long id);

}
