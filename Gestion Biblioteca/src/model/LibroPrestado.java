package model;

import java.time.LocalDate;

/**
 * Clase que representa un libro prestado y sus atributos
 * 
 * @author Bryan Leandro Pirasan Bonilla
 * @author Jenifer Alejandra Lopez Paredes
 * @author Yahir Sebastian Quiroga Morales
 * 
 * @version 1.0
 */

public class LibroPrestado {
    private Libro libro;
    private LocalDate fechaPrestamo;

    /**
     * Constructor de la clase LibroPrestado.
     *
     * @param libro         El libro que sera registrado como prestado.
     * @param fechaPrestamo La fecha en la que se realizo el prestamo.
     */

    public LibroPrestado(Libro libro, LocalDate fechaPrestamo) {
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;

    }

    /**
     * Obtiene el libro que ha sido prestado.
     *
     * @return El objeto {@link Libro} asociado al prestamo.
     */

    public Libro getLibro() {
        return libro;
    }

    /**
     * Establece el libro.
     *
     * @param libro El Libro que se desea establecer.
     */

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene la fecha en la que se realizo el prestamo.
     *
     * @return La fecha del prestamo como un objeto {@link LocalDate}.
     */

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Establece la fecha de prestamo del libro.
     *
     * @param fechaPrestamo La fecha de prestamo que se desea establecer.
     */

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Devuelve una representacion en cadena de la informacion del libro prestado.
     *
     * @return Una cadena que contiene los detalles del libro prestado y la fecha
     *         del préstamo.
     */

    @Override
    public String toString() {
        return "Título: " + libro.getTitulo() +
                " | Autor: " + libro.getAutor() +
                " | ISBN: " + libro.getIsbn() +
                " | Prestado el: " + fechaPrestamo + "\n";

    }
}