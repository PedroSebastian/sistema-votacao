package br.edu.unipampa.app.infrastructure.JSF;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessageUtil {

    public static boolean isPostback() {
        return FacesContext.getCurrentInstance().isPostback();
    }

    public static boolean isNotPostback() {
        return !isPostback();
    }

    public static void addErrorMessage(String alert, String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, alert, message));
    }

    public static void addInfoMessage(String alert, String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, alert, message));
    }

}
