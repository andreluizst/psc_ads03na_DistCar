package teste;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;

import classesBasicas.*;
import dao.DAOPessoaJuridica;
import dao.DAOTipoLogradouro;
import dao.DAOUnidadeFederativa;
import dao.IDAOPessoaJuridica;
import dao.IDAOTipoLogradouro;
import dao.IDAOUnidadeFederativa;

public class TesteAndre {
	public static void execute(EntityManagerFactory emf){
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		IDAOUnidadeFederativa daoUF = new DAOUnidadeFederativa(em);
		IDAOTipoLogradouro daoTipoLog = new DAOTipoLogradouro(em);
		IDAOPessoaJuridica daoPJ = new DAOPessoaJuridica(em);
		try{
			et.begin();
			
			//****** persistindo Tipos de logradouro
			daoTipoLog.inserirSemTratamento(new TipoLogradouro("Rua"));
			daoTipoLog.inserirSemTratamento(new TipoLogradouro("Av"));
			daoTipoLog.inserirSemTratamento(new TipoLogradouro("Rod"));
			daoTipoLog.inserirSemTratamento(new TipoLogradouro("Pra�a"));
			
			// **** persistindo unidades federativas **** 
			daoUF.inserirSemTratamento(new UnidadeFederativa("Acre", "AC", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Alagoas", "AL", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Amazonas", "AM", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Amap�", "AP", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Bahia", "BA", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Cear�", "CE", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Maranh�o", "MA", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Minas Gerais", "MG", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Mato Grosso do Sul", "MS", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Mato Grosso", "MT", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Par�", "PA", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Para�ba", "PB", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Pernambuco", "PE", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Piau�", "PI", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Paran�", "PR", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Rio de Janeiro", "RJ", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Rio Grande do Norte", "RN", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Rondonia", "RO", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Roraima", "RR", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Rio Grande do Sul", "RS", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Santa Catarina", "SC", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Sergipe", "SE", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("S�o Paulo", "SP", Calendar.getInstance(), Situacao.ATIVO));
			daoUF.inserirSemTratamento(new UnidadeFederativa("Tocantins", "TO", Calendar.getInstance(), Situacao.ATIVO));
			
			//**** persistindo Cliente PF ***
			Cliente cliente1 = new Cliente();
			cliente1.setDadosPessoa(new PessoaFisica("Maria Lima", "22233344455", "111222333", "SSPPE"));
			cliente1.getDadosPessoa().setEndereco(new Endereco(daoTipoLog.consultarPorId(1), "Rua 8", "s/n", "teste1",
												new Cidade("Recife", daoUF.pegarUF("Pernambuco", "PE")),"51245000"
											)
									);
			//pf.setTipoCliente(TipoCliente.PESSOA_FISICA);
			cliente1.getDadosPessoa().setDataUltimaAtualizacao(Calendar.getInstance());
			cliente1.getDadosPessoa().setSituacao(Situacao.ATIVO);
			em.persist(cliente1);
			
			Cliente cliente2 = new Cliente();
			cliente2.setDadosPessoa(new PessoaFisica("Ant�nio Carlos", "12145456", "3654984", "SSPPE"));
			cliente2.getDadosPessoa().setEndereco(new Endereco(daoTipoLog.consultarPorId(1), "J�lia Rocha", "26", "Bairro da Rocha",
												new Cidade("Jaboat�o dos Guararapes", daoUF.pegarUF("Pernambuco", "PE")),"51245000"
											)
									);
			//pf2.setTipoCliente(TipoCliente.PESSOA_FISICA);
			cliente2.getDadosPessoa().setDataUltimaAtualizacao(Calendar.getInstance());
			cliente2.getDadosPessoa().setSituacao(Situacao.ATIVO);
			em.persist(cliente2);
			
			//***** persistindo PJ *****
			PessoaJuridica pj = new PessoaJuridica("FIAT", "10111222000100", "111222333", Calendar.getInstance().getTime());
			pj.setEndereco(new Endereco(daoTipoLog.consultarPorId(2), "Carros Novos", "102", "endere�o 2",
											new Cidade("Olinda", daoUF.pegarUF("Pernambuco", "PE")), "50000650"
										)
								);
			//pj.setTipoCliente(TipoCliente.NAO_�_CLIENTE);
			pj.setDataUltimaAtualizacao(Calendar.getInstance());
			pj.setSituacao(Situacao.ATIVO);
			em.persist(pj);
			
			
			
			//**** persistindo Cliente PJ
			Cliente clientePJ = new Cliente();
			clientePJ.setTipoCliente(TipoCliente.PESSOA_JURIDICA);
			clientePJ.setDadosPessoa(daoPJ.consultarPorId(3));//PJ FIAT
			em.persist(clientePJ);
			
			
			
			// *** persistindo Funcao ***
			Funcao funcao1 = new Funcao("Atendente", 820.50);
			funcao1.setDataUltimaAtualizacao(Calendar.getInstance());
			funcao1.setSituacao(Situacao.ATIVO);
			em.persist(funcao1);
			em.persist(new Funcao("Telefonista", 850.33));
			
			//**** persistindo Fabricante e Marca ***
			MarcaCarro marca1 = new MarcaCarro();
			marca1.setDescricao("BMW");
			em.persist(marca1);
			
			MarcaCarro marcaCarro = new MarcaCarro();
			marcaCarro.setDescricao("Honda");
			Fabricante fabricante = new Fabricante(
							new PessoaJuridica("Honda Co", "00222444000101", "44444444", Calendar.getInstance().getTime(), 
								new Endereco(daoTipoLog.consultarPorId(3), "Carros Novos km 21", "1000", "endere�o GM",
									new Cidade("Bel�m", daoUF.pegarUF("Par�", "PA")), "51030540"
								)
							),
							/*marcaCarro,*/ 10);
			em.persist(fabricante);	
		
			//*** persistindo Centro ***
			Centro centro = new Centro(
						new PessoaJuridica("Carros Brasil", "44555666000101", "555555", Calendar.getInstance().getTime(), 
							new Endereco(daoTipoLog.consultarPorId(4), "Vitrine Automotiva", "10", "Bairro matriz",
								new Cidade("Salvador", daoUF.pegarUF("Bahia", "BA")), "55060020"
							)
						), "Central distribuição", 100, TipoCentro.DISTRIBUICAO, Calendar.getInstance(), Situacao.ATIVO
					);
			em.persist(centro);
			
			Centro centro2 = new Centro(
					new PessoaJuridica("Carros Brasil", "44555666000222", "555555", Calendar.getInstance().getTime(), 
						new Endereco(daoTipoLog.consultarPorId(4), "Logradouro teste", "20", "Bairro filial",
							new Cidade("Campinas", daoUF.pegarUF("S�o Paulo", "SP")), "54652000"
						)
					), "Filial 0002-22", 100, TipoCentro.LOJA, Calendar.getInstance(), Situacao.ATIVO
				);
			em.persist(centro2);
			
			//******* persistindo departamento
			Departamento depto = new Departamento();
			depto.setNome("Diretoria Comercial");
			depto.setCentro(centro);
			depto.setSituacao(Situacao.ATIVO);
			em.persist(depto);
			
			Departamento depto2 = new Departamento();
			depto2.setNome("Comercial 0002-22");
			depto2.setCentro(centro2);
			depto2.setSituacao(Situacao.ATIVO);
			em.persist(depto2);
			
			et.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			et.rollback();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}finally{
			em.close();
		}
	}
}
