package model.entity.vacinacao;

import java.time.LocalDate;

public class AplicacaoVacina {

	private Integer id;
	private int idPessoaVacinada;
	private Vacina vacina;
	private LocalDate dataAplicacao;
	private int nota;

	public AplicacaoVacina() {
		super();
	}

	public AplicacaoVacina(Integer id, int idPessoaVacinada, Vacina vacina, LocalDate dataAplicacao, int nota) {
		super();
		this.id = id;
		this.idPessoaVacinada = idPessoaVacinada;
		this.vacina = vacina;
		this.dataAplicacao = dataAplicacao;
		this.nota = nota;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getIdPessoaVacinada() {
		return idPessoaVacinada;
	}

	public void setIdPessoaVacinada(int idPessoaVacinada) {
		this.idPessoaVacinada = idPessoaVacinada;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
}
