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
import javax.faces.event.ValueChangeEvent;

import classesBasicas.Centro;
import classesBasicas.Cidade;
import classesBasicas.Endereco;
import classesBasicas.PessoaJuridica;
import classesBasicas.Situacao;
import classesBasicas.TipoCentro;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;

@ManagedBean
@SessionScoped
public class CentroBean {
	private static final String OP_NOVA = "NOVO Centro";
	private static final String OP_ALTERAR = "Alterar Centro";
	private static final String OP_VISUALIZAR = "Propriedades do Centro";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("util.config");
	
	private Centro centro;
	private Centro centroParaPesquisa;
	private List<Centro> lista;
	private Centro centroSelecionado;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private TipoCentro[] tiposCentros = TipoCentro.values();
	private List<TipoLogradouro> tiposLogradouros; 
	private List<Cidade> cidades;
	private List<UnidadeFederativa> ufs;
	
	private Integer ufSelecionada;
	private Integer cidadeSelecionada;
	private TipoCentro tipoCentroSelecionado;
	private Integer tipoLogradouroSelecionado;
	
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	private FacesMessage msgPendente;
	
	
	
	public CentroBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novoCentro();
		if (lista==null)
			lista = new ArrayList<Centro>();
		else
			lista.clear();
		listaEstaVazia = true;
		iniciarObjParaPesquisa();
		centroSelecionado = null;
		tituloOperacao = CentroBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			cidades = fachada.listarCidades();
			ufs = fachada.listarUFs();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void iniciarObjParaPesquisa(){
		centroParaPesquisa = new Centro();
		centroParaPesquisa.setDadosPJ(new PessoaJuridica());
		centroParaPesquisa.getDadosPJ().setEndereco(new Endereco());
		centroParaPesquisa.getDadosPJ().getEndereco().setCidade(new Cidade());
	}

	private void prepararParaExibirDados(Centro obj){
		this.centro = obj;
		this.centro.setDadosPJ(obj.getDadosPJ());
		this.centro.setSituacao(obj.getSituacao());
		cidadeSelecionada = obj.getDadosPJ().getEndereco().getCidade().getCodigo();
		ufSelecionada = obj.getDadosPJ().getEndereco().getCidade().getUnidadeFederativa().getCodigo();
		tipoLogradouroSelecionado = obj.getDadosPJ().getEndereco().getTipoLogradouro().getCodigo();
		tipoCentroSelecionado = obj.getTipoCentro();
		UnidadeFederativa uf = new UnidadeFederativa();
		uf.setCodigo(ufSelecionada);
		try{
		cidades = fachada.consultarCidadesPorUF(uf);
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtrar as cidades pelo estado selecionado!");
		}
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (centroSelecionado == null)
			return null;
		try{
			prepararParaExibirDados(fachada.pegarCentroPorId(centroSelecionado.getCodigo()));
			tituloOperacao = CentroBean.OP_ALTERAR;
			textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_CANCELAR;
			somenteLeitura = false;
			return resourceBundle.getString("linkCentroProp");//"centro-prop";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public String novo(){
		novoCentro();
		tituloOperacao = CentroBean.OP_NOVA;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkCentroProp");//"centro-prop";
	}
	
	private void novoCentro(){
		centro = new Centro();
		centro.setAlias("Digite um nome aqui");
		centro.setDadosPJ(new PessoaJuridica());
		centro.getDadosPJ().setEndereco(new Endereco());
		centro.getDadosPJ().getEndereco().setTipoLogradouro(new TipoLogradouro());
		centro.getDadosPJ().getEndereco().setCidade(new Cidade());
		cidadeSelecionada = 0;//new Cidade();
		ufSelecionada = 0;//new UnidadeFederativa();
		tipoCentroSelecionado = null;
		tipoLogradouroSelecionado = 0;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		if (centroSelecionado == null)
			return;
		try{
			centro = fachada.pegarCentroPorId(centroSelecionado.getCodigo());
			fachada.excluirCentro(centro);
			if (lista != null)
				consultar();
			MsgPrimeFaces.exibirMensagemInfomativa("Centro " + centroSelecionado.getAlias() + " excluido com sucesso!");
			novoCentro();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		centroSelecionado = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			centro.getDadosPJ().getEndereco().setCidade(fachada.pegarCidadePorId(cidadeSelecionada));
			centro.getDadosPJ().getEndereco().setTipoLogradouro(fachada.pegarTipoLogradouroPorId(tipoLogradouroSelecionado));
			centro.getDadosPJ().getEndereco().setCep(centro.getDadosPJ().getEndereco().getCep().replace("-", ""));
			centro.getDadosPJ().setCnpj(centro.getDadosPJ().getCnpj().replace(".", "").replace("/", "").replace("-", ""));
			if (centro.getCodigo() == null || centro.getCodigo() == 0)
				centro.setCodigo(null);
			fachada.salvarCentro(centro);
			consultar();
			msgPendente = MsgPrimeFaces.criarMsgInfo("Centro " + centro.getAlias() 
					+ " salvo com sucesso!");
			novoCentro();
			somenteLeitura = true;
			return resourceBundle.getString("linkCentro");//"centro";
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
			atualizarLista(fachada.consultarCentro(centroParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (centroSelecionado == null)
			return null;
		prepararParaExibirDados(centroSelecionado);
		tituloOperacao = CentroBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return resourceBundle.getString("linkCentroProp");//"centro-prop";
	}
	

	private void atualizarLista(List<Centro> lista) {
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
		centro = null;
		return resourceBundle.getString("linkCentro");
	}
	
	public String carregarPagina(){
		inicializar();
		return resourceBundle.getString("linkCentro");//"centro.xhtml?faces-redirect=true";
	}
	
	public String voltarParaPaginaPrincipal(){
		return resourceBundle.getString("linkHome");
	}
	
	
	
	// GETs e SETs

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Centro getCentroParaPesquisa() {
		return centroParaPesquisa;
	}

	public void setCentroParaPesquisa(Centro centroParaPesquisa) {
		this.centroParaPesquisa = centroParaPesquisa;
	}

	public Centro getCentroSelecionado() {
		return centroSelecionado;
	}

	public void setCentroSelecionado(Centro centroSelecionado) {
		this.centroSelecionado = centroSelecionado;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public TipoCentro getTipoCentroSelecionado() {
		return tipoCentroSelecionado;
	}

	public void setTipoCentroSelecionado(TipoCentro tipoCentroSelecionado) {
		this.tipoCentroSelecionado = tipoCentroSelecionado;
	}

	public List<Centro> getLista() {
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

	public TipoCentro[] getTiposCentros() {
		return tiposCentros;
	}

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}
	
	public boolean isSomenteLeitura(){
		return somenteLeitura;
	}

	public boolean isNenhumItemSelecionado(){
		boolean desabilitado = true;
		if ((!listaEstaVazia) && (centroSelecionado != null)){
			desabilitado = false;
		}
		return desabilitado;
	}
	
	public List<TipoLogradouro> getTiposLogradouros(){
		return tiposLogradouros;
	}
	
	public List<Cidade> getCidades(){
		return cidades;
	}

	public void filtrarCidades(ValueChangeEvent evento){
		if (!somenteLeitura){
		try{
			ufSelecionada = (Integer)evento.getNewValue();
			UnidadeFederativa uf = new UnidadeFederativa();
			uf.setCodigo(ufSelecionada);
			if (ufSelecionada != null){
				cidades = fachada.consultarCidadesPorUF(uf);
			}else
				MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades da UF = null.");
			if (centro.getDadosPJ().getEndereco().getCidade().getCodigo() != null && centro.getDadosPJ().getEndereco().getCidade().getCodigo() > 0)
				cidadeSelecionada = centro.getDadosPJ().getEndereco().getCidade().getCodigo();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades pela UF!");
		}
		}
	}

	public List<UnidadeFederativa> getUfs() {
		return ufs;
	}

	public Integer getUfSelecionada() {
		return ufSelecionada;
	}

	public void setUfSelecionada(Integer ufSelecionada) {
		this.ufSelecionada = ufSelecionada;
	}

	public Integer getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Integer cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public Integer getTipoLogradouroSelecionado() {
		return tipoLogradouroSelecionado;
	}

	public void setTipoLogradouroSelecionado(Integer tipoLogradouroSelecionado) {
		this.tipoLogradouroSelecionado = tipoLogradouroSelecionado;
	}
	
	
	
		
}
