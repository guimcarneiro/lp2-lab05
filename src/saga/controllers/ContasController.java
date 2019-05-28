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


/**
 * 
 * Classe responsável por gerênciar as contas de um Cliente: adicionar conta, adicionar compra, finalizar débito, etc.
 * 
 * @author Guilherme de Melo carneiro
 *
 */
public class ContasController {
	
	/**
	 * HashMap contendo as contas de um Cliente, onde chave é nome do fornecedor e objeto é a Conta dele para o cliente
	 */
	private Map<String, Conta> contas;
	
	/**
	 * Constrói um gerenciador de Contas
	 */
	public ContasController() {
		this.contas = new HashMap<String, Conta>();
	}
	
	/**
	 * Não possui retorno. Adiciona uma compra de um cliente ao sistema. Uma compra é composta por 
	 * um produto, uma data de compra, um fornecedor que possui o produto e o cliente que o comprou.
	 * 
	 * @param cpf String contendo o cpf do cliente
	 * @param fornecedor String contendo o nome do fornecedor
	 * @param data Date contendo a data de compra do produto
	 * @param produto Produto do Fornecedor que foi comprado pelo Cliente
	 */
	public void adicionaCompra(String cpf, String fornecedor, Date data, Produto produto) {
		if(this.contas.containsKey(fornecedor)) {
			this.contas.get(fornecedor).adicionaCompra(new Compra(data, produto));
		}else {
			this.contas.put(fornecedor, new Conta(cpf, fornecedor));
			this.contas.get(fornecedor).adicionaCompra(new Compra(data, produto));
		}
	}
	
	/**
	 * Retorna um double com o valor do débito que o cliente deve a um determinado fornecedor. Lança Exceptions
	 * para quando não existir uma Conta no sistema, quando não existir tal fornecedor.
	 * 
	 * @param fornecedor String contendo o nome do fornecedor
	 * @return double com o valor do débito que o cliente deve
	 */
	public double getDebitoFornecedor(String fornecedor) {
		if(!this.contas.containsKey(fornecedor)) {
			throw new NullPointerException("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
		}
		if(this.contas.get(fornecedor).isQuitado()) {
			throw new IllegalArgumentException("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
		}
		return this.contas.get(fornecedor).getDebito();
	}
	
	/**
	 * Não possui retorno. Realiza o pagamento da conta de um cliente para com o fornecedor passado como parâmetro.
	 * Lança Exception para quando o cliente não possui conta para com o fornecedor passado como parâmetro. Uma vez
	 * que o cliente realizou o pagamento do débito, a conta respectiva àquele fornecedor é removida do sistema.
	 * 
	 * @param fornecedor String contendo o nome do fornecedor nominal à conta do cliente
	 */
	public void realizaPagamento(String fornecedor) {
		if(!this.existeContaFornecedor(fornecedor)) {
			throw new IllegalArgumentException("Erro no pagamento de conta: nao ha debito do cliente associado a este fornecedor.");
		}
		this.contas.get(fornecedor).realizaPagamento();
		this.contas.remove(fornecedor);
	}
	
	/**
	 * Retorna booleano sobre a existência de contas pertencentes a um cliente. Retorna true para quando houver pelo menos
	 * uma conta existente para o cliente, falso caso contrário.
	 * 
	 * @return true para pelo menos uma conta pendente no sistema, false caso contrário
	 */
	public boolean existeContas() {
		return this.contas.values().isEmpty();
	}
	
	/**
	 * Retorna booleano sobre a existência de uma conta pertencente ao cliente respectivo a um fornecedor específico. Retorna
	 * true para quando houver pedência do cliente para com tal fornecedor, false caso contrário.
	 * 
	 * @param fornecedor String contendo o nome do fornecedor
	 * @return true para existência de pendência do Cliente para com o fornecedor, false caso contrário
	 */
	public boolean existeContaFornecedor(String fornecedor) {
		return this.contas.containsKey(fornecedor);
	}
	
	
	/**
	 * Retorna String contendo informações sobre a conta pendente de um cliente para com um fornecedor.
	 * 
	 * @param fornecedor String contendo o nome do fornecedor
	 * @param nome String contendo o nome do cliente
	 * @return String contendo informações sobre a conta cliente-fornecedor
	 */
	public String imprimeConta(String fornecedor, String nome) {
		String mensagem = "Cliente: " + nome +" | " + fornecedor + " | ";
		
		for(Compra compra: this.contas.get(fornecedor).getCompras()) {
			mensagem += compra.getProduto().getNome() + " - " + new SimpleDateFormat("dd-MM-yyyy").format(compra.getData()) + " | ";
		}
		
		mensagem = mensagem.substring(0, mensagem.length()-3);
		
		return mensagem;
	}
	
	/**
	 * Retorna String contendo informações sobre todas as contas que um cliente possui para com vários fornecedores.
	 * 
	 * @param nome String contendo o nome do cliente
	 * @return String contendo informações sobre todas as contas que um cliente possui
	 */
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
