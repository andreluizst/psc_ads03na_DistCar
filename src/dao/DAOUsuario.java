package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import seguranca.Usuario;

public class DAOUsuario extends DAOGenerico<Usuario> implements IDAOUsuario {

	public DAOUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOUsuario(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usuario pegarUsuarioPeloLogin(String login) throws Exception {
		Usuario obj = null;
		String jpql = "select u from Usuario u where u.login = :login";
		try{
			TypedQuery<Usuario> tqry = entityManager.createQuery(jpql, Usuario.class);
			tqry.setParameter("login", login.toLowerCase());
			try{
				obj = tqry.getSingleResult();
			}catch(Exception ex){
				return null;
			}
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
		return obj;
	}
	
}
