package seguranca;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.Parametros;

/**
 * Este filtro intercepta todas as chamadas de páginas JSF
 * 
 * @see http
 *      ://www.developer.com/security/article.php/3467801/Securing-J2EE-Applications
 *      -with-a-Servlet-Filter.htm
 * @see http
 *      ://fernandofranzini.wordpress.com/2009/09/09/autenticacao-e-autorizacao/
 */

@WebFilter(filterName = "/EntityManagerFilter", urlPatterns = { "*.xhtml" })
public class EntityManagerFilter implements Filter {

	private static EntityManagerFactory theEntityManagerFactory = null;

	/**
	 * Filtro
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			// Cria um EntityManager para a requisição
			em = theEntityManagerFactory.createEntityManager();
			// Associa a Thread que esta sendo executada
			EntityManagerThreads.ENTITY_MANAGERS.set(em);
			// Abre uma transacao para processar a acao
			tx = em.getTransaction();
			tx.begin();
			
			System.out.println("Transação.begin: "
					+ EntityManagerThreads.ENTITY_MANAGERS.hashCode());
			
			// Executa a acao
			chain.doFilter(request, response);

			tx.commit();

			// Remover o EntityManager da Thread
			EntityManagerThreads.ENTITY_MANAGERS.remove();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			try {
				if (em != null)
					em.close();
			} catch (Throwable t) {
				System.out.println("Erro ao fechar o EntityManager");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		destroy();
		theEntityManagerFactory = Parametros.EMF_Default;
				//Persistence.createEntityManagerFactory(Parametros.UNIT_PERSISTENCE_NAME);
	}

	public void destroy() {
		if (theEntityManagerFactory != null)
			theEntityManagerFactory.close();
	}

}