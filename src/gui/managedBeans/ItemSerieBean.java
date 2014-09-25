package gui.managedBeans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import fachada.Fachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class ItemSerieBean {

	private ItemSerieCarro itemSerieCarro;
	private List<ModeloCarro> modelos;
	public String mensagem;
	private Date data;
	private List<ItemSerieCarro> listaItens;
	private ItemSerieCarro itemSelecionado;
	private ItemSerieCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private int codigoSelecionado;
	
	
	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
	}

	public ItemSerieCarro getItemSerieCarro() {
		return itemSerieCarro;
	}

	public void setItemSerieCarro(ItemSerieCarro itemSerieCarro) {
		this.itemSerieCarro = itemSerieCarro;
	}

	public List<ModeloCarro> getModelo() {
		return modelos;
	}

	public void setModelo(List<ModeloCarro> modelo) {
		this.modelos = modelo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ItemSerieCarro> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ItemSerieCarro> listaItens) {
		this.listaItens = listaItens;
	}

	public ItemSerieCarro getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(ItemSerieCarro itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public ItemSerieCarro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(ItemSerieCarro situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

	public int getCodigoSelecionado() {
		return codigoSelecionado;
	}

	public void setCodigoSelecionado(int codigoSelecionado) {
		this.codigoSelecionado = codigoSelecionado;
	}

	@PostConstruct
	public void init() {
		itemSerieCarro = new ItemSerieCarro();
		itemSerieCarro.setModelo(new ModeloCarro());
		listarModelo();
		listarItens();
		listarModelo();
	}

	private List<ItemSerieCarro> listarItens() {  
      listaItens = Fachada.obterInstancia().listarItens();
      return listaItens;
      } 
	
	public String salvar(){
		
		itemSerieCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		try {
			Fachada.obterInstancia().salvarItemSerie(itemSerieCarro);
			MsgPrimeFaces.exibirMensagemInfomativa("Item Séria salvo com sucesso!");
			init();
			return "item";
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
			listarItens();
		}
		return null;
	}
		
	public void listar(){
		listaItens = Fachada.obterInstancia().listarItens();
	}
		
	public List<ModeloCarro> listarModelo() {
		try {
			modelos = Fachada.obterInstancia().listarModelosCarros();
			return modelos;
		} catch (Exception ex) {
			mensagem = ex.getMessage();
		}
		return modelos;
	}


	public void excluir(){
		if(itemSelecionado==null){
			MsgPrimeFaces.exibirMensagemInfomativa("Selecione um item para exclusão!");
		}
		else{
		try {
			Fachada.obterInstancia().removerItem(
					Fachada.obterInstancia().pesquisarItemCodigo(itemSelecionado.getCodigo()));
			MsgPrimeFaces.exibirMensagemInfomativa("Item Excluído com sucesso!");
			consulta();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MsgPrimeFaces.exibirMensagemDeErro("Item não encontrado!");
		}
		
		}
	}
	
	public void consulta(){
		listaItens = Fachada.obterInstancia().listarItens();
	}
	
		  /* public void onEdit(RowEditEvent event) throws Exception {  
		   
		   ItemSerieCarro i = (ItemSerieCarro) event.getObject();
		   itemSerieCarro = Fachada.obterInstancia().pesquisarItem(i.getCodigo());
		   Fachada.obterInstancia().salvarItemSerie(itemSerieCarro);
      
	    }  
	      
	    public void onCancel(RowEditEvent event) {  
	        FacesMessage msg = new FacesMessage("Car Cancelled", "Cancelado");    
	  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	    } */
	    public String novo(){
	    	init();
			return "item-prop";
		}           
	    
	    public String alterar(){
	    	if (itemSelecionado==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione um item para alterar!");
	    		return "item";
	    	}
	    	else{
	    	itemSerieCarro = Fachada.obterInstancia().pesquisarItemCodigo(itemSelecionado.getCodigo());
	    	//itemSerieCarro.setModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(itemSelecionado.getModelo().getCodigo()));
	    	return "item-prop";
	    	}
	    }
	    
	    public String cancelar(){
	    	itemSerieCarro = new ItemSerieCarro();
	    	itemSelecionado = null;
	    	return "item";
	    }  
	    
	    public String consultar(){
	  
		 	 try {
				listaItens = Fachada.obterInstancia().consultarItens(itemSerieCarro);
				 itemSerieCarro = new ItemSerieCarro();  
				 return  "item";
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				MsgPrimeFaces.exibirMensagemInfomativa(ex.getMessage());
			}
			return null;
	    }
	    
}
