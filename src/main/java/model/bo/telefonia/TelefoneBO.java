package model.bo.telefonia;

import exception.telefonia.NumeroIndisponivelException;
import model.dao.telefonia.TelefoneDAO;
import model.entity.telefonia.Telefone;

public class TelefoneBO {

	private TelefoneDAO dao = new TelefoneDAO();

	public Telefone cadastrar(Telefone novoTelefone) throws NumeroIndisponivelException {

		if (dao.numeroEstaDisponivel(novoTelefone)) {
			dao.cadastrar(novoTelefone);
		} else {
			throw new NumeroIndisponivelException("O número informado (" + novoTelefone.getNumero() + ")"
					+ " já foi utilizado no DDD " + novoTelefone.getDdd());
		}

		return novoTelefone;
	}

	public String excluir(Integer id) {
		String mensagem = "";

		if (dao.excluir(id)) {
			mensagem = "Telefone excluído com sucesso";
		} else {
			mensagem = "Erro ao excluir telefone";
		}

		return mensagem;
	}
}
