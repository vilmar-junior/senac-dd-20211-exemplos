package controller.revendora;

import java.util.ArrayList;

import model.bo.revendedora.RelatorioBO;
import model.dto.revendedora.FaturamentoDTO;
import model.dto.revendedora.VendasDTO;

public class ControladoraRelatorio {

	public ArrayList<FaturamentoDTO> consultarRelatorioFaturamentoController() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.consultarRelatorioFaturamentoBO();
	}

	public ArrayList<VendasDTO> consultarRelatorioVendasController() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.consultarRelatorioVendasBO();
	}

}
