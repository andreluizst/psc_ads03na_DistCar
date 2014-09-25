package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.PessoaFisica;

public class DAOPessoaFisica extends DAOGenerico<PessoaFisica> implements IDAOPessoaFisica{
	
	public DAOPessoaFisica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOPessoaFisica(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PessoaFisica> pesquisarPF(PessoaFisica pf) {
		String jpql = "from PessoaFisica p where p.nome like :nome";
		String nome = "%";
		if (pf.getNome() != null)
			nome = "%" + pf.getNome() + "%";
		if (pf.getCpf() != null)
			jpql+= " and p.cpf like :cpf";
		if (pf.getRg() != null)
			jpql+= " and p.rg like :rg";
		if (pf.getEndereco().getCidade() != null){
			jpql+= " and p.endereco.cidade.codigo = :cidade";
			if (pf.getEndereco().getCidade().getUnidadeFederativa().getSigla() != null)
				jpql+= " and p.endereco.cidade.unidadeFederativa.sigla like :uf";
		}
		TypedQuery<PessoaFisica> tqry = getEntityManager().createQuery(jpql, PessoaFisica.class);
		tqry.setParameter("nome", nome);
		if (pf.getCpf() != null)
			tqry.setParameter("cpf", pf.getCpf());
		if (pf.getRg() != null)
			tqry.setParameter("rg", pf.getRg());
		if (pf.getEndereco().getCidade() != null){
			tqry.setParameter("cidade", pf.getEndereco().getCidade().getCodigo());
			if (pf.getEndereco().getCidade().getUnidadeFederativa().getSigla() != null)
				tqry.setParameter("sigla", pf.getEndereco().getCidade().getUnidadeFederativa().getSigla());
		}
		return tqry.getResultList();
	}
	
}
