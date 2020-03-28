package br.edu.unipampa.app.domain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ApuracaoDaVotacao {

    private Votacao votacao;

    public ApuracaoDaVotacao(Votacao votacao) {

        this.setVotacao(votacao);

    }

    public ResultadoDaVotacao realizaApuracao() {

        ResultadoDaVotacao resultadoDaVotacao = new ResultadoDaVotacao();

        for (Encaminhamento encaminhamento : this.votacao.getItemDePauta().getEncaminhamentos()) {

            ResultadosDoEncaminhamento resultadosDoEncaminhamento =
                    this.processaResultadoDoEncaminhamento(encaminhamento, this.votacao);
            resultadoDaVotacao.adicionaUm(resultadosDoEncaminhamento);
        }

        resultadoDaVotacao.ordenar();
        resultadoDaVotacao = this.calculaPorcentagem(resultadoDaVotacao);
        // this.apuraSegundoTurno(resultadoDaVotacao);

        return resultadoDaVotacao;
    }

    private ResultadosDoEncaminhamento processaResultadoDoEncaminhamento(Encaminhamento encaminhamento, Votacao votacao) {
        ResultadosDoEncaminhamento resultadosDoEncaminhamento = new ResultadosDoEncaminhamento();
        resultadosDoEncaminhamento.setEncaminhamento(encaminhamento);

        for (Voto voto : votacao.getUrna().getVotosParaContagem()) {
            if (voto.pegaItemDeVoto().equals(resultadosDoEncaminhamento.getEncaminhamento())) {
                resultadosDoEncaminhamento.computaUmVoto().eAdiciona(voto.getVotante());
            }
        }
        return resultadosDoEncaminhamento;
    }

//    private void defineStatusDoResultadosDeEncaminhamento(ResultadoDaVotacao resultadoDaVotacao) {
//        if (this.votacao.getTurno() == Turno.PRIMEIRO && this.votacao.i) {
//            for (ResultadosDoEncaminhamento doEncaminhamento : resultadoDaVotacao.getResultadosDoEncaminhamentos()) {
//
//            }
//        }
//    }

    public ResultadoDaVotacao calculaPorcentagem(ResultadoDaVotacao resultadoDaVotacao) {
        AtomicInteger totalDeVotos = new AtomicInteger();

        resultadoDaVotacao.getResultadosDoEncaminhamentos().forEach(encaminhamento -> {
            totalDeVotos.addAndGet(encaminhamento.getQuantidadeDeVotos());
        });

        resultadoDaVotacao.getResultadosDoEncaminhamentos().forEach(resultado -> {
            double porcentagem;

            if (resultado.getQuantidadeDeVotos() != 0) {
                porcentagem = (double) ((resultado.getQuantidadeDeVotos() * 100) / totalDeVotos.get());
            } else {
                porcentagem = 0.0;
            }

            resultado.setPorcentagem(porcentagem);
        });

        return resultadoDaVotacao;
    }

    public Boolean apuraSegundoTurno(ResultadoDaVotacao resultadoDaVotacao) {
        List<ResultadosDoEncaminhamento> resultadosDoEncaminhamento = resultadoDaVotacao.getResultadosDoEncaminhamentos();
        Boolean isEmpate = false;
        for (int i = 0; i < resultadoDaVotacao.getResultadosDoEncaminhamentos().size(); i++) {
            for (int j = 1; j < resultadoDaVotacao.getResultadosDoEncaminhamentos().size(); j++) {
                if (resultadoDaVotacao.getResultadosDoEncaminhamentos().get(i).getQuantidadeDeVotos() ==
                        resultadoDaVotacao.getResultadosDoEncaminhamentos().get(j).getQuantidadeDeVotos() &&
                        resultadoDaVotacao.getResultadosDoEncaminhamentos().get(i).getQuantidadeDeVotos() != 0
                        ) {
                    isEmpate = true;
                }
            }
        }
        return isEmpate;
    }

    public Votacao getVotacao() {
        return votacao;
    }

    /**
     * @param votacao
     */
    private void setVotacao(Votacao votacao) {
        if (!(votacao instanceof Votacao)) {
            throw new IllegalArgumentException("Votação deve ser do tipo votação!");
        }
        if (votacao == null) {
            throw new IllegalArgumentException("Votação não pode ser nula!");
        }
        this.votacao = votacao;
    }
}
