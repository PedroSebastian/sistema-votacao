package br.edu.unipampa.app;

import br.edu.unipampa.app.integracao.EncaminhamentosTest;
//import br.edu.unipampa.app.testesAutomatizados.CadastrarItemDePautaTest;
import br.edu.unipampa.app.testesAutomatizados.ReuniaoTest;
import br.edu.unipampa.app.testesUnitarios.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		/* Unidade */
		EncaminhamentoTest.class,
		ItemDePautaTest.class,
		MembroTest.class,
		ModeradorTest.class,
		br.edu.unipampa.app.testesUnitarios.ReuniaoTest.class,
		br.edu.unipampa.app.unidade.domain.EncaminhamentoTest.class,
		br.edu.unipampa.app.unidade.domain.ItemDePautaTest.class,

		/* Integração */
		EncaminhamentosTest.class,

		/* Sistema */
		ReuniaoTest.class,
})
public class AppApplicationTests {



}
