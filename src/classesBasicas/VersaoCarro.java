package classesBasicas;


import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class VersaoCarro extends ObjetoGeral {
	
	@Transient
	public static final int TO_STRING_PADRAO = 0;
	@Transient
	public static final int TO_STRING_DESCRICAO_PARA_LISTA = 1;
	
	@Transient
	private int comportamentoToString = TO_STRING_PADRAO;
	
	private double valor;
	@Column(unique=true)
	private String descricao;
	@ManyToOne(fetch = FetchType.EAGER)
	private ModeloCarro modeloCarro;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@Fetch(FetchMode.SUBSELECT)
	private List<AcessorioCarro> acessorios;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<ItemSerieCarro> itens;
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ModeloCarro getModeloCarro() {
		if(modeloCarro == null){
	        modeloCarro = new ModeloCarro();
	    }
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
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
	
	public int getComportamentoToString() {
		return comportamentoToString;
	}
	
	public void setComportamentoToString(int comportamentoToString) {
		this.comportamentoToString = comportamentoToString;
	}
	
	
	@Override
	public String toString() {
		String strAcessorios = "";
		if (getAcessorios() != null && getAcessorios().size() > 0)
			strAcessorios = getAcessorios().toString();
		String str = "VersaoCarro [valor=" + valor + ", descricao=" + descricao
				+ ", modeloCarro=" + modeloCarro + ", acessorios=" + strAcessorios
				+ ", itens=" + itens + "]";
		if (comportamentoToString == VersaoCarro.TO_STRING_DESCRICAO_PARA_LISTA)
			str = modeloCarro.getDescricao() + " " + modeloCarro.getAno() 
				+ " " + getDescricao() + strAcessorios + " + " + itens;
		return str;
	}
	
	public VersaoCarro() {
		super();
		modeloCarro = new ModeloCarro();
	}
	public VersaoCarro(double valor, String descricao, ModeloCarro modeloCarro,
			List<AcessorioCarro> acessorios, List<ItemSerieCarro> itens) {
		super();
		this.valor = valor;
		this.descricao = descricao;
		this.modeloCarro = modeloCarro;
		this.acessorios = acessorios;
		this.itens = itens;
	}
	
	
}
