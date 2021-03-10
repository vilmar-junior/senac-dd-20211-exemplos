package view.revendedora;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.revendora.ControladoraVenda;
import model.entity.revendedora.VendaVO;

public class MenuVenda {
	
	Scanner teclado = new Scanner(System.in);
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static final int OPCAO_MENU_CADASTRAR_VENDA = 1;
	private static final int OPCAO_MENU_CONSULTAR_VENDA = 2;
	private static final int OPCAO_MENU_ATUALIZAR_VENDA = 3;
	private static final int OPCAO_MENU_EXCLUIR_VENDA = 4;
	private static final int OPCAO_MENU_VENDA_VOLTAR = 5;
	
	private static final int OPCAO_MENU_CONSULTAR_TODAS_VENDAS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UMA_VENDA = 2;
	private static final int OPCAO_MENU_CONSULTAR_VENDA_VOLTAR = 3;

	public void apresentarMenuVenda() {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_VENDA_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_VENDA: {
					this.cadastrarVenda();
					break;
				}
				case OPCAO_MENU_CONSULTAR_VENDA: {
					this.consultarVenda();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_VENDA: {
					this.atualizarVenda();
					break;
				}
				case OPCAO_MENU_EXCLUIR_VENDA: {
					this.excluirVenda();
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
		System.out.println("---- Menu Carro ----");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_CADASTRAR_VENDA + " - Cadastrar Venda");
		System.out.println(OPCAO_MENU_CONSULTAR_VENDA + " - Consultar Venda");
		System.out.println(OPCAO_MENU_ATUALIZAR_VENDA + " - Atualizar Venda");
		System.out.println(OPCAO_MENU_EXCLUIR_VENDA + " - Excluir Venda");
		System.out.println(OPCAO_MENU_VENDA_VOLTAR + " - Voltar");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void cadastrarVenda() {
		VendaVO vendaVO = new VendaVO();
		System.out.print("\nDigite o código do Cliente: ");
		vendaVO.setIdCliente(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite o código do Veiculo: ");
		vendaVO.setIdVeiculo(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite valor da Venda: ");
		vendaVO.setValorVenda(Double.parseDouble(teclado.nextLine()));
		System.out.print("Digite a data da Venda: ");
		vendaVO.setDataVenda(LocalDate.parse(teclado.nextLine(), dataFormatter));
		
		ControladoraVenda controladoraVenda = new ControladoraVenda();
		String resultado = controladoraVenda.cadastrarVendaController(vendaVO);
		System.out.println(resultado);
	}
	
	private void consultarVenda() {
		int opcao = this.apresentarOpcoesMenuConsulta();
		ControladoraVenda controladoraVenda = new ControladoraVenda();
		while (opcao != OPCAO_MENU_CONSULTAR_VENDA_VOLTAR) {
			switch (opcao) {
				case OPCAO_MENU_CONSULTAR_TODAS_VENDAS: {
					opcao = OPCAO_MENU_CONSULTAR_VENDA_VOLTAR;
					ArrayList<VendaVO> listaVendasVO = controladoraVenda.consultarTodasVendasController();
					if(listaVendasVO.isEmpty()) {
						System.out.println("\nLista de Vendas não localizada.");
					}
					System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
					System.out.printf("\n%3s   %-10s   %-10s   %-11s  %-10s   \n", 
							"ID", "ID_CLIENTE", "ID_VEICULO", "VALOR_VENDA", "DATA_VENDA");
					for (int i = 0; i < listaVendasVO.size(); i++) {
						listaVendasVO.get(i).imprimir();
					}
					break;
				}
				case OPCAO_MENU_CONSULTAR_UMA_VENDA: {
					opcao = OPCAO_MENU_CONSULTAR_VENDA_VOLTAR;
					VendaVO vendaVO = new VendaVO();
					System.out.print("\nInforme o código da Venda: ");
					vendaVO.setIdVenda(Integer.parseInt(teclado.nextLine()));
					VendaVO venda = controladoraVenda.consultarVendaController(vendaVO);
					if(venda == null) {
						System.out.println("\nVenda não localizada.");
					}
					System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
					System.out.printf("\n%3s   %-10s   %-10s   %-11s  %-10s   \n", 
							"ID", "ID_CLIENTE", "ID_VEICULO", "VALOR_VENDA", "DATA_VENDA");
					if(venda != null) {
						venda.imprimir();
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
		System.out.println(OPCAO_MENU_CONSULTAR_TODAS_VENDAS + " - Consultar todas as Vendas");
		System.out.println(OPCAO_MENU_CONSULTAR_UMA_VENDA + " - Consultar uma Venda Específica");
		System.out.println(OPCAO_MENU_CONSULTAR_VENDA_VOLTAR + " - Voltar");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void atualizarVenda() {
		VendaVO vendaVO = new VendaVO();
		System.out.print("\nDigite o código da Venda: ");
		vendaVO.setIdVenda(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite o código do Cliente: ");
		vendaVO.setIdCliente(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite o código do Veiculo: ");
		vendaVO.setIdVeiculo(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite valor da Venda: ");
		vendaVO.setValorVenda(Double.parseDouble(teclado.nextLine()));
		System.out.print("Digite a data da Venda: ");
		vendaVO.setDataVenda(LocalDate.parse(teclado.nextLine(), dataFormatter));
		
		ControladoraVenda controladoraVenda = new ControladoraVenda();
		String resultado = controladoraVenda.atualizarVendaController(vendaVO);
		System.out.println(resultado);
	}
	
	private void excluirVenda() {
		VendaVO vendaVO = new VendaVO();
		System.out.print("\nDigite o código da Venda: ");
		vendaVO.setIdVenda(Integer.parseInt(teclado.nextLine()));
		
		ControladoraVenda controladoraVenda = new ControladoraVenda();
		String resultado = controladoraVenda.excluirVendaController(vendaVO);
		System.out.println(resultado);
	}

}
