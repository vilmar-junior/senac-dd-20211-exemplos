package model.entity.revendedora;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VendaVO {
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DecimalFormat df = new DecimalFormat("0.00");
	
	private int idVenda;
	private int idCliente;
	private int idVeiculo;
	private double valorVenda;
	private LocalDate dataVenda;
	
	public VendaVO(int idVenda, int idCliente, int idVeiculo, double valorVenda, LocalDate dataVenda) {
		super();
		this.idVenda = idVenda;
		this.idCliente = idCliente;
		this.idVeiculo = idVeiculo;
		this.valorVenda = valorVenda;
		this.dataVenda = dataVenda;
	}

	public VendaVO() {
		super();
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public void imprimir(){   
		System.out.printf("%3d   %-10d   %-10d   %-11s  %-10s \n", 
				this.getIdVenda(), 
				this.getIdCliente(), 
				this.getIdVeiculo(),
				df.format(this.getValorVenda()),
				this.getDataVenda().format(dataFormatter));
	}
	
}
