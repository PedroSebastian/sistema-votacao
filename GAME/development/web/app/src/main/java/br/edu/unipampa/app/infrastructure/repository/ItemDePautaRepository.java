package br.edu.unipampa.app.infrastructure.repository;

import br.edu.unipampa.app.domain.ItemDePauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDePautaRepository extends JpaRepository<ItemDePauta, Long> {

    List<ItemDePauta> getItemDePautaByReuniaoId(@Param("id") Long id);

    List<ItemDePauta> getItemDePautaByReuniaoIdOrderByOrdem(@Param("id") Long id);

    List<ItemDePauta> findAllByDescricaoContainingOrRelatorContainingAndReuniaoId(String descricao, String relator, Long id);

}
