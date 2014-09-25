package negocio;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import seguranca.LoginInvalidoException;
import seguranca.SituacaoSenha;
import seguranca.Usuario;
import util.Parametros;
import classesBasicas.Centro;
import classesBasicas.Cidade;
import classesBasicas.Cliente;
import classesBasicas.Departamento;
import classesBasicas.Escolaridade;
import classesBasicas.Fabricante;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.PessoaJuridica;
import classesBasicas.Situacao;
import classesBasicas.TipoGerencia;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
import dao.DAOCentro;
import dao.DAOCidade;
import dao.DAOCliente;
import dao.DAODepartamento;
import dao.DAOEscolaridade;
import dao.DAOFabricante;
import dao.DAOFuncao;
import dao.DAOFuncionario;
import dao.DAOPessoaJuridica;
import dao.DAOTipoGerencia;
import dao.DAOTipoLogradouro;
import dao.DAOUnidadeFederativa;
import dao.DAOUsuario;
import dao.IDAOCentro;
import dao.IDAOCidade;
import dao.IDAOCliente;
import dao.IDAODepartamento;
import dao.IDAOEscolaridade;
import dao.IDAOFabricante;
import dao.IDAOFuncao;
import dao.IDAOFuncionario;
import dao.IDAOPessoaJuridica;
import dao.IDAOTipoGerencia;
import dao.IDAOTipoLogradouro;
import dao.IDAOUnidadeFederativa;
import dao.IDAOUsuario;
import erro.NegocioExceptionDepartamento;
import erro.NegocioExceptionFuncao;
import erro.NegocioExceptionFuncionario;

public class ControladorOrganizacional {
	private EntityManagerFactory emf;
	private EntityManager entityManager;
	private IDAOFuncao daoFuncao;
	private IDAOFuncionario daoFuncionario;
	private IDAOCentro daoCentro;
	private IDAOTipoGerencia daoTipoGerencia;
	private IDAODepartamento daoDepto;
	private IDAOPessoaJuridica daoPJ;
	private IDAOTipoLogradouro daoTipoLogradouro;
	private IDAOCidade daoCidade;
	private IDAOUnidadeFederativa daoUF;
	private IDAOEscolaridade daoEscolaridade;
	private IDAOFabricante daoFabricante;
	private IDAOCliente daoCliente;
	private IDAOUsuario daoUsuario;
	
	public ControladorOrganizacional(){
		emf = Parametros.EMF_Default;
		entityManager = emf.createEntityManager();
		inicializarDAO();
	}
	
	public ControladorOrganizacional(EntityManagerFactory emf){
		this.emf = emf;
		this.entityManager = emf.createEntityManager();
		inicializarDAO();
	}
	
	private void inicializarDAO(){
		daoFuncao = new DAOFuncao();
		daoFuncionario = new DAOFuncionario();
		daoEscolaridade = new DAOEscolaridade();
		daoCentro = new DAOCentro();
		daoDepto = new DAODepartamento();
		daoPJ = new DAOPessoaJuridica();
		daoTipoLogradouro = new DAOTipoLogradouro();
		daoCidade = new DAOCidade();
		daoUF = new DAOUnidadeFederativa();
		daoTipoGerencia = new DAOTipoGerencia();
		daoFabricante = new DAOFabricante();
		daoCliente = new DAOCliente();
		daoUsuario = new DAOUsuario();
		/*
		daoFuncao = new DAOFuncao(entityManager);
		daoFuncionario = new DAOFuncionario(entityManager);
		daoEscolaridade = new DAOEscolaridade(entityManager);
		daoCentro = new DAOCentro(entityManager);
		daoDepto = new DAODepartamento(entityManager);
		daoPJ = new DAOPessoaJuridica(entityManager);
		daoTipoLogradouro = new DAOTipoLogradouro(entityManager);
		daoCidade = new DAOCidade(entityManager);
		daoUF = new DAOUnidadeFederativa(entityManager);
		daoTipoGerencia = new DAOTipoGerencia(entityManager);
		daoFabricante = new DAOFabricante(entityManager);
		daoCliente = new DAOCliente(entityManager);
		daoUsuario = new DAOUsuario(entityManager);
		*/
	}
	
	// ***************************  U S U Á R I O  **************************************
	public Usuario efetuarLogin(String login, String senha) throws LoginInvalidoException {
		Usuario obj = null;
		try{
			obj = daoUsuario.pegarUsuarioPeloLogin(login);
		}catch(Exception ex){
			throw new LoginInvalidoException("Erro ao tendar localizar usuário pelo login!");
		}
		if (obj == null)
			throw new LoginInvalidoException("Login inválido!");
		if (!obj.getSenhaAtual().equals(senha))
			throw new LoginInvalidoException("Senha inválida!");
		return obj;
	}
	
	public void inserirUsuario(Usuario usuario) throws Exception{
		usuario.setDataUltimaAtualizacao(Calendar.getInstance());
		usuario.setSituacaoSenha(SituacaoSenha.ATIVA);
		if (usuario.getSituacao() == null)
			usuario.setSituacao(Situacao.ATIVO);
		daoUsuario.inserirSemTratamento(usuario);
	}
	
	//*****************************  F U N Ç Ã O  *****************************************
	public boolean funcaoExiste(Funcao funcao) throws Exception{
		Funcao obj = null;
		if (funcao.getCodigo() == null)
			return false;
		obj = daoFuncao.consultarPorId(funcao.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == funcao.getCodigo())
				return true;
		return false;
	}
	

	public void inserirFuncao (Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		funcao.setDataUltimaAtualizacao(Calendar.getInstance());
		if (funcao.getSituacao() == null)
			funcao.setSituacao(Situacao.ATIVO);
		
		if(funcao.getDescricao()==null||funcao.getDescricao().equals("")||
						funcao.getSituacao()==null ||
							funcao.getDataUltimaAtualizacao()==null)
				{
					throw new Exception("inserirFuncao: Campos inválidos");
				}
		daoFuncao.inserirSemTratamento(funcao);
	}
	
	public void alterarFuncao(Funcao funcao) throws Exception {
		funcao.setDataUltimaAtualizacao(Calendar.getInstance());
		if (funcao.getSituacao() == null)
			funcao.setSituacao(Situacao.ATIVO);
		daoFuncao.alterarSemTratamento(funcao);
	}
	
	
	public void removerFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		Funcao f = daoFuncao.consultarPorId(funcao.getCodigo());
		if(f==null){
			throw new NegocioExceptionFuncao("Departamento não encontrado!");
		}
		daoFuncao.removerSemTratamento(f);
	}

	public List<Funcao> pesquisarFuncao(Funcao funcao) throws Exception{
		// TODO Auto-generated method stub
		return daoFuncao.pesquisar(funcao);
	}
	
	public List<Funcao> listarFuncoes() throws Exception{
		return daoFuncao.consultarTodos();
	}
	
	public Funcao pegarFuncaoPorId(Integer codigo) throws Exception{
		return daoFuncao.consultarPorId(codigo);
	}
	
	//************************  E S C O L A R I D A D E  ********************************
	public boolean escolaridadeExiste(Escolaridade escolaridade) throws Exception{
		Escolaridade obj = null;
		if (escolaridade.getCodigo() == null)
			return false;
		obj = daoEscolaridade.consultarPorId(escolaridade.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == escolaridade.getCodigo())
				return true;
		return false;
	}
	
	public void inserirEscolaridade(Escolaridade escolaridade) throws Exception{
		escolaridade.setDataUltimaAtualizacao(Calendar.getInstance());
        if (escolaridade.getSituacao() == null)
        	escolaridade.setSituacao(Situacao.ATIVO);
		daoEscolaridade.inserirSemTratamento(escolaridade);
	}
	
	public void alterarEscolaridade(Escolaridade escolaridade) throws Exception{
		escolaridade.setDataUltimaAtualizacao(Calendar.getInstance());
        if (escolaridade.getSituacao() == null)
        	escolaridade.setSituacao(Situacao.ATIVO);
		daoEscolaridade.alterarSemTratamento(escolaridade);
	}
	
	public void excluirEscolaridade(Escolaridade escolaridade) throws Exception{
		//Escolaridade es = daoEscolaridade.consultarPorId(escolaridade.getCodigo());
		//if (es != null)
			daoEscolaridade.removerSemTratamento(escolaridade);
	}
	
	public List<Escolaridade> consultarEscolaridade(Escolaridade escolaridade) throws Exception{
		return daoEscolaridade.pesquisar(escolaridade);
	}
	
	public List<Escolaridade> listarEscolaridades(){
		return daoEscolaridade.consultarTodos();
	}
	
	public Escolaridade pegarEscolaridadePorId(Integer codigo) throws Exception{
		return daoEscolaridade.consultarPorId(codigo);
	}
	
	
	//*************************  D E P A R T A M E N T O  **************************************
	public boolean departamentoExiste(Departamento obj) throws Exception{
		Departamento outroObj = null;
		if (obj.getCodigo() == null)
			return false;
		outroObj = daoDepto.consultarPorId(obj.getCodigo());
		if (outroObj != null)
			if (outroObj.getCodigo() == obj.getCodigo())
				return true;
		return false;
	}
	
	public void inserirDepartamento(Departamento departamento) throws Exception{
        departamento.setDataUltimaAtualizacao(Calendar.getInstance());
        if (departamento.getSituacao() == null)
                departamento.setSituacao(Situacao.ATIVO);
        if(departamento.getNome()==null||departamento.getNome().equals("")){
        	throw new NegocioExceptionDepartamento("Inserir depto: Campos inválidos");
        }
        daoDepto.inserirSemTratamento(departamento);
	}
	
	public void alterarDepartamento(Departamento departamento) throws Exception{
		departamento.setDataUltimaAtualizacao(Calendar.getInstance());
		if (departamento.getSituacao() == null)
			departamento.setSituacao(Situacao.ATIVO);
		
		if(	departamento.getCodigo()==null||departamento.getCodigo().equals("")||
				departamento.getNome()==null||departamento.getNome().equals(""))
			{
				throw new NegocioExceptionDepartamento("Alterar depto: Campos inválidos");
		}
		
		daoDepto.alterarSemTratamento(departamento);
	}
	
	public void removerDepartamento(Departamento departamento) throws Exception{
		daoDepto.removerSemTratamento(departamento);
	}

	public List<Departamento> pesquisarDepartamento(Departamento departamento) throws Exception {
		return daoDepto.consultar(departamento);
	}
	
	public List<Departamento> listarDepartamentos(){
		return daoDepto.consultarTodos();
	}
	
	public Departamento pegarDepartamentoPorId(Integer codigo) throws Exception {
		return daoDepto.consultarPorId(codigo);
	}
	
	
	//*************************  F U N C I O N Á R I O  ******************************************
	public boolean funcionarioExiste(Funcionario obj) throws Exception{
		Funcionario outroObj = null;
		if (obj.getCodigo() == null)
			return false;
		outroObj = daoFuncionario.consultarPorId(obj.getCodigo());
		if (outroObj != null)
			if (outroObj.getCodigo() == obj.getCodigo())
				return true;
		return false;
	}
	
	public void inserirFuncionario (Funcionario funcionario) throws Exception {
		funcionario.setDataUltimaAtualizacao(Calendar.getInstance());
		if (funcionario.getSituacao() == null){
			funcionario.setSituacao(Situacao.ATIVO);
		}
		if(	funcionario.getNome()==null||funcionario.getNome().equals("")){
					throw new NegocioExceptionFuncionario ("Campos inválidos");
		}
		daoFuncionario.inserirSemTratamento(funcionario);
	}
	
	public void alterarFuncionario(Funcionario funcionario) throws Exception{
		funcionario.setDataUltimaAtualizacao(Calendar.getInstance());
		if (funcionario.getSituacao() == null){
			funcionario.setSituacao(Situacao.ATIVO);
		}
		if(funcionario.getCodigo()==null||funcionario.getCodigo().equals("")||
				funcionario.getNome()==null||funcionario.getNome().equals("")||
						funcionario.getSituacao()==null||funcionario.getSituacao().equals("")||
								funcionario.getDataUltimaAtualizacao()==null||
								funcionario.getDataUltimaAtualizacao().equals("")){
			throw new NegocioExceptionFuncionario("Campos inválidos");
		}
				
		Funcionario func = daoFuncionario.consultarPorId(funcionario.getCodigo());
		if(func==null){
			throw new NegocioExceptionFuncionario("Funcionário não cadastrado");
		}
		
		daoFuncionario.alterarSemTratamento(funcionario);
	}
	
	public void removerFuncionario(Funcionario funcionario) throws Exception{
		// TODO Auto-generated method stub
		Funcionario func = daoFuncionario.consultarPorId(funcionario.getCodigo());
		if(func==null){
			throw new NegocioExceptionFuncionario("Funcionario não encontrado!");
		}
		daoFuncionario.removerSemTratamento(func);
	}

	public List<Funcionario> pesquisarFuncionario(Funcionario funcionario) throws NegocioExceptionFuncionario {
		return daoFuncionario.pesquisar(funcionario);
	}
	
	public List<Funcionario> consultarFuncionario(Funcionario funcionario) throws Exception{
		return daoFuncionario.consultar(funcionario);
	}
	
	public List<Funcionario> listarFuncionariosGestores() throws Exception{
		return daoFuncionario.listarFuncionariosGestores();
	}
	
	public Funcionario pegarFuncionarioPorId(Integer codigo) throws Exception{
		return daoFuncionario.consultarPorId(codigo);
	}
	
	
	//*********************************  C E N T R O  *********************************************
	public boolean centroExiste(Centro centro) throws Exception{
		Centro obj = null;
		if (centro.getCodigo() == null)
			return false;
		obj = daoCentro.consultarPorId(centro.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == centro.getCodigo())
				return true;
		return false;
	}
	
	public void inserirCentro(Centro centro) throws Exception{
		centro.setDataUltimaAtualizacao(Calendar.getInstance());
		if (centro.getSituacao() == null)
			centro.setSituacao(Situacao.ATIVO);
		daoCentro.inserirSemTratamento(centro);
	}
	
	public void alterarCentro(Centro centro) throws Exception{
		centro.setDataUltimaAtualizacao(Calendar.getInstance());
		if (centro.getSituacao() == null)
			centro.setSituacao(Situacao.ATIVO);
		daoCentro.alterarSemTratamento(centro);
	}
	
	public void removerCentro(Centro centro) throws Exception{
		Centro c = daoCentro.consultarPorId(centro.getCodigo());
		if (c != null)
			daoCentro.removerSemTratamento(c);
		/*EntityTransaction et = entityManager.getTransaction();
		try{
			et.begin();
			daoCentro.removerSemTratamento(centro);
			daoPJ.removerSemTratamento(centro.getDadosPJ());
			
			et.commit();
		}catch(Exception ex){
			et.rollback();
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}*/
	}
	
	public List<Centro> consultarCentro(Centro centro) throws Exception{
		return daoCentro.consultar(centro);
	}
	
	public List<Centro> listarCentros() throws Exception{
		return daoCentro.consultarTodos();
	}
	
	public Centro pegarCentroPorId(Integer codigo) throws Exception {
		return daoCentro.consultarPorId(codigo);
	}
	
	//************************* T I P O S   L O G R A D O U R O S  *********************
	public boolean tipoLogradouroExiste(TipoLogradouro tipoLogradouro) throws Exception{
		TipoLogradouro obj = null;
		if (tipoLogradouro.getCodigo() == null)
			return false;
		obj = daoTipoLogradouro.consultarPorId(tipoLogradouro.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == tipoLogradouro.getCodigo())
				return true;
		return false;
	}
	
	public void inserirTipoLogradouro(TipoLogradouro tipoLogradouro) throws Exception{
		tipoLogradouro.setDataUltimaAtualizacao(Calendar.getInstance());
		if (tipoLogradouro.getSituacao() == null)
			tipoLogradouro.setSituacao(Situacao.ATIVO);
		daoTipoLogradouro.inserirSemTratamento(tipoLogradouro);
	}
	
	public void alterarTipoLogradouro(TipoLogradouro tipoLogradouro) throws Exception{
		tipoLogradouro.setDataUltimaAtualizacao(Calendar.getInstance());
		if (tipoLogradouro.getSituacao() == null)
			tipoLogradouro.setSituacao(Situacao.ATIVO);
		daoTipoLogradouro.alterarSemTratamento(tipoLogradouro);
	}
	
	public List<TipoLogradouro> listarTiposLogradouros() throws Exception{
		return daoTipoLogradouro.consultarTodos();
	}
	
	public TipoLogradouro pegarTipoLogradouroPorId(Integer codigo) throws Exception{
		return daoTipoLogradouro.consultarPorId(codigo);
	}
	
	//****************************  C I D A D E  *****************************************
	public boolean cidadeExiste(Cidade cidade) throws Exception{
		Cidade obj = null;
		if (cidade.getCodigo() == null)
			return false;
		obj = daoCidade.consultarPorId(cidade.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == cidade.getCodigo())
				return true;
		return false;
	}
	
	public void inserirCidade(Cidade cidade) throws Exception{
		cidade.setDataUltimaAtualizacao(Calendar.getInstance());
		if (cidade.getSituacao() == null)
			cidade.setSituacao(Situacao.ATIVO);
		List<Cidade> lista = daoCidade.pesquisar(cidade);
		if (lista.size() > 0)
			throw new Exception("A cidade " + cidade.getNome()
					+ "/" +cidade.getUnidadeFederativa().getSigla() + ", já existe!");
		daoCidade.inserirSemTratamento(cidade);
	}
	
	public void alterarCidade(Cidade cidade) throws Exception{
		cidade.setDataUltimaAtualizacao(Calendar.getInstance());
		if (cidade.getSituacao() == null)
			cidade.setSituacao(Situacao.ATIVO);
		daoCidade.alterarSemTratamento(cidade);
	}
	
	public void excluirCidade(Cidade cidade) throws Exception{
		daoCidade.removerSemTratamento(cidade);
	}
	
	public List<Cidade> listarCidades() throws Exception{
		return daoCidade.consultarTodos();
	}
	
	public List<Cidade> consultarCidade(Cidade cidade) throws Exception{
		return daoCidade.pesquisar(cidade);
	}
	
	public List<Cidade> consultarCidadesPorUF(UnidadeFederativa uf) throws Exception{
		return daoCidade.pesquisarCidadePorUF(uf);
	}
	
	public Cidade pegarCidadePorId(Integer codigo) throws Exception{
		return daoCidade.consultarPorId(codigo);
	}
	
	//*******************  U N I D A D E   F E D E R A T I V A  ****************************
	public List<UnidadeFederativa> listarUFs() throws Exception{
		return daoUF.consultarTodos();
	}
	
	public UnidadeFederativa pegarUfPorId(Integer codigo) throws Exception{
		return  daoUF.consultarPorId(codigo);
	}
	
	//************************  T I P O   G E R E N C I A  **********************************
	public boolean tipoGerenciaExiste(TipoGerencia obj) throws Exception{
		TipoGerencia outroObj = null;
		if (obj.getCodigo() == null)
			return false;
		outroObj = daoTipoGerencia.consultarPorId(obj.getCodigo());
		if (outroObj != null)
			if (outroObj.getCodigo() == obj.getCodigo())
				return true;
		return false;
	}
	
	public void inserirTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		tipoGerencia.setDataUltimaAtualizacao(Calendar.getInstance());
		if (tipoGerencia.getSituacao() == null)
			tipoGerencia.setSituacao(Situacao.ATIVO);
		daoTipoGerencia.inserirSemTratamento(tipoGerencia);
	}
	
	public void alterarTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		tipoGerencia.setDataUltimaAtualizacao(Calendar.getInstance());
		if (tipoGerencia.getSituacao() == null)
			tipoGerencia.setSituacao(Situacao.ATIVO);
		daoTipoGerencia.alterarSemTratamento(tipoGerencia);
	}
	
	public void removerTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		daoTipoGerencia.removerSemTratamento(tipoGerencia);
	}
	
	public List<TipoGerencia> consultarTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		return daoTipoGerencia.pesquisar(tipoGerencia);
	}
	
	public List<TipoGerencia> listarTiposGerencia() throws Exception{
		return daoTipoGerencia.consultarTodos();
	}
	
	public TipoGerencia pegarTipoGerenciaPorId(Integer codigo) throws Exception{
		return daoTipoGerencia.consultarPorId(codigo);
	}
	
	//***********************  P E S S O A     J U R Í D I C A  *************************
	public List<PessoaJuridica> listarPJ() throws Exception{
		return daoPJ.consultarTodos();
	}
		
	
	public PessoaJuridica pegarPessoaJuridicaPorId(Integer codigo) throws Exception{
		return daoPJ.consultarPorId(codigo);
	}
	
	//***************************  C L I E N T E  *************************************
	public boolean clienteExiste(Cliente obj) throws Exception{
		Cliente outroObj = null;
		if (obj.getCodigo() == null)
			return false;
		outroObj = daoCliente.consultarPorId(obj.getCodigo());
		if (outroObj != null)
			if (outroObj.getCodigo() == obj.getCodigo())
				return true;
		return false;
	}
	
	public void inserirCliente(Cliente cliente) throws Exception{
		cliente.getDadosPessoa().setDataUltimaAtualizacao(Calendar.getInstance());
		if (cliente.getDadosPessoa().getSituacao() == null)
			cliente.getDadosPessoa().setSituacao(Situacao.ATIVO);
		daoCliente.inserirSemTratamento(cliente);
	}
	
	public void alterarCliente(Cliente cliente) throws Exception{
		cliente.getDadosPessoa().setDataUltimaAtualizacao(Calendar.getInstance());
		if (cliente.getDadosPessoa().getSituacao() == null)
			cliente.getDadosPessoa().setSituacao(Situacao.ATIVO);
		daoCliente.alterarSemTratamento(cliente);
	}
	
	public void excluirCliente(Cliente cliente) throws Exception{
		daoCliente.removerSemTratamento(cliente);
	}
	
	public List<Cliente> listarClientes() throws Exception{
		return daoCliente.consultarTodos();
	}
	
	public List<Cliente> pesquisarCliente(Cliente exemplo) throws Exception{
		return daoCliente.pesquisar(exemplo);
	}
	
	public List<Cliente> consultarCliente(Cliente cliente) throws Exception{
		return daoCliente.consultar(cliente);
	}
	
	public Cliente pegarClientePorId(Integer codigo) throws Exception{
		return daoCliente.consultarPorId(codigo);
	}
	

	//************************  F A B R I C A N T E  ************************************
	public boolean fabricanteExiste(Fabricante obj) throws Exception{
		Fabricante outroObj = null;
		if (obj.getCodigo() == null)
			return false;
		outroObj = daoFabricante.consultarPorId(obj.getCodigo());
		if (outroObj != null)
			if (outroObj.getCodigo() == obj.getCodigo())
				return true;
		return false;
	}
	
	public void inserirFabricante(Fabricante fabricante) throws Exception{
		fabricante.getPj().setDataUltimaAtualizacao(Calendar.getInstance());
		if (fabricante.getPj().getSituacao() == null)
			fabricante.getPj().setSituacao(Situacao.ATIVO);
		daoFabricante.inserirSemTratamento(fabricante);
	}
	
	public void alterarFabricante(Fabricante fabricante) throws Exception{
		fabricante.getPj().setDataUltimaAtualizacao(Calendar.getInstance());
		if (fabricante.getPj().getSituacao() == null)
			fabricante.getPj().setSituacao(Situacao.ATIVO);
		daoFabricante.alterarSemTratamento(fabricante);
	}
	
	public void excluirFabricante(Fabricante fabricante) throws Exception{
		Fabricante f = daoFabricante.consultarPorId(fabricante.getCodigo());
		if (f != null)
			daoFabricante.removerSemTratamento(f);
		/*EntityTransaction et = entityManager.getTransaction();
		try{
			et.begin();
			daoFabricante.removerSemTratamento(fabricante);
			daoPJ.removerSemTratamento(fabricante.getPj());
			et.commit();
		}catch(Exception ex){
			et.rollback();
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}*/
	}
	
	public List<Fabricante> listarFabricantes() throws Exception{
		return daoFabricante.consultarTodos();
	}
	
	public Fabricante consultarFabricantePorId(Integer codigo) throws Exception{
		return daoFabricante.consultarPorId(codigo);
	}
	
	public List<Fabricante> consultarFabricante(Fabricante fabricante) throws Exception{
		return daoFabricante.consultar(fabricante);
	}
	
	
	
}
