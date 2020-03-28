package br.edu.unipampa.app.integracao.service;

import br.edu.unipampa.app.application.service.ItemDePautaService;
import br.edu.unipampa.app.domain.ItemDePauta;
import br.edu.unipampa.app.infrastructure.repository.ItemDePautaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Teste de integração para a classe br.edu.unipampa.app.application.service.ItemDePautaService
 *
 * @author Cristiane
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemDePautaServiceTest {

    @TestConfiguration
    static class ItemDePautaServiceTestContextConfigurtion {

        @Bean
        public ItemDePautaService itemDePautaService() {
            return new ItemDePautaService();
        }

    }

    @Autowired
    private ItemDePautaService itemDePautaService;

    @Autowired
    private ItemDePautaRepository itemDePautaRepository;

    /**
     * Verifica se salva um item de pauta valido com encaminhamento padrão
     *
     * Teste positivo
     *
     * @author Cristiane
     */
    @Test
    public void dadoUmItemDePauta_quantoItemDeEncaminhamentoPadraoForValido_entaoSalvaERetornaOItemSalvo() {
        ItemDePauta itemDePauta = this.recuperaItemPor(1L);

        ItemDePauta itemSalvo = this.itemDePautaService.criaItemDePautaComPadrao(itemDePauta);

        assertThat(itemDePauta.getId()).isEqualTo(itemSalvo.getId());
    }

    /**
     * Verifica se salva item com encaminhamento padrão com opções corretas: FAVORÁVEL, CONTRÁRIO E ABSTENÇÃO
     *
     * Teste positivo
     *
     * @author Cristiane
     */
    @Test
    public void dadoUmItemDePauta_quantoEnviaPraEncaminhamentoPadrao_entaoSalvaItemComEncaminhamentosERetorna() {
        ItemDePauta itemDePauta = this.recuperaItemPor(1L);

        ItemDePauta itemSalvo = this.itemDePautaService.criaItemDePautaComPadrao(itemDePauta);

        assertThat(itemDePauta.getEncaminhamentos().get(0).getDescricao()).isEqualTo("Favorável");
        assertThat(itemDePauta.getEncaminhamentos().get(1).getDescricao()).isEqualTo("Contrário");
        assertThat(itemDePauta.getEncaminhamentos().get(2).getDescricao()).isEqualTo("Abstenção");
    }

    /**
     * Dado um item de pauta já salvo com encaminhamento padrão, ao enviar para adicionar item padrão novamente
     * então deve sobreescrever o encaminhamento.
     *
     * Teste negativo
     *
     * @author Cristiane
     */
    @Test(expected = IllegalArgumentException.class)
    public void dadoUmItemComEncaminhamentoPadrao_quandoEnviarParaAdicionarEncaminhamentoPadrao_entaoSobrescreveEncaminhamento() {
        ItemDePauta itemDePauta = this.recuperaItemPor(1L);
        itemDePauta = this.itemDePautaService.criaItemDePautaComPadrao(itemDePauta);

        ItemDePauta itemSalvoNovamente = this.itemDePautaService.criaItemDePautaComPadrao(itemDePauta);

        assertThat(itemDePauta.getEncaminhamentos().size())
                .isEqualTo(itemSalvoNovamente.getEncaminhamentos().size());

        assertThat(itemSalvoNovamente.getEncaminhamentos().get(0).getDescricao()).isEqualTo("Favorável");
        assertThat(itemSalvoNovamente.getEncaminhamentos().get(1).getDescricao()).isEqualTo("Contrário");
        assertThat(itemSalvoNovamente.getEncaminhamentos().get(2).getDescricao()).isEqualTo("Abstenção");
    }

    /**
     * Deve lançar exceção de argumento ilegal caso ItemDePauta for nulo ao tentar adicionar
     * encaminhamento padrão.
     *
     * Teste negativo
     *
     * @author Cristiane
     */
    @Test(expected = IllegalArgumentException.class)
    public void dadoUmItemDePautaNulo_quantoEnviaPraEncaminhamentoPadrao_entaoLancaEcexaoDeArgumentoIlegal() {
        ItemDePauta itemDePauta = null;

        ItemDePauta itemSalvo = this.itemDePautaService.criaItemDePautaComPadrao(itemDePauta);
    }

    /**
     * Dado um conjunto de itens salvos, verifica se retorna uma lista de itens corretamente.
     *
     * Teste positivo
     *
     * @author Cristiane
     */
    @Test
    public void dadoUmConjuntoDeItensSalvos_quandoBuscarPorTodos_entaoRetornaUmaListaComTodosOsItens() {
//        List<ItemDePauta> itensDePauta = this.itemDePautaService.listaItensDePauta();
//
//        assertThat(itensDePauta.isEmpty()).isFalse();
    }

    /**
     * Dado um conjunto de itens salvos, verifica se retorna uma lista de itens de acordo com o ID da reunião.
     *
     * Teste positivo
     *
     * @author Cristiane
     */
    @Test
    public void dadoUmConjuntoDeItensSalvos_quandoBuscarPeloIdDaReuniao_entaoRetornaUmaListaComTodosOsItensDessaReuniao() {
        List<ItemDePauta> itensDePauta = this.itemDePautaService.pegaPeloIdDaReuniao(1L);

        assertThat(itensDePauta.isEmpty()).isFalse();
        assertThat(itensDePauta.size()).isEqualTo(9);
    }

    /**
     * Dado um conjunto de itens salvos, verifica se não retorna uma lista de itens de acordo com o ID da reunião inexistente.
     *
     * Teste negativo
     *
     * @author Cristiane
     */
    @Test
    public void dadoUmConjuntoDeItensSalvos_quandoBuscarPeloIdDaReuniaoInexistente_entaoRetornaUmaListaVaziaDeItens() {
        List<ItemDePauta> itensDePauta = this.itemDePautaService.pegaPeloIdDaReuniao(100L);

        assertThat(itensDePauta.isEmpty()).isTrue();
    }

    private ItemDePauta recuperaItemPor(Long id) {
        return this.itemDePautaRepository.getOne(id);
    }

}
