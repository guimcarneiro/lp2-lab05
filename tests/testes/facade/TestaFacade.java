package testes.facade;

import static org.junit.Assert.assertEquals;

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
	}
	
	@Test
	void testaImprimeCliente() {
		
	}
	
	@Test
	void testaListaClientes() {
		
	}
	
	@Test
	void testaEditaCliente() {
		
	}
	
	@Test
	void testaRemoveCliente() {
		
	}
	
	@Test
	void testaCadastraFornecedor() {
		
	}
	
	@Test
	void testaImprimeFornecedor() {
		
	}
	
	@Test
	void testaListaFornecedores() {
		
	}
	
	@Test
	void testaEditaFornecedor() {
		
	}
	
	@Test
	void testaRemoveFornecedor() {
		
	}
	
	@Test
	void testaCadastraProduto() {
		
	}
	
	@Test
	void testaConsultaProdutosFornecedor() {
		
	}
	
	@Test
	void testaConsultaProdutosAll() {
		
	}
	
	@Test
	void testaEditaPrecoProduto() {
		
	}
	
	@Test
	void testaRemoveProduto() {
		
	}
}
