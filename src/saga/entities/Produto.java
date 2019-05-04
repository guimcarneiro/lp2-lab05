package saga.entities;

public class Produto {
	
	private String nome;
	private Double preco;
	private String descricao;
	
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		if(preco < 0.0) {
			throw new IllegalArgumentException("O preço não pode ser negativo");
		}
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public int hashCode() {
		return (this.nome + this.descricao).hashCode();
	}
	
	@Override
	public String toString() {
		return this.nome + " - " + this.descricao + " - R$" + String.format("%.2f", this.preco);
	}
}
