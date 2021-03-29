package view.revendedora;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Menu {

	Scanner scanner = new Scanner(System.in);

	private static final int OPCAO_MENU_CLIENTE = 1;
	private static final int OPCAO_MENU_VEICULO = 2;
	private static final int OPCAO_MENU_VENDA = 3;
	private static final int OPCAO_MENU_RELATORIO = 4;
	private static final int OPCAO_MENU_SAIR = 5;

	public void apresentarMenu() {
		int opcao = this.apresentarOpcoesMenuVersao2();

		while (opcao != OPCAO_MENU_SAIR) {
			switch (opcao) {
			case OPCAO_MENU_CLIENTE: {
				MenuCliente menuCliente = new MenuCliente();
				menuCliente.apresentarMenuCliente();
				break;
			}
			case OPCAO_MENU_VEICULO: {
				MenuVeiculo menuVeiculo = new MenuVeiculo();
				menuVeiculo.apresentarMenuVeiculo();
				break;
			}
			case OPCAO_MENU_VENDA: {
				MenuVenda menuVenda = new MenuVenda();
				menuVenda.apresentarMenuVenda();
				break;
			}
			case OPCAO_MENU_RELATORIO: {
				MenuRelatorio menuRelatorio = new MenuRelatorio();
				menuRelatorio.apresentarMenuRelatorio();
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "Opção inválida!");
			}
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}

	private int apresentarOpcoesMenuVersao2() {
		String mensagem = "Opções:\n";
		mensagem += OPCAO_MENU_CLIENTE + " - Menu Cliente \n";
		mensagem += OPCAO_MENU_VEICULO + " - Menu Veículo \n";
		mensagem += OPCAO_MENU_VENDA + " - Menu Venda \n";
		mensagem += OPCAO_MENU_RELATORIO + " - Menu Relatório \n";
		mensagem += OPCAO_MENU_SAIR + " - Sair \n";
		mensagem += "\nDigite a opção: ";

		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, mensagem, "Revenda Ferro Velho",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = 0;
		try {
			opcaoSelecionada = Integer.parseInt(valorInformadoPeloUsuario);
		} catch (NumberFormatException excecao) {
			JOptionPane.showMessageDialog(null, "Informe um valor numérico");
			this.apresentarOpcoesMenuVersao2();
		}

		return opcaoSelecionada;
	}

	private int apresentarOpcoesMenu() {
		System.out.println("\nRevenda Ferro Velho");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_CLIENTE + " - Menu Cliente");
		System.out.println(OPCAO_MENU_VEICULO + " - Menu Veículo");
		System.out.println(OPCAO_MENU_VENDA + " - Menu Venda");
		System.out.println(OPCAO_MENU_RELATORIO + " - Menu Relatório");
		System.out.println(OPCAO_MENU_SAIR + " - Sair");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(scanner.nextLine());
	}

}
