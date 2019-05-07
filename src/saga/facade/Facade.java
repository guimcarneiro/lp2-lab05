package saga.facade;

import saga.controllers.ClientesController;
import saga.controllers.FornecedoresController;

public class Facade {

	private ClientesController clientes;
	private FornecedoresController fornecedores;
	
	public Facade() {
		this.clientes = new ClientesController();
		this.fornecedores = new FornecedoresController();
	}
	//Perguntar se quando lançar uma exceção de cadastro inválido o programa para
	public String cadastraCliente(String cpf, String nome, String email, String localizacao) {
		try{
			return this.clientes.cadastraCliente(cpf, nome, email, localizacao);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String imprimeCliente(String cpf) {
		return this.clientes.consultaCliente(cpf);
	}
	
	public String listaClientes() {
		return this.clientes.consultaClientesAll();
	}
	
	public boolean editaNomeCliente(String cpf, String nomeNovo) {
		return this.clientes.editaNomeCliente(cpf, nomeNovo);
	}
	
	public boolean editaEmailCliente(String cpf, String emailNovo) {
		return this.clientes.editaEmailCliente(cpf, emailNovo);
	}
	
	public boolean editaLocalizacaoCliente(String cpf, String localizacaoNova) {
		return this.clientes.editaLocalizacaoCliente(cpf, localizacaoNova);
	}

	public boolean removeCliente(String cpf) {
		return this.clientes.removeCliente(cpf);
	}
	
	public boolean cadastraFornecedor(String nome, String email, String telefone) {
		return this.fornecedores.cadastraFornecedor(nome, email, telefone);
	}

	public String imprimeFornecedor(String nome) {
		return this.fornecedores.imprimeFornecedor(nome);
	}

	public String listaFornecedores() {
		return this.fornecedores.imprimeFornecedoresAll();
	}
	
	public boolean editaEmailFornecedor(String nome, String emailNovo) {
		return this.fornecedores.editaEmailFornecedor(nome, emailNovo);
	}
	
	public boolean editaTelefoneFornecedor(String nome, String telefoneNovo) {
		return this.fornecedores.editaTelefoneFornecedor(nome, telefoneNovo);
	}
	
	public boolean removeFornecedor(String nome){
		return this.fornecedores.removeFornecedor(nome);
	}
	
	public boolean cadastraProduto(String nomeFornecedor, String nomeProduto, double precoProduto, String descProduto) {
		return this.fornecedores.cadastraProduto(nomeFornecedor, nomeProduto, precoProduto, descProduto);
	}

	public String consultaProdutosFornecedor(String nomeFornecedor) {
		return this.fornecedores.imprimeProdutosFornecedor(nomeFornecedor);
	}

	public String consultaProdutosAll() {
		return this.fornecedores.imprimeProdutosAll();
	}

	public boolean editaPrecoProduto(String nomeFornecedor, String nomeProduto, double precoProduto, String descProduto) {
		return this.fornecedores.cadastraProduto(nomeFornecedor, nomeProduto, precoProduto, descProduto);
	}

	public boolean removeProduto(String nomeFornecedor, String nomeProduto) {
		return this.fornecedores.removeProduto(nomeFornecedor, nomeProduto);
	}
	
}
