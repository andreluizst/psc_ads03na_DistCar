package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.Movimentacao;

public class DAOMovimentacao extends DAOGenerico<Movimentacao> implements
		IDAOMovimentacao {


	public DAOMovimentacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOMovimentacao(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public List<Movimentacao> consultar(Movimentacao movimentacao, Date dataFinal)
			throws Exception {
		try{
			//String jpql = "select m from Movimentacao m join fetch m.itens";
		String jpql = "select m from Movimentacao m ";
		boolean temParametros = false;
		boolean temNumero = false;
		boolean temCentroOrigem = false;
		boolean temCentroDestino = false;
		boolean temData = false;
		boolean temSituacao = false;
		if (movimentacao.getNumero() != null && movimentacao.getNumero() > 0){
			jpql+= " where m.numero = :numero";
			temParametros = true;
			temNumero = true;
		}else{
			if (movimentacao.getCtoDestino() != null 
					&& movimentacao.getCtoDestino().getCodigo() != null
					&& movimentacao.getCtoDestino().getCodigo() > 0){
				if (temParametros)
					jpql+= " and m.ctoDestino.codigo = :codigoCentroDestino";
				else{
					jpql+= " where m.ctoDestino.codigo = :codigoCentroDestino";
					temParametros = true;
				}
				temCentroDestino = true;
			}
			if (movimentacao.getCtoOrigem() != null 
					&& movimentacao.getCtoOrigem().getCodigo() != null
					&& movimentacao.getCtoOrigem().getCodigo() > 0){
				if (temParametros)
					jpql+= " and m.ctoOrigem.codigo = :codigoCentroOrigem";
				else{
					jpql+= " where m.ctoOrigem.codigo = :codigoCentroOrigem";
					temParametros = true;
				}
				temCentroOrigem = true;
			}
			if (movimentacao.getDataMovimentacao() != null){
				if (dataFinal == null)
					dataFinal = movimentacao.getDataMovimentacao();
				if (temParametros)
					jpql+= " and (m.dataMovimentacao >= :dataInicial and m.dataMovimentacao <= :dataFinal)";
				else{
					jpql+= " where (m.dataMovimentacao >= :dataInicial and m.dataMovimentacao <= :dataFinal)";
					temParametros = true;
				}
				temData = true;
			}
			if (movimentacao.getSituacao() != null){
				if (temParametros)
					jpql+= " and m.situacao = :situacao";
				else{
					jpql+= " where m.situacao = :situacao";
					temParametros = true;
				}
				temSituacao = true;
			}
		}
		TypedQuery<Movimentacao> tqry = getEntityManager().createQuery(jpql, Movimentacao.class);
		if (temNumero){
			tqry.setParameter("numero", movimentacao.getNumero());
			return tqry.getResultList();
		}
		if (temCentroDestino)
			tqry.setParameter("codigoCentroDestino", movimentacao.getCtoDestino().getCodigo());
		if (temCentroOrigem)
			tqry.setParameter("codigoCentroOrigem", movimentacao.getCtoOrigem().getCodigo());
		if (temData){
			tqry.setParameter("dataInicial", movimentacao.getDataMovimentacao());
			tqry.setParameter("dataFinal", dataFinal);
		}
		if (temSituacao)
			tqry.setParameter("situacao", movimentacao.getSituacao());
		return tqry.getResultList();
		}catch(Exception ex){
			throw new Exception("DAOMovimentacao.ERRO: " + ex.getMessage());
		}
	}

	@Override
	public List<Movimentacao> consultar(Movimentacao movimentacao) throws Exception {
		return consultar(movimentacao, null);
	}

}
