package model.bo.telefonia;

import exception.telefonia.EnderecoNaoInformadoException;
import model.dao.telefonia.ClienteDAO;
import model.dao.telefonia.EnderecoDAO;
import model.entity.telefonia.Cliente;

public class ClienteBO {

	private ClienteDAO dao = new ClienteDAO();

	public Cliente salvar(Cliente cliente) throws EnderecoNaoInformadoException {

		if (cliente.getEndereco() == null) {
			throw new EnderecoNaoInformadoException("Endereço é obrigatório, favor informar");
		}

		if (cliente.getEndereco().getId() == null) {
			// Salva primeiro o Endereco no banco
			EnderecoDAO eDAO = new EnderecoDAO();
			eDAO.cadastrar(cliente.getEndereco());
		}

		dao.cadastrar(cliente);

		return cliente;
	}

}
