package seguranca;

import javax.persistence.EntityManager;

public class EntityManagerThreads {

	// Guarda a Thread que esta sendo executada
	public static final ThreadLocal<EntityManager> ENTITY_MANAGERS = new ThreadLocal<EntityManager>();

	/** Returns a fresh EntityManager */
	public static EntityManager getEntityManager() {
		return ENTITY_MANAGERS.get();
	}

	/**
	 * Executa o flush no entity manager.
	 * 
	 */
	public static final void flush() {
		if (getEntityManager().isOpen()){
			getEntityManager().flush();
		}
	}

	public static final void clear() {
		flush();
		getEntityManager().clear();
	}
	
}
