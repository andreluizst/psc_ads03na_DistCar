package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import classesBasicas.Movimentacao;
import classesBasicas.MovimentacaoItem;

public class DAOMovimentacaoItem extends DAOGenerico<MovimentacaoItem> implements IDAOMovimentacaoItem {

	public DAOMovimentacaoItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOMovimentacaoItem(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MovimentacaoItem> listarItensDaMovimentacaoNumero(Integer numero)
			throws Exception {
		String jpql = " select i from MovimentacaoItem i where i.movimentoCarroPK.movimentacao.numero = :numero";
		try{
			TypedQuery<MovimentacaoItem> tqry = getEntityManager().createQuery(jpql, MovimentacaoItem.class);
			tqry.setParameter("numero", numero);
			return tqry.getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("DAOMovimentacaoItem_ERROR: " + ex.getMessage());
		}
	}

	@Override
	public void atualizarItensDaMovimentacao(Movimentacao movimentacao) throws Exception {
		try{
			removerItensDaMovimentacaoNumero(movimentacao.getNumero());
			if (movimentacao.getItens() != null && movimentacao.getItens().size() > 0){
				for (MovimentacaoItem item : movimentacao.getItens()){
					if (item.getMovimentoCarroPK().getMovimentacao() == null)
						item.getMovimentoCarroPK().setMovimentacao(movimentacao);
					else
						item.getMovimentoCarroPK().getMovimentacao().setNumero(movimentacao.getNumero());
					/*if (item.getMovimentoCarroPK().getCarro().getCodigo() == null){
						getEntityManager().persist(item.getMovimentoCarroPK().getCarro());
						getEntityManager().refresh(item.getMovimentoCarroPK().getCarro());
					}*/
					getEntityManager().persist(item);
				}
			}	
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public void removerItensDaMovimentacaoNumero(Integer numero) throws Exception {
		Query qry = getEntityManager().createQuery("delete from MovimentacaoItem i where i.movimentoCarroPK.movimentacao.numero = :numeroDel");
		qry.setParameter("numeroDel", numero);
		try{
			qry.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}
	
	
	
}
