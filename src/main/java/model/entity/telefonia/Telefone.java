package model.entity.telefonia;

public class Telefone {

	private String codigoInternacional;
	private String ddd;
	private String numero;
	private boolean ativo;

	public Telefone(String codigoInternacional, String ddd, String numero, boolean ativo) {
		super();
		this.codigoInternacional = codigoInternacional;
		this.ddd = ddd;
		this.numero = numero;
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "+" + codigoInternacional + " (" + ddd + ") " + numero + " - " + (ativo ? "ativo" : "inativo");
	}

	public String getCodigoInternacional() {
		return codigoInternacional;
	}

	public void setCodigoInternacional(String codigoInternacional) {
		this.codigoInternacional = codigoInternacional;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
