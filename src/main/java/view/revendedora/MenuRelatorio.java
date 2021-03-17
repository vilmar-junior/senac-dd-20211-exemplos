package view.revendedora;

import java.util.ArrayList;
import java.util.Scanner;

import controller.revendora.ControladoraRelatorio;
import model.dto.revendedora.FaturamentoDTO;
import model.dto.revendedora.VendasDTO;

public class MenuRelatorio {

	Scanner teclado = new Scanner(System.in);

	private static final int OPCAO_MENU_RELATORIO_FATURAMENTO = 1;
	private static final int OPCAO_MENU_RELATORIO_VENDAS = 2;
	private static final int OPCAO_MENU_RELATORIO_VOLTAR = 3;

	public void apresentarMenuRelatorio() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_RELATORIO_VOLTAR) {
			switch (opcao) {
			case OPCAO_MENU_RELATORIO_FATURAMENTO: {
				opcao = OPCAO_MENU_RELATORIO_VOLTAR;
				this.relatorioFaturamento();
				break;
			}
			case OPCAO_MENU_RELATORIO_VENDAS: {
				opcao = OPCAO_MENU_RELATORIO_VOLTAR;
				this.relatorioVendas();
				break;
			}
			default: {
				System.out.println("\nOpção inválida!");
				opcao = this.apresentarOpcoesMenu();
			}
			}
		}
	}

	private int apresentarOpcoesMenu() {
		System.out.println("\nRevenda Ferro Velho");
		System.out.println("---- Menu Relatório ----");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_RELATORIO_FATURAMENTO + " - Relatório de Faturamento do Mês");
		System.out.println(OPCAO_MENU_RELATORIO_VENDAS + " - Relatório de Vendas realizadas");
		System.out.println(OPCAO_MENU_RELATORIO_VOLTAR + " - Voltar");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private void relatorioVendas() {
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		ArrayList<VendasDTO> listaVendasDTO = controladoraRelatorio.consultarRelatorioVendasController();
		if (listaVendasDTO.isEmpty()) {
			System.out.println("\nLista de Faturamento não localizada.");
		}
		System.out.print("\n--------- RELATÓRIO DE VENDAS ---------");
		System.out.printf("\n%5s   %-5s   %12s  \n", "MÊS", "ANO", "TOTAL VENDAS");
		for (int i = 0; i < listaVendasDTO.size(); i++) {
			listaVendasDTO.get(i).imprimir();
		}
	}

	private void relatorioFaturamento() {
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		ArrayList<FaturamentoDTO> listaFaturamentoDTO = controladoraRelatorio.consultarRelatorioFaturamentoController();
		if (listaFaturamentoDTO.isEmpty()) {
			System.out.println("\nLista de Faturamento não localizada.");
		}
		System.out.print("\n--------- RELATÓRIO DE FATURAMENTO ---------");
		System.out.printf("\n%-20s   %-15s   %-15s   %-10s   %-10s   %-11s   %10s   \n", "NOME", "CPF", "TELEFONE",
				"MODELO", "PLACA", "DATA VENDA", "VALOR");
		for (int i = 0; i < listaFaturamentoDTO.size(); i++) {
			listaFaturamentoDTO.get(i).imprimir();
		}
	}

}
