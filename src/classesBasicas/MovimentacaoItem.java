package classesBasicas;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class MovimentacaoItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private MovimentoCarroPK movimentoCarroPK;


	public MovimentacaoItem() {
		super();
		movimentoCarroPK = new MovimentoCarroPK();
	}


	public MovimentacaoItem(MovimentoCarroPK movimentoCarroPK) {
		this();
		this.movimentoCarroPK = movimentoCarroPK;
	}


	public MovimentoCarroPK getMovimentoCarroPK() {
		return movimentoCarroPK;
	}


	public void setMovimentoCarroPK(MovimentoCarroPK movimentoCarroPK) {
		this.movimentoCarroPK = movimentoCarroPK;
	}


	@Override
	public String toString() {
		return "MovimentacaoItem [movimentoCarroPK=" + movimentoCarroPK + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((movimentoCarroPK == null) ? 0 : movimentoCarroPK.hashCode());
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
		MovimentacaoItem other = (MovimentacaoItem) obj;
		if (movimentoCarroPK == null) {
			if (other.movimentoCarroPK != null)
				return false;
		} else if (!movimentoCarroPK.equals(other.movimentoCarroPK))
			return false;
		return true;
	}
	
}
