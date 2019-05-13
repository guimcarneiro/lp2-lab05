package saga.facade;

import saga.controllers.ClientesController;
import saga.controllers.FornecedoresController;

/**
 * Fachada de métodos do Sistema SAGA. Contém todos os métodos necessários para gerir
 * um sistema de auto-gestão de alimentos.
 * 
 * @author Guilherme de Melo Carneiro
 */
public class Facade {

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
	public String cadastraCliente(String cpf, String nome, String email, String localizacao) {
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
	public String imprimeCliente(String cpf) {
		return this.clientes.consultaCliente(cpf);
	}

	/**
	 * Retorna Strign contendo todos os clientes já cadastrados no sistema. Caso não haja clientes cadastrados,
	 * o sistema retornará uma String vazia.
	 * 
	 * @return String contendo informações sobre todos os clientes já cadastrados no sistema
	 */
	public String listaClientes() {
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
	 * Retorna um booleano sobre o sucesso do cadastro de um fornecedor. Cadastra um fornecedor
	 * com base em seu nome, seu e-mail e seu telefone. Valores nulos ou vazios não são aceitos e
	 * lançam NullPointerException e IllegalArgumentException, respectivamente. Um fornecedor é
	 * identificado unicamente pelo seu nome.
	 *  
	 * @param nome String contendo o nome do fornecedor
	 * @param email String contendo o email do fornecedor
	 * @param telefone String contendo o telefone do fornecedor
	 * @return true para um cadastro bem-sucedido, false caso contrário
	 */
	public String cadastraFornecedor(String nome, String email, String telefone) {
		return this.fornecedores.cadastraFornecedor(nome, email, telefone);
	}

	/**
	 * Retorna uma String com informações sobre o fornecedor buscado. Caso seja passado um nome de
	 * fornecedor que não existe no sistema, é retornado null.
	 * 
	 * @param nome String contendo o nome do fornecedor que se quer buscar
	 * @return String contendo informações sobre o fornecedor buscado, null caso não exista tal fornecedor
	 */
	public String imprimeFornecedor(String nome) {
		return this.fornecedores.imprimeFornecedor(nome);
	}

	/**
	 * Retorna uma String contendo todos os fornecedores já cadastrados no sistema. Caso não haja fornecedores
	 * cadastrados, será retornada uma String vazia.
	 * 
	 * @return String contendo todos os fornecedores já cadastrados
	 */
	public String listaFornecedores() {
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
	public boolean editaFornecedor(String nome, String atributo, String informacao) {
		return this.fornecedores.editaFornecedor(nome, atributo, informacao);
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
	public boolean cadastraProduto(String nomeFornecedor, String nomeProduto, double precoProduto, String descProduto) {
		return this.fornecedores.cadastraProduto(nomeFornecedor, nomeProduto, precoProduto, descProduto);
	}

	/**
	 * Retorna uma String contendo informações sobre o fornecedor buscado. Caso seja passado o nome de
	 * fornecedor que não está cadastrado no sistema, null é retornado.
	 * 
	 * @param nomeFornecedor String contendo o nome do fornecedor que se quer buscar
	 * @return String contendo informações sobre o fornecedor buscado, null caso não exista tal fornecedor no sistema
	 */
	public String consultaProdutosFornecedor(String nomeFornecedor) {
		return this.fornecedores.imprimeProdutosFornecedor(nomeFornecedor);
	}

	/**
	 * Retorna uma String contendo todos os produtos já cadastrados no sistema, de todos os fornecedores.
	 * Caso não haja produtos cadastrados, uma String vazia é retornada
	 * 
	 * @return String contendo todos os produtos já cadastrados no sistema, de todos os fornecedores
	 */
	public String consultaProdutosAll() {
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
	public boolean editaPrecoProduto(String nomeFornecedor, String nomeProduto, double precoProduto) {
		return this.fornecedores.editarPrecoProduto(nomeFornecedor, nomeProduto, precoProduto);
	}

	/**
	 * Retorna um booleano sobre o sucesso da remoção de um produto. Caso o nome do produto passado
	 * não esteja cadastrado no sistema, será retornado false.
	 * 
	 * @param nomeFornecedor String contendo o nome do fornecedor que possui o produto que se quer remover
	 * @param nomeProduto String contendo o nome do produto que se quer remover
	 * @return true para uma remoção de produto bem-sucedida, false caso contrário
	 */
	public boolean removeProduto(String nomeFornecedor, String nomeProduto) {
		return this.fornecedores.removeProduto(nomeFornecedor, nomeProduto);
	}
}
