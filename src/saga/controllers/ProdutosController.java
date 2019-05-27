package saga.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saga.entities.Combo;
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
	 * 
	 */
	private Map<String, Combo> combos;
	/**
	 * Constroi um controlador de produtos.
	 */
	public ProdutosController() {
		this.produtos = new HashMap<String, Produto>();
		this.combos = new HashMap<String, Combo>();
	}
	
	public Produto getProduto(String nome) {
		if(this.produtos.containsKey(nome)) {
			return this.produtos.get(nome);
		}
		if(this.combos.containsKey(nome)) {
			return this.combos.get(nome);
		}
		return null;
	}
	
	/**
	 * Retorna um booleano sobre o sucesso do cadastro de um produto. Um produto possui um nome, um
	 * preço válido e uma descrição. Caso sejam inseridos valores nulos ou vazios, será lançada NullPointerException
	 * ou IllegalArgumentException, respectivamente. Para o caso de valores negativos para o preço
	 * do produto, será lançada uma exceção IllegalArgumentException. Caso o produto que se quer cadastrar
	 * já exista no sistema, lançará uma IllegalArgumentException. Um produto é identificado unicamente pelo seu nome e pela
	 * sua descrição.
	 * 
	 * @param nome String com o nome do produto
	 * @param preco double com preço do produto
 	 * @param descricao String com a descrição do produto
	 * @return true para um cadastro válido
	 */
	public boolean cadastraProduto(String nome, double preco, String descricao) {
		if(!this.produtos.containsKey(nome)) {
			this.produtos.put(nome, new Produto(nome, preco, descricao));
			return true;
		}
		throw new IllegalArgumentException("Erro no cadastro de produto: produto ja existe.");
	}
	
	public boolean cadastraCombo(String nome, String descricao, double fator, String produtos) {
		if(nome == null) {
			throw new NullPointerException("Erro no cadastro de combo: nome nao pode ser vazio ou nulo.");
		}
		if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: nome nao pode ser vazio ou nulo.");
		}
		if(descricao == null) {
			throw new NullPointerException("Erro no cadastro de combo: descricao nao pode ser vazia ou nula.");
		}
		if(descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: descricao nao pode ser vazia ou nula.");
		}
		if(produtos == null) {
			throw new NullPointerException("Erro no cadastro de combo: combo deve ter produtos.");
		}
		if(produtos.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: combo deve ter produtos.");
		}
		if(fator < 0.0 || fator  >= 1.0) {
			throw new IllegalArgumentException("Erro no cadastro de combo: fator invalido.");
		}
		
		if(this.combos.containsKey(nome)) {
			throw new IllegalArgumentException("Erro no cadastro de combo: combo ja existe.");
		}
		//Confere se os produtos passados estão contidos nos Produtos cadastrados
		List<String> prods = new ArrayList<String>();
		
		
		for(String produto: produtos.split(", ")) {
			String nomeProduto = produto.split(" - ")[0];
			if(this.combos.containsKey(nomeProduto)) {
				throw new IllegalArgumentException("Erro no cadastro de combo: um combo nao pode possuir combos na lista de produtos.");
			}
			if(!this.produtos.containsKey(nomeProduto)) {
				throw new NullPointerException("Erro no cadastro de combo: produto nao existe.");
			}
			prods.add(nomeProduto);
		}
		
		//Adiciona os produtos a uma List que será passada para o Combo
		List<Produto> produtosCombo = new ArrayList<Produto>();
		
		for(String nomeProduto: prods) {
			produtosCombo.add(this.produtos.get(nomeProduto));
		}
		
		this.combos.put(nome, new Combo(nome, descricao, produtosCombo, fator));
		return true;
		
	}
	/**
	 * Retorna String com informações sobre o produto com o nome inserido como parâmetro. Caso não exista
	 * tal produto, será retornado null.
	 * 
	 * @param nome String com o nome do produto
	 * @return String com informações sobre o produto buscado, null caso não exista tal produto
	 */
	public String consultaProduto(String nome) {
		if(this.produtos.get(nome) == null && this.combos.get(nome) == null) {
			return null;
		}
		if(this.produtos.get(nome) == null) {
			return this.combos.get(nome).toString();
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
		
		List<Produto> produtosAll = new ArrayList<Produto>(this.produtos.values());
		produtosAll.addAll(this.combos.values());
		
		Collections.sort(produtosAll);
		
		List<String> produtosToStringSorted = new ArrayList<String>();
		for(Produto prod1: produtosAll) {
			produtosToStringSorted.add(prod1.toString());
		}
		
		return produtosToStringSorted;
	}
	
	/**
	 * Retorna um booleano sobre o sucesso deu ma remoção de um produto com base em seu nome. Caso
	 * não exista o produto respectivo ao nome inserido, será retornado false.
	 * 
	 * @param nome String com o nome do produto a ser removido
	 * @return true para uma remoção bem-sucedida, false caso contrário
	 */
	public boolean removeProduto(String nome) {
		if(this.produtos.containsKey(nome)) {
			this.produtos.remove(nome);
			return true;
		}
		else if(this.combos.containsKey(nome)) {
			this.combos.remove(nome);
			return true;
		}
		
		throw new NullPointerException("Erro na remocao de produto: produto nao existe.");
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
			throw new IllegalArgumentException("Erro na edicao de produto: preco invalido.");
		}
		this.produtos.get(nome).setPreco(precoNovo);
		return true;
	}
	
	public boolean editaCombo(String nome, String descricao, double novoFator) {
		if(nome == null) {
			throw new NullPointerException("Erro na edicao de combo: nome nao pode ser vazio ou nulo.");
		}
		if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de combo: nome nao pode ser vazio ou nulo.");
		}
		if(descricao == null) {
			throw new NullPointerException("Erro na edicao de combo: descricao nao pode ser vazia ou nula.");
		}
		if(descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de combo: descricao nao pode ser vazia ou nula.");
		}
		if(novoFator <= 0.0 || novoFator >= 1.0) {
			throw new IllegalArgumentException("Erro na edicao de combo: fator invalido.");
		}
		
		if(!this.combos.containsKey(nome)) {
			throw new NullPointerException("Erro na edicao de combo: produto nao existe.");
		}
		
		this.combos.get(nome).setFator(novoFator);
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
