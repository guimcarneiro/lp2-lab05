package saga.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saga.entities.Produto;

/**
 * Esta classe gerencia produtos: cadastra, edita, retorna e remove produtos. 
 * 
 * @author Guilherme de Melo Carneiro
 *
 */
public class ProdutosController {

	/**
	 * HashMap que armazena os produtos cadastrados, no qual a chave de acesso é o nome do produto.
	 */
	private Map<String, Produto> produtos;
	
	/**
	 * Constroi um controlador de produtos.
	 */
	public ProdutosController() {
		this.produtos = new HashMap<String, Produto>();
	}
	
	/**
	 * Retorna um booleano sobre o sucesso do cadastro de um produto. Um produto possui um nome, um
	 * preço válido e uma descrição. Caso sejam inseridos valores nulos ou vazios, será lançada NullPointerException
	 * ou IllegalArgumentException, respectivamente. Para o caso de valores negativos para o preço
	 * do produto, será lançada uma exceção IllegalArgumentException. Caso o produto que se quer cadastrar
	 * já exista no sistema, retornará false. Um produto é identificado unicamente pelo seu nome e pela
	 * sua descrição.
	 * 
	 * @param nome String com o nome do produto
	 * @param preco double com preço do produto
 	 * @param descricao String com a descrição do produto
	 * @return true para um cadastro válido, false caso contrário
	 */
	public boolean cadastraProduto(String nome, double preco, String descricao) {
		if(!this.produtos.containsKey(nome)) {
			this.produtos.put(nome, new Produto(nome, preco, descricao));
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna String com informações sobre o produto com o nome inserido como parâmetro. Caso não exista
	 * tal produto, será retornado null.
	 * 
	 * @param nome String com o nome do produto
	 * @return String com informações sobre o produto buscado, null caso não exista tal produto
	 */
	public String consultaProduto(String nome) {
		if(this.produtos.get(nome) == null) {
			return null;
		}
		return this.produtos.get(nome).toString();
	}
	
	/**
	 * Retorna uma List de String contendo informações sobre cada Produto já cadastrado no sistema.
	 * Caso não haja produtos cadastrados, será retornado uma List vazia.
	 * 
	 * @return List de String contendo informações sobre cada produto
	 */
	public List<String> listaProdutos() {
		List<String> produtosAll = new ArrayList<String>();
		
		for(String nomeProduto: this.produtos.keySet()) {
			produtosAll.add(this.produtos.get(nomeProduto).toString());
		}
		
		return produtosAll;
	}
	
	/**
	 * Retorna um booleano sobre o sucesso deu ma remoção de um produto com base em seu nome. Caso
	 * não exista o produto respectivo ao nome inserido, será retornado false.
	 * 
	 * @param nome String com o nome do produto a ser removido
	 * @return true para uma remoção bem-sucedida, false caso contrário
	 */
	public boolean removeProduto(String nome) {
		if(!this.produtos.containsKey(nome)) {
			return false;
		}
		this.produtos.remove(nome);
		return true;
	}
	
	/**
	 * Retorna um booleano sobre o sucesso de uma edição do preço de um produto. Caso o nome inserido
	 * não corresponda a nenhum produto já cadastrado no sistema, será retornado false. Caso o novo preço
	 * inserido como parâmetro seja negativo, retornará false.
	 * 
	 * @param nome String com o nome do produto
	 * @param precoNovo novo preço que o produto em questão adotará
	 * @return true para uma edição de preço bem-sucedida, false caso contrário
	 */
	public boolean editaPrecoProduto(String nome, double precoNovo) {
		if(!this.produtos.containsKey(nome)) {
			return false;
		}
		if(precoNovo < 0.0) {
			return false;
		}
		this.produtos.get(nome).setPreco(precoNovo);
		return true;
	}
	
	/**
	 * Retorna um booleano sobre a existêcia de um produto no sistema.
	 * 
	 * @param nome String com o nome do produto que se quer saber se existe no sistema
	 * @return true para a existência de um produto com o nome passado como parâmetro, false caso contrário
	 */
	public boolean existeProduto(String nome) {
		if(this.produtos.containsKey(nome)) {
			return true;
		}
		return false;
	}
}
