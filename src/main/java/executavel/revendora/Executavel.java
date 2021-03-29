package executavel.revendora;

import view.revendedora.Menu;

public class Executavel {

	public static void main(String[] args) {

		// MVC:
		// Tela (View) --VO--> Controller --VO--> Model[ BO --VO--> DAO]
		// Tela (View) <--VO-- Controller <--VO-- Model[ BO <--VO-- DAO]

		Menu menu = new Menu();
		menu.apresentarMenu();

	}

}
