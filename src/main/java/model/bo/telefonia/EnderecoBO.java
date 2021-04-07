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
}
