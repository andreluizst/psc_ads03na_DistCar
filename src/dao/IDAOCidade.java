package dao;

import java.util.List;

import classesBasicas.Cidade;
import classesBasicas.UnidadeFederativa;

public interface IDAOCidade extends IDAOGenerico<Cidade> {
	public List<Cidade> pesquisarCidade(Cidade cidade) throws Exception;
	public List<Cidade> pesquisarCidadePorUF(UnidadeFederativa uf) throws Exception;
}
