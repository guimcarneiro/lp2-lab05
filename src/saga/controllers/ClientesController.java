package saga.controllers;

import java.util.HashMap;

import saga.entities.Cliente;

public class ClientesController {

	private HashMap<String, Cliente> clientes;
	
	public ClientesController() {
		this.clientes = new HashMap<String, Cliente>();
	}
	
	public String cadastraCliente(String cpf, String nome, String email, String localizacao) {
		if(this.clientes.containsKey(cpf)) {
			return null;
		}
		try{
			this.clientes.put(cpf, new Cliente(cpf, nome, email, localizacao));
			return cpf;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String consultaCliente(String cpf) {
		try{
			return this.clientes.get(cpf).toString();
		}catch(NullPointerException npe) {
			return null;
		}
	}
	
	public String consultaClientesAll() {
		String mensagem = "";
		for(String cpfCliente: this.clientes.keySet()) {
			mensagem += this.clientes.get(cpfCliente).toString() + " | ";
		}
		return mensagem;
	}
	
	public boolean editaNomeCliente(String cpf, String nome) {
		try{
			this.clientes.get(cpf).setNome(nome);
		}catch(NullPointerException npe) {
			return false;
		}
		return true;
	}
	
	public boolean editaEmailCliente(String cpf, String email) {
		try{
			this.clientes.get(cpf).setEmail(email);
		}catch(NullPointerException npe) {
			return false;
		}
		return true;
	}
	
	public boolean editaLocalizacaoCliente(String cpf, String localizacao) {
		try{
			this.clientes.get(cpf).setLocalizacao(localizacao);
		}catch(NullPointerException npe) {
			return false;
		}
		return true;
	}
	
	public boolean removeCliente(String cpf) {
		if(this.clientes.remove(cpf) != null) {
			return true;
		}
		return false;
	}
}
