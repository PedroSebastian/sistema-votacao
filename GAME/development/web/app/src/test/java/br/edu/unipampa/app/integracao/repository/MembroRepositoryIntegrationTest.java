package br.edu.unipampa.app.integracao.repository;

import br.edu.unipampa.app.domain.Membro;
import br.edu.unipampa.app.domain.Reuniao;
import br.edu.unipampa.app.infrastructure.repository.MembroRepository;
import br.edu.unipampa.app.infrastructure.repository.ReuniaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testes de Integração para a classe MembroRepository
 *
 * @author Cristiane
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MembroRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private ReuniaoRepository reuniaoRepository;

//    /**
//     * Quando busca um determinado Membro pelo seu id, então deve retornar o Membro corretamente.
//     *
//     * Teste positivo.
//     *
//     * @author Cristiane
//     */
//    @Test
//    public void quandoProcurarPeloId_entaoRetornaOMembro() {
//        Membro membro = new Membro();
//        membro.setNome("Cristiane");
//        membro.setToken("1234500");
//        membro.setReunioes(this.retornaListaDeReunioes());
//        Membro novoMembro = this.entityManager.persistAndFlush(membro);
//
//        Membro membroRecuperado = this.membroRepository.findById(novoMembro.getId()).orElse(null);
//
////        assertThat(membroRecuperado.getId()).isEqualTo(novoMembro.getId());
////        assertThat(membroRecuperado.getNome()).isEqualTo(novoMembro.getNome());
////        assertThat(membroRecuperado.getToken()).isEqualTo(novoMembro.getToken());
//    }

    /**
     * Quando busca um determinado Membro pelo seu id, então deve retornar o Membro e suas Reuniões.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void quandoProcurarPeloId_entaoRetornaOMembroEReunioes() {
        Membro membro = new Membro();
        membro.setNome("Cristiane F.");
        membro.setReunioes(this.retornaListaDeReunioes());
        Membro novoMembro = this.entityManager.persistAndFlush(membro);

        Membro membroRecuperado = this.membroRepository.findById(novoMembro.getId()).orElse(null);

        assertThat(membroRecuperado.getReunioes()).isEqualTo(novoMembro.getReunioes());
    }

    /**
     * Quando busca um determinado Membro inexistente por id, então deve retornar null.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void quandoProcurarPeloId_entaoRetorneValorNull() {
        Membro membroRecuperado = this.membroRepository.findById(20000L).orElse(null);

        assertThat(membroRecuperado).isNull();
    }

    /**
     * Quando busca um determinado Membro pelo seu token, então deve retornar o Membro corretamente.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void quandoProcurarPeloToken_entaoRetornaMembro() {
        Membro membro = new Membro();
        membro.setNome("Cristiane Fontoura");
        membro.setToken("1234500LK");
        membro.setReunioes(this.retornaListaDeReunioes());
        Membro novoMembro = this.entityManager.persistAndFlush(membro);

        String token = "1234500LK";
        Membro membroDB = this.membroRepository.getMembroByToken(token);

        assertThat(membroDB).isEqualTo(novoMembro);
        assertThat(membroDB.getToken()).isEqualTo(novoMembro.getToken());
        assertThat(membroDB.getId()).isEqualTo(novoMembro.getId());
    }

    /**
     * Quando busca um determinado Membro inexistente por um token, então deve retornar null.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void quandoProcurarPeloToken_entaoRetornaValorNull() {
        String token = "1234500LK!!LK";
        Membro membroRecuperado = this.membroRepository.getMembroByToken(token);

        assertThat(membroRecuperado).isNull();
    }

    /**
     * Dado um conjunto de Membros e Reuniões salvas, quando busca um determinado Membro pelo token
     * e pela Reunião na qual participa, então deve retornar o Membro dessa Reunião.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void dadoUmConjundoDeMembrosEReunioes_quandoBuscarPorTokenEReuniao_retornaOMembroDaReuniao() {
        String token = "1234500";
        Reuniao reuniao = this.buscaUmaReuniaoPor(1L);
        Membro membroRecuperado = this.membroRepository.getMembroByTokenAndReuniaoId(reuniao, token);

        assertThat(membroRecuperado).isEqualTo(this.membroRepository.findById(1L).get());
    }

    /**
     * Dado um conjunto de Membros e Reuniões salvas, quando busca um determinado Membro pelo token
     * e pela Reunião na qual NÃO participa, então deve retornar valor null.
     *
     * Teste positivo.
     *
     * @author Cristiane
     */
    @Test
    public void dadoUmConjundoDeMembrosEReunioes_quandoBuscarPorTokenEReuniao_retornaNulo() {
        String token = "1234500";
        Reuniao reuniao = this.buscaUmaReuniaoPor(5L);
        Membro membroRecuperado = this.membroRepository.getMembroByTokenAndReuniaoId(reuniao, token);

        assertThat(membroRecuperado).isNull();
    }

    /**
     * Busca uma reunião por id do banco para posterior montagem de Membro
     *
     * @param id
     * @return Reuniao
     */
    private Reuniao buscaUmaReuniaoPor(Long id) {
        return this.reuniaoRepository.findById(id).orElse(null);
    }


    /**
     * Retorna uma lista de reuniões
     *
     * @return java.util.ArrayList
     * @author Cristiane
     */
    private List<Reuniao> retornaListaDeReunioes() {
        List<Reuniao> reunioes = new ArrayList<>();
        reunioes.add(this.buscaUmaReuniaoPor(1L));
        reunioes.add(this.buscaUmaReuniaoPor(2L));
        reunioes.add(this.buscaUmaReuniaoPor(3L));

        return reunioes;
    }

}
