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
import classesBasicas.Cliente;
import classesBasicas.PessoaFisica;
import classesBasicas.PessoaJuridica;
import classesBasicas.Situacao;
import classesBasicas.TipoCliente;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class ClienteBean {
	private static final String OP_NOVA = "NOVO Cliente";
	private static final String OP_ALTERAR = "Alterar Cliente";
	private static final String OP_VISUALIZAR = "Propriedades do Cliente";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	private static final String CPF_MASK = "999.999.999-99";
	private static final String CNPJ_MASK = "99.999.999/9999-99";
	private static final String MSG_TEL = "<Telefones>";
	
	private IFachada fachada;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("util.config");
	
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	
	private Cliente cliente;
	private Cliente clienteParaPesquisa;
	private List<Cliente> lista;
	private Cliente clienteSelecionado;
	private Situacao[] situacoes = Situacao.values();
	private TipoCliente[] tiposCliente = TipoCliente.values(); 
	private List<TipoLogradouro> tiposLogradouros; 
	private List<Cidade> cidades;
	private List<UnidadeFederativa> ufs;
	private List<UnidadeFederativa> ufsPesquisa;
	private List<Cidade> cidadesPesquisa;
	private Integer codigoUfPesquisa;
	private Integer codigoCidadePesquisa;
	private String cpfCnpjPesquisa;
	private String nomeParaPesquisa;
	private Integer codigoUfSelecionada;
	private Integer codigoCidadeSelecionada;
	private Integer codigoTipoLogradouroSelecionado;
	private String cpfOuCnpj;
	private String mascaraCpfOuCnpj;
	private boolean cpfOuCnpjVisible;
	private boolean mostrarCamposPJ;
	private boolean mostrarCamposPF;
	private boolean modoDeInclusao;
	private PessoaFisica pfAux;
	private PessoaJuridica pjAux;
	private String telefone;
	private String telefoneSelecionado;
	private ArrayList<String> listaOriginalDeTelefones;
	private FacesMessage msgPendente;
	
	
	public ClienteBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novoCliente();
		if (lista==null)
			lista = new ArrayList<Cliente>();
		else
			lista.clear();
		telefone = "";
		telefoneSelecionado = "";
		listaOriginalDeTelefones = new ArrayList<String>();
		listaEstaVazia = true;
		cpfOuCnpjVisible = false;
		mostrarCamposPJ = false;
		mostrarCamposPF = false;
		modoDeInclusao = false;
		iniciarObjParaPesquisa();
		clienteSelecionado = null;
		tituloOperacao = ClienteBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = ClienteBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			cidadesPesquisa = fachada.listarCidades();
			ufsPesquisa = fachada.listarUFs();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void iniciarObjParaPesquisa(){
		clienteParaPesquisa = new Cliente();
		clienteParaPesquisa.setTipoCliente(null);
		codigoUfPesquisa = null;
		codigoCidadePesquisa = null;
		cpfCnpjPesquisa = "";
		nomeParaPesquisa = "";
		cpfOuCnpjVisible = false;
		/*clienteParaPesquisa.setDadosPessoa(new PessoaJuridica());
		clienteParaPesquisa.getDadosPessoa().setEndereco(new Endereco());
		clienteParaPesquisa.getDadosPessoa().getEndereco().setCidade(new Cidade());*/
	}

	private void prepararParaExibirDados(Cliente obj){
		this.cliente = obj;
		this.cliente.setDadosPessoa(obj.getDadosPessoa());
		this.cliente.getDadosPessoa().setSituacao(obj.getDadosPessoa().getSituacao());
		codigoTipoLogradouroSelecionado = obj.getDadosPessoa().getEndereco().getTipoLogradouro().getCodigo();	
		codigoCidadeSelecionada = obj.getDadosPessoa().getEndereco().getCidade().getCodigo();
		codigoUfSelecionada = obj.getDadosPessoa().getEndereco().getCidade().getUnidadeFederativa().getCodigo();
		codigoTipoLogradouroSelecionado = obj.getDadosPessoa().getEndereco().getTipoLogradouro().getCodigo();
		mostrarCamposPF = cliente.getDadosPessoa() instanceof PessoaFisica?true:false;
		mostrarCamposPJ = !mostrarCamposPF; 
		UnidadeFederativa uf = new UnidadeFederativa();
		if (pjAux == null)
			pjAux = new PessoaJuridica();
		if (pfAux == null)
			pfAux = new PessoaFisica();
		if (cliente.getDadosPessoa() instanceof PessoaFisica){
			pfAux.setCpf(((PessoaFisica)cliente.getDadosPessoa()).getCpf());
			pfAux.setDataNascimento(((PessoaFisica)cliente.getDadosPessoa()).getDataNascimento());
			pfAux.setRg(((PessoaFisica)cliente.getDadosPessoa()).getRg());
			pfAux.setOrgaoExpedidor(((PessoaFisica)cliente.getDadosPessoa()).getOrgaoExpedidor());
		}
		if (cliente.getDadosPessoa() instanceof PessoaJuridica){
			pjAux.setCnpj(((PessoaJuridica)cliente.getDadosPessoa()).getCnpj());
			pjAux.setDataAbertura(((PessoaJuridica)cliente.getDadosPessoa()).getDataAbertura());
			pjAux.setInscricaoEstadual(((PessoaJuridica)cliente.getDadosPessoa()).getInscricaoEstadual());
		}
		listaOriginalDeTelefones.clear();
		telefone = "";
		telefoneSelecionado = "";
		if (cliente.getDadosPessoa().getTelefones() != null && cliente.getDadosPessoa().getTelefones().size() > 0)
			listaOriginalDeTelefones.addAll(cliente.getDadosPessoa().getTelefones());
		if (listaOriginalDeTelefones.size() == 0)
			cliente.getDadosPessoa().getTelefones().add(ClienteBean.MSG_TEL);
		uf.setCodigo(codigoUfSelecionada);
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			ufs = fachada.listarUFs();
			cidades = fachada.consultarCidadesPorUF(uf);
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtrar as cidades pelo estado selecionado!");
		}
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (clienteSelecionado == null)
			return null;
		try{
			prepararParaExibirDados(fachada.pegarClientePorId(clienteSelecionado.getCodigo()));
			tituloOperacao = ClienteBean.OP_ALTERAR;
			textoBotaoFecharOuCancelar = ClienteBean.TXT_BTN_CANCELAR;
			somenteLeitura = false;
			modoDeInclusao = false;
			return resourceBundle.getString("linkClienteProp");//"cliente-prop";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public String novo(){
		novoCliente();
		prepararParaExibirDados(cliente);
		tituloOperacao = ClienteBean.OP_NOVA;
		textoBotaoFecharOuCancelar = ClienteBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		modoDeInclusao = true;
		return resourceBundle.getString("linkClienteProp");//"cliente-prop";
	}
	
	private void novoCliente(){
		cliente = new Cliente();
		pfAux = new PessoaFisica();
		pjAux = new PessoaJuridica();
		/*cliente.setPj(new PessoaJuridica());
		cliente.getPj().setEndereco(new Endereco());
		cliente.getPj().getEndereco().setTipoLogradouro(new TipoLogradouro());
		cliente.getPj().getEndereco().setCidade(new Cidade());*/
		codigoCidadeSelecionada = null;//new Cidade();
		codigoUfSelecionada = null;//new UnidadeFederativa();
		codigoTipoLogradouroSelecionado = null;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		if (clienteSelecionado == null)
			return;
		try{
			cliente = fachada.pegarClientePorId(clienteSelecionado.getCodigo());
			fachada.excluirCliente(cliente);
			if (lista != null)
				consultar();
			MsgPrimeFaces.exibirMensagemInfomativa("Cliente " + clienteSelecionado.getDadosPessoa().getNome() + " excluido com sucesso!");
			novoCliente();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		clienteSelecionado = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			//cliente.getDadosPessoa().getEndereco().setCidade(fachada.pegarCidadePorId(codigoCidadeSelecionada));
			//cliente.getDadosPessoa().getEndereco().setTipoLogradouro(fachada.pegarTipoLogradouroPorId(codigoTipoLogradouroSelecionado));
			cliente.getDadosPessoa().getEndereco().setCep(cliente.getDadosPessoa().getEndereco().getCep().replace("-", ""));
			if (modoDeInclusao){
				if (cliente.getTipoCliente() == TipoCliente.PESSOA_FISICA){
					pfAux.setCpf(pfAux.getCpf().replace(".", "").replace("-", ""));
					pfAux.setEndereco(cliente.getDadosPessoa().getEndereco());
					pfAux.setNome(cliente.getDadosPessoa().getNome());
					pfAux.setEmail(cliente.getDadosPessoa().getEmail());
					pfAux.setTelefones(cliente.getDadosPessoa().getTelefones());
					pfAux.setSituacao(cliente.getDadosPessoa().getSituacao());
					cliente.setDadosPessoa(pfAux);
				}
				if (cliente.getTipoCliente() == TipoCliente.PESSOA_JURIDICA){
					pjAux.setCnpj(pjAux.getCnpj().replace(".", "").replace("/", "").replace("-", ""));
					pjAux.setEndereco(cliente.getDadosPessoa().getEndereco());
					pjAux.setNome(cliente.getDadosPessoa().getNome());
					pjAux.setEmail(cliente.getDadosPessoa().getEmail());
					pjAux.setTelefones(cliente.getDadosPessoa().getTelefones());
					pjAux.setSituacao(cliente.getDadosPessoa().getSituacao());
					cliente.setDadosPessoa(pjAux);
				}
			}else{
				if (cliente.getDadosPessoa() instanceof PessoaJuridica){
					((PessoaJuridica)cliente.getDadosPessoa()).setCnpj(pjAux.getCnpj().replace(".", "").replace("/", "").replace("-", ""));
					((PessoaJuridica)cliente.getDadosPessoa()).setDataAbertura(pjAux.getDataAbertura());
					((PessoaJuridica)cliente.getDadosPessoa()).setInscricaoEstadual(pjAux.getInscricaoEstadual());
				}
				else
					if (cliente.getDadosPessoa() instanceof PessoaFisica){
						((PessoaFisica)cliente.getDadosPessoa()).setCpf(pfAux.getCpf().replace(".", "").replace("-", ""));
						((PessoaFisica)cliente.getDadosPessoa()).setDataNascimento(pfAux.getDataNascimento());
						((PessoaFisica)cliente.getDadosPessoa()).setRg(pfAux.getRg());
						((PessoaFisica)cliente.getDadosPessoa()).setOrgaoExpedidor(pfAux.getOrgaoExpedidor());
					}
			}
			if (cliente.getCodigo() == null || cliente.getCodigo() == 0)
				cliente.setCodigo(null);
			fachada.salvarCliente(cliente);
			consultar();
			msgPendente = MsgPrimeFaces.criarMsgInfo("Cliente " + cliente.getDadosPessoa().getNome()
					+ " salvo com sucesso!");
			modoDeInclusao = false;
			novoCliente();
			somenteLeitura = true;
			tiposLogradouros.clear();
			cidades.clear();
			ufs.clear();
			return resourceBundle.getString("linkCliente");//"cliente";
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
			if (clienteParaPesquisa.getTipoCliente() == TipoCliente.PESSOA_FISICA){
				clienteParaPesquisa.setDadosPessoa(new PessoaFisica());
				((PessoaFisica)clienteParaPesquisa.getDadosPessoa()).setCpf(
						cpfCnpjPesquisa.replace(".", "").replace("-", ""));
			}else{
				if (clienteParaPesquisa.getTipoCliente() == TipoCliente.PESSOA_JURIDICA){
					clienteParaPesquisa.setDadosPessoa(new PessoaJuridica());
					((PessoaJuridica)clienteParaPesquisa.getDadosPessoa()).setCnpj(
							cpfCnpjPesquisa.replace(".", "").replace("/", "").replace("-", ""));
				}
			}
			if (codigoCidadePesquisa != null && codigoCidadePesquisa > 0)
				clienteParaPesquisa.getDadosPessoa().getEndereco().getCidade().setCodigo(codigoCidadePesquisa);
			else
				clienteParaPesquisa.getDadosPessoa().getEndereco().getCidade().setCodigo(null);
			if (codigoUfPesquisa != null && codigoUfPesquisa > 0)
				clienteParaPesquisa.getDadosPessoa().getEndereco().getCidade().getUnidadeFederativa().setCodigo(codigoUfPesquisa);
			else
				clienteParaPesquisa.getDadosPessoa().getEndereco().getCidade().getUnidadeFederativa().setCodigo(null);
			if (nomeParaPesquisa != null && nomeParaPesquisa.length() > 0)
				clienteParaPesquisa.getDadosPessoa().setNome(nomeParaPesquisa);
			else
				clienteParaPesquisa.getDadosPessoa().setNome(null);
			atualizarLista(fachada.consultarCliente(clienteParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (clienteSelecionado == null)
			return null;
		prepararParaExibirDados(clienteSelecionado);
		tituloOperacao = ClienteBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = ClienteBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		modoDeInclusao = false;
		return resourceBundle.getString("linkClienteProp");//"cliente-prop";
	}
	

	private void atualizarLista(List<Cliente> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public String cancelar(){
		somenteLeitura = true;
		tiposLogradouros.clear();
		if (cidades != null)
			cidades.clear();
		if (ufs != null)
			ufs.clear();
		cliente.getDadosPessoa().getTelefones().clear();
		cliente.getDadosPessoa().getTelefones().addAll(listaOriginalDeTelefones);
		return resourceBundle.getString("linkCliente");
	}
	
	public void limpar(){
		iniciarObjParaPesquisa();
	}
	
	public String carregarPagina(){
		inicializar();
		return resourceBundle.getString("linkCliente");//"cliente.xhtml?faces-redirect=true";
	}
	
	public String voltarParaPaginaPrincipal(){
		return resourceBundle.getString("linkHome");
	}
	
	public void filtrarCidadesPesquisa(ValueChangeEvent evento){
		codigoUfPesquisa = (Integer)evento.getNewValue();
		if (codigoUfPesquisa != null){
			try{
				UnidadeFederativa uf = new UnidadeFederativa();
				uf.setCodigo(codigoUfPesquisa);
				if (codigoUfPesquisa != null){
					cidadesPesquisa = fachada.consultarCidadesPorUF(uf);
				}else
					MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades da UF = null.");
				//if (cliente.getDadosPessoa().getEndereco().getCidade().getCodigo() != null && cliente.getDadosPessoa().getEndereco().getCidade().getCodigo() > 0)
					codigoCidadePesquisa = null;//cliente.getDadosPessoa().getEndereco().getCidade().getCodigo();
			}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades pela UF!");
			}
		}
	}
	
	public void filtrarCidades(ValueChangeEvent evento){
		if (!somenteLeitura){
			try{
				codigoUfSelecionada = (Integer)evento.getNewValue();
				UnidadeFederativa uf = new UnidadeFederativa();
				uf.setCodigo(codigoUfSelecionada);
				if (codigoUfSelecionada != null){
					cidades = fachada.consultarCidadesPorUF(uf);
				}else
					MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades da UF = null.");
				if (cliente.getDadosPessoa().getEndereco().getCidade().getCodigo() != null && cliente.getDadosPessoa().getEndereco().getCidade().getCodigo() > 0)
					codigoCidadeSelecionada = cliente.getDadosPessoa().getEndereco().getCidade().getCodigo();
			}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades pela UF!");
			}
		}
	}
	
	public void mudarCpfCnpj(ValueChangeEvent evento){
		TipoCliente tpCli = (TipoCliente)evento.getNewValue();
		if (tpCli != null){
			if (tpCli == TipoCliente.PESSOA_FISICA){
				mascaraCpfOuCnpj = ClienteBean.CPF_MASK;
				cpfOuCnpj = "CPF";
				mostrarCamposPF = true;
				mostrarCamposPJ = false;
			}
			if (tpCli == TipoCliente.PESSOA_JURIDICA){
				mascaraCpfOuCnpj = ClienteBean.CNPJ_MASK;
				cpfOuCnpj = "CNPJ";
				mostrarCamposPF = false;
				mostrarCamposPJ = true;
			}
		}
		cpfOuCnpjVisible = tpCli == null?false:true;
	}
	
	public void mudarTipoCliente(ValueChangeEvent evento){
		TipoCliente tpCli = (TipoCliente)evento.getNewValue();
		if (tpCli != null){
			if (tpCli == TipoCliente.PESSOA_FISICA){
				mostrarCamposPF = true;
				mostrarCamposPJ = false;
			}
			if (tpCli == TipoCliente.PESSOA_JURIDICA){
				mostrarCamposPF = false;
				mostrarCamposPJ = true;
			}
		}
	}
	
	public void telefonesChange(ValueChangeEvent evento){
		if (cliente.getDadosPessoa().getTelefones().size() > 0 
				&& cliente.getDadosPessoa().getTelefones().get(0).compareTo(MSG_TEL) != 0){
			telefone = (String)evento.getNewValue();
		}
	}
	
	public void adicionarTelefone(){
		if (telefone != null && telefone.length() >= 10){
			if (cliente.getDadosPessoa().getTelefones().size() > 0 
					&& cliente.getDadosPessoa().getTelefones().get(0).equals(MSG_TEL)){
				cliente.getDadosPessoa().getTelefones().set(0, telefone);
			}else
				cliente.getDadosPessoa().getTelefones().add(telefone);
			//telefoneSelecionado = telefone;
			telefone = "";
		}
	}
	
	public void excluirTelefone(){
		if (cliente.getDadosPessoa().getTelefones().size() > 0 
				&& cliente.getDadosPessoa().getTelefones().get(0).compareTo(MSG_TEL) != 0){
			cliente.getDadosPessoa().getTelefones().remove(telefoneSelecionado);
			telefone = "";
		}
		if (cliente.getDadosPessoa().getTelefones().size() == 0)
			cliente.getDadosPessoa().getTelefones().add(MSG_TEL);
	}
	
	public void alterarTelefone(){
		if (cliente.getDadosPessoa().getTelefones().size() > 0 
				&& cliente.getDadosPessoa().getTelefones().get(0).compareTo(MSG_TEL) != 0){
			for(int i =0;i < cliente.getDadosPessoa().getTelefones().size();i++){
				if (cliente.getDadosPessoa().getTelefones().get(i).equals(telefoneSelecionado)){
					cliente.getDadosPessoa().getTelefones().set(i, telefone);
					break;
				}
			}
			//telefoneSelecionado = telefone;
			telefone = "";
		}
	}
	
	
	// GETs e SETs

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteParaPesquisa() {
		return clienteParaPesquisa;
	}

	public void setClienteParaPesquisa(Cliente clienteParaPesquisa) {
		this.clienteParaPesquisa = clienteParaPesquisa;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
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

	public List<Cliente> getLista() {
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

	public TipoCliente[] getTiposCliente() {
		return tiposCliente;
	}

	public Integer getCodigoUfPesquisa() {
		return codigoUfPesquisa;
	}

	public void setCodigoUfPesquisa(Integer codigoUfPesquisa) {
		this.codigoUfPesquisa = codigoUfPesquisa;
	}

	public Integer getCodigoCidadePesquisa() {
		return codigoCidadePesquisa;
	}

	public void setCodigoCidadePesquisa(Integer codigoCidadePesquisa) {
		this.codigoCidadePesquisa = codigoCidadePesquisa;
	}

	public List<UnidadeFederativa> getUfsPesquisa() {
		return ufsPesquisa;
	}

	public List<Cidade> getCidadesPesquisa() {
		return cidadesPesquisa;
	}

	public String getCpfCnpjPesquisa() {
		return cpfCnpjPesquisa;
	}

	public void setCpfCnpjPesquisa(String cpfCnpjPesquisa) {
		this.cpfCnpjPesquisa = cpfCnpjPesquisa;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public String getMascaraCpfOuCnpj() {
		return mascaraCpfOuCnpj;
	}

	public boolean isCpfOuCnpjVisible() {
		return cpfOuCnpjVisible;
	}

	public String getNomeParaPesquisa() {
		return nomeParaPesquisa;
	}

	public void setNomeParaPesquisa(String nomeParaPesquisa) {
		this.nomeParaPesquisa = nomeParaPesquisa;
	}

	public boolean isMostrarCamposPJ() {
		return mostrarCamposPJ;
	}

	public boolean isMostrarCamposPF() {
		return mostrarCamposPF;
	}

	public PessoaFisica getPfAux() {
		return pfAux;
	}

	public void setPfAux(PessoaFisica pfAux) {
		this.pfAux = pfAux;
	}

	public PessoaJuridica getPjAux() {
		return pjAux;
	}

	public void setPjAux(PessoaJuridica pjAux) {
		this.pjAux = pjAux;
	}

	public boolean isModoDeInclusao() {
		return modoDeInclusao;
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
