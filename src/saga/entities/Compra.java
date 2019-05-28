package saga.entities;

import java.util.Date;

/**
 * Classe que representa uma Compra feita por um Cliente. Uma compra possui uma data e um produto.
 * 
 * @author Guilherme de Melo Carneiro
 *
 */
public class Compra {

	/**
	 * Date contendo a respectiva data da compra
	 */
	private Date data;
	
	/**
	 * Produto que foi comprado
	 */
	private Produto produto;
	
	/**
	 * Constroi uma compra com base na data de compra do produto, e do produto em si
	 * 
	 * @param data Date da compra
	 * @param produto Produto comprado
	 */
	public Compra(Date data, Produto produto) {
		this.data = data;
		this.produto = produto;
	}

	/**
	 * Retorna um Date contendo a data de quando aconteceu a compra.
	 * @return Date da data da compra
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Retorna o produto comprado.
	 * @return retorna o produto comprado
	 */
	public Produto getProduto() {
		return produto;
	}
	
}
