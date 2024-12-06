package cl.ucn.biblioteca.servicios.tui;

import cl.ucn.biblioteca.api.ExcepcionLibroNoEncontrado;
import cl.ucn.biblioteca.api.Libro;
import cl.ucn.biblioteca.servicio.api.ServicioInventarioLibro;

import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import cl.ucn.biblioteca.api.ExcepcionLibroExistente;
import cl.ucn.biblioteca.api.ExcepcionLibroInvalido;

import java.util.List;


public class ServicioInventarioProxy{

	public static final String AMBITO = "libro";
	public static final String[] FUNCIONES = new String[] {"obtener", "ingresar", "remover", "modificarCategoria", "obtenerPorTitulo"};
	private BundleContext contexto;

	public ServicioInventarioProxy(BundleContext contexto)
	{
		this.contexto = contexto;
	}

	@Descriptor("Ingresar libro")
	public void ingresar(@Descriptor("ISBN") String isbn,
						 @Descriptor("Titulo") String titulo,
						 @Descriptor("Autor") String autor,
						 @Descriptor("Categoria") String categoria) throws ExcepcionLibroExistente, ExcepcionLibroInvalido
	{
		ServicioInventarioLibro servicio = obtenerServicio();
		servicio.ingresar(isbn, titulo, autor, categoria);
    }

	@Descriptor("Remover libro")
	public void remover(@Descriptor("ISBN") String isbn) {
		ServicioInventarioLibro servicio = obtenerServicio();
		try {
			servicio.remover(isbn);
		} catch (ExcepcionLibroNoEncontrado e) {
			System.out.println(e.getMessage());
		}
	}

	@Descriptor("Modificar categoria de un libro")
	public void modificarCategoria(@Descriptor("ISBN") String isbn, @Descriptor("Categoria") String categoria) {
		ServicioInventarioLibro servicio = obtenerServicio();
		try {
			servicio = obtenerServicio();
			servicio.modificarCategoria(isbn, categoria);
		} catch (ExcepcionLibroNoEncontrado e) {
            System.out.println(e.getMessage());
        }
	}

	@Descriptor("Obtener libro")
	public Libro obtener(@Descriptor("ISBN") String isbn) throws ExcepcionLibroNoEncontrado {
		ServicioInventarioLibro servicioInventarioLibro = obtenerServicio();
        return servicioInventarioLibro.obtener(isbn);
	}

	@Descriptor("Obtener libro por titulo")
	public void obtenerPorTitulo(@Descriptor("Titulo") String titulo) {
		ServicioInventarioLibro servicioInventarioLibro = obtenerServicio();
		List<Libro> libros = servicioInventarioLibro.obtenerPorTitulo(titulo);
		if (libros.isEmpty()) {
			System.out.println("No se ha encontrado ningun libro con el titulo");
		}
		else {
			System.out.println("Resultados de busqueda:");
			for (Libro libro : libros) {
				System.out.println("ISBN: " + libro.getIsbn());
				System.out.println(libro);
				System.out.println("\n");
			}
		}
	}
	protected ServicioInventarioLibro obtenerServicio() {

		ServiceReference referencia = contexto.getServiceReference(ServicioInventarioLibro.class.getName());
		if (referencia == null)
			throw new RuntimeException("ServicioInventarioLibro no esta registrado, no puedo invocar operacion");

		ServicioInventarioLibro servicio = (ServicioInventarioLibro) this.contexto.getService(referencia);
		if (servicio == null)
			throw new RuntimeException("ServicioInventarioLibro no esta registrado, no puedo invocar operacion");

		return servicio;
	}

}
