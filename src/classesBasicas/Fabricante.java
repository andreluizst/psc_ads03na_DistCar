package classesBasicas;

//import java.util.Calendar;

import javax.persistence.*;

@Entity
public class Fabricante /*extends ObjetoGeral*/ {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codPJ", insertable=true, updatable=true)
	private PessoaJuridica pj;
	
	
	private Integer loteMinimo;
	
	
	
	// ***** CONSTRUTORES ******
	
	public Fabricante() {
		super();
		pj = new PessoaJuridica();
	}
	/*
	public Fabricante(Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}

	public Fabricante(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(codigo, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}	*/
	
	public Fabricante(PessoaJuridica pj, /*MarcaCarro marcaCarro,*/Integer loteMinimo) {
		super();
		this.pj = pj;
		//this.marcaCarro = marcaCarro;
		this.loteMinimo = loteMinimo;
	}
	
	
	// --- GETs e SEts
	public PessoaJuridica getPj() {
		return pj;
	}

	public void setPj(PessoaJuridica pj) {
		this.pj = pj;
	}

	public Integer getLoteMinimo() {
		return loteMinimo;
	}

	public void setLoteMinimo(Integer loteMinimo) {
		this.loteMinimo = loteMinimo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Fabricante [codigo=" + codigo + ", pj=" + pj + ", loteMinimo=" + loteMinimo + "]";
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
		Fabricante other = (Fabricante) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
