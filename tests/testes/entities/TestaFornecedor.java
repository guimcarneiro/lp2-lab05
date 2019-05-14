package testes.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saga.entities.Fornecedor;

class TestaFornecedor {

	private Fornecedor fornecedor;
	
	@BeforeEach
	void setUp() throws Exception {
		this.fornecedor = new Fornecedor("Nome", "email@", "1111-1111");
	}

	@Test
	void testaFornecedor() {
		try {
			new Fornecedor("", "email@", "1111-1111");
			fail("Não lançou IllegalArgumentException para nome vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			new Fornecedor("nome", "", "1111-1111");
			fail("Não lançou IllegalArgumentException para email vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			new Fornecedor("nome", "email@", "");
			fail("Não lançou IllegalArgumentException para telefone vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			new Fornecedor(null, "email@", "1111-1111");
			fail("Não lançou NullPointerException para nome nulo");
		}catch(NullPointerException npe) {}
		try {
			new Fornecedor("nome", null, "1111-1111");
			fail("Não lançou NullPointerException para email nulo");
		}catch(NullPointerException npe) {}
		try {
			new Fornecedor("nome", "email@", null);
			fail("Não lançou NullPointerException para telefone nulo");
		}catch(NullPointerException npe) {}
	}
	
	@Test
	void testaAdicionaProduto() {
		assertTrue("Retornou false para uma adição de produto bem-sucedida",this.fornecedor.adicionaProduto("nome", 10.0, "descricao produto"));
		assertFalse("Retornou true para uma adição de produto mal-sucedida(produto já existente no sistema)", this.fornecedor.adicionaProduto("nome", 10.0, "descricao produto"));
	}
	
	@Test
	void testaRemoveProduto() {
		assertFalse("Retornou true para a remoção de um produto inexistente no fornecedor", this.fornecedor.removeProduto("produto inexistente"));
		this.fornecedor.adicionaProduto("produto1", 10.0, "descricao produto");
		assertTrue("Retornou false para a remoção de um produto já existente", this.fornecedor.removeProduto("produto1"));
		assertFalse("Retornou true para a remoção de um produto que já tinha sido removido anteriormente", this.fornecedor.removeProduto("produto1"));
	}
	
	@Test
	void testaConsultaProduto() {
		assertNull("Não retornou null para uma busca de produto inexistente",this.fornecedor.consultaProduto("produto inexistente"));
		this.fornecedor.adicionaProduto("produto1", 10.0, "descricao produto");
		assertEquals("String de retorno da consulta mal-formatada", "produto1 - descricao produto - R$10,00", this.fornecedor.consultaProduto("produto1"));
	}
	
	@Test
	void testaEditaPrecoProduto() {
		assertFalse("Retornou true para a edição do preço de um produto inexistente", this.fornecedor.editaPrecoProduto("produto inexistente", 10.0));
		this.fornecedor.adicionaProduto("produto1", 10.0, "descricao produto");
		assertFalse("Retornou true para a edição do preço do produto para um valor negativo", this.fornecedor.editaPrecoProduto("produto1", -10.0));
		assertTrue("Retornou false para uma edição de preço do produto bem-sucedida", this.fornecedor.editaPrecoProduto("produto1", 15.0));
		assertEquals("A alteração do preço não surtiu o efeito desejado","produto1 - descricao produto - R$15,00", this.fornecedor.consultaProduto("produto1"));
	}
	
	@Test
	void testaGetProdutosAll() {
		assertEquals("Não retornou uma String vazia quando não possui produtos cadastrados", "",this.fornecedor.getProdutosAll());
		this.fornecedor.adicionaProduto("produto1", 10.0, "descricao produto1");
		this.fornecedor.adicionaProduto("produto2", 15.0, "descricao produto2");
		assertEquals("String de retorno do método mal-formatada", "Nome - produto1 - descricao produto1 - R$10,00 | Nome - produto2 - descricao produto2 - R$15,00", this.fornecedor.getProdutosAll());
	}
	
	@Test
	void testaToString() {
		assertEquals("toString do fornecedor mal-formatada","Nome - email@ - 1111-1111" ,this.fornecedor.toString());
	}

}
