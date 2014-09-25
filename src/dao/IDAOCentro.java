package dao;

import java.util.List;

import classesBasicas.Centro;

public interface IDAOCentro extends IDAOGenerico<Centro> {
	public List<Centro> pesquisarCentro(Centro centro);
	public List<Centro> consultar(Centro centro) throws Exception;
}
