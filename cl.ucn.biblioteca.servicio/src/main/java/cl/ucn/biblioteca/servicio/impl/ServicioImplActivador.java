package cl.ucn.biblioteca.servicio.impl;

import cl.ucn.biblioteca.servicio.api.ServicioInventarioLibroImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import cl.ucn.biblioteca.servicio.api.ServicioInventarioLibro;

public class ServicioImplActivador implements BundleActivator {

	ServiceRegistration reg = null;
	
	@Override
	public void start(BundleContext context) throws Exception {
		ServicioInventarioLibroImpl servicio = new ServicioInventarioLibroImpl(context);
		reg = context.registerService(ServicioInventarioLibro.class, servicio, null);
		System.out.println("Servicio de Biblioteca registrado");
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if (reg !=null) {
			reg.unregister();
			System.out.println("osgi-example-service: Service unregistered");
		}
	}

}
