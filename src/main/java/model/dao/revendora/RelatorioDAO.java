package model.dao.revendora;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.dao.Banco;
import model.dto.revendedora.FaturamentoDTO;
import model.dto.revendedora.VendasDTO;

public class RelatorioDAO {

	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public ArrayList<FaturamentoDTO> consultarRelatorioFaturamentoDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<FaturamentoDTO> listaFaturamentoDTO = new ArrayList<FaturamentoDTO>();
		String query = "SELECT c.nome, c.cpf, c.telefone, vv.modelo, vv.placa, v.datavenda, v.valorvenda "
				+ "FROM venda v, cliente c, veiculo vv " + "WHERE v.IDCLIENTE = c.IDCLIENTE "
				+ "AND v.IDVEICULO = vv.IDVEICULO";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				FaturamentoDTO faturamentoDTO = new FaturamentoDTO();
				faturamentoDTO.setNome(resultado.getString(1));
				faturamentoDTO.setCpf(resultado.getString(2));
				faturamentoDTO.setTelefone(resultado.getString(3));
				faturamentoDTO.setModelo(resultado.getString(4));
				faturamentoDTO.setPlaca(resultado.getString(5));
				faturamentoDTO.setDataVenda(LocalDate.parse(resultado.getString(6), dataFormatter));
				faturamentoDTO.setValorVenda(Double.parseDouble(resultado.getString(7)));
				listaFaturamentoDTO.add(faturamentoDTO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query do Relatório de Faturamento.");
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaFaturamentoDTO;
	}

	public ArrayList<VendasDTO> consultarRelatorioVendasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<VendasDTO> listaVendasDTO = new ArrayList<VendasDTO>();
		String query = "SELECT month(DATAVENDA) as MÊS, year(DATAVENDA) as ANO, sum(valorvenda) as TOTAL_VENDA "
				+ "FROM venda " + "GROUP BY year(DATAVENDA), month(DATAVENDA)";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				VendasDTO vendaDTO = new VendasDTO();
				vendaDTO.setMes(Integer.parseInt(resultado.getString(1)));
				vendaDTO.setAno(Integer.parseInt(resultado.getString(2)));
				vendaDTO.setTotalVendas(Double.parseDouble(resultado.getString(3)));
				listaVendasDTO.add(vendaDTO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query do Relatório de Vendas.");
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaVendasDTO;
	}

}
