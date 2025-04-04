package model;

/**
 * Clase que representa un libro y sus atributos
 * 
 * @author Bryan Leandro Pirasan Bonilla
 * @author Jenifer Alejandra Lopez Paredes
 * @author Yahir Sebastian Quiroga Morales
 * 
 * @version 1.0
 */

public class Libro {
    private String titulo;
    private String autor;
    private String aniopublicacion;
    private String isbn;
    private int ejemplaresDisponibles;

    /**
     * Constructor vacio
     */

    public Libro() {
    }

    /**
     * Constructor con parametros para inicializar los atributos de un libro.
     *
     * @param titulo                El titulo del libro.
     * @param autor                 El autor del libro.
     * @param aniopublicacion       El año de publicación del libro.
     * @param isbn                  El ISBN único del libro.
     * @param ejemplaresDisponibles La cantidad de ejemplares disponibles del libro.
     */

    public Libro(String titulo, String autor, String aniopublicacion, String isbn, int ejemplaresDisponibles) {
        this.titulo = titulo;
        this.autor = autor;
        this.aniopublicacion = aniopublicacion;
        this.isbn = isbn;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    /**
     * Obtiene el titulo del libro.
     *
     * @return El titulo del libro.
     */

    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el titulo del libro.
     *
     * @param titulo El titulo que se desea establecer.
     */

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return El autor del libro.
     */

    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     *
     * @param autor El autor que se desea establecer.
     */

    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el anio de publicacion del libro.
     *
     * @return El anio de publicación del libro.
     */

    public String getAniopublicacion() {
        return aniopublicacion;
    }

    /**
     * Establece el anio de publicacion del libro.
     *
     * @param aniopubli El anio de publicacion que se desea establecer.
     */

    public void setAniopublicacion(String aniopubli) {
        this.aniopublicacion = aniopubli;
    }

    /**
     * Obtiene el ISBN unico del libro.
     *
     * @return El ISBN del libro.
     */

    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN del libro.
     *
     * @param isbn El ISBN que se desea establecer.
     */

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene la cantidad de ejemplares disponibles del libro.
     *
     * @return La cantidad de ejemplares disponibles.
     */

    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    /**
     * Establece la cantidad de ejemplares disponibles del libro.
     *
     * @param ejemplaresDispo La cantidad de ejemplares que se desea establecer.
     */

    public void setEjemplaresDisponibles(int ejemplaresDispo) {
        this.ejemplaresDisponibles = ejemplaresDispo;
    }

}