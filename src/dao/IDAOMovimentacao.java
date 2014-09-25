package dao;

import java.util.Date;
import java.util.List;

import classesBasicas.Movimentacao;

public interface IDAOMovimentacao extends IDAOGenerico<Movimentacao> {
	public List<Movimentacao> consultar(Movimentacao movimentacao) throws Exception;
	public List<Movimentacao> consultar(Movimentacao movimentacao, Date dataFinal) throws Exception;
}
