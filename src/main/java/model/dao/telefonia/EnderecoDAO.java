package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.dao.Banco;
import model.entity.telefonia.Endereco;

/**
 * Classe responsavel pelas operacoes SQL de CRUD para a entidade Endereco, na
 * tabela ENDERECO.
 * 
 * @author vilmar
 *
 */
public class EnderecoDAO {

	/**
	 * Cadastra um novo endereco no banco.
	 * 
	 * @param novoEndereco o objeto com os dados a serem persistidos na tabela
	 *                     ENDERECO
	 * 
	 * @return novoEndereco com a PK cadastrada no banco.
	 */
	public Endereco cadastrar(Endereco novoEndereco) {
		String sql = " INSERT INTO ENDERECO(CEP, LOGRADOURO, NUMERO, UF, CIDADE) VALUES (?, ?, ?, ?, ?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			// Preenche os parametros do insert
			stmt.setString(1, novoEndereco.getCep());
			stmt.setString(2, novoEndereco.getLogradouro());
			stmt.setString(3, novoEndereco.getNumero());
			stmt.setString(4, novoEndereco.getUf());
			stmt.setString(5, novoEndereco.getCidade());

			stmt.executeUpdate();

			// Obtem a PK gerada
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novoEndereco.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar endereço: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (cadastrar endereço): \n" + e.getMessage());

			}
		}

		return novoEndereco;
	}

	/**
	 * Atualiza um endereco no banco.
	 * 
	 * @param endereco o objeto com os dados a serem atualizados na tabela ENDERECO
	 * 
	 * @return se o registro foi ou nao atualizado na tabela.
	 */
	public boolean atualizar(Endereco endereco) {
		boolean atualizou = false;

		String sql = " UPDATE ENDERECO SET CEP = ?, LOGRADOURO = ?, NUMERO = ?, UF = ?, CIDADE = ? " + " WHERE ID = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			// Preenche os parametros do update
			stmt.setString(1, endereco.getCep());
			stmt.setString(2, endereco.getLogradouro());
			stmt.setString(3, endereco.getNumero());
			stmt.setString(4, endereco.getUf());
			stmt.setString(5, endereco.getCidade());
			stmt.setInt(6, endereco.getId());

			int quantidadeLinhasAfetadas = stmt.executeUpdate();

			atualizou = quantidadeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar endereço: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (atualizar endereço): \n" + e.getMessage());

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

		String sql = " DELETE FROM ENDERECO " + " WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);

			// stmt.executeUpdate() retorna a quantidade de
			// linhas afetadas com o SQL executado
			excluiu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao excluir endereço: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (excluir endereço): \n" + e.getMessage());

			}
		}

		return excluiu;
	}
	
	public Endereco consultarPorCepExterno(String cep) {
		Endereco enderecoConsultado = null;
		
		ClienteViaCepWS viaCep = new ClienteViaCepWS();
		String json = viaCep.buscarCep(cep);
		
		if(json != null) {
			Map<String,String> mapa = new HashMap<>();
			
			Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
			while (matcher.find()) {
				String[] group = matcher.group().split(":");
				mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
			}
			enderecoConsultado = new Endereco();
			enderecoConsultado.setCep(cep);
			enderecoConsultado.setCidade(mapa.get("localidade"));
			enderecoConsultado.setLogradouro(mapa.get("logradouro"));
			enderecoConsultado.setUf(mapa.get("uf"));
		}
		
		return enderecoConsultado;
	}
	

	/**
	 * Busca um endereco no banco.
	 * 
	 * @param a chave primária do endereço a ser buscado.
	 * 
	 * @return o endereço buscado.
	 */
	public Endereco consultarPorId(Integer id) {
		Endereco enderecoConsultado = null;

		String sql = " SELECT * FROM ENDERECO WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);

			ResultSet resultadoConsulta = stmt.executeQuery();

			if (resultadoConsulta.next()) {
				enderecoConsultado = this.converterDoResultSet(resultadoConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar endereço por id: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar endereço por id): \n" + e.getMessage());

			}
		}

		return enderecoConsultado;
	}

	/**
	 * Busca todos os enderecos do banco.
	 * 
	 * @return os endereços buscados.
	 */
	public List<Endereco> consultarTodos() {
		List<Endereco> enderecos = new ArrayList<Endereco>();

		String sql = " SELECT * FROM ENDERECO ";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultadoConsulta = stmt.executeQuery();

			while (resultadoConsulta.next()) {
				Endereco endereco = this.converterDoResultSet(resultadoConsulta);
				enderecos.add(endereco);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar endereços: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar endereços): \n" + e.getMessage());

			}
		}

		return enderecos;
	}

	private Endereco converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		Endereco endereco = new Endereco();
		endereco.setId(resultadoConsulta.getInt("id"));
		endereco.setCep(resultadoConsulta.getString("cep"));
		endereco.setLogradouro(resultadoConsulta.getString("logradouro"));
		endereco.setNumero(resultadoConsulta.getString("numero"));
		endereco.setUf(resultadoConsulta.getString("uf"));
		endereco.setCidade(resultadoConsulta.getString("cidade"));

		return endereco;
	}
}
