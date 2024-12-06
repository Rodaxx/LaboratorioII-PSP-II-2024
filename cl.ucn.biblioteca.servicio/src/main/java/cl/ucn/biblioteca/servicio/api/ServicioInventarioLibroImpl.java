package cl.ucn.biblioteca.servicio.api;

import java.util.*;

import cl.ucn.biblioteca.api.*;
import org.easymock.internal.matchers.Null;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServicioInventarioLibroImpl implements ServicioInventarioLibro{
	private BundleContext contexto;

	public ServicioInventarioLibroImpl(BundleContext contexto) {
		
		this.contexto = contexto;
	}

	public void ingresar(String isbn, String titulo, String autor, String categoria) throws ExcepcionLibroExistente, ExcepcionLibroInvalido {
		Inventario inventario = obtenerInventario();
		LibroMutable libro = inventario.crearLibro(isbn);
		libro.setTitulo(titulo);
		libro.setAutor(autor);
		libro.setCategoria(categoria);
		inventario.guardarLibro(libro);
    }

	public void remover(String isbn) throws ExcepcionLibroNoEncontrado{
		Inventario inventario = obtenerInventario();
		inventario.removerLibro(isbn);
    }

	@Override
	public void modificarCategoria(String isbn, String categoria) throws ExcepcionLibroNoEncontrado{
		Inventario inventario = obtenerInventario();
		LibroMutable libro = inventario.cargarLibroParaEdicion(isbn);
		libro.setCategoria(categoria);
	}

	@Override
	public Libro obtener(String isbn) throws ExcepcionLibroNoEncontrado {
		Inventario inventario = obtenerInventario();
		return inventario.cargarLibro(isbn);
	}

	@Override
	public List<Libro> obtenerPorTitulo(String titulo) {
		Inventario inventario = obtenerInventario();
		Map<Inventario.CriterioBusqueda, String> criterios = new HashMap<>();
		criterios.put(Inventario.CriterioBusqueda.TITULO_LIKE, titulo);
		Set<String> isbns = inventario.buscarLibros(criterios);
		List<Libro> libros = new ArrayList<Libro>();
		for (String isbn : isbns) {
            try {
                libros.add(inventario.cargarLibro(isbn));
            } catch (ExcepcionLibroNoEncontrado ignored) {}
        }
		return libros;
	}


	private Inventario obtenerInventario(){
		String nombre = Inventario.class.getName();
		ServiceReference ref = this.contexto.getServiceReference(nombre);
		return (Inventario) this.contexto.getService(ref);
	}
}
