package testes.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saga.entities.Produto;

class TestaProduto {

	private Produto produto;
	
	@BeforeEach
	void setUp() throws Exception {
		this.produto = new Produto("produto1", 10.0, "descricao produto");
	}

	@Test
	void testaProduto() {
		try {
			new Produto("", 10.0, "descricao produto");
			fail("Não lançou IllegalArgumentException para o nome vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			new Produto("Nome", 10.0, "");
			fail("Não lançou IllegalArgumentException para a descricao vazia");
		}catch(IllegalArgumentException iae) {}
		try {
			new Produto(null, 10.0, "descricao produto");
			fail("Não lançou NullPointerException para o nome nulo");
		}catch(NullPointerException npe) {}
		try {
			new Produto("Nome", 10.0, null);
			fail("Não lançou NullPointerException para a descricao nula");
		}catch(NullPointerException npe) {}
		try {
			new Produto("Nome", -10.0, "descricao produto");
			fail("Não lançou IllegalArgumentException para o preço negativo");
		}catch(IllegalArgumentException iae) {}
	}
	
	@Test
	void testaSetPreco() {
		try {
			this.produto.setPreco(-10.0);
			fail("Não lançou IllegalArgumentException para preço negativo do produto");
		}catch(IllegalArgumentException iae) {}
		this.produto.setPreco(20.0);
		if(this.produto.getPreco() != 20.0) {
			fail("Preço do produto não bateu com o valor editado");
		}
	}
	
	@Test
	void testaToString() {
		assertEquals("toString do produto mal-formatado", "produto1 - descricao produto - R$10,00",this.produto.toString());
	}

}
