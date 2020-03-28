package br.edu.unipampa.app.integracao.br.edu.unipampa.app.infrastructure.repository;

import br.edu.unipampa.app.domain.*;
import br.edu.unipampa.app.infrastructure.repository.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testes de Integração para a classe VotoRepository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VotoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private ItemDePautaRepository itemDePautaRepository;

    @Autowired
    MembroRepository membroRepository;

    @Before
    public void init() {

    }

    /**
     * Quando busca um determinado voto pelo seu id, então deve retornar o voto corretamente.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void quandoProcurarPeloId_entaoRetorneOVoto() {
        Voto voto = new Voto(this.buscaUmMembro(1L),
                this.buscaUmItemDePauta(1L).getEncaminhamentos().get(0));
        voto = this.entityManager.persistAndFlush(voto);

        Voto novoVoto = this.votoRepository.findById(voto.getId()).get();

        assertThat(novoVoto.getId()).isEqualTo(voto.getId());
    }

    /**
     * Quando busca um determinado voto pelo seu id, então deve retornar o voto e o encaminhamento relacionado.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void quandoBuscaVotoPeloId_entaoRetorneJuntoOEncaminhamento() {
        Voto voto = new Voto(this.buscaUmMembro(2L),
                this.buscaUmItemDePauta(1L).getEncaminhamentos().get(0));
        Voto votoSalvo = this.entityManager.persistAndFlush(voto);

        Voto votoRecuperado = this.votoRepository.findById(votoSalvo.getId()).get();

        assertThat(votoRecuperado.getEscolha()).isEqualTo(votoSalvo.getEscolha());
    }

    /**
     * Quando busca um determinado voto pelo seu id, então deve retornar o voto e o membro que votou.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void quandoBuscaVotoPeloId_entaoRetorneJuntoOMembroQueVotou() {
        Voto voto = new Voto(this.buscaUmMembro(2L),
                this.buscaUmItemDePauta(1L).getEncaminhamentos().get(0));
        Voto votoSalvo = this.entityManager.persistAndFlush(voto);

        Voto votoRecuperado = this.votoRepository.findById(votoSalvo.getId()).get();

        assertThat(votoRecuperado.getVotante()).isEqualTo(votoSalvo.getVotante());
    }

    /**
     * Quando busca um determinado voto inexistente por id então deve retornar null.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void quandoProcuraPorIdInvalido_entaoRetorneNull() {
        Voto voto = this.votoRepository.findById(2000L).orElse(null);
        assertThat(voto).isNull();
    }

    /**
     * Recupera um Membro para posterior montagem do Voto
     *
     * @param id
     * @return
     * @author Cristiane
     */
    private Membro buscaUmMembro(Long id) {
        return this.membroRepository.findById(id).get();
    }

    /**
     * Recupera um Item de Pauta para posterior montagem do Voto
     *
     * @param id
     * @return
     * @author Cristiane
     */
    private ItemDePauta buscaUmItemDePauta(Long id) {
        ItemDePauta itemDePauta = this.itemDePautaRepository.findById(id).get();
        itemDePauta.adiciona(new Encaminhamento("Encaminhamento X", itemDePauta));
        itemDePauta.adiciona(new Encaminhamento("Encaminhamento Y", itemDePauta));
        itemDePauta.adiciona(new Encaminhamento("Encaminhamento Z", itemDePauta));

        return this.itemDePautaRepository.save(itemDePauta);
    }

}
