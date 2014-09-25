package classesBasicas;

import java.util.Date;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="codigo")
public class PessoaFisica extends Pessoa {
	
	@Column(length=11, nullable=false, unique=true)
	private String cpf;
	
	@Column(nullable=false)
	private String rg;
	
	@Column(nullable=false)
	private String orgaoExpedidor;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	
	// *** CONSTRUTORES ***
	
	public PessoaFisica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PessoaFisica(String nome, String cpf, String rg, String orgaoExpedidor) {
		super();
		super.setNome(nome);
		this.cpf = cpf;
		this.rg = rg;
		this.orgaoExpedidor = orgaoExpedidor;
	}
	
	
	
	// --- GETs e SETs ---
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", rg=" + rg + ", orgaoExpedidor="
				+ orgaoExpedidor + ", dataNascimento=" + dataNascimento + "]";
	}

	
}
