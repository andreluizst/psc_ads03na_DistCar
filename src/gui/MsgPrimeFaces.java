package gui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MsgPrimeFaces {
	public static void exibirMensagemDeErro(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"ERRO", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", mensagem));
    }
    
    public static void exibirMensagemDeErro(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"ERRO", new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo, mensagem));
    }
    
    public static void exibirMensagemInfomativa(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"Informação", new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação", mensagem));
    }
    
    public static void exibirMensagemInfomativa(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"Informação", new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem));
    }
    
    public static void exibirMensagemDeAviso(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"Aviso", new FacesMessage(FacesMessage.SEVERITY_WARN,"Informação", mensagem));
    }
    
    public static void exibirMensagemDeAviso(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"Aviso", new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensagem));
    }
    
    public static void exibirMensagemFatal(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"FALHA", new FacesMessage(FacesMessage.SEVERITY_FATAL,"FALHA", mensagem));
    }
    
    public static void exibirMensagemFatal(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"FALHA", new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensagem));
    }
    
    public static FacesMessage criarMsgInfo(String mensagem){
    	return new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação", mensagem);
    }
    
    public static FacesMessage criarMsgErro(String mensagem){
    	return new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", mensagem);
    }
    
    public static FacesMessage criarMsgAviso(String mensagem){
    	return new FacesMessage(FacesMessage.SEVERITY_WARN,"Informação", mensagem);
    }
    
    public static FacesMessage criarMsgFalha(String mensagem){
    	return new FacesMessage(FacesMessage.SEVERITY_FATAL,"FALHA", mensagem);
    }
    
    public static void exibirMensagem(FacesMessage facesMessage){
    	if (facesMessage.getSeverity() == FacesMessage.SEVERITY_INFO)
    		FacesContext.getCurrentInstance().addMessage("Informação", facesMessage);
    	if (facesMessage.getSeverity() == FacesMessage.SEVERITY_WARN)
    		FacesContext.getCurrentInstance().addMessage("Aviso", facesMessage);
    	if (facesMessage.getSeverity() == FacesMessage.SEVERITY_FATAL)
    		FacesContext.getCurrentInstance().addMessage("FALHA", facesMessage);
    	if (facesMessage.getSeverity() == FacesMessage.SEVERITY_ERROR)
    		FacesContext.getCurrentInstance().addMessage("Erro", facesMessage);
    	
    }
    
}
