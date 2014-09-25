package classesBasicas;

import java.util.Calendar;

import javax.persistence.*;

@Entity
public class Centro extends ObjetoGeral {
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codPJ", insertable=true, updatable=true)
	private PessoaJuridica dadosPJ;
	
	@Column(length=40, nullable=false, unique=true)
	private String alias;

	private Integer capacidadeArmazenamento;
	
	@Enumerated(EnumType.STRING)
	private TipoCentro tipoCentro;
	
	
	
	public Centro() {
		super();
	}

	public Centro(PessoaJuridica dadosPJ, String alias,
			Integer capacidadeArmazenamento, TipoCentro tipoCentro) {
		super();
		this.dadosPJ = dadosPJ;
		this.alias = alias;
		this.capacidadeArmazenamento = capacidadeArmazenamento;
		this.tipoCentro = tipoCentro;
	}
	
	public Centro(PessoaJuridica dadosPJ, String alias, Integer capacidadeArmazenamento, 
			TipoCentro tipoCentro, Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(dataUltimaAtualizacao, situacao);
		this.dadosPJ = dadosPJ;
		dadosPJ.setDataUltimaAtualizacao(dataUltimaAtualizacao);
		dadosPJ.setSituacao(situacao);
		this.alias = alias;
		this.capacidadeArmazenamento = capacidadeArmazenamento;
		this.tipoCentro = tipoCentro;
	}
	
	
	public PessoaJuridica getDadosPJ() {
		return dadosPJ;
	}

	public void setDadosPJ(PessoaJuridica dadosPJ) {
		this.dadosPJ = dadosPJ;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getCapacidadeArmazenamento() {
		return capacidadeArmazenamento;
	}

	public void setCapacidadeArmazenamento(Integer capacidadeArmazenamento) {
		this.capacidadeArmazenamento = capacidadeArmazenamento;
	}

	public TipoCentro getTipoCentro() {
		return tipoCentro;
	}

	public void setTipoCentro(TipoCentro tipoCentro) {
		this.tipoCentro = tipoCentro;
	}

	@Override
	public String toString() {
		return alias + " [" + tipoCentro + "]";
	}
	
	
}
