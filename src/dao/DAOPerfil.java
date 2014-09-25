package dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Order;

import seguranca.Perfil;

public class DAOPerfil extends DAOGenerico<Perfil> implements IDAOPerfil {

	public DAOPerfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOPerfil(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
}
