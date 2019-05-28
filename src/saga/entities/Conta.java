package saga.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe equivalente a uma conta de um cliente. Controla o débito de um cliente referente a um fornecedor. Também
 * contém as compras feitas e que o pagamento ainda é pendente.
 * 
 * @author guimcarneiro
 *
 */
public class Conta {

	/**
	 * String contendo o cpf do cliente dono da conta
	 */
	private String cpf;
	
	/**
	 * String contendo o nome do fornecedor ao qual o cliente deve
	 */
	private String fornecedor;
	
	/**
	 * double contendo o débito pendente do cliente dono da conta
	 */
	private double debito;
	
	/**
	 * List contendo todas as compras pendentes de pagamento feitas pelo cliente dono da conta
	 */
	private List<Compra> compras;
	
	/**
	 * Constrói uma conta a partir do cpf do cliente dono da conta e do nome do Fornecedor nominal a conta
	 * 
	 * @param cpf
	 * @param fornecedor
	 */
	public Conta(String cpf, String fornecedor) {
		this.cpf = cpf;
		this.fornecedor = fornecedor;
		this.debito = 0.0;
		this.compras = new ArrayList<Compra>();
	}

	/**
	 * Não possui retorno. Adiciona uma compra às compras já feitas pelo cliente. Quando uma Compra é adicionada,
	 * o débito pendente é aumentado no valor da Compra adicionada.
	 * 
	 * @param compra Compra a ser adicionada às compras já feitas por um cliente
	 */
	public void adicionaCompra(Compra compra) {
		this.compras.add(compra);
		this.setDebito();
	}

	/**
	 * Retorna String contendo o cpf do cliente dono da conta.
	 * 
	 * @return String contendo o cpf do cliente dono da conta
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Retorna String contendo o nome do fornecedor nominal a conta.
	 * 
	 * @return String contendo o nome do fornecedor nominal a conta
	 */
	public String getFornecedor() {
		return fornecedor;
	}

	/**
	 * Retorna uma List de Compras contendo todas as compras pendentes de um cliente
	 * 
	 * @return
	 */
	public List<Compra> getCompras() {
		return compras;
	}
	
	/**
	 * Não possui retorno. Redefine o débito da conta igualando o débito à soma dos preços das compras pendentes que a conta possui.
	 */
	private void setDebito() {
		this.debito = 0.0;
		if(!this.compras.isEmpty()) {
			for(Compra compra: this.compras) {
				this.debito += compra.getProduto().getPreco();
			}
		}
	}
	
	/**
	 * Retorna double contendo o valor do débito pendente do cliente ao fornecedor.
	 * 
	 * @return double contendo o valor do débito pendente
	 */
	public double getDebito() {
		return this.debito;
	}

	/**
	 * Não possui retorno. Realiza o pagamento completo do débito pendente existente.
	 */
	public void realizaPagamento() {
		this.debito = 0.0;
	}
	
	/**
	 * Retorna booleano sobre a existência ou não de valores pendentes em uma conta de um cliente para com um fornecedor.
	 * True para uma conta totalmente quitada, false caso contrário.
	 * 
	 * @return true para uma conta totalmente quitada, false caso contrário
	 */
	public boolean isQuitado() {
		if(this.debito == 0.0) {
			return true;
		}
		return false;
	}
	
}
