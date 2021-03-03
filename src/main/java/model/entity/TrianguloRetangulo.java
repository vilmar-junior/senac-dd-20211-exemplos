package model.entity;

/**
 * Classe de exemplo para ilustrar o uso de atributos e métodos privados
 * 
 * A área do triangulo retangulo nao pode ser alterada externamente.
 * 
 * @author vilmar
 * 
 */
public class TrianguloRetangulo extends Triangulo {

	public TrianguloRetangulo(double base, double altura) {
		super(base, altura);
	}

	@Override
	public void calcularArea() {
		double area = (this.getBase() * this.getAltura()) / 2;
		this.setArea(area);
	}

}
