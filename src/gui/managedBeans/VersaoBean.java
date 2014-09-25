package gui.managedBeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import classesBasicas.AcessorioCarro;
import classesBasicas.Fabricante;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import classesBasicas.VersaoCarro;
import fachada.Fachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class VersaoBean {
	
	private VersaoCarro versaoCarro;
	private List<ModeloCarro> modelos;
	private List<VersaoCarro> listarVersoes;
	private VersaoCarro versaoSelecionada;
	private VersaoCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private List<AcessorioCarro> acessorios;
	private List<ItemSerieCarro> itens = new ArrayList<>();
    private List<Fabricante> fabricantes;
    private List<MarcaCarro> marcas;
    private double valorItens;
    private double valorAcessorios;
    private List<ItemSerieCarro> auxItens;
    private List<AcessorioCarro> auxAcessorios;
    private double subItens;
    private double subtAcessorios;
    private Fabricante f;
    private MarcaCarro m;
    
    
	public Fabricante getF() {
		return f;
	}
	public void setF(Fabricante f) {
		this.f = f;
	}
	public MarcaCarro getM() {
		return m;
	}
	public void setM(MarcaCarro m) {
		this.m = m;
	}
	public double getSubItens() {
		return subItens;
	}
	public void setSubItens(double subItens) {
		this.subItens = subItens;
	}
	public double getSubtAcessorios() {
		return subtAcessorios;
	}
	public void setSubtAcessorios(double subtAcessorios) {
		this.subtAcessorios = subtAcessorios;
	}
	public List<ItemSerieCarro> getAuxItens() {
		return auxItens;
	}
	public void setAuxItens(List<ItemSerieCarro> auxItens) {
		this.auxItens = auxItens;
	}

	public List<AcessorioCarro> getAuxAcessorios() {
		return auxAcessorios;
	}

	public void setAuxAcessorios(List<AcessorioCarro> auxAcessorios) {
		this.auxAcessorios = auxAcessorios;
	}

	public double getValorItens() {
		return valorItens;
	}

	public void setValorItens(double valorItens) {
		this.valorItens = valorItens;
	}

	public double getValorAcessorios() {
		return valorAcessorios;
	}

	public void setValorAcessorios(double valorAcessorios) {
		this.valorAcessorios = valorAcessorios;
	}
	public List<MarcaCarro> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaCarro> marcas) {
		this.marcas = marcas;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public VersaoCarro getVersaoCarro() {
		return versaoCarro;
	}

	public void setVersaoCarro(VersaoCarro versaoCarro) {
		this.versaoCarro = versaoCarro;
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
	}

	public List<VersaoCarro> getListarVersoes() {
		return listarVersoes;
	}

	public void setListarVersoes(List<VersaoCarro> listarVersoes) {
		this.listarVersoes = listarVersoes;
	}

	public VersaoCarro getVersaoSelecionada() {
		return versaoSelecionada;
	}

	public void setVersaoSelecionada(VersaoCarro versaoSelecionada) {
		this.versaoSelecionada = versaoSelecionada;
	}

	public VersaoCarro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(VersaoCarro situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

	public List<AcessorioCarro> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<AcessorioCarro> acessorios) {
		this.acessorios = acessorios;
	}

	public List<ItemSerieCarro> getItens() {
		return itens;
	}

	public void setItens(List<ItemSerieCarro> itens) {
		this.itens = itens;
	}

	
	@PostConstruct
	public void init() {
		versaoCarro = new VersaoCarro();
		listarVersoes();
		versaoSelecionada=null;
		listarFabricantes();
		marcas =null;
		situacaoSelecionada=null;
		modelos=null;
		auxItens=null;
		itens=null;
		acessorios=null;
		valorAcessorios=0;
		valorItens=0;
	}

	private List<VersaoCarro> listarVersoes() {  
      listarVersoes = Fachada.obterInstancia().listarVersoes();
      return listarVersoes;
      } 
	
	public String salvar() {
		
		versaoCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		
		try {	
			auxItens = versaoCarro.getItens();
			auxAcessorios= versaoCarro.getAcessorios();
			for(ItemSerieCarro i : auxItens){
				valorItens= valorItens+i.getValorItemSerie();
			}
			for(AcessorioCarro a : auxAcessorios){
				valorAcessorios = valorAcessorios +a.getValor();
			}
			
			versaoCarro.setValor(versaoCarro.getModeloCarro().getValor()+valorAcessorios+valorItens);
			Fachada.obterInstancia().salvarVersao(versaoCarro);
			MsgPrimeFaces.exibirMensagemInfomativa("Versão de carro salvo com sucesso!");
			init();
			itens=null;
			acessorios=null;
			return "versao";
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			versaoCarro.setValor(versaoCarro.getValor()-valorAcessorios-valorItens+subItens+subtAcessorios);
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
			listarVersoes();
		}
		return null;
	}
		
	public void listar(){
		listarVersoes = Fachada.obterInstancia().listarVersoes();
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
		if(versaoSelecionada==null){
			MsgPrimeFaces.exibirMensagemInfomativa("Selecione uma versão do carro para exclusão!");
		}
		else{
		Fachada.obterInstancia().removerVersao(
				Fachada.obterInstancia().pesquisarVersaoCodigo(versaoSelecionada.getCodigo()));
		MsgPrimeFaces.exibirMensagemInfomativa("Versão excluída com sucesso!");
		consulta();
		}
	}
	
	public void consulta(){
		listar();
	}
	
	    public String novo(){
	    	versaoCarro = new VersaoCarro();
	    	itens=null;
	    	acessorios=null;
			return "versao-prop";
		}           
	    
	    public String alterar(){
	    try{
	    	if (versaoSelecionada==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione uma versão de carro para alterar!");
	    		return "versao";
	    	}
	    	else{
	    	fabricantes= Fachada.obterInstancia().listarFabricantes();
	    	marcas=Fachada.obterInstancia().pesquisarMarcaPorFabr(versaoSelecionada.getModeloCarro().getMarcaCarro().getFabricante().getCodigo());
	    	modelos=Fachada.obterInstancia().pesquisarModeloPorMarca(versaoSelecionada.getModeloCarro().getMarcaCarro().getCodigo());
	    	acessorios= Fachada.obterInstancia().listarAcessoriosPorModelo(versaoSelecionada.getModeloCarro());
	    	versaoCarro = versaoSelecionada;
	    	versaoCarro.getModeloCarro().getMarcaCarro().setFabricante(versaoSelecionada.getModeloCarro().getMarcaCarro().getFabricante());
	    	versaoCarro.getModeloCarro().setMarcaCarro(versaoSelecionada.getModeloCarro().getMarcaCarro());
	    	versaoCarro.setModeloCarro(versaoSelecionada.getModeloCarro());
	    	itens = clonarLista(Fachada.obterInstancia().listarItensPorModelo(versaoSelecionada.getModeloCarro()));
	    	versaoCarro.setItens(clonarLista(versaoSelecionada.getItens()));
	    	versaoCarro.setAcessorios(clonarListaAces(versaoSelecionada.getAcessorios()));
	    	return "versao-prop";
	    	}
	    }
	    	catch (Exception e) {
				e.getStackTrace();
				init();
				listarVersoes();
			}
		return null;
	    }
	    
	    private List<ItemSerieCarro> clonarLista(List<ItemSerieCarro> colecao){
	    	List<ItemSerieCarro> colNova = new ArrayList<>();
	    	try {
				
				for(ItemSerieCarro it : colecao){
					colNova.add(it.clone());
				}
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return colNova;
	    }
	    
	    private List<AcessorioCarro> clonarListaAces(List<AcessorioCarro> colecao){
	    	List<AcessorioCarro> colNova = new ArrayList<>();
	    	try {
				
				for(AcessorioCarro ac : colecao){
					colNova.add(ac.clone());
				}
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return colNova;
	    }
	    public String cancelar(){
	    	init();
	    	return "versao";
	    }  
	    
	    public String consultar(){
	    	
		 	 try {
		 		 System.out.println(versaoCarro);
		 		 System.out.println(versaoCarro.getModeloCarro().getMarcaCarro());
				listarVersoes = Fachada.obterInstancia().consultarVersoes(versaoCarro,f,m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 versaoCarro= new VersaoCarro();  
			 return  "versao";
	    }
	    
	    public void listarAcessorios(){
	    	
	    	acessorios = Fachada.obterInstancia().listarAcessoriosPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoCarro.getModeloCarro().getCodigo()));
	    }
	    
	    public void filtrarItens(ValueChangeEvent evento){
	    		ModeloCarro mo = new ModeloCarro();
	    		mo = (ModeloCarro) evento.getNewValue();
	    		if(mo!=null){
	    		versaoCarro.setModeloCarro(mo);
	    		versaoCarro.setValor(mo.getValor());
				itens = Fachada.obterInstancia().listarItensPorModelo(mo);
				acessorios = Fachada.obterInstancia().listarAcessoriosPorModelo(mo);
	    		}
	    		else{
	    		itens=null;
	    		acessorios=null;
				versaoCarro.setValor(0);
				}
	    }
	    
	    public void somar(){
	    	//AcessorioCarro a = (AcessorioCarro)evento.getNewValue();
	    	try{
	    		double soma = 0;
	    		if (versaoCarro.getAcessorios() != null){
	    			for(AcessorioCarro ac : versaoCarro.getAcessorios()){//acessoriosSelecionados){
	    				soma+= ac.getValor();
	    			}
	    		}
	    		if (versaoCarro.getItens() != null){
	    			for(ItemSerieCarro ic : versaoCarro.getItens()){//itensDeSerieSelecionados){
	    				soma+= ic.getValorItemSerie();
	    			}
	    		}
	    		soma+= versaoCarro.getModeloCarro().getValor();// + a.getValor();
	    		versaoCarro.setValor(soma);//+valorAcessorios+valorItens);
	    	}catch(Exception ex){
	    		MsgPrimeFaces.exibirMensagemDeErro("Não é possível atualizar o valor da versão do carro!");
	    	}
	    }
		
		  public void filtrarMarca(ValueChangeEvent evento){
		  
		    		Fabricante fab = new Fabricante();
		    		fab = (Fabricante) evento.getNewValue();
		    		if(fab!=null && fab.getCodigo()!=-1 ){
					marcas = Fachada.obterInstancia().pesquisarMarcaPorFabr(fab.getCodigo());
		    		}
					else{
					marcas=null;
		    		modelos=null;
		    		marcas=null;
		    		acessorios=null;
		    		itens=null;
				}
		    }
		    
		  public void filtrarModelo(ValueChangeEvent evento){
	
		    		MarcaCarro ma = new MarcaCarro();
		    		ma = (MarcaCarro) evento.getNewValue();
		    		if(ma!=null){
					modelos = Fachada.obterInstancia().pesquisarModeloPorMarca(ma.getCodigo());		
		    		}
		    		else{
		    		modelos=null;
					itens=null;
					acessorios=null;
				}
		    }
		
}
