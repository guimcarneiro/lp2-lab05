package saga.services;

import saga.controllers.FornecedoresController;
import saga.entities.Produto;

public class FornecedorService {

	private FornecedoresController fornecedores;
	
	public FornecedorService(FornecedoresController fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public boolean existeFornecedor(String fornecedor) {
		return this.fornecedores.existeFornecedor(fornecedor);
	}
	
	public Produto getProduto(String fornecedor, String nome) {
		if(this.fornecedores.getFornecedor(fornecedor) == null) {
			return null;
		}
		return this.fornecedores.getFornecedor(fornecedor).getProduto(nome);
	}
}
