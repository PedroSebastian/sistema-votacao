package br.edu.unipampa.app.infrastructure.repository;

import br.edu.unipampa.app.domain.Membro;
import br.edu.unipampa.app.domain.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long> {

    @Query("SELECT m FROM membros m WHERE m.token = :token")
    Membro getMembroByToken(@Param("token") String token);

    @Query("SELECT m FROM membros m JOIN m.reunioes r WHERE r = ?1 AND m.token = ?2")
    Membro getMembroByTokenAndReuniaoId(Reuniao reuniao, String token);

}
