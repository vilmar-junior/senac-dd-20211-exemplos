package controller.telefonia;

import exception.telefonia.ClienteInvalidoException;
import exception.telefonia.EnderecoNaoInformadoException;
import model.bo.telefonia.ClienteBO;
import model.entity.telefonia.Cliente;

public class ClienteController {

	private ClienteBO bo = new ClienteBO();
	
	public Cliente salvar(Cliente novoCliente) throws EnderecoNaoInformadoException, 
	ClienteInvalidoException {
		
		String validacaoCampos = "";
		if(novoCliente == null) {
			validacaoCampos = "Cliente não informado";
		}else {
			if(novoCliente.getNome() == null || novoCliente.getNome().length() < 2) {
				validacaoCampos += "Informe o nome (2 ou mais caracteres)\n";
			}
			
			if(novoCliente.getCpf() == null || novoCliente.getCpf().length() != 11) {
				validacaoCampos += "Informe o CPF (11 dígitos)\n";
			}
		}
		
		if(!validacaoCampos.isEmpty()) {
			throw new ClienteInvalidoException(validacaoCampos);
		}
		
		
		return bo.salvar(novoCliente);
	}
}
