package saga.entities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//Deve ser Comparable entre Datas

public class Conta {

	private String cpf;
	private String fornecedor;
	private double debito;
	private List<Compra> compras;
	
	public Conta(String cpf, String fornecedor) {
		this.cpf = cpf;
		this.fornecedor = fornecedor;
		this.debito = 0.0;
		this.compras = new ArrayList<Compra>();
	}

	public void adicionaCompra(Compra compra) {
		this.compras.add(compra);
		this.setDebito();
	}

	public String getCpf() {
		return cpf;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public List<Compra> getCompras() {
		return compras;
	}
	
	private void setDebito() {
		this.debito = 0.0;
		if(!this.compras.isEmpty()) {
			for(Compra compra: this.compras) {
				this.debito += compra.getProduto().getPreco();
			}
		}
	}
	
	public double getDebito() {
		return this.debito;
	}

	public void realizaPagamento() {
		this.debito = 0.0;
	}
	
	public boolean isQuitado() {
		if(this.debito == 0.0) {
			return true;
		}
		return false;
	}
	
}
