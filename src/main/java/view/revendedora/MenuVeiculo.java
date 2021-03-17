package view.revendedora;

import java.util.ArrayList;
import java.util.Scanner;

import controller.revendora.ControladoraVeiculo;
import model.entity.revendedora.TipoVeiculo;
import model.entity.revendedora.VeiculoVO;

public class MenuVeiculo {
	
	Scanner teclado = new Scanner(System.in);
	
	private static final int OPCAO_MENU_CADASTRAR_VEICULO = 1;
	private static final int OPCAO_MENU_CONSULTAR_VEICULO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_VEICULO = 3;
	private static final int OPCAO_MENU_EXCLUIR_VEICULO = 4;
	private static final int OPCAO_MENU_VEICULO_VOLTAR = 5;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_VEICULOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_VEICULO = 2;
	private static final int OPCAO_MENU_CONSULTAR_VEICULOS_VOLTAR = 3;
	
	private static final int OPCAO_VEICULO_CARRO = 1;
	private static final int OPCAO_VEICULO_MOTO = 2;
	private static final int OPCAO_VEICULO_SUV = 3;
	private static final int OPCAO_VEICULO_FIM = 99;
	

	public void apresentarMenuVeiculo() {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_VEICULO_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_VEICULO: {
					this.cadastrarVeiculo();
					break;
				}
				case OPCAO_MENU_CONSULTAR_VEICULO: {
					this.consultarVeiculo();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_VEICULO: {
					this.atualizarVeiculo();
					break;
				}
				case OPCAO_MENU_EXCLUIR_VEICULO: {
					this.excluirVeiculo();
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
		System.out.println("---- Menu Veículo ----");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_CADASTRAR_VEICULO + " - Cadastrar Veículo");
		System.out.println(OPCAO_MENU_CONSULTAR_VEICULO + " - Consultar Veículo");
		System.out.println(OPCAO_MENU_ATUALIZAR_VEICULO + " - Atualizar Veículo");
		System.out.println(OPCAO_MENU_EXCLUIR_VEICULO + " - Excluir Veículo");
		System.out.println(OPCAO_MENU_VEICULO_VOLTAR + " - Voltar");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void cadastrarVeiculo() {
		VeiculoVO veiculoVO = new VeiculoVO();
		System.out.print("\nDigite o modelo do Veículo: ");
		veiculoVO.setModelo(teclado.nextLine());
		System.out.print("Digite o tipo do Veículo: ");
		int opcao = this.apresentarOpcoesTipoVeiculo();
		while(opcao != OPCAO_VEICULO_FIM) {
			switch(opcao) {
				case OPCAO_VEICULO_CARRO: {
					opcao = OPCAO_VEICULO_FIM;
					veiculoVO.setTipo(TipoVeiculo.CARRO);
					break;
				}
				case OPCAO_VEICULO_MOTO: {
					opcao = OPCAO_VEICULO_FIM;
					veiculoVO.setTipo(TipoVeiculo.MOTO);
					break;
				}
				case OPCAO_VEICULO_SUV: {
					opcao = OPCAO_VEICULO_FIM;
					veiculoVO.setTipo(TipoVeiculo.SUV);
					break;
				}
				default: {
					System.out.println("\nOpção inválida!");
					opcao = this.apresentarOpcoesTipoVeiculo();
				}
			}
		}
		System.out.print("Digite o fabricante do Veículo: ");
		veiculoVO.setFabricante(teclado.nextLine());
		System.out.print("Digite o ano do Veículo: ");
		veiculoVO.setAno(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite a cor do Veículo: ");
		veiculoVO.setCor(teclado.nextLine());
		System.out.print("Digite a placa do Veículo: ");
		veiculoVO.setPlaca(teclado.nextLine());
		
		ControladoraVeiculo controladoraVeiculo = new ControladoraVeiculo();
		String resultado = controladoraVeiculo.cadastrarVeiculoController(veiculoVO);
		System.out.println(resultado);
	}

	private int apresentarOpcoesTipoVeiculo() {
		System.out.println("\nOpções:");
		System.out.println(OPCAO_VEICULO_CARRO + " - Carro");
		System.out.println(OPCAO_VEICULO_MOTO + " - Moto");
		System.out.println(OPCAO_VEICULO_SUV + " - SUV");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void atualizarVeiculo() {
		VeiculoVO veiculoVO = new VeiculoVO();
		System.out.print("\nDigite o código do Veículo: ");
		veiculoVO.setIdVeiculo(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite o modelo do Veículo: ");
		veiculoVO.setModelo(teclado.nextLine());
		System.out.print("Digite o tipo do Veículo: ");
		int opcao = this.apresentarOpcoesTipoVeiculo();
		while(opcao != OPCAO_VEICULO_FIM) {
			switch(opcao) {
				case OPCAO_VEICULO_CARRO: {
					opcao = OPCAO_VEICULO_FIM;
					veiculoVO.setTipo(TipoVeiculo.CARRO);
					break;
				}
				case OPCAO_VEICULO_MOTO: {
					opcao = OPCAO_VEICULO_FIM;
					veiculoVO.setTipo(TipoVeiculo.MOTO);
					break;
				}
				case OPCAO_VEICULO_SUV: {
					opcao = OPCAO_VEICULO_FIM;
					veiculoVO.setTipo(TipoVeiculo.SUV);
					break;
				}
				default: {
					System.out.println("\nOpção inválida!");
					opcao = this.apresentarOpcoesTipoVeiculo();
				}
			}
		}
		System.out.print("Digite o fabricante do Veículo: ");
		veiculoVO.setFabricante(teclado.nextLine());
		System.out.print("Digite o ano do Veículo: ");
		veiculoVO.setAno(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite a cor do Veículo: ");
		veiculoVO.setCor(teclado.nextLine());
		System.out.print("Digite a placa do Veículo: ");
		veiculoVO.setPlaca(teclado.nextLine());
		
		ControladoraVeiculo controladoraVeiculo = new ControladoraVeiculo();
		String resultado = controladoraVeiculo.atualizarVeiculoController(veiculoVO);
		System.out.println(resultado);
	}
	
	
	private void consultarVeiculo() {
		int opcao = this.apresentarOpcoesMenuConsulta();
		ControladoraVeiculo controladoraVeiculo = new ControladoraVeiculo();
		while(opcao != OPCAO_MENU_CONSULTAR_VEICULOS_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CONSULTAR_TODOS_VEICULOS:{
					opcao = OPCAO_MENU_CONSULTAR_VEICULOS_VOLTAR;
					ArrayList<VeiculoVO> listaVeiculosVO = controladoraVeiculo.consultarTodosVeiculosController();
					if(listaVeiculosVO.isEmpty()) {
						System.out.println("\nLista de Veículos não localizada.");
					}
					System.out.print("\n---------- RESULTADO DA CONSULTA ----------");
					System.out.printf("\n%3s   %-10s   %-10s   %-10s   %-5s   %-10s   %-10s \n",
							"ID", "MODELO", "TIPO", "FABRICANTE", "ANO", "COR", "PLACA");
					for(int i = 0; i < listaVeiculosVO.size(); i++) {
						listaVeiculosVO.get(i).imprimir();
					}
					break;
				}
				case OPCAO_MENU_CONSULTAR_UM_VEICULO:{
					opcao = OPCAO_MENU_CONSULTAR_VEICULOS_VOLTAR;
					VeiculoVO veiculoVO = new VeiculoVO();
					System.out.print("\nDigite o código do Veículo: ");
					veiculoVO.setIdVeiculo(Integer.parseInt(teclado.nextLine()));
					VeiculoVO veiculo = controladoraVeiculo.consultarVeiculoController(veiculoVO);
					if(veiculo == null) {
						System.out.println("\nVeículo não localizado.");
					}
					System.out.print("\n---------- RESULTADO DA CONSULTA ----------");
					System.out.printf("\n%3s   %-10s   %-10s   %-10s   %-5s   %-10s   %-10s \n",
							"ID", "MODELO", "TIPO", "FABRICANTE", "ANO", "COR", "PLACA");
					if(veiculo != null) {
						veiculo.imprimir();
					}
					break;
				}
				default: {
					System.out.println("\nOpção Inválida!");
					opcao = this.apresentarOpcoesMenuConsulta();
				}
			}
		}
	}
	
	
	private int apresentarOpcoesMenuConsulta() {
		System.out.println("\nInforme o tipo de consulta a ser realizada.");
		System.out.println(OPCAO_MENU_CONSULTAR_TODOS_VEICULOS + " - Consultar todos os Veículos");
		System.out.println(OPCAO_MENU_CONSULTAR_UM_VEICULO + " - Consultar um Veículo Específico");
		System.out.println(OPCAO_MENU_CONSULTAR_VEICULOS_VOLTAR + " - Voltar");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private void excluirVeiculo() {
		VeiculoVO veiculoVO = new VeiculoVO();
		System.out.print("\nDigite o código do Veículo: ");
		veiculoVO.setIdVeiculo(Integer.parseInt(teclado.nextLine()));
		
		ControladoraVeiculo controladoraVeiculo = new ControladoraVeiculo();
		String resultado = controladoraVeiculo.excluirVeiculoController(veiculoVO);
		System.out.println(resultado);
		
	}

}
