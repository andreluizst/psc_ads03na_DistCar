package classesBasicas;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UnidadeFederativa extends ObjetoGeral {

	@Column(length=30, nullable=false)
	private String nome;
	
	@Column(length=2, nullable=false)
	private String sigla;
	
	
	
	public UnidadeFederativa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UnidadeFederativa(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla.toUpperCase();
	}
	
	public UnidadeFederativa(String nome, String sigla, Calendar dataUltimaAtualizacao, Situacao situacao){
		this(nome, sigla);
		setDataUltimaAtualizacao(dataUltimaAtualizacao);
		setSituacao(situacao);
	}
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla.toUpperCase();
	}
	public void setSigla(String sigla) {
		this.sigla = sigla.toUpperCase();
	}


	@Override
	public String toString() {
		return   sigla + " - " + nome;
	}
	
}
