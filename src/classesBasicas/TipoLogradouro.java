package classesBasicas;

import javax.persistence.Entity;

@Entity
public class TipoLogradouro extends EntidadeBasica {

	public TipoLogradouro() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TipoLogradouro(String descricao){
		super();
		super.setDescricao(descricao);
	}
	
	@Override
	public String toString(){
		return getDescricao();
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	
}
