package model.entity;

/**
 * Classe de exemplo para ilustrar o uso de atributos e métodos privados
 * 
 * A área do triangulo retangulo nao pode ser alterada externamente.
 * 
 * @author vilmar
 * 
 */
public abstract class Triangulo {

	private double base;
	private double altura;
	private double area;

	public Triangulo(double base, double altura) {
		super();
		this.base = base;
		this.altura = altura;
		this.calcularArea();
	}

	public abstract void calcularArea();

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
		this.calcularArea();
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
		this.calcularArea();
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
}
