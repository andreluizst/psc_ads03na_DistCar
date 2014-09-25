package gui.managedBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import classesBasicas.Centro;
import classesBasicas.Departamento;
import classesBasicas.Funcionario;
import classesBasicas.Situacao;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;


@ManagedBean
@SessionScoped
public class DeptoBean {
	private static final String OP_NOVA = "NOVO Departamento";
	private static final String OP_ALTERAR = "Alterar Departamento";
	private static final String OP_VISUALIZAR = "Propriedades do Departamento";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("util.config");
	
	private Departamento departamento;
	private Departamento departamentoParaPesquisa;
	private List<Departamento> lista;
	private Departamento departamentoSelecionado;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	private List<Centro> centros;
	private List<Departamento> deptosSuperiores;
	private List<Funcionario> gestores;
	private boolean temGestores;
	private boolean temCentros;
	private boolean temDeptosSuperiores;
	private Integer codigoDeptoSuperiorSelecionado;
	private Integer codigoGestorSelecionado;
	private Integer codigoCentroSelecionado;
	private FacesMessage msgPendente;
	
	
	
	public DeptoBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializarObjParaPesquisa(){
		departamentoParaPesquisa = new Departamento();
		//departamentoParaPesquisa.setDepartamentoSuperior(new Departamento());
		//departamentoParaPesquisa.setGestor(new Funcionario());
		//departamentoParaPesquisa.setCentro(new Centro());
	}
	
	private void inicializar(){
		novoDepartamento();
		if (lista==null)
			lista = new ArrayList<Departamento>();
		else
			lista.clear();
		listaEstaVazia = true;
		inicializarObjParaPesquisa();
		departamentoSelecionado = null;
		codigoDeptoSuperiorSelecionado = null;
		codigoCentroSelecionado = null;
		codigoGestorSelecionado = null;
		tituloOperacao = DeptoBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = DeptoBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			centros = fachada.listarCentros();
			deptosSuperiores = fachada.listarDepartamentos();
			gestores = fachada.listarFuncionariosGestores();
			temGestores = gestores.size()>0?true:false;
			temCentros = centros.size()>0?true:false;
			temDeptosSuperiores = deptosSuperiores.size()>0?true:false;
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void prepararParaExibirDados(Departamento obj){
		departamento = obj;
		departamento.setCentro(obj.getCentro());
		departamento.setDepartamentoSuperior(obj.getDepartamentoSuperior());
		departamento.setGestor(obj.getGestor());
		departamento.setSituacao(obj.getSituacao());
		if (obj.getDepartamentoSuperior() != null && obj.getDepartamentoSuperior().getCodigo() != null)
			codigoDeptoSuperiorSelecionado = obj.getDepartamentoSuperior().getCodigo();
		else
			codigoDeptoSuperiorSelecionado = null;
		if (obj.getGestor() != null && obj.getGestor().getCodigo() != null)
			codigoGestorSelecionado = obj.getGestor().getCodigo();
		else
			codigoGestorSelecionado = null;
		if (obj.getCentro() != null && obj.getCentro().getCodigo() != null)
			codigoCentroSelecionado = obj.getCentro().getCodigo();
		else
			codigoCentroSelecionado = null;
		try{
			centros = fachada.listarCentros();
			deptosSuperiores = fachada.listarDepartamentos();
			gestores = fachada.listarFuncionariosGestores();
			temGestores = gestores.size()>0?true:false;
			temCentros = centros.size()>0?true:false;
			temDeptosSuperiores = deptosSuperiores.size()>0?true:false;
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (departamentoSelecionado == null)
			return null;
		try{
			prepararParaExibirDados(fachada.pegarDepartamentoPorId(departamentoSelecionado.getCodigo()));
			tituloOperacao = DeptoBean.OP_ALTERAR;
			textoBotaoFecharOuCancelar = DeptoBean.TXT_BTN_CANCELAR;
			somenteLeitura = false;
			return resourceBundle.getString("linkDepartamentoProp");//"departamento-prop";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public String novo(){
		novoDepartamento();
		prepararParaExibirDados(departamento);
		tituloOperacao = DeptoBean.OP_NOVA;
		textoBotaoFecharOuCancelar = DeptoBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkDepartamentoProp");//"departamento-prop";
	}
	
	private void novoDepartamento(){
		departamento = new Departamento();
		departamento.setNome("Digite um nome aqui");
		departamento.setDepartamentoSuperior(new Departamento());
		departamento.setCentro(new Centro());
		departamento.setGestor(new Funcionario());
		codigoCentroSelecionado = null;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		if (departamentoSelecionado == null)
			return;
		try{
			departamento = fachada.pegarDepartamentoPorId(departamentoSelecionado.getCodigo());
			fachada.excluirDepartamento(departamento);
			if (lista != null)
				consultar();
			MsgPrimeFaces.exibirMensagemInfomativa("Departamento " + departamentoSelecionado.getNome() + " excluido com sucesso!");
			novoDepartamento();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		departamentoSelecionado = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			departamento.setCentro(fachada.pegarCentroPorId(codigoCentroSelecionado));
			/*if (departamento.getDepartamentoSuperior() == null
					|| departamento.getDepartamentoSuperior().getCodigo() == null 
					|| departamento.getDepartamentoSuperior().getCodigo() == 0){*/
			if (codigoDeptoSuperiorSelecionado == null){
				departamento.setDepartamentoSuperior(null);
			}else{
				departamento.setDepartamentoSuperior(fachada.pegarDepartamentoPorId(codigoDeptoSuperiorSelecionado));
			}
			/*if (departamento.getGestor() == null || departamento.getGestor().getCodigo() == null 
					|| departamento.getGestor().getCodigo() == 0){*/
			if (codigoGestorSelecionado ==null){
				departamento.setGestor(null);
			}else{
				departamento.setGestor(fachada.pegarFuncionarioPorId(codigoGestorSelecionado));
			}
			
			if (departamento.getCodigo() == null || departamento.getCodigo() == 0)
				departamento.setCodigo(null);
			fachada.salvarDepartamento(departamento);
			consultar();
			msgPendente = MsgPrimeFaces.criarMsgInfo("Departamento " + departamento.getNome() 
					+ " salvo com sucesso!");
			novoDepartamento();
			somenteLeitura = true;
			return resourceBundle.getString("linkDepartamento");//"departamento";
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
			atualizarLista(fachada.consultarDepartamento(departamentoParaPesquisa));
		}catch(Exception ex){
			//System.out.println(ex.getStackTrace());
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (departamentoSelecionado == null)
			return null;
		prepararParaExibirDados(departamentoSelecionado);
		tituloOperacao = DeptoBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = DeptoBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return resourceBundle.getString("linkDepartamentoProp");//"departamento-prop";
	}
	

	private void atualizarLista(List<Departamento> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public void limpar(){
		inicializarObjParaPesquisa();
		situacaoSelecionada = null;
	}
	
	public String cancelar(){
		somenteLeitura = true;
		return resourceBundle.getString("linkDepartamento");
	}
	
	public String carregarPagina(){
		inicializar();
		return resourceBundle.getString("linkDepartamento");
	}
	
	public String voltarParaPaginaPrincipal(){
		return resourceBundle.getString("linkHome");
	}
	
	
	// GETs e SETs
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Departamento getDepartamentoParaPesquisa() {
		return departamentoParaPesquisa;
	}

	public void setDepartamentoParaPesquisa(Departamento departamentoParaPesquisa) {
		this.departamentoParaPesquisa = departamentoParaPesquisa;
	}

	public Departamento getDepartamentoSelecionado() {
		return departamentoSelecionado;
	}

	public void setDepartamentoSelecionado(Departamento departamentoSelecionado) {
		this.departamentoSelecionado = departamentoSelecionado;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public List<Departamento> getLista() {
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

	public List<Centro> getCentros() {
		return centros;
	}

	public List<Departamento> getDeptosSuperiores() {
		return deptosSuperiores;
	}

	public List<Funcionario> getGestores() {
		return gestores;
	}

	public boolean isTemGestores() {
		return temGestores;
	}

	public boolean isTemCentros() {
		return temCentros;
	}

	public boolean isTemDeptosSuperiores() {
		return temDeptosSuperiores;
	}

	public Integer getCodigoDeptoSuperiorSelecionado() {
		return codigoDeptoSuperiorSelecionado;
	}

	public void setCodigoDeptoSuperiorSelecionado(
			Integer codigoDeptoSuperiorSelecionado) {
		this.codigoDeptoSuperiorSelecionado = codigoDeptoSuperiorSelecionado;
	}

	public Integer getCodigoGestorSelecionado() {
		return codigoGestorSelecionado;
	}

	public void setCodigoGestorSelecionado(Integer codigoGestorSelecionado) {
		this.codigoGestorSelecionado = codigoGestorSelecionado;
	}

	public Integer getCodigoCentroSelecionado() {
		return codigoCentroSelecionado;
	}

	public void setCodigoCentroSelecionado(Integer codigoCentroSelecionado) {
		this.codigoCentroSelecionado = codigoCentroSelecionado;
	}
	
	
	
}
