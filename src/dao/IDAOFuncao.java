package dao;

import java.util.List;

import classesBasicas.Funcao;

public interface IDAOFuncao extends IDAOGenerico<Funcao> {
	public List<Funcao> pesquisarFuncao(Funcao funcao);
}
