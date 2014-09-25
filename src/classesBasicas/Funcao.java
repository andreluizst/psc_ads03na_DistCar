package classesBasicas;

import java.util.Calendar;

import javax.persistence.Entity;


@Entity
public class Funcao extends EntidadeBasica {

	private double salario;
	
	//*** CONSTRUTORES ***
	public Funcao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Funcao(String nome, double salario) {
		this(nome, salario, null, null);
	}
	
	public Funcao(String nome, double salario, Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(nome, dataUltimaAtualizacao, situacao);
		this.salario = salario;
	}


	// *** GETs e SETs 
	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	

	@Override
	public String toString() {
		return getDescricao() + " [" + salario +"]";
	}	
	
}
