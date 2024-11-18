package cl.ucn.biblioteca.servicio.api;

import java.util.Set;

import cl.ucn.biblioteca.api.ExcepcionLibroNoEncontrado;
import cl.ucn.biblioteca.api.Libro;

public interface ServicioInventarioLibro {

	void ingresar(String isbn, String titulo, String autor, String categoria);
	void remover(String isbn);
	void modificarCategoria(String isbn, String categoria);
	Libro obtener(String isbn)  throws ExcepcionLibroNoEncontrado;
}
