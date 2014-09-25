package classesBasicas;

//import comum.ObjetoGeral;
import javax.persistence.*;

@Entity
public class Cidade extends ObjetoGeral {

	@Column(length=80, nullable=false)
	private String nome;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="coduf", insertable=true, updatable=true)
	private UnidadeFederativa unidadeFederativa;
	
	
	
	public Cidade() {
		super();
		unidadeFederativa = new UnidadeFederativa();
	}
	
	public Cidade(String nome, UnidadeFederativa unidadeFederativa) {
		super();
		this.nome = nome;
		this.unidadeFederativa = unidadeFederativa;
	}



	public UnidadeFederativa getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString(){
		return getNome() +"/" + getUnidadeFederativa().getSigla();
	}
	
}
