package saga.entities;

import java.util.Date;

public class Compra {

	private Date data;
	private Produto produto;
	
	public Compra(Date data, Produto produto) {
		this.data = data;
		this.produto = produto;
	}

	public Date getData() {
		return data;
	}

	public Produto getProduto() {
		return produto;
	}
	
}
