package classesBasicas;

import java.util.Calendar;

import javax.persistence.*;


/*
	FALTA fazer o relacionamento N:N entre Departamento e Centro 

*/


@Entity
public class Departamento extends ObjetoGeral {
	@Column(length=20, nullable=false)
	private String nome;
	
	/* Necessario para montar o organograma da empresa 
	   Diretoria Financeira > Controladoria > Gerencia de Faturamento
	*/
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="codDepSup", insertable=true, updatable=true)
	private Departamento departamentoSuperior;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="codGestor", insertable=true, updatable=true)
	private Funcionario gestor;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="codCentro", insertable=true, updatable=true, nullable=false)
	private Centro centro;
	
	
	public Departamento() {
		super();
		//centro = new Centro();
		//departamentoSuperior = new Departamento();
		//gestor = new Gestor();
	}
	
	public Departamento(String nome, Departamento departamentoSuperior,
			Funcionario gestor, Centro centro) {
		super();
		this.nome = nome;
		this.departamentoSuperior = departamentoSuperior;
		this.gestor = gestor;
		this.centro = centro;
	}

	public Departamento(Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
		centro = new Centro();
		//departamentoSuperior = new Departamento();
		//gestor = new Gestor();
	}
	
	public Departamento(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(codigo, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
		centro = new Centro();
		//departamentoSuperior = new Departamento();
		//gestor = new Gestor();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Departamento getDepartamentoSuperior() {
		return departamentoSuperior;
	}
	
	public void setDepartamentoSuperior(Departamento departamentoSuperior) {
		this.departamentoSuperior = departamentoSuperior;
	}
	
	public Funcionario getGestor() {
		return gestor;
	}
	
	public void setGestor(Funcionario gestor) {
		this.gestor = gestor;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	@Override
	public String toString() {
		return nome + " / " + centro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		result = prime
				* result
				+ ((departamentoSuperior == null) ? 0 : departamentoSuperior
						.hashCode());
		result = prime * result + ((gestor == null) ? 0 : gestor.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Departamento))
			return false;
		Departamento other = (Departamento) obj;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		if (departamentoSuperior == null) {
			if (other.departamentoSuperior != null)
				return false;
		} else if (!departamentoSuperior.equals(other.departamentoSuperior))
			return false;
		if (gestor == null) {
			if (other.gestor != null)
				return false;
		} else if (!gestor.equals(other.gestor))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	
}
