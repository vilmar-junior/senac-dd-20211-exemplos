package view.telefonia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.telefonia.EnderecoController;
import model.entity.telefonia.Endereco;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaCadastroEndereco {

	private JFrame janela;
	private JTextField txtCep;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JComboBox cbUf;
	private JTextArea txtLogradouro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEndereco window = new TelaCadastroEndereco();
					window.janela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroEndereco() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		janela = new JFrame();
		janela.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				txtCep.requestFocus();
			}
		});
		janela.setTitle("Cadastro de Endere\u00E7o");
		janela.setBounds(100, 100, 390, 300);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.getContentPane().setLayout(null);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(100, 16, 50, 14);
		janela.getContentPane().add(lblCep);
		
		txtCep = new JTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evento) {
				if(evento.getKeyChar() == KeyEvent.VK_ENTER) {
					consultarCep();
				}
				
			}
		});
		
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				consultarCep();
			}
		});
		txtCep.setBounds(145, 10, 100, 20);
		janela.getContentPane().add(txtCep);
		txtCep.setColumns(10);
		
		JButton btnBuscarCep = new JButton("Buscar CEP");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarCep();
			}
		});
		btnBuscarCep.setBounds(249, 10, 120, 23);
		janela.getContentPane().add(btnBuscarCep);
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setBounds(10, 130, 100, 14);
		janela.getContentPane().add(lblLogradouro);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 55, 100, 14);
		janela.getContentPane().add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 80, 100, 14);
		janela.getContentPane().add(lblEstado);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(10, 100, 100, 14);
		janela.getContentPane().add(lblNmero);
		
		txtLogradouro = new JTextArea();
		txtLogradouro.setBounds(120, 135, 245, 80);
		janela.getContentPane().add(txtLogradouro);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(120, 55, 245, 20);
		janela.getContentPane().add(txtCidade);
		txtCidade.setColumns(10);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(120, 100, 245, 20);
		janela.getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		cbUf = new JComboBox();
		cbUf.setModel(new DefaultComboBoxModel(new String[] {"Selecione a UF", "AC", "AP", "AM", "AL", "SC"}));
		cbUf.setBounds(120, 80, 245, 20);
		janela.getContentPane().add(cbUf);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cadastrarEndereco();
			}
		});
		btnCadastrar.setBounds(10, 230, 350, 23);
		janela.getContentPane().add(btnCadastrar);
	}

	protected void consultarCep() {
		EnderecoController controller = new EnderecoController();
		Endereco e = controller.consultarPorCep(txtCep.getText());
		
		if(e != null) {
			preencherEnderecoNaTela(e);
		}
	}

	protected void preencherEnderecoNaTela(Endereco e) {
		this.txtCidade.setText(e.getCidade());
		this.txtLogradouro.setText(e.getLogradouro());
		this.cbUf.setSelectedItem(e.getUf());
		
		this.txtCidade.setEnabled(false);

		if(e.getLogradouro() != null && !e.getLogradouro().isEmpty()) {
			this.txtLogradouro.setEnabled(false);
		}else {
			this.txtLogradouro.setEnabled(true);
		}
		
		this.cbUf.setEnabled(false);
	}

	protected void cadastrarEndereco() {
		if(cbUf.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um estado");
		}else {
			//Preencher um EnderecoVO
			Endereco novoEndereco = new Endereco();
			novoEndereco.setCep(txtCep.getText());
			novoEndereco.setCidade(txtCidade.getText());
			
			//TODO validar o estado selecionado
			novoEndereco.setUf(cbUf.getSelectedItem().toString());
			novoEndereco.setLogradouro(txtLogradouro.getText());
			novoEndereco.setNumero(txtNumero.getText());
			
			//Chamar EnderecoController, passando o novo endereço
			EnderecoController controlador = new EnderecoController();
			String mensagem = controlador.salvar(novoEndereco);
			
			//Mostrar na tela o que aconteceu com o cadastrar
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
}
