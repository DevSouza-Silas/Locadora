package com.locadora.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Message {
	
	public static final String MSG_EDICAO = "Editado com sucesso.";
	public static final String MSG_REMOCAO = "Removido com sucesso.";
	public static final String MSG_SALVO = "Salvo com sucesso.";

	public static void info(String text) {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"", text);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void erro(String text) {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", text);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
