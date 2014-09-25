package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.Centro;

public class DAOCentro extends DAOGenerico<Centro> implements IDAOCentro{

	@Override
	public List<Centro> pesquisarCentro(Centro centro) {
		TypedQuery<Centro> tqry = getEntityManager().createQuery("from Centro c where c.alias like :alias", Centro.class);
		tqry.setParameter("alias", "%" + centro.getAlias() + "%");
		return tqry.getResultList();
	}

	public DAOCentro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOCentro(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Centro> consultar(Centro centro) throws Exception {
		String jpql = "Select c from Centro c where c.alias like :alias";
		String alias = "%";
		boolean temCidade = false;
		boolean temSituacao = false;
		if (centro.getAlias() != null && centro.getAlias().length() > 0)
			alias = "%" + centro.getAlias() + "%";
		if (centro.getDadosPJ() != null && centro.getDadosPJ().getEndereco() != null
				&& centro.getDadosPJ().getEndereco().getCidade() != null
				&& centro.getDadosPJ().getEndereco().getCidade().getNome() != null
				&& centro.getDadosPJ().getEndereco().getCidade().getNome().length() > 0){
			jpql+= " and c.dadosPJ.endereco.cidade.nome like :nomeCidade";
			temCidade = true;
		}
		if (centro.getSituacao() != null){
			jpql+= " and c.situacao = :situacao";
			temSituacao = true;
		}
		TypedQuery<Centro> tqry = getEntityManager().createQuery(jpql, Centro.class);
		tqry.setParameter("alias", alias);
		if (temCidade)
			tqry.setParameter("nomeCidade", "%" + centro.getDadosPJ().getEndereco().getCidade().getNome() + "%");
		if (temSituacao)
			tqry.setParameter("situacao", centro.getSituacao());
		return tqry.getResultList();
	}
	
	

}
