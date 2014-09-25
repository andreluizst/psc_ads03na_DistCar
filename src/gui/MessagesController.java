package gui;

import javax.faces.event.ActionEvent;  
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;  
 
@ManagedBean  
public class MessagesController {  
  
    public void addInfo(ActionEvent actionEvent) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sample info message", "PrimeFaces rocks!"));  
    }  
  
    public void addWarn(ActionEvent actionEvent) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Sample warn message", "Watch out for PrimeFaces!"));  
    }  
  
    public void addError(ActionEvent actionEvent) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sample error message", "PrimeFaces makes no mistakes"));  
    }  
  
    public void addFatal(ActionEvent actionEvent) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Sample fatal message", "Fatal Error in System"));  
    }
    /*
    public static void exibirMensagemDeErro(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", mensagem));
    }
    
    public static void exibirMensagemDeErro(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			null, new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo, mensagem));
    }
    
    public static void exibirMensagemInfomativa(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informa��o", mensagem));
    }
    
    public static void exibirMensagemInfomativa(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem));
    }*/
}
