package tpo.despacho.entidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import tpo.ia.vos.VODetalleOrdenDeDespachoCompleta;
import tpo.ia.vos.VOEnvioOrdenDeDespachoLista;
import tpo.ia.vos.VOOrdenDeDespachoCompleta;

@Entity
@Table(name="OrdenesDeDespacho")
public class OrdenDeDespacho {
	
	private IdOrdenDeDespacho id;
	private LogisticaYMonitoreo logisticaYMonitoreo;
	private String estado;
	private List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho;
	private Date fechaRecepcion;
	private Date fechaEntrega;
	
	public OrdenDeDespacho(){
		detallesOrdenDeDespacho = new ArrayList<DetalleOrdenDeDespacho>();
	}
	
	// Métodos	
	public void verificarOrdenCompleta(){
		boolean ordenCompleta = true;
		for(DetalleOrdenDeDespacho detalle : detallesOrdenDeDespacho){
			if(detalle.getEstado().equals("incompleto"))
				ordenCompleta = false;
		}
		if(ordenCompleta == true){
			this.setFechaEntrega(Calendar.getInstance().getTime());
			setEstado("despachada");
			// ENVIO DE INFORME DE CAMBIO DE ESTADO A LOGISTICA Y MONITOREO POR REST
			//informarOrdenDeDespachoListaSync(logisticaYMonitoreo.getUrlRecepcionEstadoOrdenDeDesapcho(), new VOEnvioOrdenDeDespachoLista(id.getIdOrdenDeDespacho()));
			//ENVIO DE INFORME DE CAMBIO DE ESTADO A PORTAL WEB POR WEB SERVICE
			// --
		}
	}
	
	public boolean estaCompleta(){
		if(estado.equals("despachada"))
			return true;
		else
			return false;
	}
	
	public String obtenerInformeCompletitud(){
		return "Se ha completado la orden de despacho " + id.getIdOrdenDeDespacho() +
				" perteneciente al Portal Web " + id.getPortalWeb();
	}
	
	@Transient
	public VOOrdenDeDespachoCompleta getOrdenDeDespachoVO(){
		VOOrdenDeDespachoCompleta oddvo = new VOOrdenDeDespachoCompleta();
		oddvo.setIdOrdenDeDespacho(id.getIdOrdenDeDespacho());
		oddvo.setNombreLogisticaYMonitoreo(logisticaYMonitoreo.getNombre());
		oddvo.setNombrePortalWeb(id.getPortalWeb().getNombre());
		oddvo.setEstado(estado);
		oddvo.setFechaRecepcion(fechaRecepcion);
		oddvo.setFechaEntrega(fechaEntrega);
		List<VODetalleOrdenDeDespachoCompleta> detallesOrdenDeDespachoVO = new ArrayList<VODetalleOrdenDeDespachoCompleta>();
		for(DetalleOrdenDeDespacho dodd : detallesOrdenDeDespacho){
			detallesOrdenDeDespachoVO.add(dodd.getDetalleOrdenDeDespachoVO());
		}
		oddvo.setDetallesOrdenDeDespachoVO(detallesOrdenDeDespachoVO);
		return oddvo;
	}
	
	public void agregarDetalle(DetalleOrdenDeDespacho dodd){
		if(detallesOrdenDeDespacho == null){
			detallesOrdenDeDespacho = new ArrayList<DetalleOrdenDeDespacho>();
		}
		detallesOrdenDeDespacho.add(dodd);
	}
	
	@SuppressWarnings("unused")
	private boolean informarOrdenDeDespachoListaSync (String urlString, VOEnvioOrdenDeDespachoLista voEnvioOrdenDeDespachoLista) {
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
	
	public List<Deposito> obtenerDepositosOrden(){
		List<Deposito> depositos = new ArrayList<Deposito>();
		for(DetalleOrdenDeDespacho dodd : detallesOrdenDeDespacho){
			SolicitudDeArticulo sda = dodd.getSolicitudDeArticulo();
			Deposito deposito = sda.getDeposito();
			boolean noExiste = true;
			// Verifico que no exista
			for(Deposito d : depositos){
				if(d.getNombre().equals(deposito.getNombre()))
					noExiste = false;
			}
			if(noExiste == true){
				depositos.add(deposito);
			}
		}
		return depositos;
	}

	// Getters y Setters
	
	@Column(name="Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@EmbeddedId
	public IdOrdenDeDespacho getId() {
		return id;
	}

	public void setId(IdOrdenDeDespacho id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "ordenDeDespacho", cascade=CascadeType.ALL)
	public List<DetalleOrdenDeDespacho> getDetallesOrdenDeDespacho() {
		return detallesOrdenDeDespacho;
	}

	public void setDetallesOrdenDeDespacho(
			List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho) {
		this.detallesOrdenDeDespacho = detallesOrdenDeDespacho;
	}

	@ManyToOne
	@JoinColumn (name = "NombreLogisticaYMonitoreo")
	public LogisticaYMonitoreo getLogisticaYMonitoreo() {
		return logisticaYMonitoreo;
	}

	public void setLogisticaYMonitoreo(LogisticaYMonitoreo logisticaYMonitoreo) {
		this.logisticaYMonitoreo = logisticaYMonitoreo;
	}

	@Column (name = "FechaRecepcion", columnDefinition = "datetime", nullable = true)
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	@Column (name = "FechaEntrega", columnDefinition = "datetime", nullable = true)
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
}
