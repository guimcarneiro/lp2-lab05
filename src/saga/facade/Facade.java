package saga.facade;

import java.text.DecimalFormat;

import easyaccept.EasyAccept;
import saga.controllers.ClientesController;
import saga.controllers.FornecedoresController;
import saga.services.FornecedorService;

/**
 * Fachada de métodos do Sistema SAGA. Contém todos os métodos necessários para gerir
 * um sistema de auto-gestão de alimentos.
 * 
 * @author Guilherme de Melo Carneiro
 */
public class Facade {

	//TODO: Trocar Comparable por Comparator
	
	/**
	 * Controlador de Clientes. Responsável por gerir clientes: cadastrar, listar, editar e remover clientes.
	 */
	private ClientesController clientes;
	
	/**
	 * Controlador de Fornecedores e Produtos. Responsável por gerir fornecedores: cadastrar, listar, editar e remover
	 * fornecedores e produtos.
	 */
	private FornecedoresController fornecedores;
	
	/**
	 * Constroi uma fachada.
	 */
	public Facade() {
		this.clientes = new ClientesController();
		this.fornecedores = new FornecedoresController();
	}

	/**
	 * Retorna o CPF do cliente cadastrado, se bem-sucedido, e null caso contrário. Cadastra um cliente
	 * no SAGA com base em seu cpf, seu nome, seu e-mail e laboratório de origem(localizacao). Um 
	 * cliente é identificado unicamente pelo seu CPF. Não se pode cadastrar mais de
	 * um cliente por CPF, caso se tente, o Cliente anterior prevalece. Não aceita valores nulos ou
	 * vazios, são lançadas NullPointerException e IllegalArgumentException, respectivamente.
	 * 
	 * @param cpf String contendo o CPF do cliente
	 * @param nome String contendo o nome do cliente
	 * @param email String contendo o email do cliente
	 * @param localizacao String contendo o laboratório de origem do cliente(localização)
	 * @return String contendo o CPF do cliente cadastrado quando o cadastro for bem-sucedido, null caso contrário
	 */
	public String adicionaCliente(String cpf, String nome, String email, String localizacao) {
		return this.clientes.cadastraCliente(cpf, nome, email, localizacao);
	}
	
	/**
	 * Retorna String contendo informações sobre o cliente buscado. Caso seja passado um CPF que não
	 * pertença a nenhum cliente cadastrado, é retornado null.
	 * 
	 * @param cpf String contendo o CPF do cliente que se quer buscar
	 * @return String contendo informações sobre o cliente buscado, null caso não exista tal cliente no
	 * sistema
	 */
	public String exibeCliente(String cpf) {
		return this.clientes.consultaCliente(cpf);
	}

	/**
	 * Retorna String contendo todos os clientes já cadastrados no sistema. Caso não haja clientes cadastrados,
	 * o sistema retornará uma String vazia.
	 * 
	 * @return String contendo informações sobre todos os clientes já cadastrados no sistema
	 */
	public String exibeClientes() {
		return this.clientes.consultaClientesAll();
	}
	
	/**
	 * Retorna um booleano sobre o sucesso de uma edição de informações sobre um cliente.
	 * Edita uma informação com base no cpf do cliente, no atributo que se quer alterar e
	 * na nova informação que se pretende estabelecer no lugar da antiga. Caso não exista
	 * o cliente passado como parâmetro, retornará false.
	 * 
	 * @param cpf String contendo o cpf do cliente
	 * @param atributo String contendo o atributo do cliente que se deseja editar: "nome", "email" ou "localizacao"
	 * @param informacao String contendo a nova informação a ser substituída no atributo que se estabeleceu no parâmetro "atributo"
	 * @return true para uma edição de cliente bem-sucedida, false caso contrário
	 */
	public boolean editaCliente(String cpf, String atributo, String informacao) {
		return this.clientes.editaCliente(cpf, atributo, informacao);
	}
	
	/**
	 * Retorna um booleano sobre o sucesso de uma remoção de um cliente com base em seu cpf.
	 * Caso não haja cliente com o cpf passado como parâmetro, será retornado false.
	 * 
	 * @param cpf String contendo o cpf do cliente que se quer remover do sistema
	 * @return true para uma remoção de cliente bem-sucedida, false caso contrário
	 */
	public boolean removeCliente(String cpf) {
		return this.clientes.removeCliente(cpf);
	}
	
	/**
	 * Retorna uma String com o nome do fornecedor cadastrado. Cadastra um fornecedor
	 * com base em seu nome, seu e-mail e seu telefone. Valores nulos ou vazios não são aceitos e
	 * lançam NullPointerException e IllegalArgumentException, respectivamente. Um fornecedor é
	 * identificado unicamente pelo seu nome. Tentativa de cadastro de um fornecedor já existente
	 * lança uma IllegalArgumentException.
	 *  
	 * @param nome String contendo o nome do fornecedor
	 * @param email String contendo o email do fornecedor
	 * @param telefone String contendo o telefone do fornecedor
	 * @return String com o nome do fornecedor para um cadastro bem-sucedido
	 */
	public String adicionaFornecedor(String nome, String email, String telefone) {
		return this.fornecedores.cadastraFornecedor(nome, email, telefone);
	}

	/**
	 * Retorna uma String com informações sobre o fornecedor buscado. Caso seja passado um nome de
	 * fornecedor que não existe no sistema, é retornado null.
	 * 
	 * @param nome String contendo o nome do fornecedor que se quer buscar
	 * @return String contendo informações sobre o fornecedor buscado, null caso não exista tal fornecedor
	 */
	public String exibeFornecedor(String nome) {
		return this.fornecedores.imprimeFornecedor(nome);
	}

	/**
	 * Retorna uma String contendo todos os fornecedores já cadastrados no sistema. Caso não haja fornecedores
	 * cadastrados, será retornada uma String vazia.
	 * 
	 * @return String contendo todos os fornecedores já cadastrados
	 */
	public String exibeFornecedores() {
		return this.fornecedores.imprimeFornecedoresAll();
	}
	
	/**
	 * Retorna um booleano sobre o sucesso da edição de um fornecedor. Edita uma informação de um
	 * fornecedor com base em seu nome, no atributo que se quer alterar e na informação que será sobreposta
	 * no lugar da antiga. Caso seja passado um fornecedor que não existe no sistema, será retornado false.
	 * 
	 * @param nome String contendo o nome do fornecedor
	 * @param atributo String contendo o atributo do fornecedor que se quer alterar: "telefone" ou "email"
	 * @param informacao String contendo a nova informação que será sobreposta ao atributo passado no parâmetro "atributo"
	 * @return true para uma edição de fornecedor bem-sucedida, false caso contrário
	 */
	public boolean editaFornecedor(String nome, String atributo, String novoValor) {
		return this.fornecedores.editaFornecedor(nome, atributo, novoValor);
	}
	
	/**
	 * Retorna um booleano sobre o sucesso da remoção de um fornecedor do sistema. Caso seja passado
	 * um nome de fornecedor que não existe no sistema, será retornado false.
	 * 
	 * @param nome String contendo o nome do fornecedor que se quer remover
	 * @return true para uma remoção de fornecedor bem-sucedida, false caso contrário.
	 */
	public boolean removeFornecedor(String nome){
		return this.fornecedores.removeFornecedor(nome);
	}
	
	/**
	 * Retorna um booleano sobre o sucesso de um cadastro de um produto. Cadastra um produto a um
	 * fornecedor específico. Um produto é identificado unicamente pelo seu nome e sua descrição.
	 * Não se pode cadastrar duas vezes o mesmo produto para o mesmo fornecedor. Cadastra um produto
	 * a um fornecedor com base em seu nome, seu preço e sua descrição. Caso sejam passados valores nulos
	 * ou vazios para o produto, serão lançadas NullPointerException e IllegalArgumentException, respectivamente.
	 * O preço do produto não deve ser negativo, se o for, é lançada uma IllegalArgumentException.
	 * 
	 * @param nomeFornecedor String contendo o nome do fornecedor 
	 * @param nomeProduto String contendo o nome do produto que se quer cadastrar
	 * @param precoProduto double com o preço do produto que se quer cadastrar
	 * @param descProduto String contendo a descrição do produto que se quer cadastrar
	 * @return true para um cadastro bem-sucedido, false caso contrário
	 */
	public boolean adicionaProduto(String fornecedor, String nome, String descricao, double preco) {
		return this.fornecedores.cadastraProduto(fornecedor, nome, preco, descricao);
	}
	
	/**
	 * 
	 * @param fornecedor
	 * @param nome
	 * @param descricao
	 * @param fator
	 * @param produtos
	 * @return
	 */
	public boolean adicionaCombo(String fornecedor, String nome, String descricao, double fator, String produtos) {
		return this.fornecedores.adicionaCombo(fornecedor, nome, descricao, fator, produtos);
	}
	/**
	 * Retorna String contendo informações sobre um produto. Recupera informações sobre um produto pertencente
	 * a um fornecedor com base no nome do produto. Lança NullPointerException quando for passado um fornecedor
	 * inexistente ou um produto inexistente. Lança IllegalArgumentException para parâmetros vazios. Lança NullPointerException
	 * para parâmetros nulos.
	 * 
	 * @param nome String correspondente ao nome do produto
	 * @param descricao String correspondente a descricao do produto
	 * @param fornecedor String correspondente ao nome do fornecedor que possui o produto
	 * @return String contendo informações sobre o produto buscado
	 */
	public String exibeProduto(String nome, String descricao, String fornecedor) {
		return this.fornecedores.imprimeProduto(fornecedor, nome, descricao);
	}

	/**
	 * Retorna uma String contendo informações sobre o fornecedor buscado. Caso seja passado o nome de
	 * fornecedor que não está cadastrado no sistema, null é retornado.
	 * 
	 * @param nomeFornecedor String contendo o nome do fornecedor que se quer buscar
	 * @return String contendo informações sobre o fornecedor buscado, null caso não exista tal fornecedor no sistema
	 */
	public String exibeProdutosFornecedor(String fornecedor) {
		return this.fornecedores.imprimeProdutosFornecedor(fornecedor);
	}

	/**
	 * Retorna uma String contendo todos os produtos já cadastrados no sistema, de todos os fornecedores.
	 * Caso não haja produtos cadastrados, uma String vazia é retornada
	 * 
	 * @return String contendo todos os produtos já cadastrados no sistema, de todos os fornecedores
	 */
	public String exibeProdutos() {
		return this.fornecedores.imprimeProdutosAll();
	}

	/**
	 * Retorna um booleano sobre o sucesso da edição de preço de um produto. Edita um produto com base
	 * no fornecedor que o pertence e em seu nome. Não aceita valores negativos. Caso valores negativos
	 * sejam passados, retornará false.
	 * 
	 * @param nomeFornecedor String contendo o nome do fornecedor que possui o produto
	 * @param nomeProduto String contendo o nome do produto o qual se quer editar o preço
	 * @param precoProduto double contendo o novo preço do produto
	 * 
	 * @return true para uma edição de preço de produto bem-sucedida, false caso contrário
	 */
	public boolean editaProduto(String nomeProduto, String descricao, String nomeFornecedor, double precoProduto) {
		return this.fornecedores.editarPrecoProduto(nomeFornecedor, nomeProduto, descricao, precoProduto);
	}
	
	/**
	 * 
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @param novoFator
	 * @return
	 */
	public boolean editaCombo(String nome, String descricao, String fornecedor, double novoFator) {
		return this.fornecedores.editaCombo(nome, descricao, fornecedor, novoFator);
	}

	/**
	 * Retorna um booleano sobre o sucesso da remoção de um produto. Caso o nome do produto passado
	 * não esteja cadastrado no sistema, será retornado false.
	 * 
	 * @param nomeFornecedor String contendo o nome do fornecedor que possui o produto que se quer remover
	 * @param nomeProduto String contendo o nome do produto que se quer remover
	 * @return true para uma remoção de produto bem-sucedida, false caso contrário
	 */
	public boolean removeProduto(String nome, String descricao, String fornecedor) {
		return this.fornecedores.removeProduto(fornecedor, nome, descricao);
	}
	
	public boolean adicionaCompra(String cpf, String fornecedor, String data, String nome_prod, String desc_prod) {
		return this.clientes.adicionaCompra(cpf, fornecedor, data, nome_prod, desc_prod, new FornecedorService(this.fornecedores));
	}
	
	public String getDebito(String cpf, String fornecedor) {
		return new DecimalFormat("####.00").format(this.clientes.getDebitoFornecedor(cpf, fornecedor, new FornecedorService(this.fornecedores))).replaceAll(",", ".");
		//return this.clientes.getDebitoFornecedor(cpf, fornecedor, new FornecedorService(this.fornecedores));
	}
	
	public String exibeContas(String cpf, String fornecedor) {
		return this.clientes.exibeConta(cpf, fornecedor, new FornecedorService(this.fornecedores));
	}
	
	public String exibeContasClientes(String cpf) {
		return this.clientes.exibeContasAll(cpf);
	}
	
	public static void main(String[] args) {
		args = new String[] {"saga.facade.Facade",
				"acceptance_tests/use_case_1.txt",
				"acceptance_tests/use_case_2.txt",
				"acceptance_tests/use_case_3.txt",
				"acceptance_tests/use_case_4.txt",
				"acceptance_tests/use_case_5.txt",
				"acceptance_tests/use_case_6.txt"};
		EasyAccept.main(args);
	}
}
