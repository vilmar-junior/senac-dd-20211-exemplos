package model.entity.telefonia;

public class Endereco {

	private Integer id;
	private String logradouro;
	private String uf;
	private String cidade;
	private String cep;
	private String numero;

	public Endereco() {

	}

	public Endereco(String logradouro, String uf, String cidade, String cep, String numero) {
		super();
		this.logradouro = logradouro;
		this.uf = uf;
		this.cidade = cidade;
		this.cep = cep;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Endereco #" + id + " [logradouro=" + logradouro + ", uf=" + uf + ", cidade=" + cidade + ", cep=" + cep
				+ ", numero=" + numero + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
