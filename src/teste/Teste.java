package teste;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DistCar");
		//TesteCarlos.execute(emf);
		TesteAndre.execute(emf);
		//TesteJose.execute(emf);
		emf.close();
	}

}
