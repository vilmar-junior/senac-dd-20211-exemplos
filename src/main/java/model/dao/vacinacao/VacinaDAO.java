package model.dao.vacinacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.Banco;
import model.entity.vacinacao.Pessoa;
import model.entity.vacinacao.Vacina;

/**
 * Classe responsavel pelas operacoes SQL de CRUD para a entidade Vacina, na
 * tabela VACINA.
 * 
 * @author vilmar
 *
 */
public class VacinaDAO {

	/**
	 * Cadastra um novo vacina no banco.
	 * 
	 * @param novaVacina o objeto com os dados a serem persistidos na tabela VACINA
	 * 
	 * @return novaVacina com a PK cadastrada no banco.
	 */
	public Vacina cadastrar(Vacina novaVacina) {
		String sql = " INSERT INTO vacina(nome, pais_origem, estagio_pesquisa, "
				+ " id_pessoa_responsavel, data_inicio_pesquisa, fase, quantidade_doses) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setString(1, novaVacina.getNome().toUpperCase());
			stmt.setString(2, novaVacina.getPaisOrigem());
			stmt.setString(3, novaVacina.getEstagioPesquisa());

			// TODO o pesquisador já deve ter sido salvo no banco antes
			stmt.setInt(4, novaVacina.getPesquisadorResponsavel().getId());
			stmt.setDate(5, java.sql.Date.valueOf(novaVacina.getDataInicioPesquisa()));
			stmt.setInt(6, novaVacina.getFase());
			stmt.setInt(7, novaVacina.getQuantidadeDoses());

			stmt.executeUpdate();

			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novaVacina.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar vacina: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (cadastrar vacina): \n" + e.getMessage());

			}
		}

		return novaVacina;
	}

	/**
	 * Atualiza um vacina no banco.
	 * 
	 * @param vacina o objeto com os dados a serem atualizados na tabela VACINA
	 * 
	 * @return se o registro foi ou nao atualizado na tabela.
	 */
	public boolean atualizar(Vacina vacina) {
		boolean atualizou = false;

		String sql = " UPDATE vacina SET nome = ?, pais_origem = ?, estagio_pesquisa = ?, "
				+ " id_pessoa_responsavel = ?, data_inicio_pesquisa = ?, fase = ?, quantidade_doses = ? "
				+ " WHERE ID = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setString(1, vacina.getNome());
			stmt.setString(2, vacina.getPaisOrigem());
			stmt.setString(3, vacina.getEstagioPesquisa());
			stmt.setInt(4, vacina.getPesquisadorResponsavel().getId());
			stmt.setDate(5, java.sql.Date.valueOf(vacina.getDataInicioPesquisa()));
			stmt.setInt(6, vacina.getFase());
			stmt.setInt(7, vacina.getQuantidadeDoses());
			stmt.setInt(8, vacina.getId());

			int quantidadeLinhasAfetadas = stmt.executeUpdate();

			atualizou = quantidadeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (atualizar vacina): \n" + e.getMessage());

			}
		}

		return atualizou;
	}

	/**
	 * Exclui um vacina no banco.
	 * 
	 * @param a chave primária do vacina a ser excluída.
	 * 
	 * @return se o registro foi ou nao excluído na tabela.
	 */
	public boolean excluir(Integer id) {
		boolean excluiu = false;

		String sql = " DELETE FROM VACINA " + " WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);
			excluiu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (excluir vacina): \n" + e.getMessage());

			}
		}

		return excluiu;
	}

	/**
	 * Busca um vacina no banco.
	 * 
	 * @param a chave primária do vacina a ser buscado.
	 * 
	 * @return o vacina buscada.
	 */
	public Vacina consultarPorId(Integer id) {
		Vacina vacinaConsultada = null;

		String sql = " SELECT * FROM VACINA WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);

			ResultSet resultadoConsulta = stmt.executeQuery();

			if (resultadoConsulta.next()) {
				vacinaConsultada = this.converterDoResultSet(resultadoConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacina por id: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar vacina por id): \n" + e.getMessage());

			}
		}

		return vacinaConsultada;
	}

	/**
	 * Busca todos os vacinas do banco.
	 * 
	 * @return os vacinas buscadas.
	 */
	public List<Vacina> consultarTodos() {
		List<Vacina> vacinas = new ArrayList<Vacina>();

		String sql = " SELECT * FROM VACINA ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultadoConsulta = stmt.executeQuery();

			while (resultadoConsulta.next()) {
				Vacina vacina = this.converterDoResultSet(resultadoConsulta);
				vacinas.add(vacina);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacinas: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar vacinas): \n" + e.getMessage());

			}
		}

		return vacinas;
	}

	private Vacina converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		Vacina vacina = new Vacina();
		vacina.setId(resultadoConsulta.getInt("id"));
		vacina.setNome(resultadoConsulta.getString("nome"));
		vacina.setPaisOrigem(resultadoConsulta.getString("pais_origem"));
		vacina.setEstagioPesquisa(resultadoConsulta.getString("estagio_pesquisa"));

		PessoaDAO pDAO = new PessoaDAO();
		Pessoa responsavel = pDAO.consultarPorId(resultadoConsulta.getInt("id_pessoa_responsavel"));
		vacina.setPesquisadorResponsavel(responsavel);

		vacina.setDataInicioPesquisa(resultadoConsulta.getDate("data_inicio_pesquisa").toLocalDate());
		vacina.setFase(resultadoConsulta.getInt("fase"));
		vacina.setQuantidadeDoses(resultadoConsulta.getInt("quantidade_doses"));

		return vacina;
	}
}
