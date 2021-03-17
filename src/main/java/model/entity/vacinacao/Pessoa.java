package model.entity.vacinacao;

import java.util.List;

public class Pessoa {

	public static final int PESQUISADOR = 1;
	public static final int VOLUNTARIO = 2;
	public static final int PUBLICO_GERAL = 2;

	private Integer id;
	private String nome;
	private String cpf;
	private char sexo;
	private int tipo;
	private List<AplicacaoVacina> vacinacoes;

	public Pessoa() {
		super();
	}

	public Pessoa(Integer id, String nome, String cpf, char sexo, int tipo, List<AplicacaoVacina> vacinacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.tipo = tipo;
		this.vacinacoes = vacinacoes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public List<AplicacaoVacina> getVacinacoes() {
		return vacinacoes;
	}

	public void setVacinacoes(List<AplicacaoVacina> vacinacoes) {
		this.vacinacoes = vacinacoes;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", sexo=" + sexo + ", tipo=" + tipo + ", vacinacoes="
				+ vacinacoes + "]";
	}
}
