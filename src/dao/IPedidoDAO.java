package dao;

import java.util.List;
import classesBasicas.Pedido;


public interface IPedidoDAO extends IDAOGenerico<Pedido> {

	public List<Pedido> pesquisarPedido(Pedido p);
	
}
