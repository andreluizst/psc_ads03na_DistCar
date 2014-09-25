package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.UnidadeFederativa;

public class DAOUnidadeFederativa extends DAOGenerico<UnidadeFederativa> implements IDAOUnidadeFederativa{

	public DAOUnidadeFederativa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOUnidadeFederativa(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<UnidadeFederativa> pesquisarUF(UnidadeFederativa uf) {
		String jpql = "from UnidadeFederativa u where u.nome like :nome";
		String nome = "%";
		if (uf.getNome() != null)
			nome = "%" + uf.getNome() + "%";
		if (uf.getSigla() != null)
			jpql+= " and u.sigla like :sigla";
		TypedQuery<UnidadeFederativa> tqry = entityManager.createQuery(jpql, UnidadeFederativa.class);
		tqry.setParameter("nome", nome);
		if (uf.getSigla() != null)
			tqry.setParameter("sigla", uf.getSigla());
		return tqry.getResultList();
	}

	@Override
	public UnidadeFederativa pegarUF(String nome, String sigla)
			throws Exception {
		// TODO Auto-generated method stub
		String jpql = "from UnidadeFederativa u where u.nome like :nome and u.sigla like :sigla";
		TypedQuery<UnidadeFederativa> tqry = entityManager.createQuery(jpql, UnidadeFederativa.class);
		tqry.setParameter("nome", nome);
		tqry.setParameter("sigla", sigla);
		return tqry.getSingleResult();
	}

}
