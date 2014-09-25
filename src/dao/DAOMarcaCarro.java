package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import classesBasicas.MarcaCarro;

public class DAOMarcaCarro extends DAOGenerico<MarcaCarro> implements IDAOMarcaCarro {

	
	public DAOMarcaCarro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOMarcaCarro(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MarcaCarro> pesquisarMarcaPorFab(Integer codigo) {
		TypedQuery<MarcaCarro> query = getEntityManager().createQuery("from MarcaCarro m where m.fabricante.codigo = :codigo", MarcaCarro.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
	}

	@Override
	public List<MarcaCarro> consultar(MarcaCarro marca) throws Exception {
		String jpql = "from MarcaCarro ma where ma.descricao like :descricao";
		String descricao = "%";
		boolean temFabricante = false;
		boolean temSituacao = false;
		if (marca.getDescricao() != null && marca.getDescricao().length() > 0)
			descricao = "%" + marca.getDescricao() + "%";
		if (marca.getFabricante()!=null && marca.getFabricante().getCodigo()!= null && marca.getFabricante().getCodigo()>0){
			jpql+= " and ma.fabricante.codigo = :fabricante";
			temFabricante = true;
		}
		if (marca.getSituacao() != null){
			jpql+= " and ma.situacao = :situacao";
			temSituacao = true;
		}
		TypedQuery<MarcaCarro> tqry = getEntityManager().createQuery(jpql, MarcaCarro.class);
		tqry.setParameter("descricao", descricao);
		if (temFabricante)
			tqry.setParameter("fabricante", marca.getFabricante().getCodigo());
		if (temSituacao)
			tqry.setParameter("situacao", marca.getSituacao());
		return tqry.getResultList();
	}

	@Override
	public MarcaCarro pesquisarMarcaDesc(MarcaCarro marca) {
	try {
			TypedQuery<MarcaCarro> query = getEntityManager().createQuery("from MarcaCarro ma where ma.descricao = :descricao",MarcaCarro.class);
			query.setParameter("descricao", marca.getDescricao());
			return query.getSingleResult();
	} catch (NoResultException e) {
		//e.printStackTrace();
			return null;
		}
	}
}
