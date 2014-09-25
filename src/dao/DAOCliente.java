package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classesBasicas.Cliente;
import classesBasicas.PessoaFisica;
import classesBasicas.PessoaJuridica;
import classesBasicas.TipoCliente;


public class DAOCliente extends DAOGenerico<Cliente> implements IDAOCliente {

	public DAOCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOCliente(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cliente> consultar(Cliente cliente) throws Exception {
		String jpql = "Select c from Cliente c where c.dadosPessoa.nome like :nome";
		String cpfCnpj = "%";
		String nome = "%";
		boolean temCidade = false;
		boolean temUf = false;
		boolean temCpf = false;
		boolean temCnpj = false;
		boolean temSituacao = false;
		
		if (cliente.getTipoCliente() == TipoCliente.PESSOA_FISICA){
			jpql = "Select c from Cliente c, PessoaFisica p where c.dadosPessoa.nome like :nome"
					+ " and c.dadosPessoa.codigo = p.codigo";
			cpfCnpj = ((PessoaFisica)cliente.getDadosPessoa()).getCpf();
			if (cpfCnpj != null && cpfCnpj.length() > 0){
				jpql+= " and p.cpf like :cpf";
				temCpf = true;
			}
		}
		if (cliente.getTipoCliente() == TipoCliente.PESSOA_JURIDICA){
			jpql = "Select c from Cliente c, PessoaJuridica p where c.dadosPessoa.nome like :nome"
					+ " and c.dadosPessoa.codigo = p.codigo";
			cpfCnpj = ((PessoaJuridica)cliente.getDadosPessoa()).getCnpj();
			if (cpfCnpj != null && cpfCnpj.length() > 0){
				jpql+= " and p.cnpj like :cnpj";
				temCnpj = true;
			}
		}
		
		if (cliente.getDadosPessoa().getEndereco() != null){
			if (cliente.getDadosPessoa().getEndereco().getCidade() != null){ 
				if (cliente.getDadosPessoa().getEndereco().getCidade().getCodigo() != null
						&& cliente.getDadosPessoa().getEndereco().getCidade().getCodigo() > 0){
					jpql+= " and c.dadosPessoa.endereco.cidade.codigo = :codigoCidade";
					temCidade = true;
				}
				if (cliente.getDadosPessoa().getEndereco().getCidade().getUnidadeFederativa() != null
						&& cliente.getDadosPessoa().getEndereco().getCidade().getUnidadeFederativa().getCodigo() != null
						&& cliente.getDadosPessoa().getEndereco().getCidade().getUnidadeFederativa().getCodigo() > 0){
					jpql+= " and c.dadosPessoa.endereco.cidade.unidadeFederativa.codigo = :codigoUf";
					temUf = true;
				}
			}
		}
		if (cliente.getDadosPessoa().getSituacao() != null){
			jpql+= " and c.dadosPessoa.situacao = :situacao";
			temSituacao = true;
		}
		TypedQuery<Cliente> tqry = getEntityManager().createQuery(jpql, Cliente.class);
		if (cliente.getDadosPessoa().getNome() != null && cliente.getDadosPessoa().getNome().length() > 0)
				nome = "%" + cliente.getDadosPessoa().getNome() + "%";
		tqry.setParameter("nome", nome);
		if (temCidade)
			tqry.setParameter("codigoCidade", cliente.getDadosPessoa().getEndereco().getCidade().getCodigo());
		if (temUf)
			tqry.setParameter("codigoUf", cliente.getDadosPessoa().getEndereco().getCidade().getUnidadeFederativa().getCodigo());
		if (temCpf)
			tqry.setParameter("cpf", "%" + cpfCnpj + "%");
		if (temCnpj)
			tqry.setParameter("cnpj", "%" + cpfCnpj + "%");
		if (temSituacao)
			tqry.setParameter("situacao", cliente.getDadosPessoa().getSituacao());
		
		return tqry.getResultList();
	}
	
}
