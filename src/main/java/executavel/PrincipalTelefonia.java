package executavel;

import java.util.ArrayList;

import model.entity.telefonia.Cliente;
import model.entity.telefonia.Endereco;
import model.entity.telefonia.Telefone;

public class PrincipalTelefonia {

	public static void main(String[] args) {
		/*
		 * cadastre 5 clientes na empresa em uma lista de clientes.
		 * 
		 * Cada cliente deve possuir todos os dados preenchidos.
		 */
		Endereco endereco1 = new Endereco("Rua Mauro Ramos", "SC", "Florianópolis", "88050-500", "200");
		Telefone telefone1 = new Telefone("55", "48", "3333-1010", true);

		ArrayList<Telefone> telefonesDoPele = new ArrayList<Telefone>();
		telefonesDoPele.add(telefone1);

		Cliente cliente1 = new Cliente("Edson Arantes", "010.010.010-10", telefonesDoPele, endereco1, true);
		Cliente cliente2 = new Cliente("Sócrates Brasileiro", "008.010.010-10", new ArrayList(), endereco1, true);
		Cliente cliente3 = new Cliente("Manoel dos Santos", "011.010.010-10", new ArrayList(), endereco1, true);
		Cliente cliente4 = new Cliente("Artur Antunes", "020.010.010-10", new ArrayList(), endereco1, true);
		Cliente cliente5 = new Cliente("Marcos André", "005.010.010-10", new ArrayList(), endereco1, true);

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);

		// valor inicial; condicao de parada; passo indutivo
		for (int i = 0; i < clientes.size(); i++) {

		}

		// for each
		for (Cliente c : clientes) {
			System.out.println(c.toString());
		}
	}

}
