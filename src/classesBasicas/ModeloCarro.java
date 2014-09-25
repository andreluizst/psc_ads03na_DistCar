package classesBasicas;


import javax.persistence.*;


@Entity
public class ModeloCarro extends ObjetoGeral {
	
	@Column(length=100, nullable=false)
	private String descricao;
	@Column(length=10, nullable=false)
	private Integer ano;
	@ManyToOne(fetch=FetchType.EAGER)
	private MarcaCarro marcaCarro;
	private Double valor;
	
	
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public MarcaCarro getMarcaCarro() {
		if(marcaCarro == null){
	        marcaCarro = new MarcaCarro();
	    }
		return marcaCarro;
	}
	public void setMarcaCarro(MarcaCarro marcaCarro) {
		this.marcaCarro = marcaCarro;
	}
	
	public ModeloCarro() {
		super();
		this.marcaCarro = new MarcaCarro();
	}
	public ModeloCarro(String descricao, Integer ano, MarcaCarro marcaCarro,
			Double valor) {
		super();
		this.descricao = descricao;
		this.ano = ano;
		this.marcaCarro = marcaCarro;
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "ModeloCarro [descricao=" + descricao + ", ano=" + ano
				+ ", marcaCarro=" + marcaCarro + ", valor=" + valor + "]";
	}

	
}
