package saga.controllers;

import java.util.HashMap;
import java.util.Map;

import saga.entities.Fornecedor;

/**
 * Essa classe gerencia fornecedores: cadastra, edita, retorna e remove fornecedores. Representa
 * um controlador de fornecedores.
 * 
 * @author Guilherme de Melo Carneiro
 *
 */
public class FornecedoresController {

	/**
	 * HashMap que armazena fornecedores utilizado como chave de acesso o seu nome.
	 */
	private Map<String, Fornecedor> fornecedores;
	
	/**
	 * Constroi um controlador de fornecedores.
	 */
	public FornecedoresController() {
		this.fornecedores = new HashMap<String, Fornecedor>();
	}
	
	/**
	 * Retorna uma String com o nome do fornecedor cadastrado. Cadastra um fornecedor
	 * com base em seu nome, seu email e seu telefone. Não se pode cadastrar mais de um fornecedor
	 * com o mesmo nome. Caso o fornecedor que se quer cadastrar já exista no sistema, será retornado
	 * uma IllegalArgumentException. Parâmetros nulos ou vazios retornaram NullPointerException e IllegalArgumentException,
	 * respectivamente. Lança uma IllegalArgumentException quando o cadastro for mal-sucedido.
	 * 
	 * @param nome String do nome do fornecedor
	 * @param email String do email do fornecedor
	 * @param telefone String do telefone do fornecedor
	 * @return String contendo o nome do fornecedor cadastrado
	 */
	public String cadastraFornecedor(String nome, String email, String telefone) {
		if(this.fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Fornecedor já existente no sistema");
		}
		this.fornecedores.put(nome, new Fornecedor(nome, email, telefone));
		return nome;
	}
	
	/**
	 * Retorna booleano sobre o sucesso da edição do email de um cliente já cadastrado.
	 * Caso não exista um cliente com o nome passado como parâmetro, será retornado false.
	 * 
	 * @param nome String com o nome do fornecedor
	 * @param emailNovo String com o novo email que será do fornecedor
	 * @return true para uma edição de email bem-sucedida, false caso contrário
	 */
	private boolean editaEmailFornecedor(String nome, String emailNovo) {
		if(!this.fornecedores.containsKey(nome)) {
			return false;
		}
		this.fornecedores.get(nome).setEmail(emailNovo);
		return true;
	}
	
	/**
	  * Retorna booleano sobre o sucesso da edição do telefone de um cliente já cadastrado.
	 * Caso não exista um cliente com o nome passado como parâmetro, será retornado false.
	 * 
	 * @param nome String com o nome do fornecedor
	 * @param telefoneNovo String com o novo telefone que será do fornecedor
	 * @return true para uma edição de telefone bem-sucedida, false caso contrário
	 */
	private boolean editaTelefoneFornecedor(String nome, String telefoneNovo) {
		if(!this.fornecedores.containsKey(nome)) {
			return false;
		}
		this.fornecedores.get(nome).setTelefone(telefoneNovo);
		return true;
	}
	
	/**
	 * Retorna um booleano sobre o sucesso da edição de informação de um fornecedor. Edita uma informação
	 * com base no nome do fornecedor, no atributo que se quer alterar e na nova informação que será sobreposta
	 * ao atributo passado como parâmetro em "atributo". Caso não exista o fornecedor passado como parâmetro,
	 * false será retornado.
	 * 
	 * @param nome String contendo o nome do fornecedor
	 * @param atributo String contendo o atributo do fornecedor que se quer alterar: "email" ou "telefone"
	 * @param informacao String contendo a nova informação que será sobreposta ao atributo escolhido
	 * @return true para uma edição de informação de fornecedor bem-sucedida, false caso contrário
	 */
	public boolean editaFornecedor(String nome, String atributo, String informacao) {
		if(!this.fornecedores.containsKey(nome)) {
			return false;
		}
		if("email".equals(atributo.toLowerCase())) {
			this.editaEmailFornecedor(nome, informacao);
			return true;
		}
		if("telefone".equals(atributo.toLowerCase())) {
			this.editaTelefoneFornecedor(nome, informacao);
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna String contendo informações sobre um fornecedor. Caso não exista um fornecedor com o
	 * nome passado como parâmetro, será retornado null.
	 * 
	 * @param nome String com o nome do fornecedor
	 * @return String contendo informações sobre o fornecedor buscado
	 */
	public String imprimeFornecedor(String nome) {
		if(!this.fornecedores.containsKey(nome)) {
			return null;
		}
		return this.fornecedores.get(nome).toString();
	}
	
	/**
	 * Retorna um booleano sobre o sucesso de uma remoção de um fornecedor. Caso o fornecedor não exista,
	 * será retornado false.
	 * 
	 * @param nomeFornecedor String com o nome do fornecedor
	 * @return true para uma remoção bem-sucedida, false caso contrário
	 */
	public boolean removeFornecedor(String nomeFornecedor) {
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			return false;
		}
		this.fornecedores.remove(nomeFornecedor);
		return true;
	}
	
	/**
	 * Retorna booleano sobre o sucesso ou não do cadastro de um produto a um fornecedor. Caso
	 * não exista o fornecedor passado como parâmetro, será retornado null. Caso sejam passados
	 * valores nulos ou vazios para nomeProduto, precoProduto ou descProduto, serão lançadas
	 * NullPointerException e IllegalArgumentException, respectivamente. 
	 * 
	 * @param nomeFornecedor String do nome do fornecedor ao qual se quer cadastrar o produto
	 * @param nomeProduto String do nome do produto a ser cadastrado
	 * @param precoProduto double do preco do produto a ser cadastrado
	 * @param descProduto String da descrição do produto a ser cadastrado
	 * @return true para um cadastro bem-sucedido, false caso contrário
	 */
	public boolean cadastraProduto(String nomeFornecedor, String nomeProduto, double precoProduto, String descProduto) {
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			return false;
		}
		return this.fornecedores.get(nomeFornecedor).adicionaProduto(nomeProduto, precoProduto, descProduto);
	}
	
	/**
	 * Retorna uma String com informações sobre um produto de um fornecedor e um nome passados como
	 * parâmetro. Caso não exista o fornecedor passado como parâmetro, retornará null. Caso não exista
	 * o produto buscado, retornará null. 
	 * 
	 * @param nomeFornecedor String do nome do fornecedor ao qual deve pertencer o produto
	 * @param nomeProduto String do nome do produto que quer ser buscado
	 * @return String contendo informacoes sobre o produto buscado, null caso não exista tal produto
	 */
	public String imprimeProduto(String nomeFornecedor, String nomeProduto) {
		if(this.fornecedores.get(nomeFornecedor) == null) {
			return null;
		}
		if(this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto) == null) {
			return null;
		}
		return this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto);
	}
	
	/**
	 * Retorna String contendo as informações de todos os fornecedores. Caso não haja fornecedores
	 * cadastrados, retornará uma String vazia.
	 * 
	 * @return String contendo as informações de todos os fornecedores
	 */
	public String imprimeFornecedoresAll() {
		String mensagem = "";
			for(String fornec: this.fornecedores.keySet()) {
				mensagem += this.fornecedores.get(fornec).toString() + " | ";
			}
			if(!"".equals(mensagem)) {
				mensagem = mensagem.substring(0, mensagem.length()-3);
			}
		return mensagem;
	}
	
	/**
	 * Retorna String contendo todos os produtos pertencentes a um fornecedor. Caso não haja
	 * produtos cadastrados para um fornecedor, ou não haja fornecedor com o nome passado como
	 * parâmetro, será retornada uma String vazia.
	 * 
	 * @param nomeFornecedor String contendo o nome do fornecedor
	 * @return String contendo informações sobre todos os produtos cadastrados para um fornecedor, null
	 * caso não exista o fornecedor recebido ou não exista produtos cadastrados para tal fornecedor
	 */
	public String imprimeProdutosFornecedor(String nomeFornecedor) {
		String mensagem = "";
		if(this.fornecedores.get(nomeFornecedor) == null) {
			return null;
		}
		if("".equals(this.fornecedores.get(nomeFornecedor).getProdutosAll())) {
			return null;
		}
		mensagem += this.fornecedores.get(nomeFornecedor).getProdutosAll();
		return mensagem;
	}
	
	/**
	 * Retorna String contendo informações sobre todos os produtos cadastrados no sistema, de todos os
	 * fornecedores. Caso não haja fornecedores cadastrados, será retornado uma String vazia. Caso
	 * não haja produtos cadastrados, será retornado uma String vazia. Cada produto é dividido na String
	 * por uma "|".
	 * 
	 * @return String contendo informações sobre todos os produtos cadastrados no sistema, de todos os
	 * fornecedores
	 */
	public String imprimeProdutosAll() {
		String mensagem = "";
		for(String chaveFornec: this.fornecedores.keySet()) {
			mensagem += this.fornecedores.get(chaveFornec).getProdutosAll() + " | ";
		}
		if(!"".equals(mensagem)) {
			mensagem = mensagem.substring(0, mensagem.length()-3);
		}
		return mensagem;
	}
	
	/**
	 * Retorna um booleano sobre o sucesso da edição do preço de um produto. Caso o fornecedor inserido
	 * não exista, será retornado false. Caso não exista o produto inserido, será retornado false.
	 * Não são aceitos valores negativos para o preço, caso o faça, será lançada uma IllegalArgumentException.
	 * 
	 * @param nomeFornecedor String com o nome do fornecedor
	 * @param nomeProduto String com o nome do produto
	 * @param precoNovo double com o novo preco do produto
	 * @return true para uma edição de preço bem-sucedida, false caso contrário
	 */
	public boolean editarPrecoProduto(String nomeFornecedor, String nomeProduto, double precoNovo) {
		if(this.fornecedores.get(nomeFornecedor) == null) {
			return false;
		}
		return this.fornecedores.get(nomeFornecedor).editaPrecoProduto(nomeProduto, precoNovo);
	}
	
	/**
	 * Retorna um booleano sobre o sucesso da remoção de um produto de um fornecedor. Caso não exista
	 * o fornecedor inserido, retornará false. Caso não exista o produto inserido, retornará false.
	 * 
	 * @param nomeFornecedor String com o nome do fornecedor que deletará seu produto
	 * @param nomeProduto String com o nome do produto que será deletado
	 * @return true para uma remoção bem-sucedida, false caso contrário
	 */
	public boolean removeProduto(String nomeFornecedor, String nomeProduto) {
		if(this.fornecedores.get(nomeFornecedor) == null) {
			return false;
		}
		if(this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto) == null) {
			return false;
		}
		this.fornecedores.get(nomeFornecedor).removeProduto(nomeProduto);
		return true;
	}
	
	
}
