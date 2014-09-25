package classesBasicas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;




@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pessoa  extends ObjetoGeral {
	
	@Column(length=130, nullable=false)
	private String nome;
	
	//A classe Endereco é "@Embeddable"
	private Endereco endereco;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> telefones;
	
	private String email;
	
	
	public Pessoa() {
		super();
		endereco = new Endereco();
		telefones = new ArrayList<String>();
	}
	
	public Pessoa(Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(dataUltimaAtualizacao, situacao);
		endereco = new Endereco();
		telefones = new ArrayList<String>();
	}
	public Pessoa(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(codigo, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}
	
	public Pessoa(String nome, Endereco endereco, List<String> telefones,
			String email) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefones = telefones;
		this.email = email;
	}
	
	public Pessoa(String nome, Endereco endereco, List<String> telefones,
			String email, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefones = telefones;
		this.email = email;
		super.setDataUltimaAtualizacao(dataUltimaAtualizacao);
		super.setSituacao(situacao);
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<String> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", endereco=" + endereco
				+ ", telefones=" + telefones + ", email=" + email + "]";
	}	
	
	
}
