package model.bo.revendedora;

import java.util.ArrayList;

import model.dao.revendora.RelatorioDAO;
import model.dto.revendedora.FaturamentoDTO;
import model.dto.revendedora.VendasDTO;

public class RelatorioBO {

	public ArrayList<FaturamentoDTO> consultarRelatorioFaturamentoBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		return relatorioDAO.consultarRelatorioFaturamentoDAO();
	}

	public ArrayList<VendasDTO> consultarRelatorioVendasBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		return relatorioDAO.consultarRelatorioVendasDAO();
	}

}
