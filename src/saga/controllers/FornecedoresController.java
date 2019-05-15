package saga.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
			throw new IllegalArgumentException("Erro no cadastro de fornecedor: fornecedor ja existe.");
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
	 * lançará NullPointerException. Caso o atributo passado não possa ser editado, lança IllegalArgumentException.
	 * Lançará IllegalArgumentException para atributos vazios e NullPointerException para atributos nulos.
	 * 
	 * @param nome String contendo o nome do fornecedor
	 * @param atributo String contendo o atributo do fornecedor que se quer alterar: "email" ou "telefone"
	 * @param informacao String contendo a nova informação que será sobreposta ao atributo escolhido
	 * @return true para uma edição de informação de fornecedor bem-sucedida
	 */
	public boolean editaFornecedor(String nome, String atributo, String informacao) {
		if(!this.fornecedores.containsKey(nome)) {
			throw new NullPointerException("Erro na exibicao do fornecedor: fornecedor nao existe.");
		}
		if(atributo == null) {
			throw new NullPointerException("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		}
		if(atributo.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		}
		if(informacao == null) {
			throw new NullPointerException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		}
		if(informacao.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		}
		if("nome".equals(atributo.toLowerCase())) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser editado.");
		}
		if("email".equals(atributo.toLowerCase())) {
			this.editaEmailFornecedor(nome, informacao);
			return true;
		}
		if("telefone".equals(atributo.toLowerCase())) {
			this.editaTelefoneFornecedor(nome, informacao);
			return true;
		}
		throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao existe.");
	}
	
	/**
	 * Retorna String contendo informações sobre um fornecedor. Caso não exista um fornecedor com o
	 * nome passado como parâmetro, lançará uma NullPointerException.
	 * 
	 * @param nome String com o nome do fornecedor
	 * @return String contendo informações sobre o fornecedor buscado
	 */
	public String imprimeFornecedor(String nome) {
		if(!this.fornecedores.containsKey(nome)) {
			throw new NullPointerException("Erro na exibicao do fornecedor: fornecedor nao existe.");
		}
		return this.fornecedores.get(nome).toString();
	}
	
	/**
	 * Retorna um booleano sobre o sucesso de uma remoção de um fornecedor. Caso o fornecedor não exista,
	 * lançará NullPointerException.
	 * 
	 * @param nomeFornecedor String com o nome do fornecedor
	 * @return true para uma remoção bem-sucedida
	 */
	public boolean removeFornecedor(String nomeFornecedor) {
		if(nomeFornecedor == null) {
			throw new NullPointerException("Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio.");
		}
		if(nomeFornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio.");
		}
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new NullPointerException("Erro na remocao do fornecedor: fornecedor nao existe.");
		}
		this.fornecedores.remove(nomeFornecedor);
		return true;
	}
	
	/**
	 * Retorna booleano sobre o sucesso ou não do cadastro de um produto a um fornecedor. Caso
	 * não exista o fornecedor passado como parâmetro, será retornado null. Caso sejam passados
	 * valores nulos ou vazios para nomeProduto, precoProduto ou descProduto, serão lançadas
	 * NullPointerException e IllegalArgumentException, respectivamente. Caso não exista o fornecedor
	 * passado como parâmetro, lança NullPointerException. Caso o nome do fornecedor seja vazio ou nulo,
	 * lançará IllegalArgumentException ou NullPointerException, respectivamente.
	 * 
	 * @param nomeFornecedor String do nome do fornecedor ao qual se quer cadastrar o produto
	 * @param nomeProduto String do nome do produto a ser cadastrado
	 * @param precoProduto double do preco do produto a ser cadastrado
	 * @param descProduto String da descrição do produto a ser cadastrado
	 * @return true para um cadastro bem-sucedido
	 */
	public boolean cadastraProduto(String nomeFornecedor, String nomeProduto, double precoProduto, String descProduto) {
		if(nomeFornecedor == null) {
			throw new NullPointerException("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(nomeFornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new NullPointerException("Erro no cadastro de produto: fornecedor nao existe.");
		}
		return this.fornecedores.get(nomeFornecedor).adicionaProduto(nomeProduto, precoProduto, descProduto);
	}
	
	public boolean adicionaCombo(String nomeFornecedor, String nomeCombo, String descricao, double fator, String produtos) {
		if(nomeFornecedor == null) {
			throw new NullPointerException("Erro no cadastro de combo: fornecedor nao pode ser vazio ou nulo.");
		}
		if(nomeFornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: fornecedor nao pode ser vazio ou nulo.");
		}
		/*if(nomeCombo == null) {
			throw new NullPointerException("Erro no cadastro de combo: nome nao pode ser vazio ou nulo.");
		}
		if(nomeCombo.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: nome nao pode ser vazio ou nulo.");
		}
		if(descricao == null) {
			throw new NullPointerException("Erro no cadastro de combo: descricao nao pode ser vazio ou nulo.");
		}
		if(descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: descricao nao pode ser vazio ou nulo.");
		}
		if(produtos == null) {
			throw new NullPointerException("Erro no cadastro de combo: combo deve ter produtos.");
		}
		if(produtos.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: combo deve ter produtos.");
		}*/
		
		if(this.fornecedores.get(nomeFornecedor) == null) {
			throw new NullPointerException("Erro no cadastro de combo: fornecedor nao existe.");
		}
		//try {
		return this.fornecedores.get(nomeFornecedor).adicionaCombo(nomeCombo, descricao, fator, produtos);
		//}catch(Exception e) {
		//	throw new IllegalArgumentException("ERRO EM FORNECEDORESCONTROLLER - adicionaCombo");
		//}
	}
	/**
	 * Retorna uma String com informações sobre um produto de um fornecedor e um nome passados como
	 * parâmetro. Caso não exista o fornecedor passado como parâmetro, retornará null. Caso não exista
	 * o produto buscado, retornará null. 
	 * 
	 * @param nomeFornecedor String do nome do fornecedor ao qual deve pertencer o produto
	 * @param nomeProduto String do nome do produto que quer ser buscado
	 * @param descricaoProduto String da descricao do produto a ser buscado
	 * @return String contendo informacoes sobre o produto buscado, null caso não exista tal produto
	 */
	public String imprimeProduto(String nomeFornecedor, String nomeProduto, String descricaoProduto) {
		if(nomeFornecedor == null) {
			throw new NullPointerException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(nomeFornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(nomeProduto == null) {
			throw new NullPointerException("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		}
		if(nomeProduto.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		}
		if(descricaoProduto == null) {
			throw new NullPointerException("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		}
		if(descricaoProduto.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		}
		if(this.fornecedores.get(nomeFornecedor) == null) {
			throw new NullPointerException("Erro na exibicao de produto: fornecedor nao existe.");
		}
		if(this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto) == null) {
			throw new NullPointerException("Erro na exibicao de produto: produto nao existe.");
		}
		
		
		return this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto);
	}
	
	/**
	 * Retorna String contendo as informações de todos os fornecedores. Caso não haja fornecedores
	 * cadastrados, retornará uma String vazia. A String retornada é organizada de maneira alfabética
	 * de acordo com os nomes dos fornecedores cadastrados.
	 * 
	 * @return String contendo as informações de todos os fornecedores
	 */
	public String imprimeFornecedoresAll() {
		String mensagem = "";
		
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		for(String fornec: this.fornecedores.keySet()) {
			fornecedores.add(this.fornecedores.get(fornec));
		}
		
		Collections.sort(fornecedores);
		for(int i=0;i<fornecedores.size();i++) {
			mensagem += fornecedores.get(i).toString() + " | ";
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
		if(nomeFornecedor == null) {
			throw new NullPointerException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(nomeFornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(this.fornecedores.get(nomeFornecedor) == null) {
			throw new NullPointerException("Erro na exibicao de produto: fornecedor nao existe.");
		}
		
		String mensagem = "";
		
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
	 * por uma "|". A String é organizada em ordem alfabetica de acordo com os nomes dos fornecedores.
	 * 
	 * @return String contendo informações sobre todos os produtos cadastrados no sistema, de todos os
	 * fornecedores
	 */
	public String imprimeProdutosAll() {
		String mensagem = "";
		
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		for(String chaveFornec: this.fornecedores.keySet()) {
			fornecedores.add(this.fornecedores.get(chaveFornec));
		}
		
		Collections.sort(fornecedores);
		for(int i=0;i<fornecedores.size();i++) {
			mensagem += fornecedores.get(i).getProdutosAll() + " | ";
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
	public boolean editarPrecoProduto(String nomeFornecedor, String nomeProduto, String descricaoProduto, double precoNovo) {
		if(nomeProduto == null) {
			throw new NullPointerException("Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		}
		if(nomeProduto.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		}
		if(nomeFornecedor == null) {
			throw new NullPointerException("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(nomeFornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(descricaoProduto == null) {
			throw new NullPointerException("Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		}
		if(descricaoProduto.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		}
		if(this.fornecedores.get(nomeFornecedor) == null) {
			throw new NullPointerException("Erro na edicao de produto: fornecedor nao existe.");
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
	public boolean removeProduto(String nomeFornecedor, String nomeProduto, String descricaoProduto) {
		if(nomeFornecedor == null) {
			throw new NullPointerException("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(nomeFornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		if(nomeProduto == null) {
			throw new NullPointerException("Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		}
		if(nomeProduto.trim().isEmpty()){
			throw new IllegalArgumentException("Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		}
		if(descricaoProduto == null) {
			throw new NullPointerException("Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		}
		if(descricaoProduto.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		}
		if(this.fornecedores.get(nomeFornecedor) == null) {
			throw new NullPointerException("Erro na remocao de produto: fornecedor nao existe.");
		}
		if(this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto) == null) {
			throw new NullPointerException("Erro na remocao de produto: produto nao existe.");
		}
		this.fornecedores.get(nomeFornecedor).removeProduto(nomeProduto);
		return true;
	}
	
	
}
