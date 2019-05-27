package saga.controllers;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saga.entities.Cliente;
import saga.services.FornecedorService;
/**
 * Essa classe gerencia Clientes: cadastra, edita, remove e retorna clientes. Representa um
 * controlador de clientes.
 * 
 * @author Guilherme de Melo Carneiro
 *
 */
public class ClientesController {

	/**
	 * HashMap que armazena um cliente utilizando como chave de acesso seu cpf. Não se pode 
	 * cadastrar dois clientes exatamente com o mesmo cpf.
	 */
	private Map<String, Cliente> clientes;
	
	/**
	 * Chave é o cpf do cliente
	 */
	private Map<String, ContasController> contas;
	
	/**
	 * Constroi um controlador de Clientes.
	 */
	public ClientesController() {
		this.clientes = new HashMap<String, Cliente>();
		this.contas = new HashMap<String, ContasController>();
	}
	
	/**
	 * Retorna o cpf do cliente cadastrado, caso o cadastro seja bem-sucedido. Cadastra um 
	 * cliente com base em seu cpf, nome, email e laboratório de origem. O cpf identifica 
	 * unicamente um cliente, portanto não se pode cadastrar dois ou mais clientes com o mesmo
	 * cpf. Caso o cliente que se queira cadastrar já exista no sistema, será lançada uma IllegalArgumentException.
	 * Os parâmetros não devem ser nulos ou vazios, NullPointerException ou IllegalArgumentException
	 * serão lançadas, respectivamente.
	 * 
	 * @param cpf String que representa o cpf do cliente
	 * @param nome String que representa o nome do cliente
	 * @param email String que representa o email do cliente
	 * @param localizacao String que representa o laboratório de origem do cliente
	 * @return String contendo o cpf do cliente cadastrado
	 */
	public String cadastraCliente(String cpf, String nome, String email, String localizacao) {
		if(this.clientes.containsKey(cpf)) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cliente ja existe.");
		}
		this.clientes.put(cpf, new Cliente(cpf, nome, email, localizacao));
		return cpf;
	}
	
	/**
	 * Retorna uma String contendo as informações sobre um cliente. Caso não exista um cliente com
	 * o cpf passado como parâmetro, lançará NullPointerException.
	 * 
	 * @param cpf String que representa o cpf do cliente que se quer buscar
	 * @return String contendo informações sobre o cliente buscado
	 */
	public String consultaCliente(String cpf) {
		if(cpf == null) {
			throw new NullPointerException("Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(this.clientes.get(cpf) == null) {
			throw new NullPointerException("Erro na exibicao do cliente: cliente nao existe.");
		}
		return this.clientes.get(cpf).toString();
	}
	
	/**
	 * Retorna uma String contendo as informações sobre todos os clientes cadastrados. Caso não haja
	 * nenhum cliente cadastrado, será retornada uma String vazia. A String retornada segue a ordem 
	 * alfabetica baseada no nome dos clientes.
	 * 
	 * @return String contendo informações sobre todos os clientes já cadastrados
	 */
	public String consultaClientesAll() {
		String mensagem = "";
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		for(String cpfCliente: this.clientes.keySet()) {
			clientes.add(this.clientes.get(cpfCliente));
		}
		
		Collections.sort(clientes);
		
		for(int i=0; i<clientes.size(); i++) {
			mensagem += clientes.get(i).toString() + " | ";
		}
		
		if(!"".equals(mensagem)) {
			mensagem = mensagem.substring(0, mensagem.length()-3);
		}
		return mensagem;
	}
	
	/**
	 * Retorna booleano sobre o sucesso da edição do nome do cliente. Caso o cliente não exista,
	 * lança NullPointerException.
	 * 
	 * @param cpf String que representa o cpf do cliente
	 * @param nome String que representa o novo nome que o cliente adotará
	 * @return true para uma edição bem-sucedida
	 */
	private boolean editaNomeCliente(String cpf, String nome) {
		if(cpf == null) {
			throw new NullPointerException("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(this.clientes.get(cpf) == null) {
			throw new NullPointerException("Erro na edicao do cliente: cliente nao existe.");
		}
		this.clientes.get(cpf).setNome(nome);
		return true;
	}
	
	/**
	 * Retorna booleano sobre o sucesso da edição do email do cliente. Caso o cliente não exista,
	 * lança NullPointerException
	 * 
	 * @param cpf String que representa o cpf do cliente
	 * @param email String que representa o novo email que o cliente adotará
	 * @return true para uma edição bem-sucedida
	 */
	private boolean editaEmailCliente(String cpf, String email) {
		if(this.clientes.get(cpf) == null) {
			throw new NullPointerException("Erro na edicao do cliente: cliente nao existe.");
		}
		this.clientes.get(cpf).setEmail(email);
		return true;
	}
	
	/**
	 * Retorna booleano sobre o sucesso da edição da localizacao do cliente. Caso o cliente não exista,
	 * lança NullPointerException
	 * 
	 * @param cpf String que representa o cpf do cliente
	 * @param localizacao String que representa o novo laboratório que o cliente adotará
	 * @return true para uma edição bem-sucedida
	 */
	private boolean editaLocalizacaoCliente(String cpf, String localizacao) {
		if(cpf == null) {
			throw new NullPointerException("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(this.clientes.get(cpf) == null) {
			throw new NullPointerException("Erro na edicao do cliente: cliente nao existe.");
		}
		this.clientes.get(cpf).setLocalizacao(localizacao);
		return true;
	}
	
	/**
	 * Retorna um booleano sobre o sucesso da edição de uma informação de um cliente. Edita o atributo
	 * do cliente com base no atributo passado como parâmetro. Caso o cliente não exista,
	 * lança NullPointerException. Caso o atributo seja nulo lança uma NullPointerException.
	 * Caso o atributo seja vazio lança uma IllegalArgumentException.
	 * 
	 * @param cpf String contendo o CPF do cliente que se quer editar
	 * @param atributo String contendo o atributo do Cliente que se quer editar: nome, email ou localizacao
	 * @param informacao String contendo a informacao que se quer substituir do atributo anterior, informacao nova
	 * @return true para uma edição de Cliente bem-sucedida
	 */
	public boolean editaCliente(String cpf, String atributo, String novoValor) {
		if(atributo == null) {
			throw new NullPointerException("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
		}
		if(atributo.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
		}
		if(novoValor == null) {
			throw new NullPointerException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		}
		if(novoValor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		}
		if("nome".equals(atributo.toLowerCase())) {
			this.editaNomeCliente(cpf, novoValor);
			return true;
		}
		if("email".equals(atributo.toLowerCase())) {
			this.editaEmailCliente(cpf, novoValor);
			return true;
		}
		if("localizacao".equals(atributo.toLowerCase())) {
			this.editaLocalizacaoCliente(cpf, novoValor);
			return true;
		}
		if("cpf".equals(atributo.toLowerCase())) {
			throw new IllegalArgumentException("Erro na edicao do cliente: cpf nao pode ser editado.");
		}
		throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao existe.");
	}
	
	/**
	 * Retorna booleano para o sucesso de uma remoção de um cliente do sistema. Caso não haja
	 * cliente cadastrado com base no parâmetro passado, será retornado false.
	 *  
	 * @param cpf String que representa o cpf do cliente que será removido do sistema
	 * @return true para uma remoção de um cliente bem-sucedida, false caso contrário
	 */
	public boolean removeCliente(String cpf) {
		if(cpf == null) {
			throw new NullPointerException("Erro na remocao do cliente: cpf nao pode ser vazio ou nulo");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na remocao do cliente: cpf nao pode ser vazio ou nulo");
		}
		if(this.clientes.get(cpf) == null) {
			throw new NullPointerException("Erro na remocao do cliente: cliente nao existe.");
		}
		if(this.clientes.remove(cpf) != null) {
			return true;
		}
		return false;
	}
	
	public boolean adicionaCompra(String cpf, String fornecedor, String dataStr, String nome_prod, String desc_prod, FornecedorService fornecedorService) {
		Date data = new Date();
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				
		if(cpf == null) {
			throw new NullPointerException("Erro ao cadastrar compra: cpf nulo ou vazio.");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: cpf nulo ou vazio.");
		}
		if(cpf.length() != 11) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: cpf invalido.");
		}
		if(fornecedor == null) {
			throw new NullPointerException("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
		}
		if(fornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
		}
		if(dataStr == null) {
			throw new NullPointerException("Erro ao cadastrar compra: data nao pode ser vazia ou nula.");
		}
		if(dataStr.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: data nao pode ser vazia ou nula.");
		}
		try {
			int contador = 0;
			for(String dataTeste: dataStr.split("/")) {
				if(contador == 0) {
					if(Integer.parseInt(dataTeste)<1 || Integer.parseInt(dataTeste)>31) {
						throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");
					}
				}
				if(contador == 1) {
					if(Integer.parseInt(dataTeste)<1 || Integer.parseInt(dataTeste)>12) {
						throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");
					}
				}
				contador++;
			}
			data = formatoData.parse(dataStr);
		}catch(Exception e) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");
		}
		if(nome_prod == null) {
			throw new NullPointerException("Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
		}
		if(nome_prod.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
		}
		if(desc_prod == null) {
			throw new NullPointerException("Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula.");
		}
		if(desc_prod.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula.");
		}
		if(this.clientes.get(cpf) == null) {
			throw new NullPointerException("Erro ao cadastrar compra: cliente nao existe.");
		}
		
		if(fornecedorService.existeFornecedor(fornecedor)) {
			if(fornecedorService.getProduto(fornecedor, nome_prod) == null){
				throw new NullPointerException("Erro ao cadastrar compra: produto nao existe.");
			}
			if(!this.contas.containsKey(cpf)) {
				this.contas.put(cpf, new ContasController());
			}
			this.contas.get(cpf).adicionaCompra(cpf, fornecedor, data, fornecedorService.getProduto(fornecedor, nome_prod)); //adiciona o produto na conta
			return true;
		}
		
		throw new IllegalArgumentException("Erro ao cadastrar compra: fornecedor nao existe.");
	}
	
	public double getDebitoFornecedor(String cpf, String fornecedor, FornecedorService fornecedorService) {
		if(cpf == null) {
			throw new NullPointerException("Erro ao recuperar debito: cpf nao pode ser vazio ou nulo.");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao recuperar debito: cpf nao pode ser vazio ou nulo.");
		}
		if(cpf.length() != 11) {
			throw new IllegalArgumentException("Erro ao recuperar debito: cpf invalido.");
		}
		if(fornecedor == null) {
			throw new NullPointerException("Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");
		}
		if(fornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");
		}
		if(this.contas.get(cpf) == null) {
			throw new NullPointerException("Erro ao recuperar debito: cliente nao existe.");
		}
		if(!fornecedorService.existeFornecedor(fornecedor)) {
			throw new NullPointerException("Erro ao recuperar debito: fornecedor nao existe.");
		}
		return this.contas.get(cpf).getDebitoFornecedor(fornecedor);
	}
	
	//Exibe Conta do cliente
	public String exibeConta(String cpf, String fornecedor, FornecedorService fornecedorService) {
		if(cpf == null) {
			throw new NullPointerException("Erro ao exibir conta do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao exibir conta do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(cpf.length() != 11) {
			throw new IllegalArgumentException("Erro ao exibir conta do cliente: cpf invalido.");
		}
		if(this.contas.get(cpf) == null) {
			throw new NullPointerException("Erro ao exibir conta do cliente: cliente nao existe.");
		}
		if(fornecedor == null) {
			throw new NullPointerException("Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.");
		}
		if(fornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.");
		}
		if(!fornecedorService.existeFornecedor(fornecedor)) {
			throw new NullPointerException("Erro ao exibir conta do cliente: fornecedor nao existe.");
		}
		if(!this.contas.get(cpf).existeContaFornecedor(fornecedor)) {
			throw new NullPointerException("Erro ao exibir conta do cliente: cliente nao tem nenhuma conta com o fornecedor.");
		}
		
		return this.contas.get(cpf).imprimeConta(fornecedor, this.clientes.get(cpf).getNome());
	}
	
	public String exibeContasAll(String cpf) {
		if(cpf == null) {
			throw new NullPointerException("Erro ao exibir contas do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro ao exibir contas do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(cpf.length() != 11) {
			throw new IllegalArgumentException("Erro ao exibir contas do cliente: cpf invalido.");
		}
		if(this.clientes.get(cpf) == null) {
			throw new NullPointerException("Erro ao exibir contas do cliente: cliente nao existe.");
		}
		if(this.contas.get(cpf) == null) {
			throw new NullPointerException("Erro ao exibir contas do cliente: cliente nao tem nenhuma conta.");
		}
		
		return this.contas.get(cpf).imprimeContasAll(this.clientes.get(cpf).getNome());
	}
}
