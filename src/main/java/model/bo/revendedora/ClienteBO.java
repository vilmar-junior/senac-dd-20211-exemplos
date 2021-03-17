package model.bo.revendedora;

import java.util.ArrayList;

import model.dao.revendora.ClienteDAO;
import model.entity.revendedora.ClienteVO;

public class ClienteBO {

	public String cadastarClienteBO(ClienteVO clienteVO) {
		String retorno;
		ClienteDAO clienteDAO = new ClienteDAO();
		if (clienteDAO.verificarRegistroPorCpf(clienteVO.getCpf())) {
			retorno = "\nCliente já cadastrado no banco.";
		} else {
			int resultado = clienteDAO.cadastrarClienteDAO(clienteVO);
			if (resultado == 1) {
				retorno = "\nCliente cadastrado com Sucesso.";
			} else {
				retorno = "\nNão foi possível cadastrar o Cliente.";
			}
		}
		return retorno;
	}

	public String excluirClienteBO(ClienteVO clienteVO) {
		String retorno;
		ClienteDAO clienteDAO = new ClienteDAO();
		if (clienteDAO.verificarRegistroPorIdCliente(clienteVO.getIdCliente())) {
			int resultado = clienteDAO.excluirClienteDAO(clienteVO);
			if (resultado == 1) {
				retorno = "\nCliente excluído com Sucesso.";
			} else {
				retorno = "\nNão foi possível excluir o Cliente.";
			}
		} else {
			retorno = "\nCliente não existe no banco.";
		}
		return retorno;
	}

	public String atualizarClienteBO(ClienteVO clienteVO) {
		String retorno;
		ClienteDAO clienteDAO = new ClienteDAO();
		if (clienteDAO.verificarRegistroPorIdCliente(clienteVO.getIdCliente())) {
			int resultado = clienteDAO.atualizarClienteDAO(clienteVO);
			if (resultado == 1) {
				retorno = "\nCliente atualizado com Sucesso.";
			} else {
				retorno = "\nNão foi possível atualizar o Cliente.";
			}
		} else {
			retorno = "\nCliente ainda não foi cadastrado no banco.";
		}
		return retorno;
	}

	public ArrayList<ClienteVO> consultarTodosClientesBO() {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.consultarTodosClientesDAO();
	}

	public ClienteVO consultarClienteBO(ClienteVO clienteVO) {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.consultarClienteDAO(clienteVO);
	}

}
