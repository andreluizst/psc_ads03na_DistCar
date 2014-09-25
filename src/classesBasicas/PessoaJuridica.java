package classesBasicas;

import java.util.Date;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="codigo")
public class PessoaJuridica extends Pessoa {
	@Column(length=14, nullable=false, unique=true)
	private String cnpj;
	
	@Column(nullable=false)
	private String inscricaoEstadual;
	
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;
	
	
	//**** COSTRUTORES *****
	public PessoaJuridica() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PessoaJuridica(String nome, String cnpj, String inscricaoEstadual,
			Date dataAbertura) {
		this(nome, cnpj, inscricaoEstadual, dataAbertura, null);
	}
	
	public PessoaJuridica(String nome, String cnpj, String inscricaoEstadual,
			Date dataAbertura, Endereco endereco) {
		super();
		super.setNome(nome);
		super.setEndereco(endereco);
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.dataAbertura = dataAbertura;
	}


	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstatdual) {
		this.inscricaoEstadual = inscricaoEstatdual;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	@Override
	public String toString() {
		return "PessoaJuridica [cnpj=" + cnpj + ", inscricaoEstadual="
				+ inscricaoEstadual + ", dataAbertura=" + dataAbertura + "]";
	}

}
