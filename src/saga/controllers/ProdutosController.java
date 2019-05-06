package saga.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import saga.entities.Produto;

public class ProdutosController {

	private HashMap<String, Produto> produtos;
	
	public ProdutosController() {
		this.produtos = new HashMap<String, Produto>();
	}
	
	public boolean cadastraProduto(String nome, double preco, String descricao) {
		if(!this.produtos.containsKey(nome)) {
			try {
				this.produtos.put(nome, new Produto(nome, preco, descricao));
				return true;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public String consultaProduto(String nome) {
		try{
			return this.produtos.get(nome).toString();
		}catch(NullPointerException npe) {
			throw new NullPointerException("Produto não cadastrado");
		}
	}
	
	public ArrayList<String> listaProdutos() {
		ArrayList<String> produtosAll = new ArrayList<String>();
		
		for(String nomeProduto: this.produtos.keySet()) {
			produtosAll.add(this.produtos.get(nomeProduto).toString());
		}
		
		return produtosAll;
	}
	
	public boolean removeProduto(String nome) {
		if(!this.produtos.containsKey(nome)) {
			return false;
		}
		this.produtos.remove(nome);
		return true;
	}
	
	public boolean editaPrecoProduto(String nome, double precoNovo) {
		if(!this.produtos.containsKey(nome)) {
			return false;
		}
		try {
			this.produtos.get(nome).setPreco(precoNovo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean existeProduto(String nome) {
		if(this.produtos.containsKey(nome)) {
			return true;
		}
		return false;
	}
}
