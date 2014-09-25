package fachada;

import java.util.Date;
import java.util.List;

import seguranca.LoginInvalidoException;
import seguranca.Usuario;
import negocio.ControladorCarro;
import negocio.ControladorMovimentacao;
import negocio.ControladorOrganizacional;
import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.Centro;
import classesBasicas.Cidade;
import classesBasicas.Cliente;
import classesBasicas.Departamento;
import classesBasicas.Escolaridade;
import classesBasicas.Fabricante;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Movimentacao;
import classesBasicas.MovimentacaoItem;
import classesBasicas.PessoaJuridica;
import classesBasicas.TipoGerencia;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
import classesBasicas.VersaoCarro;

public class Fachada implements IFachada {
	private static Fachada instancia;
	private ControladorOrganizacional ctrlOrg;
	private ControladorCarro controladorCarro;
	private ControladorMovimentacao ctrlMovimentacao;
	
	private Fachada(){
		ctrlOrg = new ControladorOrganizacional();
		this.controladorCarro = new ControladorCarro();
		ctrlMovimentacao = new ControladorMovimentacao();
	}
	
	public static Fachada obterInstancia(){
		if (instancia == null)
			instancia = new Fachada();
		return instancia;
	}
	
	//***********************************************************************************
	//******************  C R U D    O R G A N I Z A C I O N A L ************************
	//***********************************************************************************
	
	//*****************************  F U N Ç Ã O  ***************************************
	@Override
	public void salvarFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		if (ctrlOrg.funcaoExiste(funcao))
			ctrlOrg.alterarFuncao(funcao);
		else
			ctrlOrg.inserirFuncao(funcao);
	}
	
	@Override
	public void excluirFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		if (ctrlOrg.funcaoExiste(funcao))
			ctrlOrg.removerFuncao(funcao);
	}

	@Override
	public List<Funcao> listarFuncoes() throws Exception {
		// TODO Auto-generated method stub
		return ctrlOrg.listarFuncoes();
	}
	
	@Override
	public List<Funcao> consultarFuncao(Funcao funcao) throws Exception{
		return ctrlOrg.pesquisarFuncao(funcao);
	}
	
	@Override
	public Funcao pegarFuncaoPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarFuncaoPorId(codigo);
	}
	
	//************************  E S C O L A R I D A D E  ********************************
	@Override
	public void salvarEscolaridade(Escolaridade escolaridade) throws Exception{
		if (ctrlOrg.escolaridadeExiste(escolaridade))
			ctrlOrg.alterarEscolaridade(escolaridade);
		else
			ctrlOrg.inserirEscolaridade(escolaridade);
	}
	
	@Override
	public List<Escolaridade> consultarEscolaridade(Escolaridade escolaridade) throws Exception{
		return ctrlOrg.consultarEscolaridade(escolaridade);
	}
	
	@Override
	public void excluirEscolaridade(Escolaridade escolaridade) throws Exception{
		ctrlOrg.excluirEscolaridade(escolaridade);
	}
	
	@Override
	public Escolaridade pegarEscolaridadePorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarEscolaridadePorId(codigo);
	}
	
	@Override
	public List<Escolaridade> listarEscolaridades() throws Exception{
		return ctrlOrg.listarEscolaridades();
	}
	
	
	//**************************  C E N T R O  **********************************************
	@Override
	public void salvarCentro(Centro centro) throws Exception{
		if (ctrlOrg.centroExiste(centro))
			ctrlOrg.alterarCentro(centro);
		else
			ctrlOrg.inserirCentro(centro);
	}
	
	@Override
	public void excluirCentro(Centro centro) throws Exception{
		if (ctrlOrg.centroExiste(centro))
			ctrlOrg.removerCentro(centro);
	}
	
	@Override
	public List<Centro> consultarCentro(Centro centro) throws Exception{
		return ctrlOrg.consultarCentro(centro);
	}
	
	@Override
	public List<Centro> listarCentros() throws Exception{
		return ctrlOrg.listarCentros();
	}
	
	@Override
	public Centro pegarCentroPorId(Integer codigo) throws Exception {
		return ctrlOrg.pegarCentroPorId(codigo);
	}
	
	//**************************  F U N C I O N Á R I O  ************************************
	@Override
	public void salvarFuncionario(Funcionario funcionario) throws Exception{
		if (ctrlOrg.funcionarioExiste(funcionario))
			ctrlOrg.alterarFuncionario(funcionario);
		else
			ctrlOrg.inserirFuncionario(funcionario);
	}
	
	@Override
	public void excluirFuncionario(Funcionario funcaionario) throws Exception{
		ctrlOrg.removerFuncionario(funcaionario);
	}
	
	@Override
	public List<Funcionario> consultarFuncionario(Funcionario funcionario) throws Exception{
		return ctrlOrg.consultarFuncionario(funcionario);
	}
	
	@Override
	public List<Funcionario> listarFuncionariosGestores() throws Exception{
		return ctrlOrg.listarFuncionariosGestores();
	}
	
	@Override
	public Funcionario pegarFuncionarioPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarFuncionarioPorId(codigo);
	}
	
	//**************************  D E P A R T A M E N T O  **********************************
	@Override
	public void salvarDepartamento(Departamento depto) throws Exception{
		if (ctrlOrg.departamentoExiste(depto))
			ctrlOrg.alterarDepartamento(depto);
		else
			ctrlOrg.inserirDepartamento(depto);
	}
	
	@Override
	public void excluirDepartamento(Departamento depto) throws Exception{
		ctrlOrg.removerDepartamento(depto);
	}
	
	@Override
	public List<Departamento> consultarDepartamento(Departamento depto) throws Exception{
		return ctrlOrg.pesquisarDepartamento(depto);
	}
	
	@Override
	public List<Departamento> listarDepartamentos() throws Exception{
		return ctrlOrg.listarDepartamentos();
	}
	
	@Override
	public Departamento pegarDepartamentoPorId(Integer codigo) throws Exception {
		return ctrlOrg.pegarDepartamentoPorId(codigo);
	}
	
	
	//************************  T I P O   G E R E N C I A  **********************************
	@Override
	public void salvarTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		if (ctrlOrg.tipoGerenciaExiste(tipoGerencia))
			ctrlOrg.alterarTipoGerencia(tipoGerencia);
		else
			ctrlOrg.inserirTipoGerencia(tipoGerencia);
	}
	
	@Override
	public void excluirTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		ctrlOrg.removerTipoGerencia(tipoGerencia);
	}
	
	@Override
	public List<TipoGerencia> consultarTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		return ctrlOrg.consultarTipoGerencia(tipoGerencia);
	}
	
	@Override
	public List<TipoGerencia> listarTiposGerencia() throws Exception{
		return ctrlOrg.listarTiposGerencia();
	}
	
	@Override
	public TipoGerencia pegarTipoGerenciaPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarTipoGerenciaPorId(codigo);
	}
	
	//*********************  T I P O   L O G R A D O U R O  **********************************
	@Override
	public void salvarTipoLogradouro(TipoLogradouro tipoLogradouro) throws Exception{
		if (ctrlOrg.tipoLogradouroExiste(tipoLogradouro))
			ctrlOrg.alterarTipoLogradouro(tipoLogradouro);
		else
			ctrlOrg.inserirTipoLogradouro(tipoLogradouro);
	}
	
	@Override
	public List<TipoLogradouro> listarTiposLogradouros() throws Exception{
		return ctrlOrg.listarTiposLogradouros();
	}
	
	@Override
	public TipoLogradouro pegarTipoLogradouroPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarTipoLogradouroPorId(codigo);
	}
	
	//****************************  C I D A D E  *****************************************
	@Override
	public void salvarCidade(Cidade cidade) throws Exception{
		if (ctrlOrg.cidadeExiste(cidade))
			ctrlOrg.alterarCidade(cidade);
		else
			ctrlOrg.inserirCidade(cidade);
	}
	
	@Override
	public void excluirCidade(Cidade cidade) throws Exception{
		ctrlOrg.excluirCidade(cidade);
	}
	
	@Override
	public List<Cidade> listarCidades() throws Exception{
		return ctrlOrg.listarCidades();
	}
	
	@Override
	public List<Cidade> consultarCidade(Cidade cidade) throws Exception {
		return ctrlOrg.consultarCidade(cidade);
	}
	
	@Override
	public List<Cidade> consultarCidadesPorUF(UnidadeFederativa uf) throws Exception{
		return ctrlOrg.consultarCidadesPorUF(uf);
	}
	
	@Override
	public Cidade pegarCidadePorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarCidadePorId(codigo);
	}
	
	//*******************  U N I D A D E   F E D E R A T I V A  ****************************
	@Override
	public List<UnidadeFederativa> listarUFs() throws Exception{
		return ctrlOrg.listarUFs();
	}
	
	@Override
	public UnidadeFederativa pegarUnidadeFederativaPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarUfPorId(codigo);
	}
	
	
	//***********************  P E S S O A     J U R Í D I C A  *************************
	@Override
	public List<PessoaJuridica> listarPJ() throws Exception{
		return ctrlOrg.listarPJ();
	}
	
	@Override
	public PessoaJuridica pegarPessoaJuridicaPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarPessoaJuridicaPorId(codigo);
	}
	
	
	//***********************************************************************************
	//********************* F I M   C R U D    Organizacional ***************************
	//***********************************************************************************
	
	//**************************  C L I E N T E  ****************************************
	@Override
	public void salvarCliente(Cliente cliente) throws Exception {
		if (ctrlOrg.clienteExiste(cliente))
			ctrlOrg.alterarCliente(cliente);
		else
			ctrlOrg.inserirCliente(cliente);
	}

	@Override
	public void excluirCliente(Cliente cliente) throws Exception {
		ctrlOrg.excluirCliente(cliente);
		
	}
	
	@Override
	public List<Cliente> pesquisarCliente(Cliente exemplo) throws Exception{
		return ctrlOrg.pesquisarCliente(exemplo);
	}

	@Override
	public List<Cliente> consultarCliente(Cliente cliente) throws Exception {
		return ctrlOrg.consultarCliente(cliente);
	}

	@Override
	public List<Cliente> listarClientes() throws Exception {
		return ctrlOrg.listarClientes();
	}
	
	@Override
	public Cliente pegarClientePorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarClientePorId(codigo);
	}
	
	//*************************  F A B R I C A N T E  ***********************************
	@Override
	public void salvarFabricante(Fabricante fabricante) throws Exception {
		if (ctrlOrg.fabricanteExiste(fabricante))
			ctrlOrg.alterarFabricante(fabricante);
		else
			ctrlOrg.inserirFabricante(fabricante);
	}

	@Override
	public void excluirFabricante(Fabricante fabricante) throws Exception {
		ctrlOrg.excluirFabricante(fabricante);
	}

	@Override
	public List<Fabricante> consultarFabricante(Fabricante fabricante)
			throws Exception {
		return ctrlOrg.consultarFabricante(fabricante);
	}

	@Override
	public List<Fabricante> listarFabricantes() throws Exception {
		return ctrlOrg.listarFabricantes();
	}
	
	@Override
	public Fabricante consultarFabricantePorId(Integer codigo) throws Exception{
		return ctrlOrg.consultarFabricantePorId(codigo);
	}


	//Controlador Carro - Felipe Carlos
	
	//Carro
	@Override
	public void salvarCarro(Carro carro) throws Exception  {
		if(controladorCarro.carroExiste(carro)){
			controladorCarro.alterar(carro);
		}
		else{
		this.controladorCarro.inserir(carro);
		}
	}

	@Override
	public void removerCarro(Carro carro) {
		this.controladorCarro.remover(carro);
	}

	@Override
	public List<Carro> listarCarros() {
		return this.controladorCarro.listarCarros();
	}

	@Override
	public Carro pesquisarCarroCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarCarro(codigo);
	}

	@Override
	public List<Carro> consultarCarros(Carro carro, Fabricante f, MarcaCarro m, 
			ModeloCarro modelo, List<ItemSerieCarro> itensSelecionado, 
			List<AcessorioCarro> acessoriosSelecionado) throws Exception {
		return this.controladorCarro.pesquisarCarros(carro, f, m, modelo,itensSelecionado, acessoriosSelecionado);
	}
	
	@Override
	public Carro pegarCarroPeloChassi(String chassi) throws Exception{
		return this.controladorCarro.pegarCarroPeloChassi(chassi);
	}

	//Marca
	
	@Override
	public void salvarMarcaCarro(MarcaCarro marcaCarro) throws Exception {
		if(controladorCarro.marcaExiste(marcaCarro)){
			this.controladorCarro.alterar(marcaCarro);
		}
		else{
			this.controladorCarro.inserir(marcaCarro);
		}
			
	}
	@Override
	public void removerMarcaCarro(MarcaCarro marcaCarro) throws Exception {
		this.controladorCarro.removerMarca(marcaCarro);
	}

	@Override
	public List<MarcaCarro> listarMarcasCarros() {
		return this.controladorCarro.listarMarcas();
	}

	@Override
	public MarcaCarro pesquisarMarcasCarroCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarMarcaCodigo(codigo);
	}

	@Override
	public List<MarcaCarro> consultarMarcasCarros(MarcaCarro marcaCarro) throws Exception {
		return this.controladorCarro.pesquisarMarcas(marcaCarro);
	}
	

	//Modelo
	
	@Override
	public void salvarModeloCarro(ModeloCarro modelo) throws Exception {
		if (modelo.getCodigo() == null)
			this.controladorCarro.inserir(modelo);
		else
			this.controladorCarro.alterar(modelo);
		
	}

	@Override
	public void removerModeloCarro(ModeloCarro modelo) throws Exception {
		this.controladorCarro.remover(modelo);
	}

	@Override
	public List<ModeloCarro> listarModelosCarros() {
		return this.controladorCarro.listarModelos();
	}

	@Override
	public ModeloCarro pesquisarModelosCarroCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarModeloCarro(codigo);
	}

	@Override
	public List<ModeloCarro> consultarModelosCarros(ModeloCarro modelo) throws Exception {
		return this.controladorCarro.pesquisarModeloCarros(modelo);
	}

	@Override
	public List<MarcaCarro> pesquisarMarcaPorFabr(Integer codigo) {
		return this.controladorCarro.pesquisarMarcaPorFabr(codigo);
	}
	

	@Override
	public List<ModeloCarro> pesquisarModeloPorMarca(Integer codigo) {
		return this.controladorCarro.pesquisarModeloPorMarca(codigo);
	}

	
	//Versao
	
	@Override
	public void salvarVersao(VersaoCarro versaoCarro) throws Exception {
		if (controladorCarro.versaoExiste(versaoCarro))
			controladorCarro.alterar(versaoCarro);
		else
			controladorCarro.inserir(versaoCarro);
	}

	@Override
	public void removerVersao(VersaoCarro versaoCarro) {
		this.controladorCarro.remover(versaoCarro);
	}

	@Override
	public List<VersaoCarro> listarVersoes() {
		return this.controladorCarro.listarVersoes();
	}

	@Override
	public VersaoCarro pesquisarVersaoCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarVersaoCodigo(codigo);
	}

	@Override
	public List<VersaoCarro> consultarVersoes(VersaoCarro versaoCarro, Fabricante f , MarcaCarro m) throws Exception {
		return this.controladorCarro.pesquisarVersoes(versaoCarro,f,m);
	}
	
	@Override
	public List<VersaoCarro> pesquisarVersaoPorModelo(Integer codigo) {
		return this.controladorCarro.pesquisarVersaoPorModelo(codigo);
	}

	//Item Série
	
	@Override
	public void salvarItemSerie(ItemSerieCarro itemSerieCarro) throws Exception {
		if(controladorCarro.itemExiste(itemSerieCarro)){
			this.controladorCarro.alterar(itemSerieCarro);
		}
		else{
		this.controladorCarro.inserir(itemSerieCarro);
		}
	}

	@Override
	public void removerItem(ItemSerieCarro itemSerieCarro) throws Exception {
		this.controladorCarro.remover(itemSerieCarro);
	}

	@Override
	public List<ItemSerieCarro> listarItens() {
		return this.controladorCarro.listarItens();
	}

	public List<ItemSerieCarro> listarItensdistintos() {
		return this.controladorCarro.listarItensDistintos();
	}
	@Override
	public ItemSerieCarro pesquisarItemCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarItemCodigo(codigo);
	}

	@Override
	public List<ItemSerieCarro> consultarItens(ItemSerieCarro itemSerieCarro) throws Exception {
		return this.controladorCarro.pesquisarItens(itemSerieCarro);
	}

	@Override
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo) {
		return this.controladorCarro.listarItensPorModelo(modelo);
	}
	public List<ItemSerieCarro> listarItensPorVersao(Integer codigo){
		return this.controladorCarro.listarItensPorVersao(codigo);
	}

	//Acessorio
	
	@Override
	public void salvarAcessorio(AcessorioCarro acessorioCarro)throws Exception {
		if(controladorCarro.acessorioExiste(acessorioCarro)){
			this.controladorCarro.alterarAcessorio(acessorioCarro);
		}else{
			this.controladorCarro.inserir(acessorioCarro);
		}
	}

	@Override
	public void removerAcessorio(AcessorioCarro acessorioCarro) throws Exception {
		this.controladorCarro.removerAcessorio(acessorioCarro);
	}

	@Override
	public List<AcessorioCarro> listarAcessorios() {
		return this.controladorCarro.listarAcessorios();
	}

	@Override
	public AcessorioCarro pesquisarAcessorioCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarAcessorioCarroCodigo(codigo);
	}

	@Override
	public List<AcessorioCarro> consultarAcessorios(AcessorioCarro acessorioCarro) throws Exception {
		return this.controladorCarro.pesquisarAcessorios(acessorioCarro);
	}

	@Override
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo) {
		return this.controladorCarro.listarAcessoriosPorModelo(modelo);
	}
	
	public List<AcessorioCarro> listarAceDiestintos(){
	 return this.controladorCarro.listarAceDiestintos();
	}
	
	// ***************************  U S U Á R I O  *******************************

	@Override
	public Usuario efetuarLogin(String login, String senha)	throws LoginInvalidoException {
		return ctrlOrg.efetuarLogin(login, senha);
	}

	@Override
	public void inserirUsuario(Usuario usuario) throws Exception {
		ctrlOrg.inserirUsuario(usuario);
		
	}
	

	
	//*************************  M O V I M E N T A Ç Ã O  *******************************

	@Override
	public void salvarMovimentacao(Movimentacao movimentacao) throws Exception {
		if (ctrlMovimentacao.movimentacaoExiste(movimentacao))
			ctrlMovimentacao.alterarMovimentacao(movimentacao);
		else
			ctrlMovimentacao.inserirMovimentacao(movimentacao);
	}

	@Override
	public void excluirMovimentacao(Movimentacao movimentacao) throws Exception {
		ctrlMovimentacao.excluirMovimentacao(movimentacao);
	}

	@Override
	public List<Movimentacao> consultarMovimentacao(Movimentacao movimentacao)
			throws Exception {
		return ctrlMovimentacao.consultarMovimentacao(movimentacao);
	}

	@Override
	public List<Movimentacao> consultarMovimentacao(Movimentacao movimentacao,
			Date dataFinal) throws Exception {
		return ctrlMovimentacao.consultarMovimentacao(movimentacao, dataFinal);
	}
	
	@Override
	public Movimentacao pegarMovimentacaoPeloNumero(Integer numero) throws Exception{
		return ctrlMovimentacao.pegarMovimentacaoPeloNumero(numero);
	}
	
	@Override
	public List<MovimentacaoItem> listarItensDaMovimentacaoNumero(Integer numero) throws Exception{
		return ctrlMovimentacao.listarItensDaMovimentacaoNumero(numero);
	}

	
}
