package dao;

import java.util.List;

import javax.persistence.EntityManager;

import classesBasicas.Funcao;


public class DAOFuncao extends DAOGenerico<Funcao> implements IDAOFuncao {

	public DAOFuncao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOFuncao(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Funcao> pesquisarFuncao(Funcao f) {
		// TODO Auto-generated method stub
		return null;
	}

}
