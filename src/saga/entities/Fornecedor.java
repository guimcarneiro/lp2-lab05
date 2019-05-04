package saga.entities;

import java.util.HashMap;

public class Fornecedor {

	private String nome;
	private String email;
	private String telefone;
	
	private HashMap<String, Produto> produtos; //HashMap com chave sendo o nome
	
	public Fornecedor(String nome, String email, String telefone) {
		if(nome == null) {
			throw new NullPointerException("O nome não pode ser nulo");
		}
		if(email == null) {
			throw new NullPointerException("O e-mail não pode ser nulo");
		}
		if(telefone == null) {
			throw new NullPointerException("O telefone não pode ser nulo");
		}
		if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser vazio");
		}
		if(email.trim().isEmpty()) {
			throw new IllegalArgumentException("O e-mail não pode ser vazio");
		}
		if(telefone.trim().isEmpty()) {
			throw new IllegalArgumentException("O telefone não pode ser vazio");
		}
		
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.produtos = new HashMap<String, Produto>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}
	
	public boolean adicionaProduto(String nome, Double preco, String descricao) {
		if(nome != null) {
			try {
				this.produtos.put(nome, new Produto(nome, preco, descricao));
				return true;
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}
		return false;
	}
	
	public boolean removeProduto(String nome, String descricao) {
		if(this.produtos.containsKey(nome)) {
			this.produtos.remove(nome);
			return true;
		}
		return false;
	}
	
	public Produto getProduto(String nome) {
		if(this.produtos.containsKey(nome)) {
			return this.produtos.get(nome);
		}
		return null;
	}
	
	public String getProdutosAll() {
		String mensagem = "";
		
		for(String chave: this.produtos.keySet()) {
			mensagem += this.produtos.get(chave).toString();
		}
		return mensagem;
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
	@Override
	public String toString() {
		return this.nome + " - " + this.email + " - " + this.telefone;
	}
}
