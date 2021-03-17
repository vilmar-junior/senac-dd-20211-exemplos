package view.triangulo;

import model.entity.triangulo.Triangulo;
import model.entity.triangulo.TrianguloRetangulo;

public class MainTriangulo {

	public static void main(String[] args) {
		Triangulo triangulo1 = new TrianguloRetangulo(4, 4);
		TrianguloRetangulo triangulo2 = new TrianguloRetangulo(5, 8);

		System.out.println("Área do T1: " + triangulo1.getArea());
		System.out.println("Área do T2: " + triangulo2.getArea());
	}
}
