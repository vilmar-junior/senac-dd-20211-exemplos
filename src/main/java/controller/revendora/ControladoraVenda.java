package controller.revendora;

import java.util.ArrayList;

import model.bo.revendedora.VendaBO;
import model.entity.revendedora.VendaVO;

public class ControladoraVenda {
	
	public String cadastrarVendaController(VendaVO vendaVO) {
		VendaBO vendaBO = new VendaBO();
		return vendaBO.cadastrarVendaBO(vendaVO);
	}

	public String excluirVendaController(VendaVO vendaVO) {
		VendaBO vendaBO = new VendaBO();
		return vendaBO.excluirVendaBO(vendaVO);
	}

	public String atualizarVendaController(VendaVO vendaVO) {
		VendaBO vendaBO = new VendaBO();
		return vendaBO.atualizarVendaBO(vendaVO);
	}

	public ArrayList<VendaVO> consultarTodasVendasController() {
		VendaBO vendaBO = new VendaBO();
		return vendaBO.consultarTodasVendasBO();
	}

	public VendaVO consultarVendaController(VendaVO vendaVO) {
		VendaBO vendaBO = new VendaBO();
		return vendaBO.consultarVendaBO(vendaVO);
	}

}
