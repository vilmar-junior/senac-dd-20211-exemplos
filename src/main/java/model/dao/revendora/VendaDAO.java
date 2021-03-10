package model.dao.revendora;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.dao.Banco;
import model.entity.revendedora.VendaVO;

public class VendaDAO {
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public boolean verificarRegistroPorIdClienteIdVeiculo(int idCliente, int idVeiculo) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT idvenda FROM venda WHERE idcliente = " + idCliente 
				+ " and idveiculo = " + idVeiculo;
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica existência da Venda.");
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public int cadastrarVendaDAO(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "INSERT INTO venda (idcliente, idveiculo, valorvenda, datavenda) VALUES (" 
				+ vendaVO.getIdCliente() + ", " 
				+ vendaVO.getIdVeiculo() + ", " 
				+ vendaVO.getValorVenda() + ", '" 
				+ vendaVO.getDataVenda() + "')";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de realização de Venda.");
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public boolean verificarRegistroPorIdVenda(int idVenda) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT idvenda FROM venda WHERE idvenda = " + idVenda;
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()){
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

	public int excluirVendaDAO(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "DELETE FROM venda WHERE idvenda = " + vendaVO.getIdVenda();
		
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de exclusão da Venda.");
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public int atualizarVendaDAO(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "UPDATE venda SET idcliente = " + vendaVO.getIdCliente() 
					+ ", idveiculo = " + vendaVO.getIdVeiculo()  
					+ ", valorvenda = " + vendaVO.getValorVenda() 
					+ ", datavenda = '" + vendaVO.getDataVenda() + "'"
					+ " WHERE idvenda = " + vendaVO.getIdVenda();
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Atualização da Venda.");
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public ArrayList<VendaVO> consultarTodasVendasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<VendaVO> listaVendasVO = new ArrayList<VendaVO>();
		String query = "SELECT idvenda, idcliente, idveiculo, valorvenda, datavenda FROM venda";
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				VendaVO vendaVO = new VendaVO();
				vendaVO.setIdVenda(Integer.parseInt(resultado.getString(1)));
				vendaVO.setIdCliente(Integer.parseInt(resultado.getString(2)));
				vendaVO.setIdVeiculo(Integer.parseInt(resultado.getString(3)));
				vendaVO.setValorVenda(Double.parseDouble(resultado.getString(4)));
				vendaVO.setDataVenda(LocalDate.parse(resultado.getString(5), dataFormatter));
				listaVendasVO.add(vendaVO);
			}
		} catch (SQLException e){
			System.out.println("Erro ao executar a Query de Consulta das Vendas.");
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaVendasVO;
	}

	public VendaVO consultarVendaDAO(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		VendaVO venda = null;
		String query = "SELECT idvenda, idcliente, idveiculo, valorvenda, datavenda "
				+ "FROM venda "
				+ "WHERE idvenda = " + vendaVO.getIdVenda();
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				venda = new VendaVO();
				venda.setIdVenda(Integer.parseInt(resultado.getString(1)));
				venda.setIdCliente(Integer.parseInt(resultado.getString(2)));
				venda.setIdVeiculo(Integer.parseInt(resultado.getString(3)));
				venda.setValorVenda(Double.parseDouble(resultado.getString(4)));
				venda.setDataVenda(LocalDate.parse(resultado.getString(5), dataFormatter));
			}
		} catch (SQLException e){
			System.out.println("Erro ao executar a Query de Consulta da Venda.");
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return venda;
	}

}
