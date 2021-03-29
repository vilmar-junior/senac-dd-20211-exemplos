package util;

import javax.swing.JOptionPane;

public class StringUtil {

	public static final int VALOR_INVALIDO = -1;

	public static int formatarStringParaInteiro(String valorInformado) {
		int opcaoSelecionada = VALOR_INVALIDO;
		try {
			opcaoSelecionada = Integer.parseInt(valorInformado);
		} catch (NumberFormatException excecao) {
			JOptionPane.showMessageDialog(null, "Informe um valor numérico");
		}

		return opcaoSelecionada;
	}

}
