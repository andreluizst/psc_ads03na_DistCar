package dao;

import java.util.List;

import classesBasicas.Fabricante;
import classesBasicas.MarcaCarro;
import classesBasicas.VersaoCarro;

public interface IDAOVersaoCarro extends IDAOGenerico<VersaoCarro> {
	
	public List<VersaoCarro> pesquisarVersaoPorModelo(Integer codigo);
	public List<VersaoCarro> consultar(VersaoCarro versao, Fabricante f, MarcaCarro m) throws Exception;
	public VersaoCarro pesquisarVersaoDesc(String descricao);
}
