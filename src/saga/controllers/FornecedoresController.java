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
	
	public boolean cadastraProduto(String nomeFornecedor, String nomeProduto, Double precoProduto, String descProduto) {
		if(this.fornecedores.containsKey(nomeFornecedor)) {
			return false;
		}
		try {
			this.fornecedores.get(nomeFornecedor).adicionaProduto(nomeProduto, precoProduto, descProduto);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String imprimeProduto(String nomeFornecedor, String nomeProduto) {
		if(this.fornecedores.containsKey(nomeFornecedor)) {
			try {
				return this.fornecedores.get(nomeFornecedor).consultaProduto(nomeProduto);
			}catch(NullPointerException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String imprimeFornecedoresAll() {
		String mensagem = "";
			for(String fornec: this.fornecedores.keySet()) {
				mensagem += this.fornecedores.get(fornec).toString();
			}
		
		return mensagem;
	}
	
	public String imprimeProdutos(String nomeFornecedor) {
		String mensagem = "";
		if(this.fornecedores.containsKey(nomeFornecedor)) {
			for(String fornec: this.fornecedores.keySet()) {
				mensagem += this.fornecedores.get(fornec).getProdutosAll();
			}
		}
		return mensagem;
	}
	
	public boolean editarPrecoProduto(String nomeFornecedor, String nomeProduto, double precoNovo) {
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			return false;
		}
		try {
			this.fornecedores.get(nomeFornecedor).editaPrecoProduto(nomeProduto, precoNovo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeProduto(String nomeFornecedor, String nomeProduto) {
		if(!this.fornecedores.containsKey(nomeFornecedor)) {
			return false;
		}
		try {
			this.fornecedores.get(nomeFornecedor).removeProduto(nomeFornecedor);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
