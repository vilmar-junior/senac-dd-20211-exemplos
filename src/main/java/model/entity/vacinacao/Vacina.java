package model.entity.vacinacao;

import java.time.LocalDate;

public class Vacina {

	private Integer id;
	private String nome;
	private String paisOrigem;
	private String estagioPesquisa;
	private LocalDate dataInicioPesquisa;
	private Pessoa pesquisadorResponsavel;
	private int fase;
	private int quantidadeDoses;

	public Vacina() {
		super();
	}

	public Vacina(Integer id, String nome, String paisOrigem, String estagioPesquisa, LocalDate dataInicioPesquisa,
			Pessoa pesquisadorResponsavel, int fase, int quantidadeDoses) {
		super();
		this.id = id;
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.fase = fase;
		this.quantidadeDoses = quantidadeDoses;
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

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public String getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(String estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public int getQuantidadeDoses() {
		return quantidadeDoses;
	}

	public void setQuantidadeDoses(int quantidadeDoses) {
		this.quantidadeDoses = quantidadeDoses;
	}

	@Override
	public String toString() {
		return "Vacina [nome=" + nome + ", paisOrigem=" + paisOrigem + ", estagioPesquisa=" + estagioPesquisa
				+ ", dataInicioPesquisa=" + dataInicioPesquisa + ", pesquisadorResponsavel=" + pesquisadorResponsavel
				+ ", fase=" + fase + ", quantidadeDoses=" + quantidadeDoses + "]";
	}
}
