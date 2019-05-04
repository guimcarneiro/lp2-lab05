package saga.controllers;

import java.util.HashMap;

import saga.entities.Cliente;

public class ClientesController {

	private HashMap<String, Cliente> clientes;
	
	public ClientesController() {
		this.clientes = new HashMap<String, Cliente>();
	}
	
	public String cadastraCliente(String cpf, String nome, String email, String localizacao) {
		try {
			this.clientes.put(cpf, new Cliente(cpf, nome, email, localizacao));
			return cpf;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String consultaCliente(String cpf) {
		return this.clientes.get(cpf).toString();
	}
	
	public String consultaClientesAll() {
		String mensagem = "";
		for(String cpfCliente: this.clientes.keySet()) {
			mensagem += this.clientes.get(cpfCliente).toString() + " | ";
		}
		return mensagem;
	}
	
	public boolean editaNomeCliente(String cpf, String nome) {
		if(!this.clientes.containsKey(cpf)) {
			return false;
		}
		this.clientes.get(cpf).setNome(nome);
		return true;
	}
	
	public boolean editaEmailCliente(String cpf, String email) {
		if(!this.clientes.containsKey(cpf)) {
			return false;
		}
		this.clientes.get(cpf).setEmail(email);
		return true;
	}
	
	public boolean editaLocalizacaoCliente(String cpf, String localizacao) {
		if(!this.clientes.containsKey(cpf)) {
			return false;
		}
		this.clientes.get(cpf).setLocalizacao(localizacao);
		return true;
	}
	
	public boolean removeCliente(String cpf) {
		if(this.clientes.remove(cpf) != null) {
			return true;
		}
		return false;
	}
}
