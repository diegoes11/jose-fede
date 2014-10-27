package tpo.despacho.rest;

import java.util.Set;
import java.util.HashSet;
import javax.ws.rs.core.Application;

public class RecepcionDeArticulos extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public RecepcionDeArticulos(){
	     singletons.add(new RecepcionDeArticulosREST());
	}
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}
}
