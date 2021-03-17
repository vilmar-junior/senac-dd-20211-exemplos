package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.Banco;
import model.entity.telefonia.Cliente;
import model.entity.telefonia.Endereco;
import model.entity.telefonia.Telefone;

public class ClienteDAO {

	// TODO e OS TELEFONES?
	/**
	 * Cadastra um novo cliente no banco.
	 * 
	 * @param novoCliente o objeto com os dados a serem persistidos na tabela
	 *                    CLIENTE
	 * 
	 *
	 * 
	 * @return novoCliente com a PK cadastrada no banco.
	 */
	public Cliente cadastrar(Cliente novoCliente) {
		String sql = " INSERT INTO CLIENTE(NOME, CPF, ID_ENDERECO, ATIVO) VALUES (?, ?, ?, ?) ";

		// Uso de try-with-resources
		try (Connection conexao = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);) {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getCpf());
			stmt.setInt(3, novoCliente.getEndereco().getId());
			stmt.setBoolean(4, true);

			stmt.executeUpdate();

			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novoCliente.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar cliente: \n" + e.getMessage());
		}

		return novoCliente;
	}

	/**
	 * Atualiza um cliente no banco.
	 * 
	 * @param cliente o objeto com os dados a serem atualizados na tabela CLIENTE
	 * 
	 * @return se o registro foi ou nao atualizado na tabela.
	 */
	public boolean atualizar(Cliente cliente) {
		boolean atualizou = false;

		String sql = " UPDATE cliente SET nome = ?, cpf = ?, id_endereco = ?, ativo = ? " + " WHERE ID = ?";

		try (Connection conexao = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);) {

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setInt(3, cliente.getEndereco().getId());
			stmt.setBoolean(4, cliente.isAtivo());
			stmt.setInt(5, cliente.getId());

			int quantidadeLinhasAfetadas = stmt.executeUpdate();

			atualizou = quantidadeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar cliente: \n" + e.getMessage());
		}

		return atualizou;
	}

	/**
	 * Exclui um cliente no banco.
	 * 
	 * @param a chave primária do cliente a ser excluído.
	 * 
	 * @return se o registro foi ou nao excluído na tabela.
	 */
	public boolean excluir(Integer id) {
		boolean excluiu = false;

		String sql = " DELETE FROM cliente " + " WHERE ID = ? ";

		try (Connection conexao = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);) {
			stmt.setInt(1, id);

			excluiu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente: \n" + e.getMessage());
		}

		return excluiu;
	}

	/**
	 * Busca um cliente no banco.
	 * 
	 * @param a chave primária do cliente a ser buscado.
	 * 
	 * @return o cliente buscado.
	 */
	public Cliente consultarPorId(Integer id) {
		Cliente clienteConsultado = null;

		String sql = " SELECT * FROM cliente WHERE id = ? ";

		try (Connection conexao = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);) {
			stmt.setInt(1, id);

			ResultSet resultadoConsulta = stmt.executeQuery();

			if (resultadoConsulta.next()) {
				clienteConsultado = this.converterDoResultSet(resultadoConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar cliente por id: \n" + e.getMessage());
		}

		return clienteConsultado;
	}

	/**
	 * Busca todos os clientes do banco.
	 * 
	 * @return os clientes buscados.
	 */
	public List<Cliente> consultarTodos() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		String sql = " SELECT * FROM cliente ";

		try (Connection conexao = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);) {
			ResultSet resultadoConsulta = stmt.executeQuery();

			while (resultadoConsulta.next()) {
				Cliente cliente = this.converterDoResultSet(resultadoConsulta);
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar clientes: \n" + e.getMessage());
		}

		return clientes;
	}

	private Cliente converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId(resultadoConsulta.getInt("id"));
		cliente.setCpf(resultadoConsulta.getString("cpf"));
		cliente.setNome(resultadoConsulta.getString("nome"));
		cliente.setAtivo(resultadoConsulta.getBoolean("ativo"));

		int idEndereco = resultadoConsulta.getInt("id_endereco");
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco enderecoDoCliente = enderecoDAO.consultarPorId(idEndereco);
		cliente.setEndereco(enderecoDoCliente);

		TelefoneDAO telefoneDAO = new TelefoneDAO();
		ArrayList<Telefone> telefones = (ArrayList<Telefone>) telefoneDAO.consultarPorIdCliente(cliente.getId());
		cliente.setTelefones(telefones);

		return cliente;
	}

}
