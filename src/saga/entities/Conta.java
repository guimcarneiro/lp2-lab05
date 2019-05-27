package saga.entities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//Deve ser Comparable entre Datas

public class Conta {

	private String cpf;
	private String fornecedor;
	private List<Compra> compras;
	
	public Conta(String cpf, String fornecedor) {
		this.cpf = cpf;
		this.fornecedor = fornecedor;
		this.compras = new ArrayList<Compra>();
	}

	public void adicionaCompra(Compra compra) {
		this.compras.add(compra);
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
	
	public double getDebito() {
		double debito = 0.00;
		for(Compra compra: this.compras) {
			debito += compra.getProduto().getPreco();
		}
		return debito;
	}
	
	
}
