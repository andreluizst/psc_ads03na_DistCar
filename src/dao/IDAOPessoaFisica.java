package dao;

import java.util.List;

import classesBasicas.PessoaFisica;

public interface IDAOPessoaFisica extends IDAOGenerico<PessoaFisica> {
	public List<PessoaFisica> pesquisarPF(PessoaFisica pf);
}
