package teste;

import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;

import classesBasicas.*;

public class TesteJose {
	public static void execute(EntityManagerFactory emf){
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		/*
		try{
			et.begin();
			//**** persistindo PF ***
			Cliente c = new Cliente ("123","junior","81-82828282");
			em.persist(c);
			
			//***** persistindo PJ *****
			Debito d = new Debito("4321", "123", Calendar.getInstance().getTime());
			
			em.persist(d);
			
			// *** persistindo Funcao ***
			CartaoCobranca c = new CartaoCobranca("1325","123", 820.50,Calendar.getInstance().getTime());
			
			em.persist(c);
						
					
			et.commit();
		}catch(Exception ex){
			et.rollback();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}finally{
			em.close();
		}*/
	}
}
