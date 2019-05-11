package saga.controllers;

import java.util.HashMap;
import java.util.Map;

import saga.entities.Cliente;
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
	 * Constroi um controlador de Clientes.
	 */
	public ClientesController() {
		this.clientes = new HashMap<String, Cliente>();
	}
	
	/**
	 * Retorna o cpf do cliente cadastrado, caso o cadastro seja bem-sucedido. Cadastra um 
	 * cliente com base em seu cpf, nome, email e laboratório de origem. O cpf identifica 
	 * unicamente um cliente, portanto não se pode cadastrar dois ou mais clientes com o mesmo
	 * cpf. Caso o cliente que se queira cadastrar já exista no sistema, será retornado null.
	 * Os parâmetros não devem ser nulos ou vazios, NullPointerException ou IllegalArgumentException
	 * serão lançadas, respectivamente.
	 * 
	 * @param cpf String que representa o cpf do cliente
	 * @param nome String que representa o nome do cliente
	 * @param email String que representa o email do cliente
	 * @param localizacao String que representa o laboratório de origem do cliente
	 * @return String contendo o cpf do cliente cadastrado, caso o cadastro seja bem-sucedido, e null
	 * caso contrário
	 */
	public String cadastraCliente(String cpf, String nome, String email, String localizacao) {
		if(this.clientes.containsKey(cpf)) {
			return null;
		}
		this.clientes.put(cpf, new Cliente(cpf, nome, email, localizacao));
		return cpf;
	}
	
	/**
	 * Retorna uma String contendo as informações sobre um cliente. Caso não exista um cliente com
	 * o cpf passado como parâmetro, será retornado null.
	 * 
	 * @param cpf String que representa o cpf do cliente que se quer buscar
	 * @return String contendo informações sobre o cliente buscado, caso não exista tal cliente, será
	 * retornado null
	 */
	public String consultaCliente(String cpf) {
		if(this.clientes.get(cpf) == null) {
			return null;
		}
		return this.clientes.get(cpf).toString();
	}
	
	/**
	 * Retorna uma String contendo as informações sobre todos os clientes cadastrados. Caso não haja
	 * nenhum cliente cadastrado, será retornada uma String vazia.
	 * 
	 * @return String contendo informações sobre todos os clientes já cadastrados
	 */
	public String consultaClientesAll() {
		String mensagem = "";
		for(String cpfCliente: this.clientes.keySet()) {
			mensagem += this.clientes.get(cpfCliente).toString() + " | ";
		}
		return mensagem;
	}
	
	/**
	 * Retorna booleano sobre o sucesso da edição do nome do cliente.
	 * 
	 * @param cpf String que representa o cpf do cliente
	 * @param nome String que representa o novo nome que o cliente adotará
	 * @return true para uma edição bem-sucedida, false caso contrário
	 */
	public boolean editaNomeCliente(String cpf, String nome) {
		if(this.clientes.get(cpf) == null) {
			return false;
		}
		this.clientes.get(cpf).setNome(nome);
		return true;
	}
	
	/**
	 * Retorna booleano sobre o sucesso da edição do email do cliente.
	 * 
	 * @param cpf String que representa o cpf do cliente
	 * @param email String que representa o novo email que o cliente adotará
	 * @return true para uma edição bem-sucedida, false caso contrário
	 */
	public boolean editaEmailCliente(String cpf, String email) {
		if(this.clientes.get(cpf) == null) {
			return false;
		}
		this.clientes.get(cpf).setEmail(email);
		return true;
	}
	
	/**
	 * Retorna booleano sobre o sucesso da edição da localizacao do cliente.
	 * 
	 * @param cpf String que representa o cpf do cliente
	 * @param localizacao String que representa o novo laboratório que o cliente adotará
	 * @return true para uma edição bem-sucedida, false caso contrário
	 */
	public boolean editaLocalizacaoCliente(String cpf, String localizacao) {
		if(this.clientes.get(cpf) == null) {
			return false;
		}
		this.clientes.get(cpf).setLocalizacao(localizacao);
		return true;
	}
	
	/**
	 * Retorna booleano para o sucesso de uma remoção de um cliente do sistema. Caso não haja
	 * cliente cadastrado com base no parâmetro passado, será retornado false.
	 *  
	 * @param cpf String que representa o cpf do cliente que será removido do sistema
	 * @return true para uma remoção de um cliente bem-sucedida, false caso contrário
	 */
	public boolean removeCliente(String cpf) {
		if(this.clientes.remove(cpf) != null) {
			return true;
		}
		return false;
	}
}
