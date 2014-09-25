package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.Funcionario;

public class DAOFuncionario extends DAOGenerico<Funcionario> implements
		IDAOFuncionario {

	
	public DAOFuncionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOFuncionario(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Funcionario> pesquisarFuncionario(Funcionario f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> consultar(Funcionario funcionario) throws Exception {
		/*String jpql = "Select f from Funcionario f, Funcao fc, Departamento d, escolaridade e"
				+ " where f.departamento.codigo = d.codigo and f.funcao.codigo = fc.codigo"
				+ " and f.escolaridade.codigo = e.codigo";*/
		String jpql = "select f from Funcionario f where f.codigo > 0";
		//String nome = "%";
		boolean temCodigo = false;
		boolean temNome = false;
		boolean temCpf = false;
		boolean temCidade = false;
		boolean temDepto = false;
		boolean temFuncao = false;
		boolean temSituacao = false;
		if (funcionario.getCodigo() != null && funcionario.getCodigo() > 0){
			jpql+= " and f.codigo = :codigo";
			temCodigo = true;
		}else{
			if (funcionario.getNome() != null && funcionario.getNome().length() > 0){
				jpql+= " and f.nome like :nome";
				temNome = true;
			}
			if (funcionario.getCpf() != null && funcionario.getCpf().length() > 0){
				jpql+= " and f.cpf like :cpf";
				temCpf = true;
			}
			if (funcionario.getDepartamento() != null && funcionario.getDepartamento().getNome() != null
					&& funcionario.getDepartamento().getNome().length() > 0){
				jpql+= " and f.departamento.nome like :nomeDepto";
				temDepto = true;
			}
			if (funcionario.getEndereco() != null && funcionario.getEndereco().getCidade() != null
					&& funcionario.getEndereco().getCidade().getNome() != null
					&& funcionario.getEndereco().getCidade().getNome().length() > 0){
				jpql+= " and f.endereco.cidade.nome like :nomeCidade";
				temCidade = true;
			}
			if (funcionario.getFuncao() != null && funcionario.getFuncao().getDescricao() != null
					&& funcionario.getFuncao().getDescricao().length() > 0){
				jpql+= " and f.funcao.descricao like :nomeFuncao";
				temFuncao = true;
			}
			if (funcionario.getDepartamento() != null && funcionario.getDepartamento().getNome() != null
					&& funcionario.getDepartamento().getNome().length() > 0){
				jpql+= " and f.departamento.nome like :nomeDepto";
				temDepto = true;
			}
			if (funcionario.getSituacao() != null){
				jpql+= " and f.situacao = :situacao";
				temSituacao = true;
			}
		}
		TypedQuery<Funcionario> tqry = getEntityManager().createQuery(jpql, Funcionario.class);
		if (temCodigo){
			tqry.setParameter("codigo", funcionario.getCodigo());
			return tqry.getResultList();
		}
		if (temNome)
			tqry.setParameter("nome", "%" + funcionario.getNome() + "%");
		if (temCidade)
			tqry.setParameter("nomeCidade", "%" + funcionario.getEndereco().getCidade().getNome() + "%");
		if (temFuncao)
			tqry.setParameter("nomeFuncao", "%" + funcionario.getFuncao().getDescricao() + "%");
		if (temDepto)
			tqry.setParameter("nomeDepto", "%" + funcionario.getDepartamento().getNome() + "%");
		if (temCpf)
			tqry.setParameter("cpf", "%" + funcionario.getCpf() + "%");
		if (temSituacao)
			tqry.setParameter("situacao", funcionario.getSituacao());
		return tqry.getResultList();
	}

	@Override
	public List<Funcionario> listarFuncionariosGestores() throws Exception {
		String jpql = "select f from Funcionario f where f.tipoGerencia is not null";
		TypedQuery<Funcionario> tqry = getEntityManager().createQuery(jpql, Funcionario.class);
		return tqry.getResultList();
	}

}
