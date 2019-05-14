package testes.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saga.controllers.ProdutosController;

class TestaProdutosController {

	private ProdutosController produtos;
	
	@BeforeEach
	void setUp() throws Exception {
		this.produtos = new ProdutosController();
	}

	@Test
	void testaCadastraProduto() {
		assertTrue("Retornou false em um cadastro bem-sucedido", this.produtos.cadastraProduto("Nome produto", 1.0, "descricao produto"));
		assertFalse("Retornou true para um cadastro onde o produto já existia no sistema", this.produtos.cadastraProduto("Nome produto", 2.0, "descricao produto"));
		try {
			this.produtos.cadastraProduto("", 1.0, "descricao produto");
			fail("Não lançou IllegalArgumentException para o nome do produto vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.produtos.cadastraProduto("nome321", 1.0, "");
			fail("Não lançou IllegalArgumentException para a descricao do produto vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			this.produtos.cadastraProduto(null, 1.0, "descricao valida");
			fail("Não lançou NullPointerException para o cadastro de um produto com nome nulo");
		}catch(NullPointerException npe) {}
		try {
			this.produtos.cadastraProduto("nome validoo", 1.0, null);
			fail("Não lançou NullPointerException para o cadastro de um produto com descricao nula");
		}catch(NullPointerException npe) {}
		try {
			this.produtos.cadastraProduto("nome com validade", -1.0, "descricao valida");
			fail("Não lançou IllegalArgumentException para o cadastro de um produto com preço negativo");
		}catch(IllegalArgumentException npe) {}
	}
	
	@Test
	void testaConsultaProduto() {
		assertNull("Não retornou null para uma busca de produto não cadastrado", this.produtos.consultaProduto("nome inexistente"));
		this.produtos.cadastraProduto("produto", 20.0, "descricao produto");
		assertEquals("Retornou uma String do produto mal-formatada", "produto - descricao produto - R$20,00",this.produtos.consultaProduto("produto"));
	}
	
	@Test
	void testaRemoveProduto() {
		assertFalse("Retornou true para uma remoção de produto inexistente no sistema", this.produtos.removeProduto("produto inexistente"));
		this.produtos.cadastraProduto("produto1", 1.0, "descricao produto1");
		assertTrue("Retornou false para uma remoção bem-sucedida", this.produtos.removeProduto("produto1"));
		assertFalse("Retornou true para uma remoção de um produto que já tinha sido removido anteriormente", this.produtos.removeProduto("produto1"));
		assertNull("Não retornou null na busca de um produto que já tinha sido removido do sistema", this.produtos.consultaProduto("produto1"));
	}
	
	@Test
	void testaEditaPrecoProduto() {
		assertFalse("Retornou true ao editar o preço de um produto inexistente no sistema", this.produtos.editaPrecoProduto("produto inexistente", 3.0));
		this.produtos.cadastraProduto("produto1", 5.0, "descricao produto1");
		assertFalse("Retornou true ao editar o preco de um produto existente no sistema para um valor negativo", this.produtos.editaPrecoProduto("produto1", -5.0));
		assertTrue("Retornou false ao editar o preço de um produto de maneira válida", this.produtos.editaPrecoProduto("produto1", 6.0));
		assertEquals("Não alterou corretamente o preço do produto, apesar de a edição ter sido bem-sucedida","produto1 - descricao produto1 - R$6,00" ,this.produtos.consultaProduto("produto1"));
	}
	
	@Test
	void testaExisteProduto() {
		assertFalse("Retornou true para um produto inexistente no sistema", this.produtos.existeProduto("produto inexistente"));
		this.produtos.cadastraProduto("produto1", 1.0, "descricao produto1");
		assertTrue("Retornou false para um produto existente no sistema", this.produtos.existeProduto("produto1"));
	}
}
