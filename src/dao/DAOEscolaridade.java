package dao;

import javax.persistence.EntityManager;

import classesBasicas.Escolaridade;

public class DAOEscolaridade extends DAOGenerico<Escolaridade> implements
		IDAOEscolaridade {

	public DAOEscolaridade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOEscolaridade(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
}
