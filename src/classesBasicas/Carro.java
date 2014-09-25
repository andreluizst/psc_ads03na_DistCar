package classesBasicas;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Carro extends ObjetoGeral {
	
	@Transient
	public static final int TO_STRING_PADRAO = 0;
	@Transient
	public static final int TO_STRING_DESCRICAO_PARA_LISTA = 1;
	
	@Transient
	private int comportamentoToString = TO_STRING_PADRAO;
	
	@Column(unique=true)
	private String chassi;
	@Column(length=50, nullable=false)
	private String cor;
	@Column(length=10, nullable=false)
	private Integer anoFabricacao;
	@ManyToOne(fetch=FetchType.EAGER)
	private VersaoCarro versao;
	private double valorCarro;
	@ManyToOne
	private Centro centro;
	@Enumerated(EnumType.STRING)
	private Status status;

	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public VersaoCarro getVersao() {
		if(versao == null){
	        versao = new VersaoCarro();
	    }
		return versao;
	}
	public void setVersao(VersaoCarro versao) {
		this.versao = versao;
	}
	public double getValorCarro() {
		return valorCarro;
	}
	
	public void setValorCarro(double valorCarro) {
		this.valorCarro = valorCarro;
	}
	
	public int getComportamentoToString() {
		return comportamentoToString;
	}
	public void setComportamentoToString(int comportamentoToString) {
		this.comportamentoToString = comportamentoToString;
	}
	
	
	public Carro(String chassi, String cor, Integer anoFabricacao,
			VersaoCarro versao, double valorCarro, Centro centro, Status status) {
		super();
		this.chassi = chassi;
		this.cor = cor;
		this.anoFabricacao = anoFabricacao;
		this.versao = versao;
		this.valorCarro = valorCarro;
		this.centro = centro;
		this.status = status;
	}
	public Carro() {
		super();
		versao = new VersaoCarro();
	}
	
	
	public Carro(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(codigo, dataUltimaAtualizacao, situacao);
		versao = new VersaoCarro();
	}
	@Override
	public String toString() {
		String str = "Carro [chassi=" + chassi + ", cor=" + cor + ", anoFabricacao="
				+ anoFabricacao + ", versao=" + versao + ", valorCarro="
				+ valorCarro + ", centro=" + centro + ", status=" + status
				+ "]";
		if (comportamentoToString == Carro.TO_STRING_DESCRICAO_PARA_LISTA){
			//int aux = versao.getComportamentoToString();
			//versao.setComportamentoToString(VersaoCarro.TO_STRING_DESCRICAO_PARA_LISTA);
			//str = versao + ", " + cor + ", fabricação " + anoFabricacao + ", chassi " + chassi;
			str = versao.getModeloCarro().getDescricao() + " " + versao.getDescricao() + " "
					+ versao.getModeloCarro().getAno() + "/" + anoFabricacao + ", "
					+ cor + ", chassi " + chassi + " - " + status;
			//versao.setComportamentoToString(aux);
		}
		return str;
	}
	
}
