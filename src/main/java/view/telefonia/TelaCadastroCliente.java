package view.telefonia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.telefonia.ClienteController;
import exception.telefonia.ClienteInvalidoException;
import exception.telefonia.EnderecoNaoInformadoException;
import model.entity.telefonia.Cliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroCliente() {
		setTitle("Cadastro de cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteController controlador = new ClienteController();
				//TODO preencher um objeto cliente com todos os dados da tela
				
				try {
					//TODO teste
					Cliente novoCliente = new Cliente();
					novoCliente.setNome("Edson Arantes");
					novoCliente.setCpf("11122233311");
					
					controlador.salvar(novoCliente);
				} catch (EnderecoNaoInformadoException | ClienteInvalidoException excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro ao salvar", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		contentPane.add(btnSalvar, BorderLayout.SOUTH);
	}

}
