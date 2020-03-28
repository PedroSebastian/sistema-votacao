package br.edu.unipampa.app.unidade.domain;

import br.edu.unipampa.app.testesUnitarios.*;
import br.edu.unipampa.app.testesUnitarios.EncaminhamentoTest;
import br.edu.unipampa.app.testesUnitarios.ItemDePautaTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EncaminhamentoTest.class,
        ItemDePautaTest.class,
        MembroTest.class,
        ModeradorTest.class,
        ReuniaoTest.class,
        br.edu.unipampa.app.unidade.domain.EncaminhamentoTest.class,
        br.edu.unipampa.app.unidade.domain.ItemDePautaTest.class
})
public class JUnitTestSuite {



}
