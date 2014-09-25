package gui.managedBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import classesBasicas.Situacao;
import classesBasicas.TipoGerencia;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;


@ManagedBean
@SessionScoped
public class TipoGerenciaBean {
	private static final String OP_NOVA = "NOVO  Tipo de Gerência";
	private static final String OP_ALTERAR = "Alterar Tipo de Gerência";
	private static final String OP_VISUALIZAR = "Propriedades do Tipo de Gerência";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("util.config");
	
	private TipoGerencia tipoGerencia;
	private TipoGerencia tipoGerenciaParaPesquisa;
	private List<TipoGerencia> lista;
	private TipoGerencia tipoGerenciaSelecionada;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	private FacesMessage msgPendente;
	
	
	
	public TipoGerenciaBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novoTipoGerencia();
		if (lista==null)
			lista = new ArrayList<TipoGerencia>();
		else
			lista.clear();
		listaEstaVazia = true;
		iniciarObjParaPesquisa();
		tipoGerenciaSelecionada = null;
		tituloOperacao = TipoGerenciaBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = TipoGerenciaBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
	}

	private void iniciarObjParaPesquisa(){
		tipoGerenciaParaPesquisa = new TipoGerencia();
	}

	private void prepararParaExibirDados(TipoGerencia obj){
		tipoGerencia = obj;
		tipoGerencia.setSituacao(obj.getSituacao());
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (tipoGerenciaSelecionada == null)
			return null;
		try{
			prepararParaExibirDados(fachada.pegarTipoGerenciaPorId(tipoGerenciaSelecionada.getCodigo()));
			tituloOperacao = TipoGerenciaBean.OP_ALTERAR;
			textoBotaoFecharOuCancelar = TipoGerenciaBean.TXT_BTN_CANCELAR;
			somenteLeitura = false;
			return resourceBundle.getString("linkTipoGerenciaProp");//"tipoGerencia-prop";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public String novo(){
		novoTipoGerencia();
		tituloOperacao = TipoGerenciaBean.OP_NOVA;
		textoBotaoFecharOuCancelar = TipoGerenciaBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkTipoGerenciaProp");//"tipogerencia-prop";
	}
	
	private void novoTipoGerencia(){
		tipoGerencia = new TipoGerencia();
		tipoGerenciaSelecionada = null;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		if (tipoGerenciaSelecionada == null)
			return;
		try{
			tipoGerencia = fachada.pegarTipoGerenciaPorId(tipoGerenciaSelecionada.getCodigo());
			fachada.excluirTipoGerencia(tipoGerencia);
			if (lista != null)
				consultar();
			MsgPrimeFaces.exibirMensagemInfomativa("Tipo de gerência " + tipoGerenciaSelecionada.getDescricao() + " excluida com sucesso!");
			novoTipoGerencia();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		tipoGerenciaSelecionada = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			if (tipoGerencia.getCodigo() == null || tipoGerencia.getCodigo() == 0)
				tipoGerencia.setCodigo(null);
			fachada.salvarTipoGerencia(tipoGerencia);
			consultar();
			msgPendente = MsgPrimeFaces.criarMsgInfo("Tipo de gerência " + tipoGerencia.getDescricao()
					+ " salva com sucesso!");
			novoTipoGerencia();
			somenteLeitura = true;
			return resourceBundle.getString("linkTipoGerencia");//"tipogerencia";
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
	
	public String cancelar(){
		return resourceBundle.getString("linkTipoGerencia");
	}
	
	public void consultar(){
		try{
			atualizarLista(fachada.consultarTipoGerencia(tipoGerenciaParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (tipoGerenciaSelecionada == null)
			return null;
		prepararParaExibirDados(tipoGerenciaSelecionada);
		tituloOperacao = TipoGerenciaBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = TipoGerenciaBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return resourceBundle.getString("linkTipoGerenciaProp");//"tipogerencia-prop";
	}
	

	private void atualizarLista(List<TipoGerencia> lista) {
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
	
	public String carregarPagina(){
		inicializar();
		return resourceBundle.getString("linkTipoGerencia");//"tipogerencia.xhtml?faces-redirect=true";
	}
	
	public String voltarParaPaginaPrincipal(){
		return resourceBundle.getString("linkHome");
	}
	
	
	// GETs e SETs

	public TipoGerencia getTipoGerencia() {
		return tipoGerencia;
	}

	public void setTipoGerencia(TipoGerencia tipoGerencia) {
		this.tipoGerencia = tipoGerencia;
	}

	public TipoGerencia getTipoGerenciaParaPesquisa() {
		return tipoGerenciaParaPesquisa;
	}

	public void setTipoGerenciaParaPesquisa(TipoGerencia tipoGerenciaParaPesquisa) {
		this.tipoGerenciaParaPesquisa = tipoGerenciaParaPesquisa;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public List<TipoGerencia> getLista() {
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

	public TipoGerencia getTipoGerenciaSelecionada() {
		return tipoGerenciaSelecionada;
	}

	public void setTipoGerenciaSelecionada(TipoGerencia tipoGerenciaSelecionada) {
		this.tipoGerenciaSelecionada = tipoGerenciaSelecionada;
	}
	
	
}
