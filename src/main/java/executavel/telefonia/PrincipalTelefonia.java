package executavel.telefonia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.entity.telefonia.Cliente;
import model.entity.telefonia.Endereco;
import model.entity.telefonia.Telefone;

public class PrincipalTelefonia {

	public static void main(String[] args) {
		ArrayList<Cliente> clientes = criarClientes();

		for (Cliente c : clientes) {
			System.out.println(c.toString());
		}
	}

	private static ArrayList<Cliente> criarClientes() {
		Cliente pele = criarCliente("Edson Arantes", "010.010.010-10");
		Cliente socrates = criarCliente("Sócrates Brasileiro", "008.010.010-10");
		Cliente garrincha = criarCliente("Manoel dos Santos", "011.010.010-10");
		Cliente zico = criarCliente("Artur Antunes", "020.010.010-10");
		Cliente vampeta = criarCliente("Marcos André", "005.010.010-10");

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(pele);
		clientes.add(socrates);
		clientes.add(garrincha);
		clientes.add(zico);
		clientes.add(vampeta);

		return clientes;
	}

	private static Cliente criarCliente(String nome, String cpf) {
		Cliente novoCliente = new Cliente(nome, cpf, criarTelefonesAleatorios(), criarEnderecoAleatorio(), true);
		return novoCliente;
	}

	private static List<Telefone> criarTelefonesAleatorios() {
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();

		int quantidadeTelefones = new Random().nextInt(5);

		for (int i = 0; i < quantidadeTelefones; i++) {
			telefones.add(criarTelefoneAleatorio());
		}

		return telefones;
	}

	private static Telefone criarTelefoneAleatorio() {
		String codigoInternacional = String.valueOf(new Random().nextInt(99));
		String ddd = String.valueOf(new Random().nextInt(99));
		String numero = String.valueOf(new Random().nextInt(99999999));
		boolean movel = new Random().nextInt(2) > 0;

		Telefone novoTelefone = new Telefone(codigoInternacional, ddd, numero, movel, true);

		return novoTelefone;
	}

	private static Endereco criarEnderecoAleatorio() {
		Endereco novoEndereco = new Endereco();

		double numeroBaseCep = new Random().nextInt(99999);
		double numero = new Random().nextInt(100);

		novoEndereco.setCep(numeroBaseCep + "-000");
		novoEndereco.setLogradouro("Rua " + numero);
		novoEndereco.setCidade("Cidade " + numero);
		novoEndereco.setNumero(numero + "");
		novoEndereco.setUf("SC");

		return novoEndereco;
	}
}
