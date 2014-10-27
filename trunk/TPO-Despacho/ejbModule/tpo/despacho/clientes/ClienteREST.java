package tpo.despacho.clientes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClienteREST {
	public static ClienteREST instancia;
	
	private ClienteREST(){
		
	}
	
	public static ClienteREST getInstancia(){
		if (instancia == null){
			instancia = new ClienteREST();
		}
		return instancia;
	}
	
	public String obtenerRespuestaREST(String urlString){
		try {
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			if (con.getResponseCode() != 200){
				throw new IOException(con.getResponseMessage());
			}
			// Si devolvió 200 (OK), devuelvo la respuesta en un String.
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = rd.readLine()) != null){
				sb.append(line);
			}
			rd.close();
			
			con.disconnect();
			return sb.toString();
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
		
	}
}
