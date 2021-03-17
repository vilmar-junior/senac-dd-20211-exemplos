package model.bo.revendedora;

import java.util.ArrayList;

import model.dao.revendora.VendaDAO;
import model.entity.revendedora.VendaVO;

public class VendaBO {

	public String cadastrarVendaBO(VendaVO vendaVO) {
		VendaDAO vendaDAO = new VendaDAO();
		if(vendaDAO.verificarRegistroPorIdClienteIdVeiculo(vendaVO.getIdCliente(), vendaVO.getIdVeiculo())){
			return "\nVenda já cadastrada no banco.";
		} else {
			int resultado = vendaDAO.cadastrarVendaDAO(vendaVO);
			if(resultado == 1){
				return "\nVenda cadastrada com Sucesso.";
			} else {
				return "\nNão foi possível cadastrar a Venda.";
			}
		}
	}

	public String excluirVendaBO(VendaVO vendaVO) {
		String retorno;
		VendaDAO vendaDAO = new VendaDAO();
		if(vendaDAO.verificarRegistroPorIdVenda(vendaVO.getIdVenda())) {
			int resultado = vendaDAO.excluirVendaDAO(vendaVO);
			if(resultado == 1) {
				retorno = "\nVenda excluída com Sucesso.";
			} else {
				retorno = "\nNão foi possível excluir a Venda.";
			}
		} else {
			retorno = "\nVenda não existe no banco.";
		}
		return retorno;
	}

	public String atualizarVendaBO(VendaVO vendaVO) {
		String retorno;
		VendaDAO vendaDAO = new VendaDAO();
		if(vendaDAO.verificarRegistroPorIdVenda(vendaVO.getIdVenda())) {
			int resultado = vendaDAO.atualizarVendaDAO(vendaVO);
			if(resultado == 1) {
				retorno = "\nVenda atualizada com Sucesso.";
			} else {
				retorno = "\nNão foi possível atualizar a Venda.";
			}
		} else {
			retorno = "\nVenda ainda não foi cadastrada no banco.";
		}
		return retorno;
	}

	public ArrayList<VendaVO> consultarTodasVendasBO() {
		VendaDAO vendaDAO = new VendaDAO();
		return vendaDAO.consultarTodasVendasDAO();
	}

	public VendaVO consultarVendaBO(VendaVO vendaVO) {
		VendaDAO vendaDAO = new VendaDAO();
		return vendaDAO.consultarVendaDAO(vendaVO);
	}

}
