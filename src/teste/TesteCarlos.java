package teste;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import util.Parametros;
import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import classesBasicas.VersaoCarro;
import dao.DAOCarro;
import dao.IDAOCarro;
import fachada.Fachada;

public class TesteCarlos {
	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory(Parametros.UNIT_PERSISTENCE_NAME);
	private static EntityManager em = emf.createEntityManager();

public static void main(String[] args) {
		
		/*ItemSerieCarro is = new ItemSerieCarro();
		is.setDescricao("Arcondicionado");
		is.setValorItemSerie(1500);
		
		ItemSerieCarro is1 = new ItemSerieCarro();
		is1.setDescricao("Direção Hidraulica");
		is1.setValorItemSerie(1500);
		
		List<ItemSerieCarro> itemSerieCarros = new ArrayList<ItemSerieCarro>();
		itemSerieCarros.add(is);
		itemSerieCarros.add(is1);
		
		AcessorioCarro ac = new AcessorioCarro();
		ac.setDescricao("Adesivo");
		ac.setValor(200);
		
		AcessorioCarro ac1 = new AcessorioCarro();
		ac1.setDescricao("Farol Mascara Negra");
		ac1.setValor(150);
		
		List<AcessorioCarro> acessorioCarros = new ArrayList<AcessorioCarro>();
		acessorioCarros.add(ac);
		acessorioCarros.add(ac1);
		
		MarcaCarro marcaCarro = new MarcaCarro();
	
		ModeloCarro modelo = new ModeloCarro();
		modelo.setAno(2014);
		modelo.setDescricao("Celta");
	
		
		VersaoCarro versaoCarro = new VersaoCarro();
		versaoCarro.setSituacao(Situacao.ATIVO );
		versaoCarro.setDescricao("1.0 LTZ");
		versaoCarro.setModeloCarro(modelo);
		versaoCarro.setValor(7.000);
		
		Carro carro = new Carro();
		carro.setAnoFabricacao(2014);
		carro.setChassi("121dawew323");
		carro.setCor("azul");
		carro.setValorCarro(30000.00);
		
		
		IDAOCarro carroDAO = new DAOCarro();
		carroDAO.inserir(carro);*/
		
		/*List<Carro> carroCons = carroDAO.pesquisarCarroPorChassi("121d");
		for(Carro c : carroCons){
			System.out.println(c.toString());
		}*/
		
		/*List<Carro> carroCons = carroDAO.pesquisarCarroPorModelo(modelo);
		for(Carro c : carroCons){
			System.out.println(c.toString());
		}*/

		/*List<Carro> carroCons = carroDAO.pesquisarCarroPorVersaoCarro(versaoModeloCarro);
		for(Carro c : carroCons){
			System.out.println(c.toString());
		}*/
		
		/*List<Carro> carroCons = carroDAO.pesquisarCarroPorItemSerie("Ar condicionado");
		for(Carro c : carroCons){
			System.out.println(c.toString());
		}*/
		
		System.out.println(Fachada.obterInstancia().listarItensPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(1)));
	em.close();
	emf.close();
	}

}
