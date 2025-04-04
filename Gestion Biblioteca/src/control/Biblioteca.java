package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Libro;
import model.LibroPrestado;
import model.Usuario;

/**
 * Clase Biblioteca la cual representa toda la gestion
 * de libros y usuarios dentro de La Biblioteca Municipal de Sogamoso.
 * 
 * @author Bryan Leandro Pirasan Bonilla
 * @author Jenifer Alejandra Lopez Paredes
 * @author Yahir Sebastian Quiroga Morales
 * 
 * @version 1.0
 */

public class Biblioteca {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Libro> libros;

    /**
     * Constructor que inicializa las listas de
     * usuario y libros.
     *
     */

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
    }

    /**
     * Obtiene la lista de usuarios registrados.
     *
     * @return Una lista de objetos de tipo Usuario.
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Establece la lista de usuarios registrados.
     *
     * @param usuarios Una lista de objetos de tipo Usuario.
     */

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * Obtiene la lista de libros disponibles.
     *
     * @return Una lista de objetos de tipo Libro.
     */

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    /**
     * Establece la lista de libros disponibles.
     *
     * @param libros Una lista de objetos de tipo Libro.
     */

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    /**
     * Muestra un inventario de los libros disponibles en formato de cadena.
     *
     * @return Una cadena que muestra los libros disponibles con su título, autor,
     *         año de publicación, ISBN y número de ejemplares disponibles.
     */

    public String mostrarLibros() {// inventario libros
        String librosString = "";
        for (int i = 0; i < libros.size(); i++) {
            librosString = librosString + " Titulo: " + libros.get(i).getTitulo() +
                    " |Autor: " + libros.get(i).getAutor() + " |Años de publicacion: "
                    + libros.get(i).getAniopublicacion() + " |ISBN: " + libros.get(i).getIsbn() +
                    " |Numero de ejemplares disponibles " + libros.get(i).getEjemplaresDisponibles() + "\n";
        }
        return librosString;
    }

    /**
     * Busca un usuario en la lista de usuarios basado en su ID.
     *
     * @param id El ID del usuario a buscar.
     * @return El objeto Usuario si se encuentra, o null si no existe.
     */

    private Usuario buscarUsuario(String id) {
        for (Usuario usu : usuarios) {
            if (usu.getId().equals(id)) {
                return usu;
            }
        }
        return null;
    }

    /**
     * Solicitar el prestamo de un libro para un usuario especificado.
     *
     * @param isbn      El ISBN del libro a prestar.
     * @param idUsuario El ID del usuario que solicita el préstamo.
     * @return Un mensaje indicando el resultado del préstamo (Usuario no
     *         encontrado, Prestamo realizado con éxito, no hay ejemplares, Libro no
     *         encontrado.).
     */

    public String solicitarPrestamo(String isbn, String idUsuario) {
        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario == null) {
            return "Usuario no encontrado";
        }

        for (Libro libroAux : libros) {
            if (libroAux.getIsbn().equals(isbn)) {
                if (libroAux.getEjemplaresDisponibles() > 0) {
                    libroAux.setEjemplaresDisponibles(libroAux.getEjemplaresDisponibles() - 1);
                    usuario.agregarLibroPrestado(libroAux);
                    return "Préstamo realizado con éxito. Fecha de prestamo: " + LocalDate.now();
                } else {
                    return "No hay ejemplares disponibles";
                }
            }
        }
        return "Libro no encontrado";
    }

    /**
     * Devuelve un libro prestado y actualiza la informacion correspondiente.
     *
     * @param isbn                  El ISBN del libro a devolver.
     * @param idUsuario             El ID del usuario que devuelve el libro.
     * @param fechaDevolucionString La fecha de devolución en formato de cadena
     *                              ("yyyy-MM-dd").
     * @return Un mensaje indicando el resultado de la devolución (Usuario no
     *         encontrado, Libro devuelto con éxito..., usuario no tiene ese libro,
     *         etc.).
     */

    public String devolverLibro(String isbn, String idUsuario, String fechaDevolucionString) {
        LocalDate fechaDevolucion = LocalDate.parse(fechaDevolucionString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario == null) {
            return "Usuario no encontrado";
        }
        for (LibroPrestado prestamo : usuario.getLibrosPrestados()) {
            if (prestamo.getLibro().getIsbn().equals(isbn)) {
                usuario.getLibrosPrestados().remove(prestamo);
                for (Libro libroAux : libros) {
                    if (libroAux.getIsbn().equals(isbn)) {
                        libroAux.setEjemplaresDisponibles(libroAux.getEjemplaresDisponibles() + 1);
                        return "Libro devuelto con éxito.\n" +
                                "Fecha de préstamo: " + prestamo.getFechaPrestamo() + "\n" +
                                "Fecha de devolución registrada:" + fechaDevolucion;
                    }
                }
            }
        }
        return "El usuario no tiene ese libro prestado";
    }

    /**
     * Busca libros por el autor especificado en la lista de libros.
     *
     * @param autorBusqueda El nombre del autor a buscar.
     * @return Una cadena con los detalles de los libros encontrados o un mensaje
     *         indicando que el autor no fue encontrado.
     */

    public String buscarLibroPorAutor(String autorBusqueda) {
        String resultado = "";
        for (Libro libroAux : libros) {
            if (libroAux.getAutor().toLowerCase().contains(autorBusqueda.toLowerCase())) {
                resultado += "Título: " + libroAux.getTitulo() +
                        " | Autor: " + libroAux.getAutor() +
                        " | Año: " + libroAux.getAniopublicacion() +
                        " | ISBN: " + libroAux.getIsbn() +
                        " | Ejemplares Disponibles: " + libroAux.getEjemplaresDisponibles() + "\n";
            }
        }
        return resultado.isEmpty() ? "No se encuentra el autor" : resultado;
    }

    /**
     * Busca libros por el titulo especificado en la lista de libros.
     *
     * @param tituloBusqueda El titulo o parte del titulo del libro a buscar.
     * @return Una cadena con los detalles de los libros encontrados o un mensaje
     *         indicando que el título no fue encontrado.
     */

    public String buscarLibroPorTitulo(String tituloBusqueda) {
        String resultado = "";
        for (Libro libroAux : libros) {
            if (libroAux.getTitulo().toLowerCase().contains(tituloBusqueda.toLowerCase())) {
                resultado += "Título: " + libroAux.getTitulo() +
                        " | Autor: " + libroAux.getAutor() +
                        " | Año: " + libroAux.getAniopublicacion() +
                        " | ISBN: " + libroAux.getIsbn() +
                        " | Ejemplares Disponibles: " + libroAux.getEjemplaresDisponibles() + "\n";
            }
        }
        return resultado.isEmpty() ? "No se encuentra ese título" : resultado;
    }

    /**
     * Actualiza las existencias de un libro basado en su ISBN.
     *
     * @param isbn                   El ISBN del libro cuyas existencias se desean
     *                               actualizar.
     * @param existenciasAdicionales El numero adicional de existencias a agregar.
     * @return Un mensaje indicando si las existencias fueron actualizadas con éxito
     *         o si el libro no fue encontrado.
     */

    public String actualizarLibroPorIsbn(String isbn, int existenciasAdicionales) {
        for (Libro libroAux : libros) {
            if (libroAux.getIsbn().equals(isbn)) {
                existenciasAdicionales += libroAux.getEjemplaresDisponibles();
                libroAux.setEjemplaresDisponibles(existenciasAdicionales);
                return "Existencias actualizadas correctamente";
            }
        }
        return "Libro no encontrado";
    }

    /**
     * Elimina un libro de la lista basado en su ISBN.
     *
     * @param isbn El ISBN del libro que se desea eliminar.
     * @return Un mensaje indicando si el libro fue eliminado exitosamente o si no
     *         fue encontrado.
     */

    public String eliminarLibroIsbn(String isbn) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getIsbn().equals(isbn)) {
                Libro libroEliminar = libros.remove(i);
                return "Libro " + libroEliminar.getTitulo() + "con ISBN: " + libroEliminar.getIsbn()
                        + " ha sido eliminado";
            }
        }
        return "Libro no encontrado";
    }

    /**
     * Elimina un usuario de la lista basado en su ID.
     *
     * @param idE El ID del usuario que se desea eliminar.
     * @return Un mensaje indicando si el usuario fue eliminado exitosamente o si no
     *         fue encontrado.
     */

    public String eliminarUsuarioPorId(String idE) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (idE.equals(usuarios.get(i).getId())) {
                Usuario usuarioEliminado = usuarios.remove(i);
                return "Usuario " + usuarioEliminado.getNombre() + "ha sido eliminado";
            }
        }
        return "Cliente no encontrado";
    }

    /**
     * Aniade un nuevo usuario a la lista de usuarios.
     *
     * @param nombre      El nombre del usuario.
     * @param apellido    El apellido del usuario.
     * @param id          El ID del usuario.
     * @param tipoUsuario El tipo de usuario (por ejemplo, Estudiante, Profesor,
     *                    etc.).
     * @return Un mensaje indicando si el usuario fue agregado exitosamente o si el
     *         ID ya está registrado.
     */

    public String anhadirUsuario(String nombre, String apellido, String id, String tipoUsuario) {
        for (Usuario usuariosAux : usuarios) {
            if (usuariosAux.getId().equals(id)) {
                return "el Id ya esta registrado";
            }
        }
        this.usuarios.add(new Usuario(nombre, apellido, id, tipoUsuario));
        return "Usuario agregado exitosamente.";
    }

    /**
     * Aniade un nuevo libro a la lista de libros.
     *
     * @param titulo                El título del libro.
     * @param autor                 El autor del libro.
     * @param aniopublicacion       El año de publicacion del libro.
     * @param isbn                  El ISBN del libro.
     * @param ejemplaresDisponibles El número de ejemplares disponibles del libro.
     * @return Un mensaje indicando si el libro fue agregado exitosamente o si el
     *         ISBN ya está registrado.
     */

    public String anhadirLibro(String titulo, String autor, String aniopublicacion, String isbn,
            int ejemplaresDisponibles) {
        for (Libro libroAux : libros) {
            if (libroAux.getIsbn().equals(isbn)) {
                return "el ISBN ya esta registrado";
            }
        }
        this.libros.add(new Libro(titulo, autor, aniopublicacion, isbn, ejemplaresDisponibles));
        return "Libro agregado exitosamente.";
    }

    /**
     * Busca un usuario en la lista basado en su ID y devuelve su informacion en
     * formato de cadena.
     *
     * @param idBuscar El ID del usuario a buscar.
     * @return Una cadena con los detalles del usuario encontrado o un mensaje
     *         indicando que no fue encontrado.
     */

    public String buscarUsuPorId(String idBuscar) {
        String usuarioString = "";
        for (Usuario usuarioAux : usuarios) {
            if (usuarioAux.getId().equals(idBuscar)) {
                usuarioString += "Nombre: " + usuarioAux.getNombre() +
                        " |Apellido: " + usuarioAux.getApellido() +
                        " |ID: " + usuarioAux.getId() +
                        " |Tipo de usuario: " + usuarioAux.getTipoUsuario() + "\n";
            }
        }
        return usuarioString.isEmpty() ? "Usuario no encontrado" : usuarioString;

    }

    /**
     * Actualiza el tipo de un usuario basado en su ID.
     *
     * @param id          El ID del usuario a actualizar.
     * @param tipousuario El nuevo tipo de usuario (1: Estudiante, 2: Profesor, 3:
     *                    Ciudadano, 0: Sin cambio).
     * @return Un mensaje indicando si el usuario fue actualizado exitosamente o si
     *         no fue encontrado.
     */

    public String actualizarUsuario(String id, String tipousuario) {
        for (Usuario usuarioAux : usuarios) {
            if (usuarioAux.getId().equals(id)) {
                if (tipousuario.equals("1")) {
                    usuarioAux.setTipoUsuario("Estudiante");
                } else if (tipousuario.equals("2")) {
                    usuarioAux.setTipoUsuario("Profesor");
                } else if (tipousuario.equals("3")) {
                    usuarioAux.setTipoUsuario("Ciudadano");
                } else if (tipousuario.equals("0")) {
                    return "No se cambio el tipo de usuario.";
                } else {

                }
                return "Usuario actualizado exitosamente.";
            }
        }
        return "usuario no encontrado";
    }
}
