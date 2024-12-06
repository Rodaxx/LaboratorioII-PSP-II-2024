package cl.ucn.biblioteca.servicio.api;

import java.util.List;
import java.util.Set;

import cl.ucn.biblioteca.api.ExcepcionLibroExistente;
import cl.ucn.biblioteca.api.ExcepcionLibroInvalido;
import cl.ucn.biblioteca.api.ExcepcionLibroNoEncontrado;
import cl.ucn.biblioteca.api.Libro;

public interface ServicioInventarioLibro {

	void ingresar(String isbn, String titulo, String autor, String categoria) throws ExcepcionLibroExistente, ExcepcionLibroInvalido;
	void remover(String isbn) throws ExcepcionLibroNoEncontrado;
	void modificarCategoria(String isbn, String categoria) throws ExcepcionLibroNoEncontrado;
	Libro obtener(String isbn)  throws ExcepcionLibroNoEncontrado;
	List<Libro> obtenerPorTitulo(String titulo);
}
