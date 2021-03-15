package executavel.telefonia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.bo.telefonia.TelefoneBO;
import model.dao.telefonia.ClienteDAO;
import model.entity.telefonia.Cliente;
import model.entity.telefonia.Endereco;
import model.entity.telefonia.Telefone;

public class PrincipalTelefonia {

	public static void main(String[] args) {
//		TelefoneBO telefoneBO = new TelefoneBO();
//
//		Telefone novoTelefone = new Telefone("55", "70", "003333333", true, null);
//
//		try {
//			telefoneBO.cadastrar(novoTelefone);
//			System.out.println(novoTelefone);
//		} catch (NumeroIndisponivelException e) {
//			System.out.println("Telefone nao cadastrado");
//			System.out.println(e.getMessage());
//		}

		ClienteDAO cDAO = new ClienteDAO();
		Cliente pele = cDAO.consultarPorId(1);
		System.out.println(pele);

		List<Telefone> telefonesDoPele = pele.getTelefones();

		TelefoneBO telefoneBO = new TelefoneBO();
		String mensagem = telefoneBO.excluir(telefonesDoPele.get(2).getId());

		System.out.println(mensagem);
//
//		novoTelefone.setIdCliente(pele.getId());
//		tDAO.atualizar(novoTelefone);
//
//		System.out.println(novoTelefone);

//
//		System.out.println(pele);
//
//		EnderecoDAO eDAO = new EnderecoDAO();
//		Endereco rua2 = eDAO.consultarPorId(2);
//
//		pele.setEndereco(rua2);
//
//		cDAO.atualizar(pele);
//
//		System.out.println(pele);

//		if (cDAO.excluir(5)) {
//			System.out.println("Cliente excluído");
//		} else {
//			System.out.println("Cliente NAO excluído");
//		}

//		ArrayList<Cliente> clientes = (ArrayList<Cliente>) cDAO.consultarTodos();
//		for (Cliente c : clientes) {
//			System.out.println(c);
//		}

//		ArrayList<Cliente> clientes = criarClientes();
//
//		ClienteBO clienteBO = new ClienteBO();
//		for (Cliente c : clientes) {
//			try {
//				clienteBO.salvar(c);
//			} catch (EnderecoNaoInformadoException e) {
//				System.err.println("Cliente sem endereco");
//			}
//		}

//		Endereco novoEndereco = new Endereco("Rua Mauro Ramos", "SC", "Florianópolis", "88333000", "S/N");
//
//		// Teste, ainda violando o MVC
//		EnderecoDAO dao = new EnderecoDAO();
//
//		List<Endereco> enderecos = dao.consultarTodos();
//		for (Endereco enderecoBuscado : enderecos) {
//			System.out.println(enderecoBuscado);
//		}
//		dao.cadastrar(novoEndereco);
//
//		System.out.println("Endereço novo: " + novoEndereco.toString());
//
//		novoEndereco.setLogradouro("Rua ALTERADA");
//
//		if (dao.atualizar(novoEndereco)) {
//			System.out.println("Endereço atualizado: " + novoEndereco.toString());
//		} else {
//			System.out.println("Nao atualizou endereço");
//		}
//
//		if (dao.excluir(1)) {
//			System.out.println("Endereço excluído");
//		} else {
//			System.out.println("Endereço NAO excluído");
//		}

	}

	private static ArrayList<Cliente> criarClientes() {
		Cliente pele = criarCliente("Edson Arantes", "01001001010");
		Cliente socrates = criarCliente("Sócrates Brasileiro", "00801001010");
		Cliente garrincha = criarCliente("Manoel dos Santos", "01101001010");
		Cliente zico = criarCliente("Artur Antunes", "02001001010");
		Cliente vampeta = criarCliente("Marcos André", "00501001010");

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

		Telefone novoTelefone = new Telefone(codigoInternacional, ddd, numero, movel, null);

		return novoTelefone;
	}

	private static Endereco criarEnderecoAleatorio() {
		Endereco novoEndereco = new Endereco();

		int numeroBaseCep = new Random().nextInt(99999);
		double numero = new Random().nextInt(100);

		novoEndereco.setCep(numeroBaseCep + "" + "000");
		novoEndereco.setLogradouro("Rua " + numero);
		novoEndereco.setCidade("Cidade " + numero);
		novoEndereco.setNumero(numero + "");
		novoEndereco.setUf("SC");

		return novoEndereco;
	}
}
