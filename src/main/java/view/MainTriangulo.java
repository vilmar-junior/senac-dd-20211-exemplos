package view;

import model.entity.Triangulo;
import model.entity.TrianguloRetangulo;

public class MainTriangulo {

	public static void main(String[] args) {
		Triangulo triangulo1 = new TrianguloRetangulo(4, 4);
		TrianguloRetangulo triangulo2 = new TrianguloRetangulo(5, 8);

		System.out.println("Área do T1: " + triangulo1.getArea());
		System.out.println("Área do T2: " + triangulo2.getArea());
	}
}
