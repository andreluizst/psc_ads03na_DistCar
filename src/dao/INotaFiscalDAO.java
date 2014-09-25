package dao;

import java.util.List;

import classesBasicas.NotaFiscal;

public interface INotaFiscalDAO extends IDAOGenerico<NotaFiscal> {

		public List<NotaFiscal> pesquisarNotaFiscal(NotaFiscal nf);

}
