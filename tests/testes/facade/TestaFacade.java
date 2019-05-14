package testes.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saga.facade.Facade;

class TestaFacade {

	private Facade facade;
	
	@BeforeEach
	void setUp() throws Exception {
		this.facade = new Facade();
	}

	@Test
	void testaCadastraCliente() {
		assertEquals("O cadastro válido de um cliente retornou false", "222222", this.facade.cadastraCliente("222222", "Cliente novo", "cliente@", "LCC3"));
		try {
			this.facade.cadastraCliente(null, "nome valido", "email@", "lcc3");
			fail("Não lançou NullPointerException para cpf nulo");
		}catch(NullPointerException npe) {}
		try {
			this.facade.cadastraCliente("11111", null, "email@", "lcc3");
			fail("Não lançou NullPointerException para nome nulo");
		}catch(NullPointerException npe) {}
		try {
			this.facade.cadastraCliente("11111", "nome valido", null, "lcc3");
			fail("Não lançou NullPointerException para email nulo");
		}catch(NullPointerException npe) {}
		try {
			this.facade.cadastraCliente("11111", "nome valido", "email@", null);
			fail("Não lançou NullPointerException para laboratorio nulo");
		}catch(NullPointerException npe) {}
		try {
			this.facade.cadastraCliente("", "nome valido", "email@", "lcc3");
			fail("Não lançou IllegalArgumentException para cpf vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.facade.cadastraCliente("11111", "", "email@", "lcc3");
			fail("Não lançou IllegalArgumentException para nome vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.facade.cadastraCliente("11111", "nome valido", "", "lcc3");
			fail("Não lançou IllegalArgumentException para email vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.facade.cadastraCliente("11111", "nome valido", "email@", "");
			fail("Não lançou IllegalArgumentException para laboratorio vazio");
		}catch(IllegalArgumentException iae) {}
		assertNull("Não retornou null para uma tentativa de cadastrar um cliente já existente no sistema", this.facade.cadastraCliente("222222", "Cliente novo", "cliente@", "LCC3"));
	}
	
	@Test
	void testaImprimeCliente() {
		assertNull("Não retornou null para uma impressão de cliente inexistente no sistema", this.facade.imprimeCliente("343424"));
		this.facade.cadastraCliente("11111", "Nome", "email@", "lcc");
		assertEquals("Retornou uma String mal-formatada para o cliente buscado", "Nome - lcc - email@", this.facade.imprimeCliente("11111"));
	}
	
	@Test
	void testaListaClientes() {
		assertEquals("Não retornou uma String vazia quando sem clientes cadastrados", "",this.facade.listaClientes());
		this.facade.cadastraCliente("11111", "Nome1", "email1@", "lcc1");
		this.facade.cadastraCliente("22222", "nome2", "email2@", "lcc2");
		assertEquals("Retornou uma String mal-formatada", "Nome1 - lcc1 - email1@ | Nome2 - email2@ - lcc2", this.facade.listaClientes());
	}
	
	@Test
	void testaEditaCliente() {
		assertFalse("Retornou true para uma edição de cliente inexistente", this.facade.editaCliente("4343434", "nome", "novo nome"));
		this.facade.cadastraCliente("11111", "Nome", "email@", "lcc");
		assertFalse("Retornou true para uma edição de cliente sob um parâmetro inexistente", this.facade.editaCliente("11111", "atributo inexistente", "nova info"));
		assertTrue("Retornou false para uma edição válida do nome de um cliente", this.facade.editaCliente("11111", "nome", "novo nome"));
		assertEquals("Não alterou o parâmetro nome corretamente","novo nome - lcc - email@" , this.facade.imprimeCliente("11111"));
		assertTrue("Retornou false para uma edição válida do email de um cliente", this.facade.editaCliente("11111", "email", "novoemail@"));
		assertEquals("Não alterou o parâmetro nome corretamente","novo nome - lcc - novoemail@" , this.facade.imprimeCliente("11111"));
		assertTrue("Retornou false para uma edição válida da localizacao de um cliente", this.facade.editaCliente("11111", "localizacao", "nova localizacao"));
		assertEquals("Não alterou o parâmetro nome corretamente","novo nome - nova localizacao - novoemail@" , this.facade.imprimeCliente("11111"));
	}
	
	@Test
	void testaRemoveCliente() {
		assertFalse("Retornou true para uma remoção de cliente inexistente no sistema", this.facade.removeCliente("434244"));
		this.facade.cadastraCliente("11111", "nome1", "email1@", "lcc");
		assertTrue("Retornou false para uma remoção bem-sucedida", this.facade.removeCliente("11111"));
		assertNull("Não retornou null para uma busca de cliente já removido do sistema", this.facade.imprimeCliente("11111"));
	}
	
	@Test
	void testaCadastraFornecedor() {
		assertEquals("Não retornou o nome do fornecedor corretamente em um cadastro bem-sucedido", "Nome", this.facade.cadastraFornecedor("Nome", "email@", "1111-1111"));
		try {
			this.facade.cadastraFornecedor("", "email", "2332-3232");
			fail("Não lançou IllegalArgumentException para nome vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.facade.cadastraFornecedor("Nome", "", "2332-3232");
			fail("Não lançou IllegalArgumentException para email vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.facade.cadastraFornecedor("Nome", "email", "");
			fail("Não lançou IllegalArgumentException para telefone vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.facade.cadastraFornecedor(null, "email", "2332-3232");
			fail("Não lançou NullPointerException para nome nulo");
		}catch(NullPointerException iae) {}
		try {
			this.facade.cadastraFornecedor("Nome", null, "2332-3232");
			fail("Não lançou NullPointerException para email nulo");
		}catch(NullPointerException iae) {}
		try {
			this.facade.cadastraFornecedor("Nome", "email", null);
			fail("Não lançou NullPointerException para telefone nulo");
		}catch(NullPointerException iae) {}
		try {
			this.facade.cadastraFornecedor("Nome", "email@", "1111-1111");
			fail("Não lançou IllegalArgumentException para a tentativa de cadastro de um fornecedor já existente");
		}catch(IllegalArgumentException iae) {}
	}
	
	@Test
	void testaImprimeFornecedor() {
		assertNull("Não retornou null para uma impressão de fornecedor inexistente", this.facade.imprimeFornecedor("nome inexistente"));
		this.facade.cadastraFornecedor("nome", "email@", "1111-1111");
		assertEquals("Retornou uma String sobre o fornecedor mal-formatada","nome - email@ - 1111-1111",this.facade.imprimeFornecedor("nome"));
	}
	
	@Test
	void testaListaFornecedores() {
		assertEquals("Não retornou uma String vazia para quando sem fornecedores cadastrados", "", this.facade.listaFornecedores());
		this.facade.cadastraFornecedor("nome1", "email1@", "1111-1111");
		this.facade.cadastraFornecedor("nome2", "email2@", "2222-2222");
		assertEquals("Não retornou uma String dos fornecedores bem formatada", "nome1 - email1@ - 1111-1111 | nome2 - email2@ - 2222-2222", this.facade.listaFornecedores());
	}
	
	@Test
	void testaEditaFornecedor() {
		assertFalse("Retornou true para uma edição de fornecedor inexistente", this.facade.editaFornecedor("nome inexistente", "nome", "novo nome"));
		this.facade.cadastraFornecedor("nome", "email@", "1111-1111");
		assertFalse("Retornou true para uma ediçao de atributo inexistente", this.facade.editaFornecedor("nome", "atributo inexistente", "nova info"));
		assertTrue("Retornou false para uma edição de email de fornecedor bem-sucedida", this.facade.editaFornecedor("nome", "email", "novoemail@"));
		assertEquals("Edição de email não ocorreu como esperado", "nome - emailnovo@ - 1111-1111",this.facade.imprimeFornecedor("nome"));
		assertTrue("Retornou false para uma edição de telefone de fornecedor bem-sucedida", this.facade.editaFornecedor("nome", "telefone", "2222-2222"));
		assertEquals("Edição de email não ocorreu como esperado", "nome - emailnovo@ - 2222-2222",this.facade.imprimeFornecedor("nome"));
	}
	
	@Test
	void testaRemoveFornecedor() {
		assertFalse("Retornou true para uma remoção de fornecedor inexistente", this.facade.removeFornecedor("nome inexistente"));
		this.facade.cadastraFornecedor("nome", "email@", "1111-1111");
		assertTrue("Retornou false para uma remoção de fornecedor bem-sucedida", this.facade.removeFornecedor("nome"));
		assertNull("Retornou informação sobre um fornecedor que já foi removido do sistema", this.facade.imprimeFornecedor("nome"));
	}
}
