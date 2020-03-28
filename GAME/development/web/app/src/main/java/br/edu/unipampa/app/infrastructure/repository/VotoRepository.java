package br.edu.unipampa.app.infrastructure.repository;

import br.edu.unipampa.app.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VotoRepository extends JpaRepository<Voto, Long> {


}
