package dao;

import javax.persistence.EntityManager;

import classesBasicas.TipoGerencia;

public class DAOTipoGerencia extends DAOGenerico<TipoGerencia> implements IDAOTipoGerencia {

	public DAOTipoGerencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOTipoGerencia(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	
}
