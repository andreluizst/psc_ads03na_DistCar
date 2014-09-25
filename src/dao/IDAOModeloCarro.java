package dao;

import java.util.List;

import classesBasicas.ModeloCarro;

public interface IDAOModeloCarro extends IDAOGenerico<ModeloCarro> {

	public List<ModeloCarro> pesquisarModeloPorMarca(Integer codigo);

	public ModeloCarro pesquisarModeloDescAno(ModeloCarro modelo);
	public List<ModeloCarro> consultar(ModeloCarro modelo) throws Exception;
}
