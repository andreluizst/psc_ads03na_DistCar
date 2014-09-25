package classesBasicas;

import java.util.Calendar;


//import comum.EntidadeBasica;
import javax.persistence.Entity;

@Entity
public class TipoGerencia extends EntidadeBasica {

	private double valorGratificacao;
	
	
	public TipoGerencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoGerencia(Integer codigo, String descricao,
			Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(codigo, descricao, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}

	public TipoGerencia(String descricao, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(descricao, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}

	public TipoGerencia(String descricao) {
		super(descricao);
		// TODO Auto-generated constructor stub
	}

	public double getValorGratificacao() {
		return valorGratificacao;
	}

	public void setValorGratificacao(double valorGratificacao) {
		this.valorGratificacao = valorGratificacao;
	}
	
	@Override
	public String toString(){
		return getDescricao();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(valorGratificacao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TipoGerencia))
			return false;
		TipoGerencia other = (TipoGerencia) obj;
		if (Double.doubleToLongBits(valorGratificacao) != Double
				.doubleToLongBits(other.valorGratificacao))
			return false;
		return true;
	}
	
	
}
