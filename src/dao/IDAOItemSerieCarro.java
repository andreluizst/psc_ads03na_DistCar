package dao;



import java.util.List;

import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;


public interface IDAOItemSerieCarro extends IDAOGenerico<ItemSerieCarro> {

	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo);
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo);
	public List<ItemSerieCarro> listarItensPorVersao(Integer codigo);
	public ItemSerieCarro pesquisarItemDescModelo(ItemSerieCarro item);
	public List<ItemSerieCarro> consultar(ItemSerieCarro item) throws Exception;
	public VersaoCarro listarItens(Integer codigo);
	public List<ItemSerieCarro> listarItensDistintos();
}
