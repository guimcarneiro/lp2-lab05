package saga.entities;

import saga.controllers.ProdutosController;

public class Fornecedor {

	private String nome;
	private String email;
	private String telefone;
	private ProdutosController produtos;
	
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
		this.produtos = new ProdutosController();
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
	
	public boolean adicionaProduto(String nome, double preco, String descricao) {
		return this.produtos.cadastraProduto(nome, preco, descricao);
	}
	
	public boolean removeProduto(String nome) {
		if(!this.produtos.existeProduto(nome)) {
			throw new NullPointerException("Não existe produto com esse nome");
		}
		return this.produtos.removeProduto(nome);
	}
	
	public String consultaProduto(String nome) {
		return this.produtos.consultaProduto(nome);
	}
	
	public boolean editaPrecoProduto(String nome, double precoNovo) {
		try {
			return this.produtos.editaPrecoProduto(nome, precoNovo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getProdutosAll() {
		String mensagem = "";
		
		for(String produto: this.produtos.listaProdutos()) {
			mensagem += this.nome + " - " + produto.toString() + " | ";
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
	
	public static void main(String[] args) {
		
		//Perguntar se em uma consulta de produto, caso não haja produto cadastrado no fornecedor,
		//lança ou não lança uma Exception, ou retorna Null, ou os dois.
		
		//Do jeito que está retorna null e imprime o printStackTrace
		
		Fornecedor fornecedor = new Fornecedor("Chico", "chico@chico.com", "2322-3232");
		
		System.out.println(fornecedor.adicionaProduto("Buchada", 34.5, "Buchada de bode gourmet"));
		System.out.println(fornecedor.consultaProduto("Buchada"));
		System.out.println(fornecedor.consultaProduto("Inexistente"));
		
	}
}
