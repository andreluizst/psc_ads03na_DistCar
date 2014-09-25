package gui.managedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import seguranca.LoginInvalidoException;
import seguranca.Usuario;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
@SessionScoped
public class LoginBean {

	private String login;
	private String senha;
	private Usuario usuarioLogado;
	private IFachada fachada = Fachada.obterInstancia();
	private ResourceBundle rb = ResourceBundle.getBundle("util.config");
	
	public String efetuarLogin(){
		try {
			usuarioLogado = fachada.efetuarLogin(login, senha);
		} catch (LoginInvalidoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login/Senha inexistente"));
		}
		return rb.getString("linkIndex");
	}
	
	public String efetuarLogin2(){
		try {
			usuarioLogado = fachada.efetuarLogin(login, senha);
			login = "";
			senha = "";
			return rb.getString("linkHome");	
		} catch (LoginInvalidoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login/Senha inexistente"));
		}
		return rb.getString("linkIndex");
	}
	
	public String efetuarLogoff(){
		usuarioLogado = null;
		return rb.getString("linkIndex");
	}
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}
