package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase que representa a un usuario y sus atributos
 * 
 * @author Bryan Leandro Pirasan Bonilla
 * @author Jenifer Alejandra Lopez Paredes
 * @author Yahir Sebastian Quiroga Morales
 * 
 * @version 1.0
 */

public class Usuario {
    private String nombre;
    private String apellido;
    private String id;
    private String tipoUsuario;
    private ArrayList<LibroPrestado> librosPrestados;

    /**
     * Constructor vacio
     */

    public Usuario() {

    }

    /**
     * Constructor con parametros para inicializar los atributos de un usuario.
     *
     * @param nombre      El nombre del usuario.
     * @param apellido    El apellido del usuario.
     * @param id          El ID unico del usuario.
     * @param tipoUsuario El tipo de usuario (1: Estudiante, 2: Profesor, 3:
     *                    Ciudadano).
     */

    public Usuario(String nombre, String apellido, String id, String tipoUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;

        if (tipoUsuario.equals("1")) {
            this.tipoUsuario = "Estudiante";
        } else if (tipoUsuario.equals("2")) {
            this.tipoUsuario = "Profesor";
        } else if (tipoUsuario.equals("3")) {
            this.tipoUsuario = "Ciudadano";
        } else {
            System.out.println("Tipo de usuario no válido.");
        }
        this.librosPrestados = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre que se desea establecer.
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del usuario.
     *
     * @return El apellido del usuario.
     */

    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param apellido El apellido que se desea establecer.
     */

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el ID único del usuario.
     *
     * @return El ID del usuario.
     */

    public String getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id El ID que se desea establecer.
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el tipo de usuario.
     *
     * @return El tipo de usuario como cadena (por ejemplo, Estudiante, Profesor,
     *         Ciudadano).
     */

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el tipo de usuario.
     *
     * @param tipoUsuario El tipo de usuario que se desea establecer.
     */

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Obtiene la lista de libros prestados por el usuario.
     *
     * @return Una lista de objetos {@link LibroPrestado}.
     */

    public ArrayList<LibroPrestado> getLibrosPrestados() {
        return librosPrestados;
    }

    /**
     * Establece la lista de libros prestados por el usuario.
     *
     * @param librosPrestados La lista de libros prestados que se desea establecer.
     */

    public void setLibrosPrestados(ArrayList<LibroPrestado> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    /**
     * Agrega un libro a la lista de libros prestados por el usuario.
     * La fecha del prestamo se registra automaticamente como la fecha actual.
     * 
     * @param libro El libro que será prestado.
     */

    public void agregarLibroPrestado(Libro libro) {
        LocalDate fechaPrestamo = LocalDate.now();

        librosPrestados.add(new LibroPrestado(libro, fechaPrestamo));
    }

    /**
     * Devuelve un libro prestado por el usuario basado en su ISBN.
     *
     * @param isbn                El ISBN del libro a devolver.
     * @param fechaPrestamoString La fecha del prestamo en formato de cadena (sin
     *                            usar en la lógica actual).
     * @return Un mensaje indicando si el libro fue devuelto exitosamente o si no
     *         fue encontrado.
     */

    public String devolverLibro(String isbn, String fechaPrestamoString) {

        for (int i = 0; i < librosPrestados.size(); i++) {
            if (librosPrestados.get(i).getLibro().getIsbn().equals(isbn)) {
                librosPrestados.remove(i);
                i--;
                return "libro devuelto exitosamente";
            }
        }
        return "el usuario no pidio prestado el libro";
    }

}
