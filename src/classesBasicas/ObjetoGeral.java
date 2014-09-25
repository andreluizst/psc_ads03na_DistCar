package classesBasicas;

import java.util.Calendar;

import javax.persistence.*;

@MappedSuperclass
public abstract class ObjetoGeral {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataUltimaAtualizacao;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
		
	public ObjetoGeral() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ObjetoGeral(Calendar dataUltimaAtualizacao, Situacao situacao) {
		this(null, dataUltimaAtualizacao, situacao);
	}

	public ObjetoGeral(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super();
		this.codigo = codigo;
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
		this.situacao = situacao;
	}


	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Calendar getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	public void setDataUltimaAtualizacao(Calendar dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
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
		ObjetoGeral other = (ObjetoGeral) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}


	
	
	
}
