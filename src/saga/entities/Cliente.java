package saga.entities;

public class Cliente {

	private String cpf;
	private String nome;
	private String email;
	private String localizacao;
	
	public Cliente(String cpf, String nome, String email, String localizacao) {
		if(cpf == null) {
			throw new NullPointerException("O CPF não pode ser nulo");
		}
		if(nome == null) {
			throw new NullPointerException("O nome não pode ser nulo");
		}
		if(email == null){
			throw new NullPointerException("O e-mail não pode ser nulo");
		}
		if(localizacao == null) {
			throw new NullPointerException("A localização não pode ser nula");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("O CPF não pode ser vazio");
		}
		if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser vazio");
		}
		if(email.trim().isEmpty()) {
			throw new IllegalArgumentException("O e-mail não pode ser vazio");
		}
		if(localizacao.trim().isEmpty()) {
			throw new IllegalArgumentException("A localização não pode ser vazia");
		}
		
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.localizacao = localizacao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getCpf() {
		return cpf;
	}
	
	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}

	@Override
	public String toString() {
		return this.nome + " - " + this.localizacao + " - " + this.email;
	}
	
}
