package saga.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saga.entities.Compra;
import saga.entities.Conta;
import saga.entities.Produto;

public class ContasController {
	
	/**
	 * chave é nome do fornecedor, objeto é a Conta dele para o cliente
	 */
	private Map<String, Conta> contas;
	
	public ContasController() {
		this.contas = new HashMap<String, Conta>();
	}
	public void adicionaCompra(String cpf, String fornecedor, Date data, Produto produto) {
		if(this.contas.containsKey(fornecedor)) {
			this.contas.get(fornecedor).adicionaCompra(new Compra(data, produto));
		}else {
			this.contas.put(fornecedor, new Conta(cpf, fornecedor));
			this.contas.get(fornecedor).adicionaCompra(new Compra(data, produto));
		}
	}
	
	public double getDebitoFornecedor(String fornecedor) {
		if(!this.contas.containsKey(fornecedor)) {
			throw new NullPointerException("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
		}
		return this.contas.get(fornecedor).getDebito();
	}
	
	public boolean existeContas() {
		return this.contas.values().isEmpty();
	}
	
	public boolean existeContaFornecedor(String fornecedor) {
		return this.contas.containsKey(fornecedor);
	}
	
	public String imprimeConta(String fornecedor, String nome) {
		String mensagem = "Cliente: " + nome +" | " + fornecedor + " | ";
		
		for(Compra compra: this.contas.get(fornecedor).getCompras()) {
			mensagem += compra.getProduto().getNome() + " - " + new SimpleDateFormat("dd-MM-yyyy").format(compra.getData()) + " | ";
		}
		
		mensagem = mensagem.substring(0, mensagem.length()-3);
		
		return mensagem;
	}
	public String imprimeContasAll(String nome) {
		
		String mensagem = "Cliente: " + nome +" | ";
		List<String> nomesFornecedores = new ArrayList<String>(this.contas.keySet());
		Collections.sort(nomesFornecedores);
		
		for(String name: nomesFornecedores) {
			mensagem += this.contas.get(name).getFornecedor() + " | ";
			for(Compra compra: this.contas.get(name).getCompras()) {
				mensagem += compra.getProduto().getNome() + " - " + new SimpleDateFormat("dd-MM-yyyy").format(compra.getData()) + " | ";
			}
		}
		mensagem = mensagem.substring(0, mensagem.length()-3);
		return mensagem;
	}

}
