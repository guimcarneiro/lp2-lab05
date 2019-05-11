package saga.entities;

/**
 * Entidade que representa um Produto no sistema. Um produto possui um nome, um preço e uma descrição.
 * Um produto é identificado unicamente a partir de seu nome e de sua descrição.
 * 
 * @author Guilherme de Melo Carneiro
 *
 */
public class Produto {
	
	/**
	 * String que representa o nome do produto. Um produto é identificado unicamente com base no seu nome e na sua descrição.
	 */
	private String nome;
	
	/**
	 * Double que representa o preco do produto. Não pode ser negativo.
	 */
	private double preco;
	
	/**
	 * String que representa a descrição do produto. Um produtivo é identificado unicamente com base no seu nome e na sua descrição.
	 */
	private String descricao;
	
	/**
	 * Constroi um produto com base em nome, preco e descrição. NullPointerException será lançada para 
	 * valores nulos passados como parâmetro. IllegalArgumentException será lançada para valores vazios
	 * passados como parâmetro. IllegalArgumentException será lançada para valores negativos passado como
	 * preço.
	 * 
	 * @param nome String que representa o nome do produto
	 * @param preco double que representa o preco do produto
	 * @param descricao String que representa a descrição do produto
	 */
	public Produto(String nome, double preco, String descricao) {
		if(nome == null) {
			throw new NullPointerException("O nome não pode ser nulo");
		}
		if(descricao == null){
			throw new NullPointerException("A descrição não pode ser nula");
		}
		if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O CPF não pode ser vazio");
		}
		if(preco < 0.0) {
			throw new IllegalArgumentException("O preço não pode ser negativo");
		}
		if(descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("O e-mail não pode ser vazio");
		}
		
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	/**
	 * Retorna um double relativo ao preço cadastrado do produto.
	 * 
	 * @return double contendo o preço do produto
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Não possui retorno. Redefine o preço do produto com base no valor passado como parâmetro.
	 * Será lançada IllegalArgumentException caso seja inserido um preço negativo.
	 * 
	 * @param preco double respectivo ao novo valor que o produto adotará caso a alteração seja válida
	 */
	public void setPreco(double preco) {
		if(preco < 0.0) {
			throw new IllegalArgumentException("O preço não pode ser negativo");
		}
		this.preco = preco;
	}

	/**
	 * Retorna o nome do produto.
	 * 
	 * @return String relativa ao nome do produto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna a descrição do produto.
	 * 
	 * @return String relativa a descriçao do produto
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Retorna um inteiro que identifica um produto unicamente. Esse valor é gerado com base no nome e
	 * com base na descrição do produto. Dois produtos com os mesmos nomes e descrições serão considerados
	 * iguais.
	 * 
	 * @return inteiro que identifica unicamente o produto
	 */
	@Override
	public int hashCode() {
		return (this.nome + this.descricao).hashCode();
	}
	
	/**
	 * Retorna uma String contendo informações sobre um produto. Essa String segue o seguinte o formato:
	 * "NOME - DESCRICAO - R$X.YY"
	 * 
	 * @return String com informações sobre o produto
	 */
	@Override
	public String toString() {
		return this.nome + " - " + this.descricao + " - R$" + String.format("%.2f", this.preco);
	}
}
