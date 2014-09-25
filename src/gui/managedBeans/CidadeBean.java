package gui.managedBeans;

import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import classesBasicas.Cidade;
import classesBasicas.Situacao;
import classesBasicas.UnidadeFederativa;


@ManagedBean
@SessionScoped
public class CidadeBean {
	private static final String OP_NOVA = "NOVA  Cidade";
	private static final String OP_ALTERAR = "Alterar Cidade";
	private static final String OP_VISUALIZAR = "Propriedades da Cidade";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	private ResourceBundle rb = ResourceBundle.getBundle("util.config");
	
	private Cidade cidade;
	private Cidade cidadeParaPesquisa;
	private List<Cidade> lista;
	private Cidade cidadeSelecionada;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private List<UnidadeFederativa> ufs;
	private Integer codigoUfSelecionada;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	private FacesMessage msgPendente;
	
	
	
	public CidadeBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novaCidade();
		if (lista==null)
			lista = new ArrayList<Cidade>();
		else
			lista.clear();
		listaEstaVazia = true;
		iniciarObjParaPesquisa();
		cidadeSelecionada = null;
		tituloOperacao = CidadeBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = CidadeBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			ufs = fachada.listarUFs();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void iniciarObjParaPesquisa(){
		cidadeParaPesquisa = new Cidade();
		cidadeParaPesquisa.setUnidadeFederativa(new UnidadeFederativa());
		//cidadeParaPesquisa.getUnidadeFederativa().getCodigo();
	}

	private void prepararParaExibirDados(Cidade obj){
		cidade = obj;
		cidade.setUnidadeFederativa(obj.getUnidadeFederativa());
		codigoUfSelecionada = cidade.getUnidadeFederativa().getCodigo();
		cidade.setSituacao(obj.getSituacao());
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (cidadeSelecionada == null)
			return null;
		try{
			prepararParaExibirDados(fachada.pegarCidadePorId(cidadeSelecionada.getCodigo()));
			tituloOperacao = CidadeBean.OP_ALTERAR;
			textoBotaoFecharOuCancelar = CidadeBean.TXT_BTN_CANCELAR;
			somenteLeitura = false;
			return rb.getString("linkCidadeProp");//"cidade-prop";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public String novo(){
		novaCidade();
		prepararParaExibirDados(cidade);
		tituloOperacao = CidadeBean.OP_NOVA;
		textoBotaoFecharOuCancelar = CidadeBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return rb.getString("linkCidadeProp");//"cidade-prop";
	}
	
	private void novaCidade(){
		cidade = new Cidade();
		cidadeSelecionada = null;
		codigoUfSelecionada = null;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		if (cidadeSelecionada == null)
			return;
		try{
			cidade = fachada.pegarCidadePorId(cidadeSelecionada.getCodigo());
			fachada.excluirCidade(cidade);
			if (lista != null)
				consultar();
			MsgPrimeFaces.exibirMensagemInfomativa("Cidade " + cidadeSelecionada.getNome() + " excluida com sucesso!");
			novaCidade();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		cidadeSelecionada = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			cidade.setUnidadeFederativa(fachada.pegarUnidadeFederativaPorId(codigoUfSelecionada));
			if (cidade.getCodigo() == null || cidade.getCodigo() == 0)
				cidade.setCodigo(null);
			fachada.salvarCidade(cidade);
			consultar();
			msgPendente = MsgPrimeFaces.criarMsgInfo("Cidade " + cidade.getNome() + " salva com sucesso!");
			novaCidade();
			somenteLeitura = true;
			return rb.getString("linkCidade");//"cidade";
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
			atualizarLista(fachada.consultarCidade(cidadeParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (cidadeSelecionada == null)
			return null;
		prepararParaExibirDados(cidadeSelecionada);
		tituloOperacao = CidadeBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = CidadeBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return rb.getString("linkCidadeProp");//"cidade-prop";
	}
	

	private void atualizarLista(List<Cidade> lista) {
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
		cidade = null;
		somenteLeitura = true;
		return rb.getString("linkCidade");
	}
	
	public String carregarPagina(){
		inicializar();
		return rb.getString("linkCidade");//"cidade.xhtml?faces-redirect=true";
	}
	
	public String voltarParaPaginaPrincipal(){
		return rb.getString("linkHome");
	}
	
	//*** GETs e SETs

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cidade getCidadeParaPesquisa() {
		return cidadeParaPesquisa;
	}

	public void setCidadeParaPesquisa(Cidade cidadeParaPesquisa) {
		this.cidadeParaPesquisa = cidadeParaPesquisa;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public Integer getCodigoUfSelecionada() {
		return codigoUfSelecionada;
	}

	public void setCodigoUfSelecionada(Integer codigoUfSelecionada) {
		this.codigoUfSelecionada = codigoUfSelecionada;
	}

	public List<Cidade> getLista() {
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

	public List<UnidadeFederativa> getUfs() {
		return ufs;
	}

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}
	
	
}
