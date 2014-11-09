package tpo.clientes.auditoria.ws.clientsample;

import tpo.clientes.auditoria.ws.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        WSAgregarInformeAuditoriaBeanService service1 = new WSAgregarInformeAuditoriaBeanService();
	        System.out.println("Create Web Service...");
	        WSAgregarInformeAuditoriaBean port1 = service1.getWSAgregarInformeAuditoriaBeanPort();
	        System.out.println("Call Web Service Operation...");
	        VoInformeAuditoria informe = new VoInformeAuditoria();
	        informe.setDescripcion("Prueba DESPACHO1");
	        informe.setFechaYHora(1);
	        System.out.println("Server said: " + port1.agregarInformeAuditoria(informe));
	        //Please input the parameters instead of 'null' for the upper method!
	
//	        System.out.println("Create Web Service...");
//	        WSAgregarInformeAuditoriaBean port2 = service1.getWSAgregarInformeAuditoriaBeanPort();
//	        System.out.println("Call Web Service Operation...");
//	        System.out.println("Server said: " + port2.agregarInformeAuditoria(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
