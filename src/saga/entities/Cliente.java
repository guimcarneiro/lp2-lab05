package saga.entities;


/**
 * Entidade que representa um Cliente no sistema Saga. Esse possui um CPF, um nome e um e-mail,
 * e é identificado unicamente pelo seu CPF.
 * 
 * @author Guilherme de Melo Carneiro
 *
 */
public class Cliente implements Comparable<Cliente>{

	/**
	 * Atributo correspondente ao CPF do Cliente. Identifica unicamente o Cliente, portanto
	 * não pode ser alterado.
	 */
	private String cpf;
	
	/**
	 * Atributo correspondente ao nome do Cliente.
	 */
	private String nome;
	
	/**
	 * Atributo correspondente ao e-mail do Cliente.
	 */
	private String email;
	
	/**
	 * Atributo correspondente ao laboratório do qual o Cliente faz parte.
	 */
	private String localizacao;
	
	/**
	 * Constroi um Cliente a partir de um CPF, um nome, um e-mail e de seu laboratório correspondente.
	 * Campos preenchidos com valores nulos ou vazios lançam NullPointerException e IllegalArgumentException,
	 * respectivamente. Caso o cpf não possua 11 dígitos, IllegalArguments será lançada.
	 * 
	 * @param cpf String referente ao CPF do Cliente, deve possuir 11 dígitos
	 * @param nome String referente ao nome do Cliente
	 * @param email String referente ao e-mail do Cliente
	 * @param localizacao String referente ao laboratório do qual o Cliente faz parte
	 */
	public Cliente(String cpf, String nome, String email, String localizacao) {
		if(cpf == null) {
			throw new NullPointerException("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(nome == null) {
			throw new NullPointerException("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
		}
		if(email == null){
			throw new NullPointerException("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
		}
		if(localizacao == null) {
			throw new NullPointerException("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
		}
		if(cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
		}
		if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
		}
		if(email.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
		}
		if(localizacao.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
		}
		if(cpf.length() != 11) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cpf invalido.");
		}
		
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.localizacao = localizacao;
	}
	
	/**
	 * Retorna String correspondente ao nome do Cliente.
	 * 
	 * @return String do nome do Cliente
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Não possui retorno. Altera o nome do Cliente.
	 * 
	 * @param nome Novo nome do Cliente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna String correspondente ao e-mail do Cliente.
	 * 
	 * @return String do e-mail do Cliente
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Não possui retorno. Altera o e-mail do Cliente.
	 * 
	 * @param email novo E-mail do Cliente
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna String correspondente ao laboratório em que se localiza o Cliente.
	 * 
	 * @return String da localização do Cliente
	 */
	public String getLocalizacao() {
		return localizacao;
	}

	/**
	 * Não possui retorno. Altera a localização do Cliente.
	 * 
	 * @param localizacao Nova localização do laboratório do Cliente
	 */
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	/**
	 * Retorna String correspondente ao CPF do Cliente.
	 * 
	 * @return String correspondente ao CPF do Cliente.
	 */
	public String getCpf() {
		return cpf;
	}
	
	/**
	 * Retorna um inteiro que identifica um Cliente. Um Cliente é identificado unicamente pelo seu CPF.
	 * 
	 * @return inteiro que identifica unicamente um Cliente
	 */
	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}

	/**
	 * Retorna String com informações sobre o Cliente. O texto segue o seguinte formato:
	 * "NOME_CLIENTE - LOCALIZACAO_CLIENTE - EMAIL-CLIENTE"
	 * 
	 * @return String com informacoes sobre o Cliente
	 */
	@Override
	public String toString() {
		return this.nome + " - " + this.localizacao + " - " + this.email;
	}
	
	/**
	 * Retorna um booleano sobre a igualdade entre um objeto qualquer e um Cliente. Retornará true
	 * quando o objeto comparado for do tipo Cliente e possuir o mesmo cpf do que o que se compara
	 * 
	 * @param obj objeto qualquer que será comparado ao Cliente
	 * 
	 * @return true quando o objeto comparado for do tipo Cliente e tiver o mesmo cpf do que o que se compara
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		Cliente obj1 = (Cliente) obj;
		if(this.cpf.equals(obj1.cpf)) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna inteiro sobre a ordem de um objeto cliente em relação a outro. Caso o comparado venha depois, retorna
	 * um valor negativo, caso sejam iguais retorna 0, caso o comparado venha antes retorna numero positivo. A comparação
	 * acontece de acordo com a ordem alfabética entre os nomes.
	 * 
	 * @param c Cliente com o qual se quer comparar
	 * @return antes na ordem alfabética: -1, iguais: 0, depois na ordem alfabética: 1
	 */
	@Override
	public int compareTo(Cliente c) {
		return this.nome.compareToIgnoreCase(c.nome);
	}
}
