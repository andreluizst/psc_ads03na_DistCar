package classesBasicas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer numero;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="numMovimentacaoOrigem", insertable=true, updatable=true, nullable=true)
	private Movimentacao movimentacaoOrigem;
	
	private Integer notaFiscal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="codCtoOrigem", insertable=true, updatable=true, nullable=false)
	private Centro ctoOrigem;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="codCtoDestino", insertable=true, updatable=true)
	private Centro ctoDestino;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=15)
	private TipoMovimentacao tipoMovimentacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataMovimentacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaAtualizacao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=10)
	private SituacaoMovimentacao situacao;
	
	//@OneToMany(mappedBy="movimentoCarroPK.movimentacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	///@Fetch(FetchMode.SUBSELECT)
	@Transient
	private List<MovimentacaoItem> itens;
	
	
	public Movimentacao() {
		super();
		itens = new ArrayList<MovimentacaoItem>();
		// TODO Auto-generated constructor stub
	}

	public Movimentacao(Integer numero, Integer notaFiscal, Centro ctoOrigem,
			Centro ctoDestino, TipoMovimentacao tipoMovimentacao,
			Date dataMovimentacao, Date dataUltimaAtualizacao,
			SituacaoMovimentacao situacao) {
		this();
		this.numero = numero;
		this.notaFiscal = notaFiscal;
		this.ctoOrigem = ctoOrigem;
		this.ctoDestino = ctoDestino;
		this.tipoMovimentacao = tipoMovimentacao;
		this.dataMovimentacao = dataMovimentacao;
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
		this.situacao = situacao;
	}

	public Movimentacao(Integer notaFiscal, Centro ctoOrigem,
			Centro ctoDestino, TipoMovimentacao tipoMovimentacao,
			Date dataMovimentacao, Date dataUltimaAtualizacao,
			SituacaoMovimentacao situacao) {
		this(null, notaFiscal, ctoOrigem, ctoDestino, tipoMovimentacao, dataMovimentacao,
				dataUltimaAtualizacao, situacao);
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Integer notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Centro getCtoOrigem() {
		return ctoOrigem;
	}

	public void setCtoOrigem(Centro ctoOrigem) {
		this.ctoOrigem = ctoOrigem;
	}

	public Centro getCtoDestino() {
		return ctoDestino;
	}

	public void setCtoDestino(Centro ctoDestino) {
		this.ctoDestino = ctoDestino;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	public SituacaoMovimentacao getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoMovimentacao situacao) {
		this.situacao = situacao;
	}

	public List<MovimentacaoItem> getItens() {
		return itens;
	}

	public void setItens(List<MovimentacaoItem> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Movimentacao [numero=" + numero + ", notaFiscal=" + notaFiscal
				+ ", ctoOrigem=" + ctoOrigem + ", ctoDestino=" + ctoDestino
				+ ", tipoMovimentacao=" + tipoMovimentacao
				+ ", dataMovimentacao=" + dataMovimentacao
				+ ", dataUltimaAtualizacao=" + dataUltimaAtualizacao
				+ ", situacao=" + situacao + ", itens=" + itens + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	
}
