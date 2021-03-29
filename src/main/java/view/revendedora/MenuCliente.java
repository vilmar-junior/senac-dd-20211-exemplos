package view.revendedora;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.revendora.ControladoraCliente;
import model.entity.revendedora.ClienteVO;
import util.StringUtil;

public class MenuCliente {

	private static final int OPCAO_MENU_CADASTRAR_CLIENTE = 1;
	private static final int OPCAO_MENU_CONSULTAR_CLIENTE = 2;
	private static final int OPCAO_MENU_ATUALIZAR_CLIENTE = 3;
	private static final int OPCAO_MENU_EXCLUIR_CLIENTE = 4;
	private static final int OPCAO_MENU_CLIENTE_VOLTAR = 5;

	private static final int OPCAO_MENU_CONSULTAR_TODOS_CLIENTES = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_CLIENTE = 2;
	private static final int OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR = 3;

	public void apresentarMenuCliente() {
		int opcao = this.apresentarOpcoesMenuVersao2();
		while (opcao != OPCAO_MENU_CLIENTE_VOLTAR) {
			switch (opcao) {
			case OPCAO_MENU_CADASTRAR_CLIENTE: {
				this.cadastrarClienteVersao2();
				break;
			}
			case OPCAO_MENU_CONSULTAR_CLIENTE: {
				this.consultarClienteVersao2();
				break;
			}
			case OPCAO_MENU_ATUALIZAR_CLIENTE: {
				this.atualizarClienteVersao2();
				break;
			}
			case OPCAO_MENU_EXCLUIR_CLIENTE: {
				this.excluirClienteVersao2();
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "Opção inválida!");
			}
			}
			opcao = this.apresentarOpcoesMenuVersao2();
		}
	}

	private int apresentarOpcoesMenuVersao2() {
		String titulo = "Revenda Ferro Velho";
		String mensagem = "---- Menu Cliente ----\n";
		mensagem += "Opções:\n";
		mensagem += OPCAO_MENU_CADASTRAR_CLIENTE + " - Cadastrar Cliente \n";
		mensagem += OPCAO_MENU_CONSULTAR_CLIENTE + " - Consultar Cliente \n";
		mensagem += OPCAO_MENU_ATUALIZAR_CLIENTE + " - Atualizar Cliente \n";
		mensagem += OPCAO_MENU_EXCLUIR_CLIENTE + " - Excluir Cliente \n";
		mensagem += OPCAO_MENU_CLIENTE_VOLTAR + " - Voltar \n";
		mensagem += "Digite a opção: \n";

		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, mensagem, titulo,
				JOptionPane.QUESTION_MESSAGE);

		int opcaoSelecionada = StringUtil.formatarStringParaInteiro(valorInformadoPeloUsuario);

		if (opcaoSelecionada == StringUtil.VALOR_INVALIDO) {
			this.apresentarOpcoesMenuVersao2();
		}

		return opcaoSelecionada;
	}

	private void cadastrarClienteVersao2() {
		ClienteVO clienteVO = new ClienteVO();

		String nomeInformado = JOptionPane.showInputDialog("Digite o nome do Cliente:");
		clienteVO.setNome(nomeInformado);

		clienteVO.setCpf(JOptionPane.showInputDialog("Digite o CPF do Cliente:"));
		clienteVO.setTelefone(JOptionPane.showInputDialog("Digite o telefone do Cliente: "));

		ControladoraCliente controladoraCliente = new ControladoraCliente();
		String resultado = controladoraCliente.cadastrarClienteController(clienteVO);
		JOptionPane.showMessageDialog(null, resultado);
	}

	private void consultarClienteVersao2() {
		int opcao = this.apresentarOpcoesMenuConsultaVersao2();
		ControladoraCliente controladoraCliente = new ControladoraCliente();
		while (opcao != OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR) {
			switch (opcao) {
			case OPCAO_MENU_CONSULTAR_TODOS_CLIENTES: {
				opcao = OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR;
				String mensagem = "";

				ArrayList<ClienteVO> listaClientesVO = controladoraCliente.consultarTodosClientesController();

				if (listaClientesVO.isEmpty()) {
					mensagem = "Lista de Clientes não localizada.";
				}

				mensagem = "--------- RESULTADO DA CONSULTA ---------\n";
				mensagem += "ID, NOME, CPF, TELEFONE \n";
				for (int i = 0; i < listaClientesVO.size(); i++) {
					mensagem += listaClientesVO.get(i).toString() + "\n";
				}

				JOptionPane.showMessageDialog(null, mensagem);
				break;
			}
			case OPCAO_MENU_CONSULTAR_UM_CLIENTE: {
				opcao = OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR;
				ClienteVO clienteVO = new ClienteVO();
				clienteVO.setIdCliente(obterIdDaTela());

				ClienteVO cliente = controladoraCliente.consultarClienteController(clienteVO);
				if (cliente == null) {
					JOptionPane.showMessageDialog(null, "Cliente não localizado.");
				} else {
					String mensagem = "--------- RESULTADO DA CONSULTA ---------\n";
					mensagem += "ID, NOME, CPF, TELEFONE \n";
					if (cliente != null) {
						mensagem += cliente.toString();
					}

					JOptionPane.showMessageDialog(null, mensagem);
				}

				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "Opção Inválida");
				opcao = this.apresentarOpcoesMenuConsultaVersao2();
			}
			}
		}
	}

	private int obterIdDaTela() {
		int id = StringUtil.formatarStringParaInteiro(JOptionPane.showInputDialog("Digite o código do Cliente: "));

		if (id == StringUtil.VALOR_INVALIDO) {
			JOptionPane.showMessageDialog(null, "Informe um código numérico");
		}

		return id;
	}

	private int apresentarOpcoesMenuConsultaVersao2() {
		String mensagem = "Informe o tipo de consulta a ser realizada \n";
		mensagem += OPCAO_MENU_CONSULTAR_TODOS_CLIENTES + " - Consultar todos os Clientes \n";
		mensagem += OPCAO_MENU_CONSULTAR_UM_CLIENTE + " - Consultar um Cliente Específico \n";
		mensagem += OPCAO_MENU_CONSULTAR_CLIENTE_VOLTAR + " - Voltar \n";
		mensagem += "\nDigite a Opção: ";

		int valorConvertido = StringUtil.formatarStringParaInteiro(JOptionPane.showInputDialog(mensagem));

		if (valorConvertido == StringUtil.VALOR_INVALIDO) {
			JOptionPane.showMessageDialog(null, "Informe um código numérico");
		}

		return valorConvertido;
	}

	private void atualizarClienteVersao2() {
		ClienteVO clienteVO = new ClienteVO();

		int id = obterIdDaTela();
		if (id != StringUtil.VALOR_INVALIDO) {
			clienteVO.setIdCliente(id);

			clienteVO.setNome(JOptionPane.showInputDialog("Digite o nome do Cliente: "));
			clienteVO.setCpf(JOptionPane.showInputDialog("Digite o CPF do Cliente: "));
			clienteVO.setTelefone(JOptionPane.showInputDialog("Digite o telefone do Cliente: "));

			ControladoraCliente controladoraCliente = new ControladoraCliente();
			String resultado = controladoraCliente.atualizarClienteController(clienteVO);
			JOptionPane.showMessageDialog(null, resultado);
		}
	}

	private void excluirClienteVersao2() {
		ClienteVO clienteVO = new ClienteVO();
		int id = obterIdDaTela();

		if (id != StringUtil.VALOR_INVALIDO) {
			clienteVO.setIdCliente(id);

			ControladoraCliente controladoraCliente = new ControladoraCliente();
			String resultado = controladoraCliente.excluirClienteController(clienteVO);
			JOptionPane.showMessageDialog(null, resultado);
		}
	}
}
