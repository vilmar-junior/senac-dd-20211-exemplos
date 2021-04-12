package model.bo.telefonia;

import model.dao.telefonia.EnderecoDAO;
import model.entity.telefonia.Endereco;

public class EnderecoBO {

	private EnderecoDAO dao = new EnderecoDAO();
	
	public String salvar(Endereco novo) {
		String mensagem = "Não foi possível cadastrar o novo endereço";
		
		novo = dao.cadastrar(novo);
		
		if(novo.getId() != null && novo.getId() > 0) {
			mensagem = "Endereço cadastrado com sucesso";
		}
		
		return mensagem;
	}

	public String excluir(int idEndereco) {
		String mensagem = "";
		
		//TODO validar se endereço PODE ser excluído
		
		if(dao.excluir(idEndereco)) {
			mensagem = "Endereço excluído com sucesso";
		}else {
			mensagem = "Erro ao excluir endereço";
		}
		
		return mensagem;
	}

	public String atualizar(Endereco endereco) {
		String mensagem = "Não foi possível atualizar o endereço";
		
		if(dao.atualizar(endereco)) {
			mensagem = "Endereço atualizado com sucesso";
		}
		
		return mensagem;
	}
}
