package dao;

import java.util.List;

import classesBasicas.Departamento;


public interface IDAODepartamento extends IDAOGenerico<Departamento> {

	public List<Departamento> pesquisarNomeDepartamento(String nome);
	public List<Departamento> consultar(Departamento depto) throws Exception;
	

}
