package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.Cidade;
import classesBasicas.UnidadeFederativa;

public class DAOCidade extends DAOGenerico<Cidade> implements IDAOCidade{

	
	
	public DAOCidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOCidade(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cidade> pesquisarCidade(Cidade cidade) {
		String jpql = "from Cidade c where c.nome like :nome";
		String nome = "";
		if (cidade.getNome() != null)
			nome = cidade.getNome();
		if (cidade.getUnidadeFederativa() != null){
			if (cidade.getUnidadeFederativa().getCodigo() != null && cidade.getUnidadeFederativa().getCodigo() > 0)
				jpql+= " and c.unidadeFederativa.codigo = :codigoUF";
			else{
				if (cidade.getUnidadeFederativa().getNome() != null)
					jpql+= " and c.unidadeFederativa.nome like :nomeUF";
				if (cidade.getUnidadeFederativa().getSigla() != null)
					jpql+= " and c.unidadeFederativa.sigla like :siglaUF";
			}
		}
		TypedQuery<Cidade> tqry = getEntityManager().createQuery(jpql, Cidade.class);
		tqry.setParameter("nome", nome);
		if (cidade.getUnidadeFederativa() != null){
			if (cidade.getUnidadeFederativa().getCodigo() != null && cidade.getUnidadeFederativa().getCodigo() > 0)
				tqry.setParameter("codigoUF", cidade.getUnidadeFederativa().getCodigo());
			else{
				if (cidade.getUnidadeFederativa().getNome() != null)
					tqry.setParameter("nomeUF", cidade.getUnidadeFederativa().getNome());
				if (cidade.getUnidadeFederativa().getSigla() != null)
					tqry.setParameter("siglaUF", cidade.getUnidadeFederativa().getSigla().toUpperCase());
			}
		}
		return tqry.getResultList();
	}

	@Override
	public List<Cidade> pesquisarCidadePorUF(UnidadeFederativa uf)
			throws Exception {
			return pesquisarCidade(new Cidade("%", uf));
	}

}
