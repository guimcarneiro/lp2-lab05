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
		try {
			this.fornecedores.put(nome, new Fornecedor(nome, email, telefone));
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean editaEmailFornecedor(String nome, String emailNovo) {
		if(!this.fornecedores.containsKey(nome)) {
			throw new NullPointerException("Fornecedor inexistente");
		}
		this.fornecedores.get(nome).setEmail(emailNovo);
		return true;
	}
	
	public boolean editaTelefoneFornecedor(String nome, String telefoneNovo) {
		if(!this.fornecedores.containsKey(nome)) {
			throw new NullPointerException("Fornecedor inexistente");
		}
		this.fornecedores.get(nome).setEmail(telefoneNovo);
		return true;
	}
	
	public String imprimeFornecedor(String nome) {
		if(!this.fornecedores.containsKey(nome)) {
			throw new NullPointerException("Fornecedor inexistente");
		}
		return this.fornecedores.get(nome).toString();
	}
	
	public boolean cadastraProduto(String nomeFornecedor, String nomeProduto, double precoProduto, String descProduto) {
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new NullPointerException("Fornecedor inexistente");
		}
		return this.fornecedores.get(nomeFornecedor).adicionaProduto(nomeProduto, precoProduto, descProduto);
	}
	
	public String imprimeProduto(String nomeFornecedor, String nomeProduto) {
		try{
			return this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto);
		}catch(Exception e) {
			return null;
		}
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
		try{
			mensagem += this.fornecedores.get(nomeFornecedor).getProdutosAll();
		}catch(Exception e) {
			return null;
		}
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
		try {
			this.fornecedores.get(nomeFornecedor).editaPrecoProduto(nomeProduto, precoNovo);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	public boolean removeProduto(String nomeFornecedor, String nomeProduto) {
		try {
			this.fornecedores.get(nomeFornecedor).removeProduto(nomeFornecedor);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeFornecedor(String nomeFornecedor) {
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			return false;
		}
		this.fornecedores.remove(nomeFornecedor);
		return true;
	}
	
}
