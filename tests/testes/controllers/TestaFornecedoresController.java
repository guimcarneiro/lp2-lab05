package testes.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saga.controllers.FornecedoresController;

class TestaFornecedoresController {

	private FornecedoresController fornecedores;
	
	@BeforeEach
	void setUp() throws Exception {
		this.fornecedores = new FornecedoresController();
	}

	@Test
	void testaCadastraFornecedor() {
		assertEquals("Não retornou o nome do fornecedor em um cadastro bem-sucedido","nome", this.fornecedores.cadastraFornecedor("nome", "email@", "2321-2312"));
		try {
			this.fornecedores.cadastraFornecedor("", "aaa@", "2321-3213");
			fail("Não lançou IllegalArgumentException para nome vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.fornecedores.cadastraFornecedor("nome2", "", "2531-3123");
			fail("Não lançou IllegalArgumentException para email vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.fornecedores.cadastraFornecedor("nome3", "bbb@", "");
			fail("Não lançou IllegalArgumentException para telefone vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.fornecedores.cadastraFornecedor(null, "ccc@", "3213-3123");
			fail("Não lançou NullPointerException para nome nulo");
		}catch(NullPointerException npe) {}
		try {
			this.fornecedores.cadastraFornecedor("nome4", null, "6546-7657");
			fail("Não lançou NullPointerException para email nulo");
		}catch(NullPointerException npe) {}
		try {
			this.fornecedores.cadastraFornecedor("nome5", "eee@", null);
			fail("Não lançou NullPointerException para telefone nulo");
		}catch(NullPointerException npe) {}
		try {
			this.fornecedores.cadastraFornecedor("nome", "email@", "2321-2312");
			fail("Não lançou IllegalArgumentException para a tentativa de cadastrar um cliente com nome já existente no sistema");
		}catch(IllegalArgumentException iae) {}
	}
	
	@Test
	void testaImprimeFornecedor() {
		this.fornecedores.cadastraFornecedor("nome", "email@", "2222-2222");
		assertEquals("Impressão de fornecedor incorreta","nome - email@ - 2222-2222",this.fornecedores.imprimeFornecedor("nome"));
		assertNull("Não retornou null para uma impressão de fornecedor inexistente", this.fornecedores.imprimeFornecedor("fornecedor inexistente"));
		assertNull("Não retornou null para uma impressão de fornecedor com parâmetro null", this.fornecedores.imprimeFornecedor(null));
	}
	
	@Test
	void testaEditaFornecedor() {
		this.fornecedores.cadastraFornecedor("nome", "email@", "4444-4444");
		assertTrue("Retornou false para uma edição de email bem-sucedida", this.fornecedores.editaFornecedor("nome", "email", "emailnovo@"));
		assertEquals("Não editou corretamente o email do fornecedor","nome - emailnovo@ - 4444-4444" ,this.fornecedores.imprimeFornecedor("nome"));
		assertTrue("Retornou false para uma edição de telefone bem-sucedida", this.fornecedores.editaFornecedor("nome", "telefone", "3333-3333"));
		assertEquals("Não editou corretamente o telefone do fornecedor","nome - emailnovo@ - 3333-3333" ,this.fornecedores.imprimeFornecedor("nome"));
		assertFalse("Retornou true para uma edição de fornecedor inexistente", this.fornecedores.editaFornecedor("Nome inexistente", "email", "qualquer nome"));
		assertFalse("Retornou true para uma edição de atributo inexistente", this.fornecedores.editaFornecedor("nome", "atributo inexistente", "qualquer info"));
	}
	
	@Test
	void testaRemoveFornecedor() {
		this.fornecedores.cadastraFornecedor("nome", "email@", "3333-3333");
		assertFalse("Retornou true para uma remoção de fornecedor inexistente", this.fornecedores.removeFornecedor("fornecedor inexistente"));
		assertTrue("Retornou false para uma remoção de fornecedor bem-sucedida", this.fornecedores.removeFornecedor("nome"));
		assertFalse("Retornou true para uma remoção de um fornecedor já removido anteriormente", this.fornecedores.removeFornecedor("nome"));
		assertFalse("Retornou true para uma remoção de fornecedor com nome nulo", this.fornecedores.removeFornecedor(null));
		assertNull("Retornou não-nulo para uma busca de fornecedor já removido do sistema", this.fornecedores.imprimeFornecedor("nome"));
	}
	
	@Test
	void testaImprimeProduto() {
		this.fornecedores.cadastraFornecedor("nome", "email@", "3333-3333");
		this.fornecedores.cadastraProduto("nome", "Produto", 10.0, "descricao do produto");
		assertEquals("Retornou uma String mal-formatada de um produto cadastrado", "Produto - descricao do produto - R$10,00",this.fornecedores.imprimeProduto("nome", "Produto"));
	}
	
	@Test
	void testaImprimeFornecedoresAll() {
		assertEquals("Não retornou uma String vazia quando não possui fornecedores cadastrados", "", this.fornecedores.imprimeFornecedoresAll());
		this.fornecedores.cadastraFornecedor("nome1", "email1@", "1111-1111");
		this.fornecedores.cadastraFornecedor("nome2", "email2@", "2222-2222");
		assertEquals("Retornou String com todos os fornecedores mal-formatada","nome1 - email1@ - 1111-1111 | nome2 - email2@ - 2222-2222",this.fornecedores.imprimeFornecedoresAll());
	}
	
	@Test
	void testaImprimeProdutosFornecedor() {
		this.fornecedores.cadastraFornecedor("nome", "email@", "1111-1111");
		assertNull("Não retornou null para um fornecedor sem produtos cadastrados", this.fornecedores.imprimeProdutosFornecedor("nome"));
		assertNull("Não retornou null para um fornecedor inexistente", this.fornecedores.imprimeProdutosFornecedor("nome inexistente"));
		this.fornecedores.cadastraProduto("nome", "Nome produto", 10.0, "descricao produto");
		this.fornecedores.cadastraProduto("nome", "Nome produto 2", 12.0, "descricao produto 2");
		assertEquals("Retornou uma String mal-formatada","nome - Nome produto - descricao produto - R$10,00 | nome - Nome produto 2 - descricao produto 2 - R$12,00", this.fornecedores.imprimeProdutosFornecedor("nome"));
	}
	
	@Test
	void testaImprimeProdutosAll() {
		assertEquals("Não retornou uma string vazia quando não existem produtos cadastrados","", this.fornecedores.imprimeProdutosAll());
		this.fornecedores.cadastraFornecedor("nome1", "email1@", "1111-1111");
		this.fornecedores.cadastraFornecedor("nome2", "email2@", "2222-2222");
		this.fornecedores.cadastraProduto("nome1", "Nome produto 1", 1.0, "descricao produto 1");
		this.fornecedores.cadastraProduto("nome2", "Nome produto 2", 2.0, "descricao produto 2");
		String saidaEsperada = "nome1 - Nome produto 1 - descricao produto 1 - R$1,00 | nome2 - Nome produto 2 - descricao produto 2 - R$2,00";
		assertEquals("String de saída esperada para a impressão de todos os produtos cadastrados mal-formatada", saidaEsperada, this.fornecedores.imprimeProdutosAll());
	}
	
	@Test
	void testaEditaPrecoProduto() {
		this.fornecedores.cadastraFornecedor("nome", "email1@", "1111-1111");
		this.fornecedores.cadastraProduto("nome", "produto1", 1.0, "descricao produto");
		assertTrue("Retornou false para uma edição de preço bem-sucedida", this.fornecedores.editarPrecoProduto("nome", "produto1", 2.0));
		assertEquals("Não editou corretamente o preço do produto", "produto1 - descricao produto - R$2,00",this.fornecedores.imprimeProduto("nome", "produto1"));
		assertFalse("Retornou true para uma edição para preço negativo", this.fornecedores.editarPrecoProduto("nome", "produto1", -4.0));
		assertFalse("Retornou true para uma edição de preço de produto de fornecedor inexistente", this.fornecedores.editarPrecoProduto("nome inexistente", "produto qualquer", 3.00));
		assertFalse("Retornou true para uma edição de preço de produto inexistente para um fornecedor existente", this.fornecedores.editarPrecoProduto("nome", "produto inexistente", 5.00));
	}
	
	@Test
	void testaRemoveProduto() {
		this.fornecedores.cadastraFornecedor("nome", "email1@", "1111-1111");
		this.fornecedores.cadastraProduto("nome", "produto 1", 10.0, "descricao produto 1");
		assertFalse("Retornou true para a remoção de um produto inexistente no sistema", this.fornecedores.removeProduto("nome", "produto 2"));
		assertFalse("Retornou true para a remoção de um produto de um fornecedor inexistente", this.fornecedores.removeProduto("nome inexistente", "produto inexistente"));
		assertTrue("Retornou false para a remoção de um produto bem-sucedida", this.fornecedores.removeProduto("nome", "produto 1"));
		assertNull("Não removeu realmente o produto do fornecedor", this.fornecedores.imprimeProduto("nome", "produto 1"));
	}
}