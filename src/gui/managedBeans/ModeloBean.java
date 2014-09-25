package gui.managedBeans;

import fachada.Fachada;
import gui.MsgPrimeFaces;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;


@ManagedBean
@SessionScoped
public class ModeloBean {

	private ModeloCarro modelo;
	private List<ModeloCarro> modelos;
	private ModeloCarro modeloSelecionado;
	private ModeloCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private List<MarcaCarro> marcas;
    
	
	public List<MarcaCarro> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaCarro> marcas) {
		this.marcas = marcas;
	}

	public ModeloCarro getModelo() {
		return modelo;
	}

	public void setModelo(ModeloCarro modelo) {
		this.modelo = modelo;
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
	}

	public ModeloCarro getModeloSelecionado() {
		return modeloSelecionado;
	}

	public void setModeloSelecionado(ModeloCarro modeloSelecionado) {
		this.modeloSelecionado = modeloSelecionado;
	}

	public ModeloCarro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(ModeloCarro situacaoSelecionada) {
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
		modelo = new ModeloCarro();
		modelo.setValor(0.0);
		listarModelo();
		listarMarcas();
	}

	public void novo(ActionEvent actionEvent) {
		init();
	}
	
	public String salvar(){
		modelo.setDataUltimaAtualizacao(Calendar.getInstance());
		System.out.println(modelo);
		try {
			if (modelo.getCodigo() == null || modelo.getCodigo() == 0)
				modelo.setCodigo(null);
			Fachada.obterInstancia().salvarModeloCarro(modelo);
			MsgPrimeFaces.exibirMensagemInfomativa("Versão de carro salvo com sucesso!");
			init();
			return "modelo";
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
			init();
		}
		return null;
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
	
	public List<MarcaCarro> listarMarcas(){
		return marcas = Fachada.obterInstancia().listarMarcasCarros();
	}

	public void excluir() {
		if (modeloSelecionado == null) {
			MsgPrimeFaces
					.exibirMensagemInfomativa("Selecione um modelo de carro para exclusão!");
		} else {
			try {
				Fachada.obterInstancia().removerModeloCarro(
						Fachada.obterInstancia().pesquisarModelosCarroCodigo(modeloSelecionado.getCodigo()));
				MsgPrimeFaces
						.exibirMensagemInfomativa("Modelo excluído com sucesso!");
				consulta();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
				consulta();
			}
		}
	}

	public void consulta(){
		listarModelo();
	}
	
	    public String novo(){
	    	init();
			return "modelo-prop";
		}           
	    
	    public String alterar(){
	    	if (modeloSelecionado==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione uma modelo de carro para alterar!");
	    		return "modelo";
	    	}
	    	else{
	    	modelo = Fachada.obterInstancia().pesquisarModelosCarroCodigo(modeloSelecionado.getCodigo());
	    	modelo.setMarcaCarro(Fachada.obterInstancia().pesquisarMarcasCarroCodigo(modeloSelecionado.getMarcaCarro().getCodigo()));
	    	return "modelo-prop";
	    	}
	    }
	    
	    public String cancelar(){
	    	modelo = new ModeloCarro();
	    	modeloSelecionado = null;
	    	return "modelo";
	    }  
	    
	    public String consultar(){
		 	 try {
				modelos = Fachada.obterInstancia().consultarModelosCarros(modelo);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			 modelo= new ModeloCarro();  
			 return  "modelo";
	    }
}
