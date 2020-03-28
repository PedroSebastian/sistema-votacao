package br.edu.unipampa.app.infrastructure.repository;

import br.edu.unipampa.app.domain.Urna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UrnaRepository extends JpaRepository<Urna, Long> {


}
