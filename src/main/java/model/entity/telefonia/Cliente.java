package model.entity.telefonia;

import java.util.List;

public class Cliente {

	private Integer id;
	private String nome;
	private String cpf;
	private List<Telefone> telefones;
	private Endereco endereco;
	private boolean ativo;

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, List<Telefone> telefones, Endereco endereco, boolean ativo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefones = telefones;
		this.endereco = endereco;
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		String textoTelefones = "";

		if (this.getTelefones() == null || this.getTelefones().isEmpty()) {
			textoTelefones = "Sem telefones";
		} else {
			textoTelefones = telefones.toString();
		}

		return "Nome: " + this.getNome() + " - " + this.getCpf() + " (" + (this.isAtivo() ? "ativo" : "inativo")
				+ "). \nEndereco: " + this.getEndereco() + ".\nTelefones: " + textoTelefones + "\n";
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

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
