package gui.managedBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import classesBasicas.Escolaridade;
import classesBasicas.Situacao;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class EscolaridadeBean {
	private static final String OP_NOVA = "NOVA  Escolaridade";
	private static final String OP_ALTERAR = "Alterar Escolaridade";
	private static final String OP_VISUALIZAR = "Propriedades da Escolaridade";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	private ResourceBundle rb = ResourceBundle.getBundle("util.config");
	private String home = rb.getString("linkHome");
	
	private Escolaridade escolaridade;
	private Escolaridade escolaridadeParaPesquisa;
	private List<Escolaridade> lista;
	private Escolaridade escolaridadeSelecionada;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	private FacesMessage msgPendente;
	
	
	public EscolaridadeBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novaEscolaridade();
		msgPendente = null;
		if (lista==null)
			lista = new ArrayList<Escolaridade>();
		else
			lista.clear();
		listaEstaVazia = true;
		iniciarObjParaPesquisa();
		escolaridadeSelecionada = null;
		tituloOperacao = EscolaridadeBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = EscolaridadeBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
	}

	private void iniciarObjParaPesquisa(){
		escolaridadeParaPesquisa = new Escolaridade();
	}

	private void prepararParaExibirDados(Escolaridade obj){
		escolaridade = obj;
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (escolaridadeSelecionada == null)
			return null;
		try{
			prepararParaExibirDados(fachada.pegarEscolaridadePorId(escolaridadeSelecionada.getCodigo()));
			tituloOperacao = EscolaridadeBean.OP_ALTERAR;
			textoBotaoFecharOuCancelar = EscolaridadeBean.TXT_BTN_CANCELAR;
			somenteLeitura = false;
			return rb.getString("linkEscolaridadeProp");//"escolaridade-prop";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public String novo(){
		novaEscolaridade();
		tituloOperacao = EscolaridadeBean.OP_NOVA;
		textoBotaoFecharOuCancelar = EscolaridadeBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return rb.getString("linkEscolaridadeProp");//"escolaridade-prop";
	}
	
	private void novaEscolaridade(){
		escolaridade = new Escolaridade();
		escolaridadeSelecionada = null;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		if (escolaridadeSelecionada == null)
			return;
		try{
			escolaridade = fachada.pegarEscolaridadePorId(escolaridadeSelecionada.getCodigo());
			fachada.excluirEscolaridade(escolaridade);
			if (lista!= null && lista.size() > 0)
				consultar();
			MsgPrimeFaces.exibirMensagemInfomativa("Escolaridade " + escolaridadeSelecionada.getDescricao() + " excluida com sucesso!");
			novaEscolaridade();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		escolaridadeSelecionada = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			if (escolaridade.getCodigo() == null || escolaridade.getCodigo() == 0)
				escolaridade.setCodigo(null);
			fachada.salvarEscolaridade(escolaridade);
			//MsgPrimeFaces.exibirMensagemInfomativa("Escolaridade salva com sucesso!");
			consultar();
			novaEscolaridade();
			somenteLeitura = true;
			msgPendente = MsgPrimeFaces.criarMsgInfo("Escolaridade " + escolaridade.getDescricao() 
					+ " salva com sucesso!");
			return rb.getString("linkEscolaridade");//"escolaridade";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public String getExibirMensagemPendente(){
		if (msgPendente != null){
			MsgPrimeFaces.exibirMensagem(msgPendente);
			msgPendente = null;
		}
		return "";
	}
	
	public void consultar(){
		try{
			atualizarLista(fachada.consultarEscolaridade(escolaridadeParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (escolaridadeSelecionada == null)
			return null;
		prepararParaExibirDados(escolaridadeSelecionada);
		tituloOperacao = EscolaridadeBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = EscolaridadeBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return rb.getString("linkEscolaridadeProp");//"escolaridade-prop";
	}
	

	private void atualizarLista(List<Escolaridade> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public void limpar(){
		iniciarObjParaPesquisa();
		situacaoSelecionada = null;
	}
	
	public String cancelar(){
		somenteLeitura = true;
		return rb.getString("linkEscolaridade");
	}
	
	public String carregarPagina(){
		inicializar();
		return rb.getString("linkEscolaridade");//"escolaridade.xhtml?faces-redirect=true";
	}
	
	public String voltarParaPaginaPrincipal(){
		return rb.getString("linkHome");
	}
	
	
	//*** GETs e SETs

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Escolaridade getEscolaridadeParaPesquisa() {
		return escolaridadeParaPesquisa;
	}

	public void setEscolaridadeParaPesquisa(Escolaridade escolaridadeParaPesquisa) {
		this.escolaridadeParaPesquisa = escolaridadeParaPesquisa;
	}

	public Escolaridade getEscolaridadeSelecionada() {
		return escolaridadeSelecionada;
	}

	public void setEscolaridadeSelecionada(Escolaridade escolaridadeSelecionada) {
		this.escolaridadeSelecionada = escolaridadeSelecionada;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public List<Escolaridade> getLista() {
		return lista;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public boolean isListaEstaVazia() {
		return listaEstaVazia;
	}

	public String getTituloOperacao() {
		return tituloOperacao;
	}

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public String getHome() {
		return home;
	}
	
	
	
}
