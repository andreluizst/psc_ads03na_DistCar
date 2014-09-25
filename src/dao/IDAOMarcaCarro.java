package dao;

import java.util.List;

import classesBasicas.MarcaCarro;

public interface IDAOMarcaCarro extends IDAOGenerico<MarcaCarro> {
	
	public List<MarcaCarro> pesquisarMarcaPorFab(Integer codigo);
	public List<MarcaCarro> consultar(MarcaCarro marca) throws Exception;
	public MarcaCarro pesquisarMarcaDesc(MarcaCarro marca);
}
