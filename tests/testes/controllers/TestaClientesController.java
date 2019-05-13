package testes.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saga.controllers.ClientesController;

class TestaClientesController {

	private ClientesController clientes;
	
	@BeforeEach
	void setUp() throws Exception {
		this.clientes = new ClientesController();
	}

	@Test
	void testaCadastroCliente() {
		assertEquals("Um cadastro válido não retornou o cpf do cliente corretamente","22222",this.clientes.cadastraCliente("22222", "Cliente novo", "cliente@cliente", "LCC3"));
		try {
			this.clientes.cadastraCliente("", "Nome", "email@", "lcc");
			fail("Não lançou exception em um cadastro com parâmetro cpf vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.clientes.cadastraCliente("231231", "", "email@", "lcc");
			fail("Não lançou exception em um cadastro com parâmetro nome vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.clientes.cadastraCliente("231321", "Nome", "", "lcc");
			fail("Não lançou exception em um cadastro com parâmetro email vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.clientes.cadastraCliente("123123", "Nome", "email@", "");
			fail("Não lançou exception em um cadastro com parâmetro localizacao vazia");
		}catch(IllegalArgumentException iae) {}
		try {
			this.clientes.cadastraCliente(null, "Nome", "email@", "lcc");
			fail("Não lançou exception em um cadastro com parâmetro cpf nulo");
		}catch(NullPointerException npe) {}
		try {
			this.clientes.cadastraCliente("231232", null, "email@", "lcc");
			fail("Não lançou exception em um cadastro com parâmetro nome nulo");
		}catch(NullPointerException npe) {}
		try {
			this.clientes.cadastraCliente("323132", "Nome", null, "lcc");
			fail("Não lançou exception em um cadastro com parâmetro email nulo");
		}catch(NullPointerException npe) {}
		try {
			this.clientes.cadastraCliente("312311", "Nome", "email@", null);
			fail("Não lançou exception em um cadastro com parâmetro localizacao nula");
		}catch(NullPointerException npe) {}
	}
	
	@Test
	void testaConsultaCliente() {
		this.clientes.cadastraCliente("22222", "Cliente novo", "ccc@ccc", "lcc");
		assertEquals("Não retornou uma String devidamente formatada","Cliente novo - lcc - ccc@ccc",this.clientes.consultaCliente("22222"));
		assertNull("Não retornou null para uma busca de cliente inexistente", this.clientes.consultaCliente("33333"));
		assertNull("Não retornou null para uma busca de cpf null", this.clientes.consultaCliente(null));
	}
	
	@Test
	void testaListaClientes() {
		assertEquals("Não retornou uma String vazia quando sem clientes cadastrados", "", this.clientes.consultaClientesAll());
		this.clientes.cadastraCliente("22222", "nome", "email@", "lcc");
		this.clientes.cadastraCliente("33333", "nome2", "email2@", "lcc2");
		assertEquals("Retornou uma saída mal-formatada","nome - lcc - email@ | nome2 - lcc2 - email2@" ,this.clientes.consultaClientesAll());
	}
	
	@Test
	void testaEditaCliente() {
		this.clientes.cadastraCliente("22222", "Nome", "email", "lcc");
		assertTrue("Não retornou true para uma edição de nome bem-sucedida", this.clientes.editaCliente("22222", "nome", "Nome novo"));
		assertEquals("Não editou o nome do cliente corretamente", "Nome novo - lcc - email", this.clientes.consultaCliente("22222"));
		assertTrue("Não retornou true para uma edição de email bem-sucedida", this.clientes.editaCliente("22222", "email", "emailnovo@"));
		assertEquals("Não editou o email do cliente corretamente", "Nome novo - lcc - emailnovo@");
		assertTrue("Não retornou true para uma edição de localizacao bem-sucedida", this.clientes.editaCliente("22222", "localizacao", "Outro lab"));
		assertEquals("Não editou a localizacao do cliente corretamente", "Nome novo - Outro lab - emailnovo@");
		assertFalse("Retornou true para uma edição de cliente mal-sucedida", this.clientes.editaCliente("23132", "nome", "novo nome"));
		assertFalse("Retornou true para uma edição de cliente mal-sucedida", this.clientes.editaCliente("22222", "atributo inexistente", "info"));
	}
	
	@Test
	void testaRemoveCliente() {
		this.clientes.cadastraCliente("22222", "nome", "email", "laboratorio");
		assertTrue("Retornou false para uma remoção bem-sucedida", this.clientes.removeCliente("22222"));
		if("nome - laboratorio - email".equals(this.clientes.consultaCliente("22222"))) {
			fail("Encontrou cliente cadastrado mesmo após remoção do mesmo");
		}
		assertFalse("Retornou true para uma remoção mal-sucedida(Cliente inexistente)", this.clientes.removeCliente("11111"));
		assertFalse("Retornou true para uma remoção mal-sucedida(CPF nulo)", this.clientes.removeCliente(null));
	}

}
