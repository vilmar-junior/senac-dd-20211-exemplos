package model.dao.vacinacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.Banco;
import model.entity.vacinacao.AplicacaoVacina;
import model.entity.vacinacao.Vacina;

/**
 * Classe responsavel pelas operacoes SQL de CRUD para a entidade
 * AplicacaoVacina, na tabela APLICACAOVACINA.
 * 
 * @author vilmar
 *
 */
public class AplicacaoVacinaDAO {

	/**
	 * Cadastra uma nova aplicação da vacina no banco.
	 * 
	 * @param novaAplicacaoVacina o objeto com os dados a serem persistidos na
	 *                            tabela APLICACAOVACINA
	 * 
	 * @return novaAplicacaoVacina com a PK cadastrada no banco.
	 */
	public AplicacaoVacina cadastrar(AplicacaoVacina novaAplicacaoVacina) {
		String sql = " INSERT INTO aplicacaovacina(id_vacina, id_pessoa, data_aplicacao, nota) "
				+ " VALUES (?, ?, ?, ?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setInt(1, novaAplicacaoVacina.getVacina().getId());
			stmt.setInt(2, novaAplicacaoVacina.getIdPessoaVacinada());
			stmt.setDate(3, java.sql.Date.valueOf(novaAplicacaoVacina.getDataAplicacao()));
			stmt.setInt(4, novaAplicacaoVacina.getNota());

			stmt.executeUpdate();

			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novaAplicacaoVacina.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar aplicação da vacina: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (cadastrar aplicação da vacina): \n" + e.getMessage());

			}
		}

		return novaAplicacaoVacina;
	}

	/**
	 * Atualiza uma aplicação da vacina no banco.
	 * 
	 * @param aplicacao o objeto com os dados a serem atualizados na tabela
	 *                  APLICACAOVACINA
	 * 
	 * @return se o registro foi ou nao atualizado na tabela.
	 */
	public boolean atualizar(AplicacaoVacina aplicacao) {
		boolean atualizou = false;

		String sql = " UPDATE vacina SET id_vacina = ?, id_pessoa = ?, data_aplicacao = ?, nota = ? " + " WHERE ID = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, aplicacao.getVacina().getId());
			stmt.setInt(2, aplicacao.getIdPessoaVacinada());
			stmt.setDate(3, java.sql.Date.valueOf(aplicacao.getDataAplicacao()));
			stmt.setInt(4, aplicacao.getNota());
			stmt.setInt(5, aplicacao.getId());

			int quantidadeLinhasAfetadas = stmt.executeUpdate();

			atualizou = quantidadeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar aplicação da vacina: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (atualizar aplicação da vacina): \n" + e.getMessage());

			}
		}

		return atualizou;
	}

	/**
	 * Exclui um aplicação da vacina no banco.
	 * 
	 * @param a chave primária da aplicação da vacina a ser excluída.
	 * 
	 * @return se o registro foi ou nao excluído na tabela.
	 */
	public boolean excluir(Integer id) {
		boolean excluiu = false;

		String sql = " DELETE FROM APLICACAOVACINA WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);
			excluiu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao excluir aplicação da vacina: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (excluir aplicação da vacina): \n" + e.getMessage());

			}
		}

		return excluiu;
	}

	/**
	 * Busca uma aplicação da vacina no banco.
	 * 
	 * @param a chave primária da aplicação da vacina a ser buscada.
	 * 
	 * @return o vacina buscada.
	 */
	public AplicacaoVacina consultarPorId(Integer id) {
		AplicacaoVacina vacinaConsultada = null;

		String sql = " SELECT * FROM APLICACAOVACINA WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);

			ResultSet resultadoConsulta = stmt.executeQuery();

			if (resultadoConsulta.next()) {
				vacinaConsultada = this.converterDoResultSet(resultadoConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar aplicação da vacina por id: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out
						.println("Erro ao fechar conexão (consultar aplicação da vacina por id): \n" + e.getMessage());

			}
		}

		return vacinaConsultada;
	}

	/**
	 * Busca todas as aplicacoes de vacinas do banco.
	 * 
	 * @return as aplicacoes de vacinas buscadas.
	 */
	public List<AplicacaoVacina> consultarTodos() {
		List<AplicacaoVacina> vacinas = new ArrayList<AplicacaoVacina>();

		String sql = " SELECT * FROM APLICACAOVACINA ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultadoConsulta = stmt.executeQuery();

			while (resultadoConsulta.next()) {
				AplicacaoVacina vacina = this.converterDoResultSet(resultadoConsulta);
				vacinas.add(vacina);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar aplicacoes de vacinas: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar aplicacoes de vacinas): \n" + e.getMessage());

			}
		}

		return vacinas;
	}

	public List<AplicacaoVacina> consultarPorIdPessoa(Integer idPessoa) {
		List<AplicacaoVacina> aplicacoes = new ArrayList<AplicacaoVacina>();

		String sql = " SELECT * FROM APLICACAOVACINA WHERE ID_PESSOA = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, idPessoa);
			ResultSet resultadoConsulta = stmt.executeQuery();

			while (resultadoConsulta.next()) {
				AplicacaoVacina aplicacao = this.converterDoResultSet(resultadoConsulta);
				aplicacoes.add(aplicacao);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar aplicação da vacina por idPessoa: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println(
						"Erro ao fechar conexão (consultar aplicação da vacina por idPessoa): \n" + e.getMessage());

			}
		}

		return aplicacoes;
	}

	private AplicacaoVacina converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		AplicacaoVacina aplicacao = new AplicacaoVacina();
		aplicacao.setId(resultadoConsulta.getInt("id"));
		aplicacao.setDataAplicacao(resultadoConsulta.getDate("data_aplicacao").toLocalDate());
		aplicacao.setNota(resultadoConsulta.getInt("nota"));

		aplicacao.setIdPessoaVacinada(resultadoConsulta.getInt("id_pessoa"));

		VacinaDAO vDAO = new VacinaDAO();
		Vacina vacinaAplicada = vDAO.consultarPorId(resultadoConsulta.getInt("id_vacina"));
		aplicacao.setVacina(vacinaAplicada);

		return aplicacao;
	}
}
