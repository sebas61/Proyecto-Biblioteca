package view;

import java.util.Scanner;

import control.Biblioteca;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Biblioteca municipalSoga = new Biblioteca();
        int opcion = 0;
        boolean control = true;
        while (control) {
            String menu = """
                    1. Añadir nuevo usuario.
                    2. Eliminar usuario por cedula.
                    3. Buscar usuario por cedula.
                    4. Actualizar la información de usuario por id.
                    5. Añadir nuevo libro.
                    6. Inventario libros.
                    7. Eliminar libro por ISBN.
                    8. Actualizar información libro por ISBN.
                    9. Solicitar prestamo libro.
                    10. Devolucion libro.
                    11. Buscar libro por autor o titulo.
                    0. salir
                    """;
            System.out.println(menu);
            System.out.println("Ingrese la opción deseada:");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:// Añadir nuevo usuario.
                    System.out.println("Ingrese el nombre:");
                    String nombre = sc.nextLine();

                    System.out.println("Ingrese el apellido:");
                    String apellido = sc.nextLine();

                    System.out.println("Ingrese el ID:");
                    String id = sc.nextLine();

                    String tipoUsuario;
                    while (true) { 
                        System.out.println("""
                                ¿Qué tipo de usuario eres?
                                1. Estudiante
                                2. Profesor
                                3. Ciudadano""");

                        tipoUsuario = sc.nextLine().trim();
                        if (tipoUsuario.equals("1") || tipoUsuario.equals("2") || tipoUsuario.equals("3")) {
                            break; 
                        } else {
                            System.out.println("Error: Debe ingresar 1, 2 o 3.");
                        }
                    }
                    String mensajeConfirmacion = municipalSoga.anhadirUsuario(nombre, apellido, id, tipoUsuario);
                    System.out.println(mensajeConfirmacion);
                    break;

                case 2:// Eliminar usuario por cedula.
                    System.out.println("ingrese la cedula del usuario a eliminar");
                    String idEliminar = sc.nextLine();
                    String respuestaEliminar = municipalSoga.eliminarUsuarioPorId(idEliminar);
                    System.out.println(respuestaEliminar);
                    break;
                case 3:// Buscar usuario por cedula.
                    System.out.print("Ingrese el id del usuario: ");
                    String idBuscar = sc.nextLine();
                    System.out.println(idBuscar);
                    String respuestaBuscar = municipalSoga.buscarUsuPorId(idBuscar);
                    System.out.println(respuestaBuscar);

                    break;
                case 4:// actualizar informacion
                    System.out.print("Ingrese el id del usuario: ");
                    String idActualizar = sc.nextLine();
                    System.out.println();
                    while (true) { 
                        System.out.println("""
                                ¿A que tipo de usuario quieres cambiar?
                                1. Estudiante
                                2. Profesor
                                3. Ciudadano
                                0. Mantener el tipo de usuario""");

                        tipoUsuario = sc.nextLine().trim();
                        if (tipoUsuario.equals("1") || tipoUsuario.equals("2") || tipoUsuario.equals("3")|| tipoUsuario.equals("0")) {
                            break; 
                        } else {
                            System.out.println("Error: Debe ingresar 1, 2, 3 o 0.");
                        }
                    }
                    String mensajeActualizar = municipalSoga.actualizarUsuario(idActualizar, tipoUsuario);
                    System.out.println(mensajeActualizar);
                    break;
                case 5:// Añadir nuevo libro.
                    System.out.println("Ingrese el título del libro:");
                    String titulo = sc.nextLine();
                    System.out.println("Ingrese el autor del libro:");
                    String autor = sc.nextLine();
                    System.out.println("Ingrese el año de publicación del libro:");
                    String aniopublicacion = sc.nextLine();
                    System.out.println("Ingrese el ISBN del libro:");
                    String isbn = sc.nextLine();
                    String mensajeLibro = municipalSoga.anhadirLibro(titulo, autor, aniopublicacion, isbn,
                            1);
                    System.out.println(mensajeLibro);
                    break;
                case 6:// Inventario libros.
                    System.out.println(municipalSoga.mostrarLibros());
                    break;
                case 7:// Eliminar libro por ISBN.
                    System.out.println("Ingrese el ISBN del libro:");
                    String isbnEliminar = sc.nextLine();
                    String respuestaLibro = municipalSoga.eliminarLibroIsbn(isbnEliminar);
                    System.out.println(respuestaLibro);
                    break;
                case 8:// Actualizar información libro por ISBN.
                    System.out.println("Ingrese el ISBN del libro ,para añadir mas existencias");
                    String isbnActualizar = sc.nextLine();

                    int ejemplaresAnhadir;
                    do {
                        System.out.println("Ingrese la cantidad de ejemplares a añadir:");
                        while (!sc.hasNextInt()) { // Si la entrada no es un int...
                            System.out.println("Error: Debe ingresar un número entero.");
                            sc.next(); // Limpiar la entrada inválida
                            System.out.print("Intente nuevamente: ");
                        }
                        ejemplaresAnhadir = sc.nextInt();
                    } while (false);

                    String mensajeLibroActualizar = municipalSoga.actualizarLibroPorIsbn(isbnActualizar,
                            ejemplaresAnhadir);
                    System.out.println(mensajeLibroActualizar);
                    break;
                case 9:// Solicitar prestamo libro.
                    System.out.println("ingrese ISBN del libro que desea pedir en prestamo");
                    String isbnPrestar = sc.nextLine();
                    System.out.println("ingrese el id del usuario");
                    String idPedirPrestado = sc.nextLine();
                   
                    String mensajePrestar = municipalSoga.solicitarPrestamo(isbnPrestar, idPedirPrestado);
                    System.out.println(mensajePrestar);
                    break;
                case 10:// Devolucion libro.
                    System.out.println("ingrese ISBN del libro que desea devolver");
                    String isbndevolver = sc.nextLine();
                    System.out.println("ingrese la cedula del usuario");
                    String idDevolver = sc.nextLine();
                    System.out.println("Ingrese la fecha de prestamo (formato: yyyy-MM-dd): ");
                    String fechaIngresada = sc.nextLine();
                    String mensajeDevolver = municipalSoga.devolverLibro(isbndevolver, idDevolver, fechaIngresada);
                    System.out.println(mensajeDevolver);
                    break;
                case 11: // Buscar libro por autor o título.
                    System.out.println("""
                            ¿Cómo quieres buscar el libro?
                            1. Por autor
                            2. Por título
                            """);
                    String opcionBusqueda = sc.nextLine().trim();

                    if (opcionBusqueda.equals("1")) {
                        System.out.println("Ingrese parte del nombre del autor:");
                        String autorBusqueda = sc.nextLine();
                        System.out.println(municipalSoga.buscarLibroPorAutor(autorBusqueda));
                    } else if (opcionBusqueda.equals("2")) {
                        System.out.println("Ingrese parte del título del libro:");
                        String tituloBusqueda = sc.nextLine();
                        System.out.println(municipalSoga.buscarLibroPorTitulo(tituloBusqueda));
                    } else {
                        System.out.println("Opción no válida. Debes ingresar 1 o 2.");
                    }
                    break;

                default:// salir
                    control = false;
                    break;

            }
        }
        sc.close();
    }

}
