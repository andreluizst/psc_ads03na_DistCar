package dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;


public class DAOItemSerieCarro extends DAOGenerico<ItemSerieCarro> implements IDAOItemSerieCarro{

	
	public DAOItemSerieCarro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOItemSerieCarro(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo) {
		TypedQuery<ItemSerieCarro> query = getEntityManager().createQuery("from ItemSerieCarro i where i.modeloCarro.codigo = :codigo", ItemSerieCarro.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
		
	}

	@Override
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo) {
		TypedQuery<ItemSerieCarro> query = getEntityManager().createQuery("from ItemSerieCarro i where i.modelo.codigo = :modeloCarro",ItemSerieCarro.class);
		query.setParameter("modeloCarro", modelo.getCodigo());
		return query.getResultList();
	}

	@Override
	public List<ItemSerieCarro> consultar(ItemSerieCarro item) throws Exception {
		String jpql = "from ItemSerieCarro i where i.descricao like :descricao";
		String descricao = "%";
		boolean temValor = false;
		boolean temModelo = false;
		boolean temSituacao = false;
		if (item.getDescricao() != null && item.getDescricao().length() > 0)
			descricao = "%" + item.getDescricao() + "%";
		if (item.getModelo()!=null && item.getModelo().getCodigo()!= null && item.getModelo().getCodigo()>0){
			jpql+= " and i.modelo.codigo = :modelo";
			temModelo = true;
		}
		if (item.getSituacao() != null){
			jpql+= " and i.situacao = :situacao";
			temSituacao = true;
		}
		if (item.getValorItemSerie()>0){
			jpql+= " and i.valor = :valor";
			temValor = true;
		}
		TypedQuery<ItemSerieCarro> tqry = getEntityManager().createQuery(jpql, ItemSerieCarro.class);
		tqry.setParameter("descricao", descricao);
		if(temValor)
			tqry.setParameter("valor", item.getValorItemSerie());
		if (temModelo)
			tqry.setParameter("modelo", item.getModelo().getCodigo());
		if (temSituacao)
			tqry.setParameter("situacao", item.getSituacao());
		return tqry.getResultList();
	}

	@Override
	public ItemSerieCarro pesquisarItemDescModelo(ItemSerieCarro item) {
		try {
			TypedQuery<ItemSerieCarro> query = getEntityManager().createQuery("from ItemSerieCarro i where i.descricao = :descricao and i.modelo.codigo =:modelo",ItemSerieCarro.class);
			query.setParameter("descricao", item.getDescricao());
			query.setParameter("modelo", item.getModelo().getCodigo());
			return query.getSingleResult();
	} catch (NoResultException ex) {
		//ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public VersaoCarro listarItens(Integer codigo) {
		TypedQuery<VersaoCarro> query = getEntityManager().createQuery("select v from  VersaoCarro v where v.codigo = :codigo", VersaoCarro.class);
		query.setParameter("codigo", codigo);
		return query.getSingleResult();
	}

	@Override
	public List<ItemSerieCarro> listarItensPorVersao(Integer codigo) {
		TypedQuery<ItemSerieCarro> query = getEntityManager().createQuery("select v.itens from VersaoCarro v join fetch v.itens where v.codigo = :versao",ItemSerieCarro.class);
		query.setParameter("versao", codigo);
		return query.getResultList();
	}
	
	public List<ItemSerieCarro> listarItensDistintos() {
		TypedQuery<ItemSerieCarro> query = getEntityManager().createQuery("select DISTINCT i from ItemSerieCarro i ",ItemSerieCarro.class);
		return query.getResultList();
	}
}
