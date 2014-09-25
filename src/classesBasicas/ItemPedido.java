package classesBasicas;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ItemPedido extends ObjetoGeral {

	@ManyToOne
	private Pedido pedido;
	@OneToOne
	private Carro carro;
	@OneToOne
	private AcessorioCarro acessCarro;
	private double valorUnitario;
	private int quantidade;
	
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public AcessorioCarro getAcessCarro() {
		return acessCarro;
	}
	public void setAcessCarro(AcessorioCarro acessCarro) {
		this.acessCarro = acessCarro;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
