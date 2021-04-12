package controller.telefonia;

import java.util.List;

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
	
	public List<Endereco> consultarTodos() {
		return dao.consultarTodos();
	}
	
	public String excluir(int idEndereco) {
		return bo.excluir(idEndereco);
	}

	public String atualizar(Endereco novoEndereco) {
		return bo.atualizar(novoEndereco);
	}
}
