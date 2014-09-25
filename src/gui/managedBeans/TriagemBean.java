package gui.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="triagem")
@SessionScoped
public class TriagemBean {
	//private FuncaoBean funcaoBean;
	private String tipoGerencia = "tipogerencia.xhtml?faces-redirect=true";
	private String departamento = "departamento.xhtml?faces-redirect=true";
	private String funcao = "funcao.xhtml?faces-redirect=true";
	private String funcionario = "funcionario.xhtml?faces-redirect=true";
	private String centro = "centro.xhtml?faces-redirect=true";
	private String cidade = "cidade.xhtml?faces-redirect=true";
	private String escolaridade = "escolaridade.xhtml?faces-redirect=true";
	
	
	public TriagemBean(){
		super();
		//funcaoBean = new FuncaoBean();
	}

	public String getTipoGerencia() {
		return tipoGerencia;
	}

	public String getFuncao() {
		return funcao;//funcaoBean.carregarPagina();//funcao;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public String getCentro() {
		return centro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public String getDepartamento() {
		return departamento;
	}
	
	public String carregarFuncao(){
		return "funcaoBean.carregarPagina";
	}
	
	
}
