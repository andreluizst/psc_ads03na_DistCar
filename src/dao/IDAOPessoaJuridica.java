package dao;

import java.util.List;

import classesBasicas.PessoaJuridica;

public interface IDAOPessoaJuridica extends IDAOGenerico<PessoaJuridica> {
	public List<PessoaJuridica> pesquisarPJ(PessoaJuridica pj);
	public PessoaJuridica pegarPJ(String cnpj) throws Exception;
}
