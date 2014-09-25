package classesBasicas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import classesBasicas.Pessoa;

@Entity
public class Pedido extends ObjetoGeral {

		@Column(unique=true)
		private String numeroPedido;
		@Temporal(TemporalType.DATE)
		private Date dataCompra;
		@ManyToOne
		private Pessoa pessoa;
		private double valorTotal;
		private double valorDesconto;
		
		
		public String getNumeroPedido() {
			return numeroPedido;
		}
		public void setNumeroPedido(String numeroPedido) {
			this.numeroPedido = numeroPedido;
		}
		public Date getDataCompra() {
			return dataCompra;
		}
		public void setDataCompra(Date dataCompra) {
			this.dataCompra = dataCompra;
		}
		public Pessoa getPessoa() {
			return pessoa;
		}
		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}
		public double getValorTotal() {
			return valorTotal;
		}
		public void setValorTotal(double valorTotal) {
			this.valorTotal = valorTotal;
		}
		public double getValorDesconto() {
			return valorDesconto;
		}
		public void setValorDesconto(double valorDesconto) {
			this.valorDesconto = valorDesconto;
		}
		
}


