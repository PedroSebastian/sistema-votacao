package br.edu.unipampa.app;

import br.edu.unipampa.app.domain.*;
import br.edu.unipampa.app.infrastructure.repository.ReuniaoRepository;
import com.github.javafaker.Faker;
import org.atmosphere.cpr.ContainerInitializer;
import org.primefaces.push.PushServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static br.edu.unipampa.app.domain.TipoReuniao.ORDINARIA;

@EntityScan(basePackageClasses = {AppApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class AppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppApplication.class);
    }

    @Bean
    public ServletRegistrationBean pushServlet() {
        ServletRegistrationBean pushServlet = new ServletRegistrationBean(new PushServlet(), "/primepush/*");
        pushServlet.addInitParameter("org.atmosphere.annotation.packages", "org.primefaces.push");
        pushServlet.addInitParameter("org.atmosphere.cpr.packages", "WEB-INF/classes/br.edu.unipampa.app.application.resources,br.edu.unipampa.app.application.resources");
        pushServlet.setAsyncSupported(true);
        pushServlet.setLoadOnStartup(0);
        pushServlet.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return pushServlet;
    }

    private static class EmbeddedAtmosphereInitializer extends ContainerInitializer implements ServletContextInitializer {

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
            onStartup(Collections.<Class<?>> emptySet(), servletContext);
        }

    }

    @Bean
    public EmbeddedAtmosphereInitializer atmosphereInitializer() {
        return new EmbeddedAtmosphereInitializer();
    }

    @Bean
    public CommandLineRunner init(ReuniaoRepository repository) {
        return args -> {
            repository.deleteAll();
            List<Reuniao> reunioes = new ArrayList<>();
            List<Membro> membros = new ArrayList<>();
            List<ItemDePauta> itens = new ArrayList<>();

            Faker faker = new Faker();

            for (int index = 0; index <= 5; index++) {
                Reuniao reuniao = new Reuniao();
                Moderador moderador = new Moderador();

                moderador.setNome(faker.lordOfTheRings().character());

                reuniao.setDescricao(faker.lorem().sentence(faker.number().numberBetween(5, 10), 5));
                reuniao.setData(LocalDate.now());
                reuniao.setHorario(LocalTime.now());
                reuniao.setEstaAberta(faker.bool().bool());
                reuniao.setEstaAberta(true);
                reuniao.setTipoDaReuniao(TipoDaReuniao.ORDINARIA);

                reuniao.setModerador(moderador);

                for (int j = 1; j <= 9; j++) {
                    ItemDePauta itemDePauta = new ItemDePauta();

                    itemDePauta.setDescricao(faker.lorem().sentence(faker.number().numberBetween(5, 15), 5));
                    itemDePauta.setRelator(faker.name().fullName());
                    itemDePauta.setOrdem(j);

                    itemDePauta.setReuniao(reuniao);

                    itens.add(itemDePauta);
                }

                reuniao.setItensDePauta(itens);

                for (int j = 0; j <= 9; j++) {
                    String token = "12345";

                    Membro membro = new Membro();
                    List<Reuniao> reunioesMembro = new ArrayList<>();
                    reunioesMembro.add(reuniao);
                    membro.setReunioes(reunioesMembro);

                    membro.setNome(faker.harryPotter().character());
                    membro.setToken(token + index + j);

                    membros.add(membro);
                }

                reuniao.setMembros(membros);

                reunioes.add(reuniao);
            }

            repository.saveAll(reunioes);

            System.out.println("Foi ou não?");
        };
    }


    public static void main(String[] args) {

        SpringApplication.run(AppApplication.class, args);
    }
}
