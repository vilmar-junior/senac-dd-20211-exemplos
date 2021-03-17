package model.dao.revendora;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.entity.revendedora.ClienteVO;

public class ClienteDAO {

	public boolean verificarRegistroPorCpf(String cpf) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT idcliente FROM cliente WHERE cpf = '" + cpf + "'";

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica existência de Cliente por CPF.");
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public int cadastrarClienteDAO(ClienteVO clienteVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "INSERT INTO cliente (nome, cpf, telefone) VALUES ('" + clienteVO.getNome() + "', '"
				+ clienteVO.getCpf() + "', '" + clienteVO.getTelefone() + "')";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Cadastro de Cliente.");
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public boolean verificarRegistroPorIdCliente(int idCliente) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT idcliente FROM cliente WHERE idcliente = " + idCliente;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica existência de Registro por Id.");
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public int excluirClienteDAO(ClienteVO clienteVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "DELETE FROM cliente WHERE idcliente = " + clienteVO.getIdCliente();

		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de exclusão de Cliente.");
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public int atualizarClienteDAO(ClienteVO clienteVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "UPDATE cliente SET nome = '" + clienteVO.getNome() + "'" + ", cpf = '" + clienteVO.getCpf()
				+ "'" + ", telefone = '" + clienteVO.getTelefone() + "'" + " WHERE idcliente = "
				+ clienteVO.getIdCliente();
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Atualização de Cliente.");
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public ArrayList<ClienteVO> consultarTodosClientesDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<ClienteVO> listaClientesVO = new ArrayList<ClienteVO>();
		String query = "SELECT idcliente, nome, cpf, telefone FROM cliente";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				ClienteVO clienteVO = new ClienteVO();
				clienteVO.setIdCliente(Integer.parseInt(resultado.getString(1)));
				clienteVO.setNome(resultado.getString(2));
				clienteVO.setCpf(resultado.getString(3));
				clienteVO.setTelefone(resultado.getString(4));
				listaClientesVO.add(clienteVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Consulta de Clientes.");
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaClientesVO;
	}

	public ClienteVO consultarClienteDAO(ClienteVO clienteVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ClienteVO cliente = null;
		String query = "SELECT idcliente, nome, cpf, telefone " + "FROM cliente " + "WHERE idcliente = "
				+ clienteVO.getIdCliente();
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				cliente = new ClienteVO();
				cliente.setIdCliente(Integer.parseInt(resultado.getString(1)));
				cliente.setNome(resultado.getString(2));
				cliente.setCpf(resultado.getString(3));
				cliente.setTelefone(resultado.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Consulta de um Cliente.");
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return cliente;
	}

}
