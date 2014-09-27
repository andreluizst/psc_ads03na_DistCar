package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// teste controle de versão GIT

public class Parametros {
	
	public static final String UNIT_PERSISTENCE_NAME = "DistCar";
	public static EntityManagerFactory EMF_Default = Persistence.createEntityManagerFactory(Parametros.UNIT_PERSISTENCE_NAME);
	//public static final int QUANTIDADE_DIAS_PARA_COBRAR = 5;

}
