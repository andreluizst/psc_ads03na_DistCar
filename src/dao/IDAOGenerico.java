package dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Order;

public interface IDAOGenerico<Entidade> {

	public void inserirSemTratamento(Entidade entidade) throws Exception;
	public void alterarSemTratamento(Entidade entidade) throws Exception;
	public void removerSemTratamento(Entidade entidade) throws Exception;
	
	public void inserir(Entidade entidade);
	
	public void alterar(Entidade entidade);
	
	public void remover(Entidade entidade);
	
	public Entidade consultarPorId(Integer id);
	
	public List<Entidade> consultarTodos();

	/**
	 * Pesquisa por exemplo
	 * @param exemplo - Preencha os atributos utilizados na pesquisa
	 * @param indiceInicial
	 * @param quantidade
	 * @param ordenacoes
	 * @return
	 */
	public List<Entidade> pesquisar(Entidade exemplo, Integer indiceInicial, Integer quantidade,
			Order... ordenacoes);

	public List<Entidade> pesquisar(Entidade exemplo, Integer indiceInicial, Integer quantidade);

	public List<Entidade> pesquisar(Entidade exemplo, Order... ordenacoes);

	public List<Entidade> pesquisar(Entidade exemplo);
	
	
	/**
	 * Utilizado para se injetar o Entity manager no DAO.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	public void setEntityManager(EntityManager entityManager);
	public EntityManager getEntityManager();

}
