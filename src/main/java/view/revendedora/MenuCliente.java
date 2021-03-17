package view.revendedora;

import java.util.ArrayList;
import java.util.Scanner;

import controller.revendora.ControladoraCliente;
import model.entity.revendedora.ClienteVO;

public class MenuCliente {

	Scanner teclado = new Scanner(System.in);

	private static final int OPCAO_MENU_CADASTRAR_CLIENTE = 1;
	private static final int OPCAO_MENU_CONSULTAR_CLIENTE = 2;
	private static final int OPCAO_MENU_ATUALIZAR_CLIENTE = 3;
	private static final int OPCAO_MENU_EXCLUIR_CLIENTE = 4;
	private static final int OPCAO_MENU_CLIENTE_VOLTAR = 5;

	private static final int OPCAO_MENU_CONSULTAR_TODOS_CLIENTES = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_CLIENTE = 2;
	private static final int OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR = 3;

	public void apresentarMenuCliente() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_CLIENTE_VOLTAR) {
			switch (opcao) {
			case OPCAO_MENU_CADASTRAR_CLIENTE: {
				this.cadastrarCliente();
				break;
			}
			case OPCAO_MENU_CONSULTAR_CLIENTE: {
				this.consultarCliente();
				break;
			}
			case OPCAO_MENU_ATUALIZAR_CLIENTE: {
				this.atualizarCliente();
				break;
			}
			case OPCAO_MENU_EXCLUIR_CLIENTE: {
				this.excluirCliente();
				break;
			}
			default: {
				System.out.println("\nOpção inválida!");
			}
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}

	private int apresentarOpcoesMenu() {
		System.out.println("\nRevenda Ferro Velho");
		System.out.println("---- Menu Cliente ----");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_CADASTRAR_CLIENTE + " - Cadastrar Cliente");
		System.out.println(OPCAO_MENU_CONSULTAR_CLIENTE + " - Consultar Cliente");
		System.out.println(OPCAO_MENU_ATUALIZAR_CLIENTE + " - Atualizar Cliente");
		System.out.println(OPCAO_MENU_EXCLUIR_CLIENTE + " - Excluir Cliente");
		System.out.println(OPCAO_MENU_CLIENTE_VOLTAR + " - Voltar");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private void cadastrarCliente() {
		ClienteVO clienteVO = new ClienteVO();
		System.out.print("\nDigite o nome do Cliente: ");
		clienteVO.setNome(teclado.nextLine());
		System.out.print("Digite o CPF do Cliente: ");
		clienteVO.setCpf(teclado.nextLine());
		System.out.print("Digite o telefone do Cliente: ");
		clienteVO.setTelefone(teclado.nextLine());

		ControladoraCliente controladoraCliente = new ControladoraCliente();
		String resultado = controladoraCliente.cadastrarClienteController(clienteVO);
		System.out.println(resultado);
	}

	private void consultarCliente() {
		int opcao = this.apresentarOpcoesMenuConsulta();
		ControladoraCliente controladoraCliente = new ControladoraCliente();
		while (opcao != OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR) {
			switch (opcao) {
			case OPCAO_MENU_CONSULTAR_TODOS_CLIENTES: {
				opcao = OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR;
				ArrayList<ClienteVO> listaClientesVO = controladoraCliente.consultarTodosClientesController();
				if (listaClientesVO.isEmpty()) {
					System.out.println("\nLista de Clientes não localizada.");
				}
				System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
				System.out.printf("\n%3s   %-40s   %-15s   %-15s  \n", "ID", "NOME", "CPF", "TELEFONE");
				for (int i = 0; i < listaClientesVO.size(); i++) {
					listaClientesVO.get(i).imprimir();
				}
				break;
			}
			case OPCAO_MENU_CONSULTAR_UM_CLIENTE: {
				opcao = OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR;
				ClienteVO clienteVO = new ClienteVO();
				System.out.print("\nInforme o código do Cliente: ");
				clienteVO.setIdCliente(Integer.parseInt(teclado.nextLine()));
				ClienteVO cliente = controladoraCliente.consultarClienteController(clienteVO);
				if (cliente == null) {
					System.out.println("\nCliente não localizado.");
				}
				System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
				System.out.printf("\n%3s   %-40s   %-15s   %-15s  \n", "ID", "NOME", "CPF", "TELEFONE");
				if (cliente != null) {
					cliente.imprimir();
				}
				break;
			}
			default: {
				System.out.println("\nOpção Inválida");
				opcao = this.apresentarOpcoesMenuConsulta();
			}
			}
		}
	}

	private int apresentarOpcoesMenuConsulta() {
		System.out.println("\nInforme o tipo de consulta a ser realizada");
		System.out.println(OPCAO_MENU_CONSULTAR_TODOS_CLIENTES + " - Consultar todos os Clientes");
		System.out.println(OPCAO_MENU_CONSULTAR_UM_CLIENTE + " - Consultar um Cliente Específico");
		System.out.println(OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR + " - Voltar");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private void atualizarCliente() {
		ClienteVO clienteVO = new ClienteVO();
		System.out.print("\nDigite o código do Cliente: ");
		clienteVO.setIdCliente(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite o nome do Cliente: ");
		clienteVO.setNome(teclado.nextLine());
		System.out.print("Digite o CPF do Cliente: ");
		clienteVO.setCpf(teclado.nextLine());
		System.out.print("Digite o telefone do Cliente: ");
		clienteVO.setTelefone(teclado.nextLine());

		ControladoraCliente controladoraCliente = new ControladoraCliente();
		String resultado = controladoraCliente.atualizarClienteController(clienteVO);
		System.out.println(resultado);
	}

	private void excluirCliente() {
		ClienteVO clienteVO = new ClienteVO();
		System.out.print("\nDigite o código do Cliente: ");
		clienteVO.setIdCliente(Integer.parseInt(teclado.nextLine()));

		ControladoraCliente controladoraCliente = new ControladoraCliente();
		String resultado = controladoraCliente.excluirClienteController(clienteVO);
		System.out.println(resultado);
	}

}
