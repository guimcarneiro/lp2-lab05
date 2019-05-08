package saga.controllers;

import java.util.HashMap;

import saga.entities.Fornecedor;

public class FornecedoresController {

	private HashMap<String, Fornecedor> fornecedores;
	
	public FornecedoresController() {
		this.fornecedores = new HashMap<String, Fornecedor>();
	}
	
	public boolean cadastraFornecedor(String nome, String email, String telefone) {
		if(this.fornecedores.containsKey(nome)) {
			return false;
		}
		this.fornecedores.put(nome, new Fornecedor(nome, email, telefone));
		return true;
	}
	
	public boolean editaEmailFornecedor(String nome, String emailNovo) {
		if(!this.fornecedores.containsKey(nome)) {
			return false;
		}
		this.fornecedores.get(nome).setEmail(emailNovo);
		return true;
	}
	
	public boolean editaTelefoneFornecedor(String nome, String telefoneNovo) {
		if(!this.fornecedores.containsKey(nome)) {
			return false;
		}
		this.fornecedores.get(nome).setEmail(telefoneNovo);
		return true;
	}
	
	public String imprimeFornecedor(String nome) {
		if(!this.fornecedores.containsKey(nome)) {
			return null;
		}
		return this.fornecedores.get(nome).toString();
	}
	
	public boolean cadastraProduto(String nomeFornecedor, String nomeProduto, double precoProduto, String descProduto) {
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			return false;
		}
		return this.fornecedores.get(nomeFornecedor).adicionaProduto(nomeProduto, precoProduto, descProduto);
	}
	
	public String imprimeProduto(String nomeFornecedor, String nomeProduto) {
		if(this.fornecedores.get(nomeFornecedor) == null) {
			return null;
		}
		if(this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto) == null) {
			return null;
		}
		return this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto);
	}
	
	public String imprimeFornecedoresAll() {
		String mensagem = "";
			for(String fornec: this.fornecedores.keySet()) {
				mensagem += this.fornecedores.get(fornec).toString();
			}
		
		return mensagem;
	}
	
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
	
	public String imprimeProdutosAll() {
		String mensagem = "";
		for(String chaveFornec: this.fornecedores.keySet()) {
			mensagem += this.fornecedores.get(chaveFornec).getProdutosAll() + " | ";
		}
		return mensagem;
	}
	
	public boolean editarPrecoProduto(String nomeFornecedor, String nomeProduto, double precoNovo) {
		if(this.fornecedores.get(nomeFornecedor) == null) {
			return false;
		}
		this.fornecedores.get(nomeFornecedor).editaPrecoProduto(nomeProduto, precoNovo);
		return true;
	}
	
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
	
	public boolean removeFornecedor(String nomeFornecedor) {
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			return false;
		}
		this.fornecedores.remove(nomeFornecedor);
		return true;
	}
	
}
