package gui.managedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import seguranca.TipoUsuario;
import seguranca.Usuario;
import seguranca.Perfil;
import fachada.Fachada;
import gui.MsgPrimeFaces;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private String senhaRepetida;
	private ResourceBundle rb = ResourceBundle.getBundle("util.config");

	public String salvarUsuario() {
		if (!usuario.getSenhaAtual().equals(senhaRepetida)) {
			FacesContext.getCurrentInstance().addMessage("senhaRep",
					new FacesMessage("Repetição de senha não confere"));
			return null;
		} else {
			try{
				Fachada.obterInstancia().inserirUsuario(usuario);
			}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Usuário cadastrado com sucesso!"));			
			return rb.getString("linkIndex");
		}
	}
	
	public boolean isShowDadosAdicionais(){
		if (usuario.getTipo() != null && usuario.getTipo() == TipoUsuario.ADMINISTRADOR){
			return true;
		}
		return false;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaRepetida() {
		return senhaRepetida;
	}

	public void setSenhaRepetida(String senhaRepetida) {
		this.senhaRepetida = senhaRepetida;
	}

	public TipoUsuario[] getTiposUsuario(){
		return TipoUsuario.values();
	}
}
