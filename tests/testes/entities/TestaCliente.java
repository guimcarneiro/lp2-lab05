package testes.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saga.entities.Cliente;

class TestaCliente {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testaCliente() {
		try {
			new Cliente("", "nome", "email", "laboratorio");
			fail("Não lançou IllegalArgumentException quando o cpf foi vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			new Cliente("11111", "", "email", "laboratorio");
			fail("Não lançou IllegalArgumentException quando o nome foi vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			new Cliente("11111", "nome", "", "laboratorio");
			fail("Não lançou IllegalArgumentException quando o email foi vazio");
		}catch(IllegalArgumentException iae) {}
		try {
			new Cliente("11111", "nome", "email", "");
			fail("Não lançou IllegalArgumentException quando a localizacao foi vazia");
		}catch(IllegalArgumentException iae) {}
		try {
			new Cliente(null, "nome", "email", "laboratorio");
			fail("Não lançou NullPointerException quando o cpf foi nulo");
		}catch(NullPointerException npe) {}
		try {
			new Cliente("11111", null, "email", "laboratorio");
			fail("Não lançou NullPointerException quando o nome foi nulo");
		}catch(NullPointerException npe) {}
		try {
			new Cliente("11111", "nome", null, "laboratorio");
			fail("Não lançou NullPointerException quando o email foi nulo");
		}catch(NullPointerException npe) {}
		try {
			new Cliente("11111", "nome", "email", null);
			fail("Não lançou NullPointerException quando a localizacao foi nula");
		}catch(NullPointerException npe) {}
	}
	
	@Test
	void testaToString() {
		assertEquals("toString() mal-formatado", "nome - laboratorio - email@", (new Cliente("11111", "nome", "email@", "laboratorio").toString()));
	}
	
}
