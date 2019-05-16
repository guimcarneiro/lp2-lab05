package saga.entities;

import java.util.List;

/**
 * 
 * @author guimcarneiro
 *
 */
public class Combo extends Produto{
	
	/**
	 * 
	 */
	private double precoOriginal;
	
	/**
	 * 
	 */
	private double fator;
	
	/**
	 * 
	 */
	private List<Produto> produtos;
	
	/**
	 * 
	 * @param nome
	 * @param descricao
	 * @param produtos
	 * @param fator
	 */
	public Combo(String nome, String descricao, List<Produto>produtos, double fator) {
		super(nome, 0.0, descricao);
		
		double preco = 0.0;
		for(Produto produto: produtos) {
			preco += produto.getPreco();
		}
		this.precoOriginal = preco;
		super.setPreco(preco*(1.0-fator));
		
		this.fator = fator;
		this.produtos = produtos;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getFator() {
		return this.fator;
	}
	
	/**
	 * 
	 * @param fator
	 */
	public void setFator(double fator) {
		if(fator < 0.0 || fator >= 1.0) {
			throw new IllegalArgumentException("Fator inválido");
		}
		this.fator = fator;
		this.setPreco(this.precoOriginal*(1.0-this.fator));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Produto> getProdutos(){
		return this.produtos;
	}
}
