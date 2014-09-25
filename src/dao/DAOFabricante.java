package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.Fabricante;

public class DAOFabricante extends DAOGenerico<Fabricante> implements IDAOFabricante{
	
	public DAOFabricante() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOFabricante(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Fabricante> consultar(Fabricante f) throws Exception {
		String jpql = "Select f from Fabricante f, PessoaJuridica p where f.pj.codigo = p.codigo";
		String nome = "%";
		boolean temCodigo = false;
		boolean temNome = false;
		boolean temCnpj = false;
		boolean temSituacao = false;
		boolean temUF = false;
		boolean temCidade = false;
		if (f.getCodigo() != null && f.getCodigo() > 0){
			jpql+= " and f.codigo = :codigo";
			temCodigo = true;
		}else{
			if (f.getPj() != null){
				jpql+= " and p.nome like :nome";
				nome = "%" + f.getPj().getNome() + "%";
				temNome = true;
				if (f.getPj().getCnpj() != null){
					jpql+= " and p.cnpj like :cnpj";
					temCnpj = true;
				}
				if (f.getPj().getSituacao() != null){
					jpql+= " and p.situacao = :situacao";
					temSituacao = true;
				}
				if (f.getPj().getEndereco() != null && f.getPj().getEndereco().getCidade() != null){
					if (f.getPj().getEndereco().getCidade().getCodigo() != null
							&& f.getPj().getEndereco().getCidade().getCodigo() > 0){
						jpql+= " and p.endereco.cidade.codigo = :codigoCidade";
						temCidade = true;
					}else{
						if (f.getPj().getEndereco().getCidade().getUnidadeFederativa() != null
								&& f.getPj().getEndereco().getCidade().getUnidadeFederativa().getCodigo() != null
								&& f.getPj().getEndereco().getCidade().getUnidadeFederativa().getCodigo() > 0){
							jpql+= " and p.endereco.cidade.unidadeFederativa.codigo = :codigoUF";
							temUF = true;
						}
					}
				}
			}
		}
		try{
		TypedQuery<Fabricante> tqry = getEntityManager().createQuery(jpql, Fabricante.class);
		if (temCodigo){
			tqry.setParameter("codigo", f.getCodigo());
			return tqry.getResultList();
		}
			
		if (temNome)
			tqry.setParameter("nome", nome);
		if (temCnpj)
			tqry.setParameter("cnpj", "%" + f.getPj().getCnpj() + "%");
		if (temSituacao)
			tqry.setParameter("situacao", f.getPj().getSituacao());
		if (temUF)
			tqry.setParameter("codigoUF", f.getPj().getEndereco().getCidade().getUnidadeFederativa().getCodigo());
		if (temCidade)
			tqry.setParameter("codigoCidade", f.getPj().getEndereco().getCidade().getCodigo());
		return tqry.getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("Erro em DAOFabricante.pesquisar: " + ex.getMessage());
		}
	}

}
