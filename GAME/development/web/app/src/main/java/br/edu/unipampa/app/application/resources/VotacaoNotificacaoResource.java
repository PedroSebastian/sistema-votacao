package br.edu.unipampa.app.application.resources;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

import javax.faces.application.FacesMessage;

/**
 * Created by Pedro Sebastian on 30/05/2018.
 */
@PushEndpoint("/votacao-finalizada")
public class VotacaoNotificacaoResource {

    @OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage message){
        return message;
    }

}
