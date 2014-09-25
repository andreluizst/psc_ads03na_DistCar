package classesBasicas;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cliente /*extends ObjetoGeral*/{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(15) default 'PESSOA_FISICA'", nullable=false)
	private TipoCliente tipoCliente;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codPessoa", insertable=true, updatable=true, nullable=false)
	private Pessoa dadosPessoa;
	
	public Cliente() {
		super();
		tipoCliente = TipoCliente.PESSOA_FISICA;
		dadosPessoa = new PessoaFisica();
	}

	public Cliente(Calendar dataUltimaAtualizacao, Situacao situacao) {
		//super(dataUltimaAtualizacao, situacao);
		this();
		dadosPessoa.setDataUltimaAtualizacao(dataUltimaAtualizacao);
		dadosPessoa.setSituacao(situacao);
	}

	public Cliente(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		//super(codigo, dataUltimaAtualizacao, situacao);
		this(dataUltimaAtualizacao, situacao);
		this.codigo = codigo;
		//dadosPessoa.setCodigo(codigo);
	}

	public Cliente(String nome, Endereco endereco,
			List<String> telefones, String email, TipoCliente tipoCliente,
			Calendar dataUltimaAtualizacao, Situacao situacao) {
		//super(nome, endereco, telefones, email, dataUltimaAtualizacao, situacao);
		this.dadosPessoa.setNome(nome);
		this.dadosPessoa.setEndereco(endereco);
		this.dadosPessoa.setTelefones(telefones);
		this.dadosPessoa.setEmail(email);
		this.dadosPessoa.setDataUltimaAtualizacao(dataUltimaAtualizacao);
		this.dadosPessoa.setSituacao(situacao);
		this.tipoCliente = tipoCliente;
		//setDataUltimaAtualizacao(dataUltimaAtualizacao);
		//setSituacao(situacao);
	}

	public Cliente(String nome, Endereco endereco,
			List<String> telefones, String email, TipoCliente tipoCliente) {
		//super(nome, endereco, telefones, email);
		this.dadosPessoa.setNome(nome);
		this.dadosPessoa.setEndereco(endereco);
		this.dadosPessoa.setTelefones(telefones);
		this.dadosPessoa.setEmail(email);
		this.tipoCliente = tipoCliente;
	}
	
	
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Pessoa getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(Pessoa dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}
	
	
	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", tipoCliente=" + tipoCliente
				+ ", dadosPessoa=" + dadosPessoa + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
	
}
