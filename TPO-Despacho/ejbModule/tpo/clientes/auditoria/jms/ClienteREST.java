package tpo.clientes.auditoria.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import tpo.ia.vos.VOEnvioOrdenDeDespachoLista;

public class ClienteREST {
	public static void main(String[] args) {
		//String urlString = "http://172.16.164.32:8080/TPO_Logistica_Y_MonitoreoWeb/CambiarEstadoOrdenDeDespacho";
		String urlString = "http://172.16.164.47:8080/DespachadorRest/cambioDeEstadoOD";
		VOEnvioOrdenDeDespachoLista voEnvioOrdenDeDespachoLista = new VOEnvioOrdenDeDespachoLista(1);
		informarOrdenDeDespachoListaSyncRest(urlString, voEnvioOrdenDeDespachoLista);
	}
	
	private static boolean informarOrdenDeDespachoListaSyncRest (String urlString, VOEnvioOrdenDeDespachoLista voEnvioOrdenDeDespachoLista) {
    	try {
    		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    		String json = ow.writeValueAsString(voEnvioOrdenDeDespachoLista);
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setAllowUserInteraction(false);
			con.setRequestProperty("Content-Type", "application/json; charset=utf8");
			OutputStream os = con.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();
			
			if (con.getResponseCode() != 200){
				throw new IOException(con.getResponseMessage());
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = rd.readLine()) != null){
				sb.append(line);
			}
			rd.close();
			
			con.disconnect();
			String respuesta = sb.toString();
			if (respuesta.equals("OK")) {
				return true;
			}
			else {
				return false;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
}
