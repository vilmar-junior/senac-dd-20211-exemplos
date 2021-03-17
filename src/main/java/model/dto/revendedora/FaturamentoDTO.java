package model.dto.revendedora;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FaturamentoDTO {

	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DecimalFormat df = new DecimalFormat("0.00");

	private String nome;
	private String cpf;
	private String telefone;
	private String modelo;
	private String placa;
	private LocalDate dataVenda;
	private double valorVenda;

	public FaturamentoDTO(String nome, String cpf, String telefone, String modelo, String placa, LocalDate dataVenda,
			double valorVenda) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.modelo = modelo;
		this.placa = placa;
		this.dataVenda = dataVenda;
		this.valorVenda = valorVenda;
	}

	public FaturamentoDTO() {
		super();
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public void imprimir() {
		System.out.printf("%-20s   %-15s   %-15s   %-10s   %-10s   %-11s   %10s \n", this.getNome(), this.getCpf(),
				this.getTelefone(), this.getModelo(), this.getPlaca(), this.getDataVenda().format(dataFormatter),
				df.format(this.getValorVenda()));
	}

}
