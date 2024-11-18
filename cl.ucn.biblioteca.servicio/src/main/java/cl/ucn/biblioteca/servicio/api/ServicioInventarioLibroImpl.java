package cl.ucn.biblioteca.servicio.api;

import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import cl.ucn.biblioteca.api.ExcepcionLibroNoEncontrado;
import cl.ucn.biblioteca.api.Inventario;
import cl.ucn.biblioteca.api.Libro;

public class ServicioInventarioLibroImpl implements ServicioInventarioLibro{


	private BundleContext contexto;

	public ServicioInventarioLibroImpl(BundleContext contexto) {
		
		this.contexto = contexto;
	}

	@Override
	public void ingresar(String isbn, String titulo, String autor, String categoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(String isbn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarCategoria(String isbn, String categoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Libro obtener(String isbn) throws ExcepcionLibroNoEncontrado {
		// TODO Auto-generated method stub
		Inventario inventario = buscarLibroEnInventario();
		return inventario.cargarLibro(isbn);
	}

	private Inventario buscarLibroEnInventario()  {
		
		String nombre = Inventario.class.getName();
		ServiceReference ref = this.contexto.getServiceReference(nombre);
		return (Inventario) this.contexto.getService(ref);
	}
}
