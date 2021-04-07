package model.dao.telefonia;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.entity.telefonia.Endereco;

/**
 * https://github.com/uliss3s/ceputil
 *
 */
public class ClienteViaCepWS {

    public static String buscarCep(String cep) {
        String json = null;

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));

            json = jsonSb.toString();

        } catch (IOException e) {
            System.out.println("Erro ao buscar CEP " + cep + ".\nCausa: " + e.getMessage());
        } 

        return json;
    }

    public static void main(String[] args) throws IOException {
        String json = buscarCep("8");
        //System.out.println(json);

        Map<String,String> mapa = new HashMap<>();

        if(json != null) {
        	Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
        	while (matcher.find()) {
        		String[] group = matcher.group().split(":");
        		mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
        	}
        	
        	System.out.println(mapa.get("bairro"));
        	
        } else {
        	System.out.println("CEP inválido");
        }
        EnderecoDAO dao = new EnderecoDAO();
        Endereco end = dao.consultarPorCepExterno("88495000");
        System.out.println(end.toString());
    }
}