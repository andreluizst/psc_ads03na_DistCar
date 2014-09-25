package dao;

import java.util.List;

import classesBasicas.Fabricante;

public interface IDAOFabricante extends IDAOGenerico<Fabricante> {
	public List<Fabricante> consultar(Fabricante f) throws Exception;
}
