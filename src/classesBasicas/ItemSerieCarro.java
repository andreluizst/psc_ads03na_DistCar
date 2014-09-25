package classesBasicas;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class ItemSerieCarro extends ObjetoGeral implements Cloneable{

	private String descricao;
	private double valorItemSerie;
	@ManyToOne
	private ModeloCarro modelo;
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public ItemSerieCarro clone() throws CloneNotSupportedException {
		return new ItemSerieCarro(getCodigo(), getDataUltimaAtualizacao(), getSituacao(), descricao, valorItemSerie, modelo);
	}
	
	public ItemSerieCarro(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao, String descricao, double valorItemSerie,
			ModeloCarro modelo) {
		super(codigo, dataUltimaAtualizacao, situacao);
		this.descricao = descricao;
		this.valorItemSerie = valorItemSerie;
		this.modelo = modelo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorItemSerie() {
		return valorItemSerie;
	}
	public void setValorItemSerie(double valorItemSerie) {
		this.valorItemSerie = valorItemSerie;
	}
	public ModeloCarro getModelo() {
		//if(modelo == null){
	      //  modelo = new ModeloCarro();
	    //}
		return modelo;
	}
	public void setModelo(ModeloCarro modelo) {
		this.modelo = modelo;
	}
	@Override
	public String toString() {
		return descricao +" "+ valorItemSerie;
	}
	
	public ItemSerieCarro(String descricao, double valorItemSerie,
			ModeloCarro modelo) {
		super();
		this.descricao = descricao;
		this.valorItemSerie = valorItemSerie;
		this.modelo = modelo;
	}
	public ItemSerieCarro() {
		super();
		//this.modelo = new ModeloCarro();
	}
	
}
