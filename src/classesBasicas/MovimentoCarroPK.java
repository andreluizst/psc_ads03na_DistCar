package classesBasicas;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class MovimentoCarroPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codCarro")
	private Carro carro;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="numeroMovimentacao")
	private Movimentacao movimentacao;

	public MovimentoCarroPK() {
		super();
		carro = new Carro();
		movimentacao = new Movimentacao();
	}

	public MovimentoCarroPK(Carro carro, Movimentacao movimentacao) {
		super();
		this.carro = carro;
		this.movimentacao = movimentacao;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "NumeroMovimentoCarroPK [carro=" + carro + ", movimentacao="
				+ movimentacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carro == null) ? 0 : carro.hashCode());
		result = prime * result
				+ ((movimentacao == null) ? 0 : movimentacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MovimentoCarroPK))
			return false;
		MovimentoCarroPK other = (MovimentoCarroPK) obj;
		if (carro == null) {
			if (other.carro != null)
				return false;
		} else if (!carro.equals(other.carro))
			return false;
		if (movimentacao == null) {
			if (other.movimentacao != null)
				return false;
		} else if (!movimentacao.equals(other.movimentacao))
			return false;
		return true;
	}

		
	
	
}
