package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.Banco;
import model.entity.telefonia.Telefone;

public class TelefoneDAO {

	public static final int CODIGO_TELEFONE_SEM_CLIENTE = 0;

	/**
	 * Cadastra um novo telefone no banco.
	 * 
	 * @param novoTelefone o objeto com os dados a serem persistidos na tabela
	 *                     TELEFONE
	 * 
	 * @return novoTelefone com a PK cadastrada no banco.
	 */
	public Telefone cadastrar(Telefone novoTelefone) {
		String sql = " INSERT INTO telefone(codigo_internacional, ddd, numero, id_cliente, movel, ativo) VALUES (?, ?, ?, ?, ?, ?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setString(1, novoTelefone.getCodigoInternacional());
			stmt.setString(2, novoTelefone.getDdd());
			stmt.setString(3, novoTelefone.getNumero());

			if (novoTelefone.getIdCliente() == null) {
				stmt.setInt(4, CODIGO_TELEFONE_SEM_CLIENTE);
				stmt.setBoolean(6, false);
			} else {
				stmt.setInt(4, novoTelefone.getIdCliente());
				stmt.setBoolean(6, true);
			}

			stmt.setBoolean(5, novoTelefone.isMovel());

			stmt.executeUpdate();

			// Obtem a PK gerada
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novoTelefone.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar telefone: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (cadastrar telefone): \n" + e.getMessage());

			}
		}

		return novoTelefone;
	}

	/**
	 * Atualiza um telefone no banco.
	 * 
	 * @param telefon o objeto com os dados a serem atualizados na tabela TELEFONE
	 * 
	 * @return se o registro foi ou nao atualizado na tabela.
	 */
	public boolean atualizar(Telefone telefone) {
		boolean atualizou = false;

		String sql = " UPDATE telefone SET codigo_internacional = ?, ddd = ?, numero = ?, id_cliente = ?, movel = ?, ativo = ? "
				+ " WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setString(1, telefone.getCodigoInternacional());
			stmt.setString(2, telefone.getDdd());
			stmt.setString(3, telefone.getNumero());

			if (telefone.getIdCliente() == null) {
				stmt.setInt(4, CODIGO_TELEFONE_SEM_CLIENTE);
				stmt.setBoolean(6, false);
			} else {
				stmt.setInt(4, telefone.getIdCliente());
				stmt.setBoolean(6, true);
			}

			stmt.setBoolean(5, telefone.isMovel());
			stmt.setInt(7, telefone.getId());

			int quantidadeLinhasAfetadas = stmt.executeUpdate();

			atualizou = quantidadeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar telefone: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (atualizar telefone): \n" + e.getMessage());

			}
		}

		return atualizou;
	}

	/**
	 * Exclui um endereco no banco.
	 * 
	 * @param a chave primária do endereço a ser excluído.
	 * 
	 * @return se o registro foi ou nao excluído na tabela.
	 */
	public boolean excluir(Integer id) {
		boolean excluiu = false;

		String sql = " DELETE FROM telefone " + " WHERE id = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);

			excluiu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao excluir telefone: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (excluir telefone): \n" + e.getMessage());

			}
		}

		return excluiu;
	}

	/**
	 * Busca um telefone no banco.
	 * 
	 * @param a chave primária do telefone a ser buscado.
	 * 
	 * @return o telefone buscado.
	 */
	public Telefone consultarPorId(Integer id) {
		Telefone enderecoConsultado = null;

		String sql = " SELECT * FROM telefone WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);

			ResultSet resultadoConsulta = stmt.executeQuery();

			if (resultadoConsulta.next()) {
				enderecoConsultado = this.converterDoResultSet(resultadoConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar telefone por id: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar telefone por id): \n" + e.getMessage());

			}
		}

		return enderecoConsultado;
	}

	/**
	 * Busca todos os telefones do banco.
	 * 
	 * @return os telefones buscados.
	 */
	public List<Telefone> consultarTodos() {
		List<Telefone> telefones = new ArrayList<Telefone>();

		String sql = " SELECT * FROM telefone ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultadoConsulta = stmt.executeQuery();

			while (resultadoConsulta.next()) {
				Telefone telefone = this.converterDoResultSet(resultadoConsulta);
				telefones.add(telefone);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar telefone: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar telefone): \n" + e.getMessage());

			}
		}

		return telefones;
	}

	private Telefone converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		Telefone telefone = new Telefone();
		telefone.setId(resultadoConsulta.getInt("id"));
		telefone.setCodigoInternacional(resultadoConsulta.getString("codigo_internacional"));
		telefone.setDdd(resultadoConsulta.getString("ddd"));
		telefone.setNumero(resultadoConsulta.getString("numero"));
		telefone.setMovel(resultadoConsulta.getBoolean("movel"));
		telefone.setAtivo(resultadoConsulta.getBoolean("ativo"));
		telefone.setIdCliente(resultadoConsulta.getInt("id_cliente"));

		return telefone;
	}

	public boolean numeroEstaDisponivel(Telefone novoTelefone) {
		String sql = " select COUNT(id) from telefone " + " WHERE ddd = ? " + " AND numero = ? ";
		boolean numeroDisponivel = false;

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setString(1, novoTelefone.getDdd());
			stmt.setString(2, novoTelefone.getNumero());

			ResultSet resultadoConsulta = stmt.executeQuery();

			if (resultadoConsulta.next()) {
				numeroDisponivel = resultadoConsulta.getInt(1) == 0;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar disponibilidade de número e DDD: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println(
						"Erro ao fechar conexão (consultar disponibilidade de número e DDD): \n" + e.getMessage());

			}
		}

		return numeroDisponivel;

	}

	public List<Telefone> consultarPorIdCliente(Integer id) {
		List<Telefone> telefones = new ArrayList<Telefone>();

		String sql = " SELECT * FROM telefone WHERE id_cliente = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);
			ResultSet resultadoConsulta = stmt.executeQuery();

			while (resultadoConsulta.next()) {
				Telefone telefone = this.converterDoResultSet(resultadoConsulta);
				telefones.add(telefone);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar telefones de um cliente: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar telefones de um cliente): \n" + e.getMessage());

			}
		}

		return telefones;
	}
}
