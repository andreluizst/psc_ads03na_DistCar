package negocio;

import java.util.Calendar;
import java.util.List;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.Fabricante;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;
import dao.DAOAcessorio;
import dao.DAOCarro;
import dao.DAOItemSerieCarro;
import dao.DAOMarcaCarro;
import dao.DAOModeloCarro;
import dao.DAOVersaoCarro;
import dao.IDAOAcessorio;
import dao.IDAOCarro;
import dao.IDAOItemSerieCarro;
import dao.IDAOMarcaCarro;
import dao.IDAOModeloCarro;
import dao.IDAOVersaoCarro;
import erro.NegocioExceptionAcessorio;


public class ControladorCarro {
	
	private IDAOCarro carroDAO;
	private IDAOItemSerieCarro itemSerieCarroDAO;
	private IDAOVersaoCarro versaoCarroDAO;
	private IDAOModeloCarro modeloCarroDAO;
	private IDAOAcessorio acessorioDAO;
	private IDAOMarcaCarro marcaCarroDAO;

		public ControladorCarro(){
			super();
			this.carroDAO = new DAOCarro();
			this.marcaCarroDAO = new DAOMarcaCarro();
			this.modeloCarroDAO = new DAOModeloCarro();
			this.versaoCarroDAO = new DAOVersaoCarro();
			this.acessorioDAO = new DAOAcessorio();
			this.itemSerieCarroDAO= new DAOItemSerieCarro();
		
	}
	//Carro
	
	public boolean carroExiste(Carro carro) throws Exception{
		Carro obj = null;
		if (carro.getCodigo() == null){
			obj=null;
			return false;
		}
		obj = carroDAO.consultarPorId(carro.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == carro.getCodigo())
				return true;
		return false;
	}
	
	
	public void inserir(Carro carro) {
		carro.setDataUltimaAtualizacao(Calendar.getInstance());
		carroDAO.inserir(carro);
	}
	public void alterar(Carro carro) throws Exception {
		carroDAO.alterarSemTratamento(carro);
	}
	public void remover(Carro carro) {
		carroDAO.remover(carro);
	}
	public List<Carro> pesquisarCarroPorChassi(String chassi) {
		return carroDAO.pesquisarCarroPorChassi(chassi);
	}
	public List<Carro> listarCarros() {
		return carroDAO.consultarTodos();
	}
	
	public Carro pesquisarCarro(Integer codigo) {
		return carroDAO.consultarPorId(codigo);
	}
	
	public List<Carro> pesquisarCarros(Carro carro, Fabricante f, MarcaCarro m, ModeloCarro modelo,
			List<ItemSerieCarro> itensSelecionado, List<AcessorioCarro> acessoriosSelecionado) throws Exception {
		return carroDAO.consultar(carro, f, m, modelo,itensSelecionado,acessoriosSelecionado);
	}
	public Carro pegarCarroPeloChassi(String chassi) throws Exception{
		return carroDAO.pegarCarroPeloChassi(chassi);
	}
	
	//Modelo Carro
	
	public boolean carroExiste(ModeloCarro modelo) throws Exception{
		ModeloCarro obj = null;
		if (modelo.getCodigo() == null){
			obj=null;
			return false;
		}
		obj = modeloCarroDAO.consultarPorId(modelo.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == modelo.getCodigo())
				return true;
		return false;
	}
	
	public void inserir(ModeloCarro modelo) throws Exception {
		if(modeloCarroDAO.pesquisarModeloDescAno(modelo)!=null &&
				modeloCarroDAO.pesquisarModeloDescAno(modelo).getCodigo()!=modelo.getCodigo()){
			throw new Exception("Modelo já cadastrado");
		}
		modelo.setDataUltimaAtualizacao(Calendar.getInstance());
		modeloCarroDAO.inserir(modelo);
	}

	public void alterar(ModeloCarro modelo) throws Exception {
		modeloCarroDAO.alterarSemTratamento(modelo);
	}

	public void remover(ModeloCarro modelo) throws Exception {
		if(modeloCarroDAO.consultarPorId(modelo.getCodigo())==null){
			throw new Exception("Modelo não encontrado");
		}
		modeloCarroDAO.remover(modelo);
	}
	
	public List<ModeloCarro> listarModelos() {
		return modeloCarroDAO.consultarTodos();
	}
	

	public ModeloCarro pesquisarModeloCarro(Integer codigo) {
		return modeloCarroDAO.consultarPorId(codigo);
	}
	
	public List<ModeloCarro> pesquisarModeloCarros(ModeloCarro modelo) throws Exception {
		return modeloCarroDAO.consultar(modelo);
	}
	
	public List<ModeloCarro> pesquisarModeloPorMarca(Integer codigo) {
		return modeloCarroDAO.pesquisarModeloPorMarca(codigo);
	}

	
	//Versao de carro
	
	public boolean versaoExiste(VersaoCarro versao) throws Exception{
		VersaoCarro obj = null;
		if (versao.getCodigo() == null){
			obj=null;
			return false;
		}
		obj = versaoCarroDAO.consultarPorId(versao.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == versao.getCodigo())
				return true;
		return false;
	}
	
	public void inserir(VersaoCarro versao) throws Exception {
		if(versaoCarroDAO.pesquisarVersaoDesc(versao.getDescricao())!=null &&
				(versaoCarroDAO.pesquisarVersaoDesc(versao.getDescricao())).getCodigo()!=versao.getCodigo()){
			throw new Exception("Versão já cadastrada");
		}
		versao.setDataUltimaAtualizacao(Calendar.getInstance());
		versaoCarroDAO.inserir(versao);
	}
	public void alterar(VersaoCarro versaoCarro) {
		versaoCarroDAO.alterar(versaoCarro);
	}
	public void remover(VersaoCarro versaoCarro) {
		versaoCarroDAO.remover(versaoCarro);
	}
	public List<VersaoCarro> listarVersoes() {
		return versaoCarroDAO.consultarTodos();
	}
	
	public VersaoCarro pesquisarVersaoCodigo(Integer codigo){
		return versaoCarroDAO.consultarPorId(codigo);
	}
	
	public List<VersaoCarro> pesquisarVersoes(VersaoCarro versao, Fabricante f , MarcaCarro m) throws Exception {
		return versaoCarroDAO.consultar(versao,f,m);
	}
	
	public List<VersaoCarro> pesquisarVersaoPorModelo(Integer codigo){
		return versaoCarroDAO.pesquisarVersaoPorModelo(codigo);
	}
	
	// Marca
	
	public boolean marcaExiste(MarcaCarro marca) throws Exception{
		MarcaCarro obj = null;
		if (marca.getCodigo() == null){
			obj=null;
			return false;
		}
		obj = marcaCarroDAO.consultarPorId(marca.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == marca.getCodigo())
				return true;
		return false;
	}
	
	public void inserir(MarcaCarro marcaCarro) throws Exception {
		if(marcaCarroDAO.pesquisarMarcaDesc(marcaCarro)!=null &&
				marcaCarroDAO.pesquisarMarcaDesc(marcaCarro).getCodigo()!= marcaCarro.getCodigo()){
			throw new Exception("Marca já cadastrada");
		}
		else{
		marcaCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		marcaCarroDAO.inserir(marcaCarro);
		}
	}
	
	public void alterar(MarcaCarro marcaCarro) throws Exception {
		marcaCarroDAO.alterarSemTratamento(marcaCarro);
	}
	
	public void removerMarca(MarcaCarro marcaCarro) throws Exception {
		if(marcaCarroDAO.pesquisarMarcaPorFab(marcaCarro.getCodigo())==null){
			throw new Exception("Marca não cadastrada");
		}
		marcaCarroDAO.removerSemTratamento(marcaCarro);
	}

	public List<MarcaCarro> listarMarcas() {
		return marcaCarroDAO.consultarTodos();
	}


	public MarcaCarro pesquisarMarcaCodigo(Integer codigo) {
		return marcaCarroDAO.consultarPorId(codigo);
	}

	public List<MarcaCarro> pesquisarMarcas(MarcaCarro marcaCarro) throws Exception {
		return marcaCarroDAO.consultar(marcaCarro);
	}
	
	public List<MarcaCarro> pesquisarMarcaPorFabr(Integer codigo ){
		return marcaCarroDAO.pesquisarMarcaPorFab(codigo);
	}
	
	//Item de Serie
	
	public boolean itemExiste(ItemSerieCarro item) throws Exception{
		ItemSerieCarro obj = null;
		if (item.getCodigo() == null){
			obj=null;
			return false;
		}
		obj = itemSerieCarroDAO.consultarPorId(item.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == item.getCodigo())
				return true;
		return false;
	}
	
	public void inserir(ItemSerieCarro itemSerieCarro) throws Exception {
		if(itemSerieCarroDAO.pesquisarItemDescModelo(itemSerieCarro)!=null &&
				itemSerieCarroDAO.pesquisarItemDescModelo(itemSerieCarro).getCodigo()!=itemSerieCarro.getCodigo()){
			throw new Exception("Item série cadastrado!");
		}
		else{
			itemSerieCarro.setDataUltimaAtualizacao(Calendar.getInstance());
			itemSerieCarroDAO.inserir(itemSerieCarro);
		}	
	}
	
	public void alterar(ItemSerieCarro itemSerieCarro) throws Exception {
		itemSerieCarroDAO.alterarSemTratamento(itemSerieCarro);
	}
	public void remover(ItemSerieCarro itemSerieCarro) throws Exception {
		if(itemSerieCarroDAO.consultarPorId(itemSerieCarro.getCodigo())==null){
			throw new Exception("Item de série não cadastrado!");
		}
		{
			itemSerieCarroDAO.remover(itemSerieCarro);
		}
	}
	
	public List<ItemSerieCarro> listarItens(){
		return itemSerieCarroDAO.consultarTodos();
	}
	public ItemSerieCarro pesquisarItemCodigo(Integer codigo) {
		return itemSerieCarroDAO.consultarPorId(codigo);
	}
	
	public List<ItemSerieCarro> pesquisarItens(ItemSerieCarro itemSerieCarro) throws Exception{
		return itemSerieCarroDAO.consultar(itemSerieCarro);
	}
	
	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo){
		return itemSerieCarroDAO.pesquisarPorModelo(codigo);
	}
	
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo){
		return itemSerieCarroDAO.listarItensPorModelo(modelo);
	}
	
	public List<ItemSerieCarro> listarItensPorVersao(Integer codigo){
		return itemSerieCarroDAO.listarItensPorVersao(codigo);
	}
	public List<ItemSerieCarro> listarItensDistintos(){
		return itemSerieCarroDAO.listarItensDistintos();
	}
	//Acessório Carro

	public boolean acessorioExiste(AcessorioCarro acessorioCarro) throws Exception{
		AcessorioCarro obj = null;
		if (acessorioCarro.getCodigo() == null){
			obj=null;
			return false;
		}
		obj = acessorioDAO.consultarPorId(acessorioCarro.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == acessorioCarro.getCodigo())
				return true;
		return false;
	}
	
	public void inserir(AcessorioCarro acessorioCarro) throws Exception {
		if(acessorioDAO.pesquisarAcessorioDescModelo(acessorioCarro)!=null &&
				acessorioDAO.pesquisarAcessorioDescModelo(acessorioCarro).getCodigo()!=acessorioCarro.getCodigo()){
			throw new Exception("Acessório já cadastrado!");
		}
		else{
				acessorioCarro.setDataUltimaAtualizacao(Calendar.getInstance());
				acessorioDAO.inserir(acessorioCarro);
		}
	}
	public void removerAcessorio(AcessorioCarro acessorio) throws Exception {
		if(acessorioDAO.consultarPorId(acessorio.getCodigo())==null){
			throw new NegocioExceptionAcessorio("Acessório não cadastrado.");
		}
		else{
		acessorioDAO.remover(acessorio);
		}
	}

	public void alterarAcessorio(AcessorioCarro acessorio) throws Exception{
		if(acessorioDAO.pesquisarAcessorioDescModelo(acessorio)!=null &&
				acessorioDAO.pesquisarAcessorioDescModelo(acessorio).getCodigo()!=acessorio.getCodigo())
			throw new Exception("Acessório já cadastrado.");
		else{
		
		acessorioDAO.alterarSemTratamento(acessorio);
		}
	}
	public List<AcessorioCarro> listarAcessorios() {
		return acessorioDAO.consultarTodos();
	}

	public AcessorioCarro pesquisarAcessorioCarroCodigo(Integer codigo) {
		return acessorioDAO.consultarPorId(codigo);
	}

	public List<AcessorioCarro> pesquisarAcessorios(AcessorioCarro acessorio) throws Exception {
		return acessorioDAO.consultar(acessorio);
	}
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo){
		return acessorioDAO.listarAcessoriosPorModelo(modelo);
	}
	
	public List<AcessorioCarro> listarAceDiestintos(){
		return acessorioDAO.listarAceDistintos();
	}
}
