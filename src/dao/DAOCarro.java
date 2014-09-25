package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.Fabricante;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;


public class DAOCarro extends DAOGenerico<Carro> implements IDAOCarro {

	public DAOCarro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOCarro(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Carro> pesquisarCarroPorChassi(String chassi) {
		TypedQuery<Carro> query = entityManager.createQuery("from Carro c where c.chassi like :chassi", Carro.class);
		query.setParameter("chassi", "%" + chassi + "%");
		return query.getResultList();
	}

	@Override
	public List<Carro> pesquisarCarroPorModelo(ModeloCarro modeloCarro) {
		TypedQuery<Carro> query = entityManager.createQuery("from Carro c where c.versaoModeloCarro.modeloCarro.descricaoModeloCarro = :modeloCarro", Carro.class);
		query.setParameter("modeloCarro", modeloCarro.getDescricao());
		return query.getResultList();
	}

	@Override
	public List<Carro> pesquisarCarroPorVersaoCarro(VersaoCarro versaoCarro) {
		TypedQuery<Carro> query = entityManager.createQuery("from Carro c where c.versaoModeloCarro.descricaoVersaoModeloCarro = :versaoModeloCarro", Carro.class);
		query.setParameter("versaoModeloCarro", versaoCarro.getDescricao());
		return query.getResultList();
	}
	
	@Override
	public Carro pegarCarroPeloChassi(String chassi) throws Exception {
		TypedQuery<Carro> tqry = entityManager.createQuery("from Carro c where c.chassi = :chassi", Carro.class);
		tqry.setParameter("chassi", chassi);
		try{
			return tqry.getSingleResult();
		}catch(NoResultException re){
			return null;
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}
	
	public List<Carro> consultar(Carro carro, Fabricante f, MarcaCarro m, ModeloCarro mod,
			List<ItemSerieCarro> itensSelecionado,  List<AcessorioCarro> acessoriosSelecionado) throws Exception {
		String jpql = null;
		
		if(itensSelecionado.size()==0 && acessoriosSelecionado.size()==0 ){
			jpql = "Select c from Carro c where c.chassi like :descricao";
		}else if(acessoriosSelecionado.size()!=0 && acessoriosSelecionado.size()==0){
			jpql = "Select c from Carro c, IN (c.versao.acessorios) as a where c.chassi like :descricao";
		}
		else if(acessoriosSelecionado.size()==0 && acessoriosSelecionado.size()!=0){
			jpql = "Select c from Carro c, IN (c.versao.itens) as i where c.chassi like :descricao";
		}
		else{
			jpql = "Select c from Carro c, IN (c.versao.itens) as i, IN (c.versao.acessorios) as a  where c.chassi like :descricao";
		}
		String descricao = "%";
		boolean temValor = false;
		boolean temModelo = false;
		boolean temStatus = false;
		boolean temFabricante = false;
		boolean temMarca = false;
		boolean temF=false;
		boolean temM=false;
		boolean temMod=false;
		boolean temCentro=false;
		boolean temAno=false;
		boolean temCor=false;
		boolean temItem= false;
		boolean temAcessorio=false;
		boolean temVersao=false;
		
		if (carro.getClass() != null && carro.getChassi().length() > 0)
			descricao = "%" + carro.getChassi() + "%";
		if(carro.getCor()!=null && carro.getCor().length()>0){
			jpql+= " and c.cor like :cor";
			temCor = true;
		}
		
		if(f!=null){
			jpql+= " and c.versao.modeloCarro.marcaCarro.fabricante.codigo = :fabricante";
			temF = true;
		}
		if(m!=null){
			jpql+= " and c.versao.modeloCarro.marcaCarro.codigo = :marca";
			temM = true;
		}
		if(mod!=null){
			jpql+= " and c.versao.modeloCarro.codigo = :modelo";
			temMod = true;
		}
		
		if (carro.getVersao().getModeloCarro().getMarcaCarro().getFabricante()!=null && carro.getVersao().getModeloCarro().getMarcaCarro().getFabricante().getCodigo()!= null && 
				carro.getVersao().getModeloCarro().getMarcaCarro().getFabricante().getCodigo()>0){
			jpql+= " and c.versao.modeloCarro.marcaCarro.fabricante.codigo = :fabricante";
			temFabricante = true;
			temF=false;
		}
		
	if(itensSelecionado.size()!=0){
			String item="";
			for(ItemSerieCarro i : itensSelecionado){
				 item+= " and i.descricao= :item"+i.getCodigo();
			}
			jpql+= item;
			temItem=true;
		}
	
	if(acessoriosSelecionado.size()!=0){
		String acessorio="";
		for(AcessorioCarro a : acessoriosSelecionado){
			 acessorio+= " and a.descricao= :acessorio"+a.getCodigo();
		}
		jpql+= acessorio;
		temAcessorio=true;
	}
		if (carro.getCentro()!=null && carro.getCentro().getCodigo()!= null && 
				carro.getCentro().getCodigo()>0){
			jpql+= " and c.centro.codigo = :centro";
			temCentro=true;
		}
		if (carro.getVersao().getModeloCarro().getMarcaCarro()!=null && carro.getVersao().getModeloCarro().getMarcaCarro().getCodigo()!= null && 
				carro.getVersao().getModeloCarro().getMarcaCarro().getCodigo()>0){
			jpql+= " and c.versao.modeloCarro.marcaCarro.codigo = :marca";
			temMarca = true;
			temM=false;
		}
		
		if (carro.getVersao().getModeloCarro()!=null && carro.getVersao().getModeloCarro().getCodigo()!= null
				&& carro.getVersao().getModeloCarro().getCodigo()>0){
			jpql+= " and c.versao.modeloCarro.codigo = :modelo";
			temModelo = true;
		}
		if (carro.getVersao()!=null && carro.getVersao().getCodigo()!= null
				&& carro.getVersao().getCodigo()>0){
			jpql+= " and c.versao.codigo = :versao";
			temVersao = true;
		}
		
		if (carro.getStatus() != null){
			jpql+= " and c.status = :status";
			temStatus = true;
		}
		if (carro.getValorCarro()>0){
			jpql+= " and c.valorCarro = :valor";
			temValor = true;
		}
		if (carro.getAnoFabricacao()>0 && carro.getAnoFabricacao()!=null){
			jpql+= " and c.anoFabricacao = :ano";
			temAno = true;
		}
		
		TypedQuery<Carro> tqry = getEntityManager().createQuery(jpql, Carro.class);
		tqry.setParameter("descricao", descricao);
		if(temValor)
			tqry.setParameter("valor", carro.getVersao().getValor());
		if (temModelo)
			tqry.setParameter("modelo", carro.getVersao().getModeloCarro().getCodigo());
		if (temMarca )
			tqry.setParameter("marca", carro.getVersao().getModeloCarro().getMarcaCarro().getCodigo());
		if (temFabricante)
			tqry.setParameter("fabricante", carro.getVersao().getModeloCarro().getMarcaCarro().getFabricante().getCodigo());
		if (temStatus)
			tqry.setParameter("status", carro.getStatus());
		if(temCentro)
			tqry.setParameter("centro", carro.getCentro().getCodigo());
		if(temF){
			tqry.setParameter("fabricante", f.getCodigo());
		}
		if(temM){
			tqry.setParameter("marca",m.getCodigo());
		}
		if(temMod){
			tqry.setParameter("modelo",mod.getCodigo());
		}
		if(temAno){
			tqry.setParameter("ano",carro.getAnoFabricacao());
		}
		if(temCor)
			tqry.setParameter("cor","%"+carro.getCor()+"%");
		if(temItem){
		for(ItemSerieCarro i : itensSelecionado){
				tqry.setParameter("item"+i.getCodigo(), i.getDescricao());
			}
		}
		if(temAcessorio){
			for(AcessorioCarro a : acessoriosSelecionado){
				tqry.setParameter("acessorio"+a.getCodigo(), a.getDescricao());
			}
		}
		if(temVersao){
			tqry.setParameter("versao", carro.getVersao().getCodigo());
		}
		return tqry.getResultList();
	}

	//select c from Carro c , IN (c.versao.itens) as i  where i.descricao= :item
}
