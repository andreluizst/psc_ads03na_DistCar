package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.Departamento;

public class DAODepartamento extends DAOGenerico<Departamento> implements
		IDAODepartamento {
	
	public DAODepartamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAODepartamento(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Departamento> pesquisarNomeDepartamento(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> consultar(Departamento depto) throws Exception {
		String jpql = "select d from Departamento d where d.nome like :nome";
		String nome = "%";
		boolean temDeptoSuperior = false;
		boolean temSituacao = false;
		boolean temCentro = false;
		if (depto.getNome() != null && depto.getNome().length() > 0)
			nome = "%" + depto.getNome() + "%";
		if (depto.getDepartamentoSuperior() != null && depto.getDepartamentoSuperior().getCodigo() != null
				&& depto.getDepartamentoSuperior().getCodigo() > 0){
			jpql+= " and d.departamentoSuperior.codigo = :codigoDeptoSuperior";
			temDeptoSuperior = true;
		}
		if (depto.getSituacao() != null){
			jpql+= " and d.situacao = :situacao";
			temSituacao = true;
		}
		if (depto.getCentro() != null && depto.getCentro().getCodigo() != null 
				&& depto.getCentro().getCodigo() > 0){
			jpql+= " and d.centro.codigo = :codigoCentro";
			temCentro = true;
		}
		TypedQuery<Departamento> tqry = getEntityManager().createQuery(jpql, Departamento.class);
		tqry.setParameter("nome", nome);
		if (temDeptoSuperior)
			tqry.setParameter("codigoDeptoSuperior", depto.getDepartamentoSuperior().getCodigo());
		if (temSituacao)
			tqry.setParameter("situacao", depto.getSituacao());
		if (temCentro)
			tqry.setParameter("codigoCentro", depto.getCentro().getCodigo());
		return tqry.getResultList();
	}

}
