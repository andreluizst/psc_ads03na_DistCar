package dao;

import java.util.List;

import classesBasicas.AcessorioCarro;
import classesBasicas.ModeloCarro;

public interface IDAOAcessorio extends IDAOGenerico<AcessorioCarro> {
	
	public List<AcessorioCarro> pesquisarPorModelo(Integer codigo);
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo);
	public AcessorioCarro pesquisarAcessorioDescModelo(AcessorioCarro acessorioCarro);
	public List<AcessorioCarro> consultar(AcessorioCarro acessorio) throws Exception;
	public List<AcessorioCarro>listarAcessorios(ModeloCarro modelo);
	public List<AcessorioCarro> listarAceDistintos();
 }
