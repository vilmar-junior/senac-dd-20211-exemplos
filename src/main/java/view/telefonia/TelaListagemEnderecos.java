package view.telefonia;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.telefonia.EnderecoController;
import model.entity.telefonia.Endereco;

public class TelaListagemEnderecos extends JFrame {

	private JPanel contentPane;
	private JTable tblEnderecos;
	private JButton btnExcluirEndereco;
	private JButton btnEditarEndereco;
	private List<Endereco> enderecos;
	private EnderecoController controlador = new EnderecoController();
	private String[] nomesColunas = { "CEP", "Cidade", "Estado", "Número", "Logradouro" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemEnderecos frame = new TelaListagemEnderecos();
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
	public TelaListagemEnderecos() {
		setTitle("Consulta de endereços");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 337);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(Color.DARK_GRAY));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConsultarTodos = new JButton("Consultar todos");
		btnConsultarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarTabelaEnderecos();
			}
		});
		btnConsultarTodos.setBounds(143, 11, 141, 23);
		contentPane.add(btnConsultarTodos);
		
		
		btnExcluirEndereco = new JButton("Excluir endereço");
		btnExcluirEndereco.setEnabled(false);
		btnExcluirEndereco.setBounds(30, 227, 153, 23);
		
		btnExcluirEndereco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int indiceSelecionadoNaTabela = tblEnderecos.getSelectedRow();
				Endereco enderecoSelecionado = enderecos.get(indiceSelecionadoNaTabela - 1);

				String perguntaExclusao = "Deseja excluir o endereço: " + enderecoSelecionado.toString() + "?";
				
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, perguntaExclusao, "Está certo disso?", JOptionPane.YES_NO_OPTION);

				if(opcaoSelecionada == JOptionPane.YES_OPTION) {
					String mensagem = controlador.excluir(enderecoSelecionado.getId());
					JOptionPane.showMessageDialog(null, mensagem);
					atualizarTabelaEnderecos();
				}
			}
		});
		
		contentPane.add(btnExcluirEndereco);
		
		btnEditarEndereco = new JButton("Editar endereço");
		btnEditarEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Endereco enderecoSelecionado = enderecos.get(tblEnderecos.getSelectedRow() - 1);
				TelaCadastroEndereco telaCadastro = new TelaCadastroEndereco(enderecoSelecionado);
				telaCadastro.getJanela().setVisible(true);
			}
		});
		btnEditarEndereco.setEnabled(false);
		btnEditarEndereco.setBounds(233, 227, 147, 23);
		contentPane.add(btnEditarEndereco);
		
		tblEnderecos = new JTable();
		this.limparTabelaEnderecos();
		
		tblEnderecos = new JTable(tblEnderecos.getModel()) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tblEnderecos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblEnderecos.getSelectedRow();
				
				if (indiceSelecionado > 0) {
					btnEditarEndereco.setEnabled(true);
					btnExcluirEndereco.setEnabled(true);
				} else {
					btnEditarEndereco.setEnabled(false);
					btnExcluirEndereco.setEnabled(false);
				}
			}
		});
		
		tblEnderecos.setBounds(10, 45, 415, 175);
		contentPane.add(tblEnderecos);

	}
	
	protected void atualizarTabelaEnderecos() {
		enderecos = controlador.consultarTodos();
		this.limparTabelaEnderecos();
		
		DefaultTableModel model = (DefaultTableModel) this.tblEnderecos.getModel();
		
		for(Endereco end: this.enderecos) {
			Object[] novaLinhaTabela = new Object[5];
			String cepFormatado = end.getCep().substring(0, 5) + "-" + end.getCep().substring(5, 8);
			
			novaLinhaTabela[0] = cepFormatado;
			novaLinhaTabela[1] = end.getCidade();
			novaLinhaTabela[2] = end.getUf();
			novaLinhaTabela[3] = end.getNumero();
			novaLinhaTabela[4] = end.getLogradouro();
			
			model.addRow(novaLinhaTabela);
		}
	}

	protected void limparTabelaEnderecos() {
		//Cria as colunas
		tblEnderecos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		btnEditarEndereco.setEnabled(false);
		btnExcluirEndereco.setEnabled(false);
	}
}
