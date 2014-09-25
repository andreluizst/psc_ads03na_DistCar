package dao;

import java.util.List;

import classesBasicas.Cliente;

public interface IDAOCliente extends IDAOGenerico<Cliente> {
	public List<Cliente> consultar(Cliente cliente) throws Exception;
}
