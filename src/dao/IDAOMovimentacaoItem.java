package dao;

import java.util.List;

import classesBasicas.Movimentacao;
import classesBasicas.MovimentacaoItem;

public interface IDAOMovimentacaoItem extends IDAOGenerico<MovimentacaoItem> {
	public List<MovimentacaoItem> listarItensDaMovimentacaoNumero(Integer numero) throws Exception;
	public void atualizarItensDaMovimentacao(Movimentacao movimentacao) throws Exception;
	public void removerItensDaMovimentacaoNumero(Integer numero) throws Exception;
}
