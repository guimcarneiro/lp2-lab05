package saga.entities;

import saga.controllers.ProdutosController;

/**
 * Entidade que representa um fornecedor no sistema. Uma entidade possui um nome, um email,
 * um telefone e um conjunto de produtos associado a ele. Um fornecedor é identificado 
 * unicamente pelo seu nome.
 * 
 * @author Guilherme de Melo Carneiro
 *
 */
public class Fornecedor implements Comparable<Fornecedor>{

	/**
	 * String que representa o nome do respectivo Fornecedor. Identifica unicamente um Fornecedor.
	 */
	private String nome;
	
	/**
	 * String que representa o email do respectivo Fornecedor.
	 */
	private String email;
	
	/**
	 * String que representa o telefone do respectivo Fornecedor.
	 */
	private String telefone;
	
	/**
	 * Entidade responsável pela gerência dos produtos pertencentes ao Fornecedor.
	 */
	private ProdutosController produtos;
	
	/**
	 * Constroi um Fornecedor a partir de um nome, um e-mail e um telefone. Valores nulos
	 * ou vazios lançam NullPointerException e IllegalArgumentException, respectivamente.
	 * 
	 * @param nome String correspondente ao nome do Fornecedor, irá identificá-lo unicamente
	 * @param email String correspondente ao e-mail do Fornecedor
	 * @param telefone String correspondente ao telefone do Fornecedor
	 */
	public Fornecedor(String nome, String email, String telefone) {
		if(nome == null) {
			throw new NullPointerException("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
		}
		if(email == null) {
			throw new NullPointerException("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
		}
		if(telefone == null) {
			throw new NullPointerException("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");
		}
		if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
		}
		if(email.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
		}
		if(telefone.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");
		}
		
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.produtos = new ProdutosController();
	}

	/**
	 * Retorna String do e-mail do Fornecedor.
	 * 
	 * @return String com o e-mail do Fornecedor
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Não possui retorno. Altera o email atual do Fornecedor por um novo email.
	 * 
	 * @param email Novo e-mail que substituirá o antigo email do fornecedor
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna String do telefone do Fornecedor.
	 * 
	 * @return String com o telefone do Fornecedor
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Não possui retorno. Altera o telefonel atual do Fornecedor para um novo telefone.
	 * 
	 * @param telefone Novo telefone que substituirá o antigo telefone do Fornecedor
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Retorna String com o nome do Fornecedor.
	 * 
	 * @return String com o nome do Fornecedor
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna um booleano sobre o sucesso da operação de adicionar um produto. Os parâmetros
	 * não devem ser nulos ou vazios. O preço do produto não deve ser negativo.
	 * 
	 * @param nome String que será o nome do Produto
	 * @param preco double que será o preco do Produto, não deve ser negativo
	 * @param descricao String que será a descrição do Produto
	 * @return true para uma adição de produto bem-sucedida, false caso contrário
	 */
	public boolean adicionaProduto(String nome, double preco, String descricao) {
		return this.produtos.cadastraProduto(nome, preco, descricao);
	}
	
	public boolean adicionaCombo(String nome, String descricao, double fator, String produtos) {
		return this.produtos.cadastraCombo(nome, descricao, fator, produtos);
	}
	
	/**
	 * Retorna um booleano sobre o sucesso da operação de remover um produto adicionado 
	 * anteriormente com base em seu nome. 
	 * 
	 * @param nome String que representa o nome do Produto que quer se remover
	 * @return true para uma remoção bem-sucedida, false caso contrário
	 */
	public boolean removeProduto(String nome) {
		return this.produtos.removeProduto(nome);
	}
	
	/**
	 * Retorna uma String com informações sobre um produto já adicionado no sistema. A busca é
	 * feita a partir de seu nome. Caso não haja produto armazenado com o nome buscado, será
	 * retornado null. O formato da String retornada será o toString do mesmo.
	 * 
	 * @param nome String do nome do produto que quer se buscar
	 * @return String com informações sobre o produto, caso não haja produto cadastrado com esse nome, null será retornado
	 */
	public String consultaProduto(String nome) {
		return this.produtos.consultaProduto(nome);
	}
	
	public Produto getProduto(String nome) {
		return this.produtos.getProduto(nome);
	}
	
	/**
	 *  Retorna um booleano sobre o sucesso da edição do preço de um produto já cadastrado. Caso o nome
	 *  passado como parâmetro não corresponda a nenhum Produto no sistema, false será retornado. Caso
	 *  seja passado um preço negativo, false será retornado.
	 * 
	 * @param nome String do nome do Produto que se quer alterar o preço
	 * @param precoNovo double do novo preço que o Produto adotará, não deve ser negativo
	 * @return true para uma edição de preço bem-sucecida, false caso contrário
	 */
	public boolean editaPrecoProduto(String nome, double precoNovo) {
		return this.produtos.editaPrecoProduto(nome, precoNovo);
	}
	
	public boolean editaCombo(String nome, String descricao, double novoFator) {
		return this.produtos.editaCombo(nome, descricao, novoFator);
	}
	/**
	 * Retorna String com todos os produtos de um fornecedor. Caso não haja produtos cadastrados, será
	 * retornado uma String vazia. Possuindo Produtos cadastrados, seguirá o seguinte formato:
	 * "NOME_FORNECEDOR - NOME_PRODUTO - DESCRICAO_PRODUTO - R$0.00 | NOME_FORNECEDOR - NOME_PRODUTO_2 - DESCRICAO_PRODUTO_2 - R$1.11 |"
	 * 
	 * @return String com todos os produtos de um fornecedor
	 */
	public String getProdutosAll() {
		String mensagem = "";
		
		for(String produto: this.produtos.listaProdutos()) {
			mensagem += this.nome + " - " + produto + " | ";
		}
		if(!"".equals(mensagem)) {
			mensagem = mensagem.substring(0, mensagem.length()-3);
		}else {
			mensagem = this.nome + " -";
		}
		return mensagem;
	}
	
	/**
	 * Retorna um inteiro que identifica unicamente um Fornecedor. Um fornecedor é identificado
	 * unicamente pelo seu nome.
	 * 
	 * @return int que identifica unicamente um fornecedor
	 */
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
	/**
	 * Retorna um booleano sobre a igualdade entre um objeto e um Fornecedor. Retornará true para
	 * objetos Fornecedor que possuírem o mesmo nome.
	 * 
	 * @param obj objeto qualquer a ser comparado com o fornecedor
	 * 
	 * @return true para um objeto Fornecedor que possui o mesmo nome que o com o que se compara
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		Fornecedor obj1 = (Fornecedor)obj;
		if(this.nome.equals(obj1.nome)) {
			return true;
		}
		return false;
	}
	/**
	 * Retorna String com informações de um Fornecedor. Segue o formato:
	 * "NOME_FORNECEDOR - EMAIL_FORNECEDOR - TELEFONE_FORNECEDOR"
	 * 
	 * @return String com informações de um Fornecedor
	 */
	@Override
	public String toString() {
		return this.nome + " - " + this.email + " - " + this.telefone;
	}

	/**
	 * Retorna inteiro sobre a ordem de um objeto Fornecedor em relação a outro. Caso o comparado venha depois, retorna
	 * um valor negativo, caso sejam iguais retorna 0, caso o comparado venha antes retorna numero positivo. A comparação
	 * acontece de acordo com a ordem alfabética entre os nomes.
	 * 
	 * @param f Fornecedor com o qual se quer comparar
	 * @return antes na ordem alfabética: -1, iguais: 0, depois na ordem alfabética: 1
	 */
	@Override
	public int compareTo(Fornecedor f) {
		return this.nome.compareToIgnoreCase(f.nome);
	}
	
	
}
