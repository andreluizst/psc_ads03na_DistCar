package dao;

import java.util.List;

import classesBasicas.Funcionario;



public interface IDAOFuncionario extends IDAOGenerico<Funcionario> {

		public List<Funcionario> pesquisarFuncionario(Funcionario f);
		public List<Funcionario> consultar(Funcionario funcionario) throws Exception;
		public List<Funcionario> listarFuncionariosGestores() throws Exception;

}

	
