package gui.managedBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import classesBasicas.Cidade;
import classesBasicas.Fabricante;
import classesBasicas.Situacao;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class FabricanteBean {
	private static final String OP_NOVA = "NOVO Fabricante";
	private static final String OP_ALTERAR = "Alterar Fabricante";
	private static final String OP_VISUALIZAR = "Propriedades do Fabricante";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	private static final String MSG_TEL = "<Telefones>";
	
	private IFachada fachada;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("util.config");
	
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	
	private Fabricante fabricante;
	private Fabricante fabricanteParaPesquisa;
	private List<Fabricante> lista;
	private Fabricante fabricanteSelecionado;
	private Situacao[] situacoes = Situacao.values();
	private List<TipoLogradouro> tiposLogradouros; 
	private List<Cidade> cidades;
	private List<Cidade> cidadesPesquisa;
	private List<UnidadeFederativa> ufs;
	private List<UnidadeFederativa> ufsPesquisa;
	private Integer codigoUfSelecionada;
	private Integer codigoCidadeSelecionada;
	private Integer codigoTipoLogradouroSelecionado;
	private UnidadeFederativa ufSelecionada;
	private UnidadeFederativa ufPesquisa;
	private String telefone;
	private String telefoneSelecionado;
	private ArrayList<String> listaOriginalDeTelefones;
	private FacesMessage msgPendente;
	
	
	public FabricanteBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novoFabricante();
		telefone = "";
		telefoneSelecionado = "";
		listaOriginalDeTelefones = new ArrayList<String>();
		if (lista==null)
			lista = new ArrayList<Fabricante>();
		else
			lista.clear();
		listaEstaVazia = true;
		cidadesPesquisa = new ArrayList<Cidade>();
		iniciarObjParaPesquisa();
		fabricanteSelecionado = null;
		ufSelecionada = null;
		tituloOperacao = FabricanteBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = FabricanteBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			//cidadesPesquisa = fachada.listarCidades();
			ufsPesquisa = fachada.listarUFs();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void iniciarObjParaPesquisa(){
		fabricanteParaPesquisa = new Fabricante();
		ufPesquisa = new UnidadeFederativa();
	}

	private void prepararParaExibirDados(Fabricante obj){
		this.fabricante = obj;
		this.fabricante.setPj(obj.getPj());
		this.fabricante.getPj().setSituacao(obj.getPj().getSituacao());
		codigoCidadeSelecionada = obj.getPj().getEndereco().getCidade().getCodigo();
		//codigoUfSelecionada = obj.getPj().getEndereco().getCidade().getUnidadeFederativa().getCodigo();
		ufSelecionada = obj.getPj().getEndereco().getCidade().getUnidadeFederativa();
		codigoTipoLogradouroSelecionado = obj.getPj().getEndereco().getTipoLogradouro().getCodigo();
		//UnidadeFederativa uf = new UnidadeFederativa();
		//uf.setCodigo(codigoUfSelecionada);
		listaOriginalDeTelefones.clear();
		telefone = "";
		telefoneSelecionado = "";
		if (fabricante.getPj().getTelefones() != null && fabricante.getPj().getTelefones().size() > 0)
			listaOriginalDeTelefones.addAll(fabricante.getPj().getTelefones());
		if (fabricante.getPj().getTelefones().size() == 0)
			fabricante.getPj().getTelefones().add(MSG_TEL);
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			cidades = fachada.listarCidades();
			ufs = fachada.listarUFs();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtrar as cidades pelo estado selecionado!");
		}
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (fabricanteSelecionado == null)
			return null;
		try{
			prepararParaExibirDados(fachada.consultarFabricantePorId(fabricanteSelecionado.getCodigo()));
			tituloOperacao = FabricanteBean.OP_ALTERAR;
			textoBotaoFecharOuCancelar = FabricanteBean.TXT_BTN_CANCELAR;
			somenteLeitura = false;
			return resourceBundle.getString("linkFabricanteProp");//"fabricante-prop";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public String novo(){
		novoFabricante();
		prepararParaExibirDados(fabricante);
		cidades.clear();
		tituloOperacao = FabricanteBean.OP_NOVA;
		textoBotaoFecharOuCancelar = FabricanteBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkFabricanteProp");//"fabricante-prop";
	}
	
	private void novoFabricante(){
		fabricante = new Fabricante();
		codigoCidadeSelecionada = null;//new Cidade();
		codigoUfSelecionada = null;//new UnidadeFederativa();
		codigoTipoLogradouroSelecionado = null;
		ufSelecionada = null;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		if (fabricanteSelecionado == null)
			return;
		try{
			fabricante = fachada.consultarFabricantePorId(fabricanteSelecionado.getCodigo());
			fachada.excluirFabricante(fabricante);
			if (lista != null)
				consultar();
			MsgPrimeFaces.exibirMensagemInfomativa("Fabricante " + fabricanteSelecionado.getPj().getNome() + " excluido com sucesso!");
			novoFabricante();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		fabricanteSelecionado = null;
		ufPesquisa = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			//fabricante.getPj().getEndereco().setCidade(fachada.pegarCidadePorId(codigoCidadeSelecionada));
			//fabricante.getPj().getEndereco().setTipoLogradouro(fachada.pegarTipoLogradouroPorId(codigoTipoLogradouroSelecionado));
			fabricante.getPj().getEndereco().setCep(fabricante.getPj().getEndereco().getCep().replace("-", ""));
			fabricante.getPj().setCnpj(fabricante.getPj().getCnpj().replace(".", "").replace("/", "").replace("-", ""));
			if (fabricante.getCodigo() == null || fabricante.getCodigo() == 0)
				fabricante.setCodigo(null);
			fachada.salvarFabricante(fabricante);
			consultar();
			msgPendente = MsgPrimeFaces.criarMsgInfo("Fabricante " + fabricante.getPj().getNome() 
					+ " salvo com sucesso!");
			novoFabricante();
			somenteLeitura = true;
			return resourceBundle.getString("linkFabricante");//"fabricante";
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
			if (ufPesquisa != null){
				if (fabricanteParaPesquisa.getPj().getEndereco().getCidade() == null)
					fabricanteParaPesquisa.getPj().getEndereco().setCidade(new Cidade());
				fabricanteParaPesquisa.getPj().getEndereco().getCidade().setUnidadeFederativa(ufPesquisa);
			}
			atualizarLista(fachada.consultarFabricante(fabricanteParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (fabricanteSelecionado == null)
			return null;
		prepararParaExibirDados(fabricanteSelecionado);
		tituloOperacao = FabricanteBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = FabricanteBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return resourceBundle.getString("linkFabricanteProp");//"fabricante-prop";
	}
	

	private void atualizarLista(List<Fabricante> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public String cancelar(){
		somenteLeitura = true;
		tiposLogradouros.clear();
		cidades.clear();
		ufs.clear();
		fabricante.getPj().getTelefones().clear();
		fabricante.getPj().getTelefones().addAll(listaOriginalDeTelefones);
		return resourceBundle.getString("linkFabricante");
	}
	
	public void limpar(){
		iniciarObjParaPesquisa();
	}
	
	public String carregarPagina(){
		inicializar();
		return resourceBundle.getString("linkFabricante");//"fabricante.xhtml?faces-redirect=true";
	}
	
	public String voltarParaPaginaPrincipal(){
		return resourceBundle.getString("linkHome");
	}
	
	public void filtrarCidadesPesquisa(ValueChangeEvent evento){
		try{
			UnidadeFederativa uf = (UnidadeFederativa)evento.getNewValue();
			if (uf != null){
				cidadesPesquisa = fachada.consultarCidadesPorUF(uf);
				if (uf.getCodigo() == null || uf.getCodigo() <= 0)
					cidadesPesquisa.clear();
			}else{
				cidadesPesquisa.clear();
				//MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades da UF = null.");
			}
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades pela UF!");
		}
	}
	
	public void filtrarCidades(ValueChangeEvent evento){
		if (!somenteLeitura){
			try{
				UnidadeFederativa uf = (UnidadeFederativa)evento.getNewValue();
				if (uf != null){
					cidades = fachada.consultarCidadesPorUF(uf);
					if (uf.getCodigo() == null || uf.getCodigo() <= 0)
						cidades.clear();
				}else{
					cidades.clear();
					//MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades da UF = null.");
				}
				//if (fabricante.getPj().getEndereco().getCidade().getCodigo() != null && fabricante.getPj().getEndereco().getCidade().getCodigo() > 0)
					//codigoCidadeSelecionada = fabricante.getPj().getEndereco().getCidade().getCodigo();
			}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades pela UF!");
			}
		}
	}
	
	public void telefonesChange(ValueChangeEvent evento){
		if (fabricante.getPj().getTelefones().size() > 0 
				&& fabricante.getPj().getTelefones().get(0).compareTo(MSG_TEL) != 0){
			telefone = (String)evento.getNewValue();
		}
	}
	
	public void adicionarTelefone(){
		if (telefone != null && telefone.length() >= 10){
			if (fabricante.getPj().getTelefones().size() > 0 
					&& fabricante.getPj().getTelefones().get(0).equals(MSG_TEL)){
				fabricante.getPj().getTelefones().set(0, telefone);
			}else
				fabricante.getPj().getTelefones().add(telefone);
			//telefoneSelecionado = telefone;
			telefone = "";
		}
	}
	
	public void excluirTelefone(){
		if (fabricante.getPj().getTelefones().size() > 0 
				&& fabricante.getPj().getTelefones().get(0).compareTo(MSG_TEL) != 0){
			fabricante.getPj().getTelefones().remove(telefoneSelecionado);
			telefone = "";
		}
		if (fabricante.getPj().getTelefones().size() == 0)
			fabricante.getPj().getTelefones().add(MSG_TEL);
	}
	
	public void alterarTelefone(){
		if (fabricante.getPj().getTelefones().size() > 0 
				&& fabricante.getPj().getTelefones().get(0).compareTo(MSG_TEL) != 0){
			for(int i =0;i < fabricante.getPj().getTelefones().size();i++){
				if (fabricante.getPj().getTelefones().get(i).equals(telefoneSelecionado)){
					fabricante.getPj().getTelefones().set(i, telefone);
					break;
				}
			}
			//telefoneSelecionado = telefone;
			telefone = "";
		}
	}
	
	// GETs e SETs

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Fabricante getFabricanteParaPesquisa() {
		return fabricanteParaPesquisa;
	}

	public void setFabricanteParaPesquisa(Fabricante fabricanteParaPesquisa) {
		this.fabricanteParaPesquisa = fabricanteParaPesquisa;
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

	public Integer getCodigoUfSelecionada() {
		return codigoUfSelecionada;
	}

	public void setCodigoUfSelecionada(Integer codigoUfSelecionada) {
		this.codigoUfSelecionada = codigoUfSelecionada;
	}

	public Integer getCodigoCidadeSelecionada() {
		return codigoCidadeSelecionada;
	}

	public void setCodigoCidadeSelecionada(Integer codigoCidadeSelecionada) {
		this.codigoCidadeSelecionada = codigoCidadeSelecionada;
	}

	public Integer getCodigoTipoLogradouroSelecionado() {
		return codigoTipoLogradouroSelecionado;
	}

	public void setCodigoTipoLogradouroSelecionado(
			Integer codigoTipoLogradouroSelecionado) {
		this.codigoTipoLogradouroSelecionado = codigoTipoLogradouroSelecionado;
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

	public List<Fabricante> getLista() {
		return lista;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public List<TipoLogradouro> getTiposLogradouros() {
		return tiposLogradouros;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<UnidadeFederativa> getUfs() {
		return ufs;
	}

	public List<UnidadeFederativa> getUfsPesquisa() {
		return ufsPesquisa;
	}

	public UnidadeFederativa getUfSelecionada() {
		return ufSelecionada;
	}

	public void setUfSelecionada(UnidadeFederativa ufSelecionada) {
		this.ufSelecionada = ufSelecionada;
	}

	public UnidadeFederativa getUfPesquisa() {
		return ufPesquisa;
	}

	public void setUfPesquisa(UnidadeFederativa ufPesquisa) {
		this.ufPesquisa = ufPesquisa;
	}

	public List<Cidade> getCidadesPesquisa() {
		return cidadesPesquisa;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefoneSelecionado() {
		return telefoneSelecionado;
	}

	public void setTelefoneSelecionado(String telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}
	
	
}
