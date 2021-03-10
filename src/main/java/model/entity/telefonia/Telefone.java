package model.entity.telefonia;

public class Telefone {

	private Integer id;
	private String codigoInternacional;
	private String ddd;
	private String numero;
	private Cliente dono;
	private boolean movel;
	private boolean ativo;

	public Telefone(String codigoInternacional, String ddd, String numero, boolean movel, boolean ativo) {
		super();
		this.codigoInternacional = codigoInternacional;
		this.ddd = ddd;
		this.numero = numero;
		this.movel = movel;
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return (this.movel ? "Móvel" : "Fixo") + " +" + codigoInternacional + " (" + ddd + ") " + numero + " - "
				+ (ativo ? "ativo" : "inativo");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isMovel() {
		return movel;
	}

	public void setMovel(boolean movel) {
		this.movel = movel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Cliente getDono() {
		return dono;
	}

	public void setDono(Cliente dono) {
		this.dono = dono;
	}

}
