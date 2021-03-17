package model.dao.vacinacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.Banco;
import model.entity.vacinacao.AplicacaoVacina;
import model.entity.vacinacao.Pessoa;

/**
 * Classe responsavel pelas operacoes SQL de CRUD para a entidade Pessoa, na
 * tabela PESSOA.
 * 
 * @author vilmar
 *
 */
public class PessoaDAO {

	/**
	 * Cadastra um novo pessoa no banco.
	 * 
	 * @param novaPessoa o objeto com os dados a serem persistidos na tabela PESSOA
	 * 
	 * @return novaPessoa com a PK cadastrada no banco.
	 */
	public Pessoa cadastrar(Pessoa novaPessoa) {
		String sql = " INSERT INTO pessoa(nome, sexo, cpf, tipo) VALUES (?, ?, ?, ?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setString(1, novaPessoa.getNome());
			stmt.setString(2, novaPessoa.getSexo() + "");
			stmt.setString(3, novaPessoa.getCpf());
			stmt.setInt(4, novaPessoa.getTipo());

			stmt.executeUpdate();

			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novaPessoa.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar pessoa: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (cadastrar pessoa): \n" + e.getMessage());

			}
		}

		return novaPessoa;
	}

	/**
	 * Atualiza um pessoa no banco.
	 * 
	 * @param pessoa o objeto com os dados a serem atualizados na tabela PESSOA
	 * 
	 * @return se o registro foi ou nao atualizado na tabela.
	 */
	public boolean atualizar(Pessoa pessoa) {
		boolean atualizou = false;

		String sql = " UPDATE PESSOA SET NOME = ?, SEXO = ?, CPF = ?, TIPO = ? " + " WHERE ID = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			// Preenche os parametros do update
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getSexo() + "");
			stmt.setString(3, pessoa.getCpf());
			stmt.setInt(4, pessoa.getTipo());
			stmt.setInt(5, pessoa.getId());

			int quantidadeLinhasAfetadas = stmt.executeUpdate();

			atualizou = quantidadeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar pessoa: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (atualizar pessoa): \n" + e.getMessage());

			}
		}

		return atualizou;
	}

	/**
	 * Exclui um pessoa no banco.
	 * 
	 * @param a chave primária do pessoa a ser excluída.
	 * 
	 * @return se o registro foi ou nao excluído na tabela.
	 */
	public boolean excluir(Integer id) {
		boolean excluiu = false;

		String sql = " DELETE FROM PESSOA " + " WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);
			excluiu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pessoa: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (excluir pessoa): \n" + e.getMessage());

			}
		}

		return excluiu;
	}

	/**
	 * Busca um pessoa no banco.
	 * 
	 * @param a chave primária do pessoa a ser buscado.
	 * 
	 * @return o pessoa buscada.
	 */
	public Pessoa consultarPorId(Integer id) {
		Pessoa pessoaConsultada = null;

		String sql = " SELECT * FROM PESSOA WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);

			ResultSet resultadoConsulta = stmt.executeQuery();

			if (resultadoConsulta.next()) {
				pessoaConsultada = this.converterDoResultSet(resultadoConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por id: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar pessoa por id): \n" + e.getMessage());

			}
		}

		return pessoaConsultada;
	}

	/**
	 * Busca todos os pessoas do banco.
	 * 
	 * @return os pessoas buscadas.
	 */
	public List<Pessoa> consultarTodos() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		String sql = " SELECT * FROM PESSOA ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultadoConsulta = stmt.executeQuery();

			while (resultadoConsulta.next()) {
				Pessoa pessoa = this.converterDoResultSet(resultadoConsulta);
				pessoas.add(pessoa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoas: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar pessoas): \n" + e.getMessage());

			}
		}

		return pessoas;
	}

	private Pessoa converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(resultadoConsulta.getInt("id"));
		pessoa.setNome(resultadoConsulta.getString("nome"));
		pessoa.setSexo(resultadoConsulta.getString("sexo").charAt(0));
		pessoa.setCpf(resultadoConsulta.getString("cpf"));
		pessoa.setTipo(resultadoConsulta.getInt("tipo"));

		AplicacaoVacinaDAO aplicacaoDAO = new AplicacaoVacinaDAO();
		List<AplicacaoVacina> aplicacoes = aplicacaoDAO.consultarPorIdPessoa(pessoa.getId());
		pessoa.setVacinacoes(aplicacoes);

		return pessoa;
	}
}
