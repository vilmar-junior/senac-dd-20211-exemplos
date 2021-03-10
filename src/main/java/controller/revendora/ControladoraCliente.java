package controller.revendora;

import java.util.ArrayList;

import model.bo.revendedora.ClienteBO;
import model.entity.revendedora.ClienteVO;

public class ControladoraCliente {

	public String cadastrarClienteController(ClienteVO clienteVO) {
		ClienteBO clienteBO = new ClienteBO();
		return clienteBO.cadastarClienteBO(clienteVO);
	}

	public String excluirClienteController(ClienteVO clienteVO) {
		ClienteBO clienteBO = new ClienteBO();
		return clienteBO.excluirClienteBO(clienteVO);
	}

	public String atualizarClienteController(ClienteVO clienteVO) {
		ClienteBO clienteBO = new ClienteBO();
		return clienteBO.atualizarClienteBO(clienteVO);
	}

	public ArrayList<ClienteVO> consultarTodosClientesController() {
		ClienteBO clienteBO = new ClienteBO();
		return clienteBO.consultarTodosClientesBO();
	}

	public ClienteVO consultarClienteController(ClienteVO clienteVO) {
		ClienteBO clienteBO = new ClienteBO();
		return clienteBO.consultarClienteBO(clienteVO);
	}

}
