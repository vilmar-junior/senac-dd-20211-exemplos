package controller.telefonia;

import model.bo.telefonia.EnderecoBO;
import model.dao.telefonia.EnderecoDAO;
import model.entity.telefonia.Endereco;

public class EnderecoController {
	private EnderecoBO bo = new EnderecoBO();
	private EnderecoDAO dao = new EnderecoDAO();
	
	public String salvar(Endereco novoEndereco) {
		return bo.salvar(novoEndereco);
	}
	
	public Endereco consultarPorCep(String cep) {
		return dao.consultarPorCepExterno(cep);
	}
}
