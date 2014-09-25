package gui.managedBeans;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.Centro;
import classesBasicas.Fabricante;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import classesBasicas.Status;
import classesBasicas.VersaoCarro;
import fachada.Fachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class CarroBean {


	private Carro carro;
	private List<Carro> carros;
	private Carro carroSelecionado;
	private Carro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private List<Fabricante> fabricantes;
	private List<MarcaCarro> marcas;
	private List<ModeloCarro> modelos;
	private List<VersaoCarro> versoes;
	private List<ItemSerieCarro> itens;
	private List<AcessorioCarro> acessorios;
	private Carro statusSelecionada;
	private Status[] status = Status.values();
	private List<Centro> centros;
	private Fabricante fab;
	private MarcaCarro marca;
	private ModeloCarro modelo;
	private List<ItemSerieCarro> itensSelecionado;
	private List<AcessorioCarro> acessoriosSelecionado;
	
	
	
	public List<AcessorioCarro> getAcessoriosSelecionado() {
		return acessoriosSelecionado;
	}

	public void setAcessoriosSelecionado(List<AcessorioCarro> acessoriosSelecionado) {
		this.acessoriosSelecionado = acessoriosSelecionado;
	}

	public List<ItemSerieCarro> getItensSelecionado() {
		return itensSelecionado;
	}

	public void setItensSelecionado(List<ItemSerieCarro> itensSelecionado) {
		this.itensSelecionado = itensSelecionado;
	}

	public Fabricante getFab() {
		return fab;
	}

	public void setFab(Fabricante fab) {
		this.fab = fab;
	}

	public MarcaCarro getMarca() {
		return marca;
	}

	public void setMarca(MarcaCarro marca) {
		this.marca = marca;
	}

	public ModeloCarro getModelo() {
		return modelo;
	}

	public void setModelo(ModeloCarro modelo) {
		this.modelo = modelo;
	}

	public List<Centro> getCentros() {
		return centros;
	}

	public void setCentros(List<Centro> centros) {
		this.centros = centros;
	}

	public Carro getStatusSelecionada() {
		return statusSelecionada;
	}

	public void setStatusSelecionada(Carro statusSelecionada) {
		this.statusSelecionada = statusSelecionada;
	}

	public Status[] getStatus() {
		return status;
	}

	public void setStatus(Status[] status) {
		this.status = status;
	}

	public List<ItemSerieCarro> getItens() {
		return itens;
	}

	public void setItens(List<ItemSerieCarro> itens) {
		this.itens = itens;
	}

	public List<AcessorioCarro> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<AcessorioCarro> acessorios) {
		this.acessorios = acessorios;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<MarcaCarro> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaCarro> marcas) {
		this.marcas = marcas;
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
	}

	public List<VersaoCarro> getVersoes() {
		return versoes;
	}

	public void setVersoes(List<VersaoCarro> versoes) {
		this.versoes = versoes;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public Carro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Carro situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

	@PostConstruct
	public void init() {
		carro = new Carro();
		listarCarros();
		listarFabricantes();
		listarCentros();
		listarItensDistintos();
		listarAceDistintos();
		itensSelecionado=null;
		carroSelecionado=null;
		situacaoSelecionada=null;
		statusSelecionada=null;
		modelos=null;
		marcas=null;
		versoes=null;
		fab=null;
		modelo=null;
		marca=null;
		
	}

	private List<Carro> listarCarros() {  
      carros = Fachada.obterInstancia().listarCarros();
      return carros;
      } 
	private List<Centro>listarCentros(){
		try {
			centros = Fachada.obterInstancia().listarCentros();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return centros;
	}
	
	private List<Fabricante> listarFabricantes(){  
	      try {
			fabricantes = Fachada.obterInstancia().listarFabricantes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return fabricantes;
	} 
	
	public List<ItemSerieCarro> listarItensDistintos(){
		return itens = Fachada.obterInstancia().listarItensdistintos();
	}
	public List<AcessorioCarro> listarAceDistintos(){
		return acessorios = Fachada.obterInstancia().listarAceDiestintos();
	}
	
	public String salvar() throws Exception {
		
		Fachada.obterInstancia().salvarCarro(carro);
		MsgPrimeFaces.exibirMensagemInfomativa("Carro salvo com sucesso!");
		init();
		return "carro";
	
	}
		

	public List<ModeloCarro> listarModelo() {
		try {
			modelos = Fachada.obterInstancia().listarModelosCarros();
			return modelos;
		} catch (Exception ex) {
			MsgPrimeFaces.exibirMensagemInfomativa( ex.getMessage());
		}
		return modelos;
	}


	public void excluir(){
		if(carroSelecionado==null){
			MsgPrimeFaces.exibirMensagemInfomativa("Selecione um carro para exclusão!");
		}
		else{
		Fachada.obterInstancia().removerCarro(Fachada.obterInstancia().pesquisarCarroCodigo(carroSelecionado.getCodigo()));
		MsgPrimeFaces.exibirMensagemInfomativa("Acessório Excluído com sucesso!");
		consulta();
		}
	}
	
	public void consulta(){
		listarCarros();
	}
	
	    public String novo(){
	    	carro = new Carro();
	    	itens=null;
	    	acessorios=null;
			return "carro-prop";
		}           
	    
	    public String alterar(){
	    	try {
	    		if (carroSelecionado==null){
		    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione um acessório para alterar!");
		    		return "acessorio";
		    	}
		    	else{
		    	
		    	fabricantes=Fachada.obterInstancia().listarFabricantes();
		    	marcas=Fachada.obterInstancia().pesquisarMarcaPorFabr(carroSelecionado.getVersao().getModeloCarro().getMarcaCarro().getFabricante().getCodigo());
		    	modelos=Fachada.obterInstancia().pesquisarModeloPorMarca(carroSelecionado.getVersao().getModeloCarro().getMarcaCarro().getCodigo());
		    	versoes=Fachada.obterInstancia().pesquisarVersaoPorModelo(carroSelecionado.getVersao().getModeloCarro().getCodigo());
		    	carro = carroSelecionado;
		    	itens=carroSelecionado.getVersao().getItens();
		    	acessorios=carroSelecionado.getVersao().getAcessorios();
		    	
		    	
		    	return "carro-prop";
		    	}
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
				init();
				listarCarros();
			}
			return null;
	    	
	    }
	    
	    public String cancelar(){
	    	init();
	    	return "carro";
	    }  
	    
	    public String consultar(){
	    	
		 	 try {
		 		
				carros = Fachada.obterInstancia().consultarCarros(carro, fab, marca, modelo, itensSelecionado, acessoriosSelecionado);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 	 carro = new Carro();
		 	 itensSelecionado=null;
		 	 acessoriosSelecionado=null;
		 	 fab=null;
		 	 modelo=null;
		 	 marca=null;
		 	 modelos=null;
		 	 versoes=null;
		 	 marcas=null;
			 return  "carro";
	    }
	    

	    public void filtrarMarca(ValueChangeEvent evento){
			  
    		Fabricante fab = new Fabricante();
    		fab = (Fabricante) evento.getNewValue();
    		if(fab!=null && fab.getCodigo()!=-1 ){
			marcas = Fachada.obterInstancia().pesquisarMarcaPorFabr(fab.getCodigo());
    		}
			else{
			init();
		}
    }
	    
	    public void filtrarModelo(ValueChangeEvent evento){
	    	
    		MarcaCarro ma = new MarcaCarro();
    		ma = (MarcaCarro) evento.getNewValue();
    		if(ma!=null){
			modelos = Fachada.obterInstancia().pesquisarModeloPorMarca(ma.getCodigo());		
    		}
    		else{
    		carro = new Carro();
    		modelo=null;
    		modelos=null;
    		versoes=null;
			itens=null;
			acessorios=null;
		}
    }
	    
	    public void filtrarVersao(ValueChangeEvent evento){
	    	
	    	
	    		ModeloCarro mc = new ModeloCarro();
	    		mc = (ModeloCarro) evento.getNewValue();
	    		if(mc!=null)
				versoes = Fachada.obterInstancia().pesquisarVersaoPorModelo(mc.getCodigo());
	    		else{
				carro = new Carro();
				versoes=null;
				itens=null;
				acessorios=null;
			}
	    }
	    	public void filtrarItensAces(ValueChangeEvent evento){

	    		VersaoCarro vc = new VersaoCarro();
	    		vc = (VersaoCarro) evento.getNewValue();
	    		if(vc!=null){
				itens=Fachada.obterInstancia().pesquisarVersaoCodigo(vc.getCodigo()).getItens();
				acessorios =Fachada.obterInstancia().pesquisarVersaoCodigo(vc.getCodigo()).getAcessorios();
				carro.setValorCarro(vc.getValor());
	    		}
	    		else{
			
				carro= new Carro();
			}
	    }
}

