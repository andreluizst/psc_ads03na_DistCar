package dao;

import javax.persistence.EntityManager;

import classesBasicas.TipoLogradouro;

public class DAOTipoLogradouro extends DAOGenerico<TipoLogradouro> implements IDAOTipoLogradouro {

	public DAOTipoLogradouro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOTipoLogradouro(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	
}
