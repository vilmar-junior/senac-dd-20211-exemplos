package model.dto.revendedora;

import java.text.DecimalFormat;

public class VendasDTO {
	
	DecimalFormat df = new DecimalFormat("0.00");
	
	private int mes;
	private int ano;
	private double totalVendas;
	
	public VendasDTO(int mes, int ano, double totalVendas) {
		super();
		this.mes = mes;
		this.ano = ano;
		this.totalVendas = totalVendas;
	}

	public VendasDTO() {
		super();
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public double getTotalVendas() {
		return totalVendas;
	}

	public void setTotalVendas(double totalVendas) {
		this.totalVendas = totalVendas;
	}
	
	public void imprimir(){   
		System.out.printf("%5d   %-5d   %12s\n", 
				this.getMes(), 
				this.getAno(),
				df.format(this.getTotalVendas()));
	}

}
