package cl.ucn.biblioteca.servicios.activador;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {

		// TODO Auto-generated method stub
		Hashtable<String, Object> props = new Hashtable<String, Object>();
		props.put("osgi.command.scope", cl.ucn.biblioteca.servicios.tui.ServicioInventarioProxy.AMBITO);
		props.put("osgi.command.function",cl.ucn.biblioteca.servicios.tui.ServicioInventarioProxy.FUNCIONES);
		cl.ucn.biblioteca.servicios.tui.ServicioInventarioProxy servicioInventarioProxy = new cl.ucn.biblioteca.servicios.tui.ServicioInventarioProxy(context);
		context.registerService(cl.ucn.biblioteca.servicios.tui.ServicioInventarioProxy.class.getName(),servicioInventarioProxy, props);
	}

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
