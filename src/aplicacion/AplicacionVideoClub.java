package aplicacion;

import clases.Categoria;
import clases.Cliente;
import clases.Pelicula;
import clases.VideoClub;
import java.util.Collection;
import java.util.Scanner;

/**
 * Main
 *
 * Aplicacion Video Club
 *
 * @author Jose L. Navío Mendoza
 */
public class AplicacionVideoClub extends Object {

    private static VideoClub v = new VideoClub();
    static Scanner s = new Scanner(System.in);

    //Main
    public static void main(String[] args) {

        //Menu principal
        System.out.println("***** Aplicación Videoclub *****");

        //Cargar datos
        v.cargarDatos();

        boolean salir = false;

        while (!salir) {

            int eleccion = opcionesMenu();

            switch (eleccion) {
                case 1:
                    añadirCliente();
                    break;
                case 2:
                    borrarCliente();
                    break;
                case 3:
                    añadirPelicula();
                    break;
                case 4:
                    borrarPelicula();
                    break;
                case 5:
                    alquilarPelicula();
                    break;
                case 6:
                    devolverPelicula();
                    break;
                case 7:
                    informeCliente();
                    break;
                case 8:
                    mostrarColeccion(v.getClientes());
                    break;
                case 9:
                    mostrarColeccion(v.getPeliculas());
                    break;
                case 10:
                    terminarEstreno();
                    break;
                case 11:
                    salir = true;
                    break;
                default:
                    System.out.println("El menú no contiene dicha opcion.");
                    break;
            }

            //Guardar datos
            v.guardarDatos();
        }
    }

    //Opciones menu
    private static int opcionesMenu() {

        System.out.println("\n1.Añadir un cliente.\n"
                + "2.Borrar un cliente.\n"
                + "3.Añadir una película.\n"
                + "4.Borrar una película.\n"
                + "5.Alquilar una película.\n"
                + "6.Devolver una película.\n"
                + "7.Mostrar informe de un cliente.\n"
                + "8.Mostrar clientes.\n"
                + "9.Mostrar películas.\n"
                + "10.Terminar estreno.\n"
                + "11.Salir.\n");

        try {

            int eleccion = s.nextInt();
            s.nextLine();

            return eleccion;

        } catch (Exception e) {

            System.out.println("Valor no válido. Fin del programa.");
            System.out.println("");
        }

        return 11;
    }

    //Añade un cliente
    private static void añadirCliente() {

        System.out.print("Nombre del nuevo cliente: ");

        try {

            boolean respuesta = v.addCliente(s.nextLine());
            System.out.println("");

            if (respuesta) {

                System.out.println("Cliente añadido.");

            } else {

                System.out.println("No se pudo añadir dicho cliente, ya existe un usuario con ese nombre.");
            }
        } catch (Exception e) {

            System.out.println("No se pudo añadir dicho cliente.");
        }
    }

    //Borrar un cliente
    private static void borrarCliente() {

        System.out.print("Nombre del cliente a borrar: ");

        try {

            boolean respuesta = v.delCliente(s.nextLine());
            System.out.println("");

            if (respuesta) {

                System.out.println("Cliente borrado.");
            } else {

                System.out.println("No se pudo borrar dicho cliente.");
            }

        } catch (Exception e) {

            System.out.println("No se pudo borrar dicho cliente.");
        }
    }

    //Añadir una película
    private static void añadirPelicula() {

        System.out.print("Nombre de la película a añadir: ");
        String titulo = s.nextLine();
        System.out.println("");

        //Se selecciona el tipo de película       
        System.out.print("Categoria de la película a añadir: \n"
                + "1.Normal\n"
                + "2.Estreno\n"
                + "3.Infantil\n");

        int eleccion = 0;

        try {

            eleccion = s.nextInt();

        } catch (Exception e) {

            System.out.println("No se pudo añadir la película.");
        }

        Categoria categoria = Categoria.NORMAL;
        switch (eleccion) {
            case 1:
                break;
            case 2:
                categoria = Categoria.ESTRENO;
                break;
            case 3:
                categoria = Categoria.INFANTIL;
                break;
            default:
                System.out.println("Valor no encontrado, se aplicará la categoría NORMAL por defecto.");
                break;
        }
        System.out.println("");

        //Se añade la película
        try {

            boolean respuesta = v.addPelicula(titulo, categoria);
            System.out.println("");

            if (respuesta) {

                System.out.println("Película añadida.");
            } else {

                System.out.println("No se pudo añadir la película porque ya existe.");
            }
        } catch (Exception e) {

            System.out.println("No se pudo añadir la película.");
        }
    }

    //Borrar una película
    private static void borrarPelicula() {
        System.out.print("Nombre de la película a borrar: ");
        String titulo = s.nextLine();

        try {

            boolean respuesta = v.delPelicula(titulo);
            System.out.println("");

            if (respuesta) {
                System.out.println("Película borrada.");
            } else {
                System.out.println("No se pudo borrar dicha pelicula.");
            }

        } catch (Exception e) {

            System.out.println("No se pudo añadir la película.");
        }
    }

    //Alquilar una pelicula
    private static void alquilarPelicula() {
        System.out.print("Titulo de la película: ");
        Pelicula miPelicula = v.getPelicula(s.nextLine());
        System.out.print("Cliente que la va a alquilar: ");
        Cliente clienteAl = v.getCliente(s.nextLine());
        System.out.println("");

        try {
            clienteAl.openAlquiler(miPelicula);
            System.out.println("La película fué alquilada.");
        } catch (NullPointerException e) {
            System.out.println("La película o cliente indicado no existe.");
        } catch (Exception e) {
            System.out.println("No se pudo alquilar dicha película.");
        }
    }

    //Devolver una pelicula
    private static void devolverPelicula() {
        System.out.print("Cliente que va a devolver: ");
        Cliente clienteAl = v.getCliente(s.nextLine());

        System.out.print("Titulo de la película: ");
        Pelicula miPelicula = v.getPelicula(s.nextLine());

        try {
            clienteAl.closeAlquiler(miPelicula.getTitulo());
            System.out.println("Película devuelta.");
        } catch (Exception e) {
            System.out.println("Se produjo un error.");
        }
    }

    //Mostrar informe cliente
    private static void informeCliente() {

        System.out.print("Nombre del cliente: ");
        System.out.println("");

        try {

            System.out.println(v.getCliente(s.nextLine()).toString());

        } catch (Exception e) {

            System.out.println("Hubo problemas a la hora de encontrar el cliente indicado.");
        }
    }

    //Mostrar coleccion
    private static void mostrarColeccion(Collection coleccion) {

        boolean listaPopulada = false;

        for (Object objeto : coleccion) {

            if (listaPopulada == false) {

                listaPopulada = true;
            }

            System.out.println(objeto);
            System.out.println("");
        }
        System.out.println("");

        if (!listaPopulada) {
            System.out.println("Aún no hay ningún registro.");
        }
    }
    
    //Terminar estreno
    private static void terminarEstreno(){
        
        System.out.print("Terminar la condición de estreno de la película: ");
        String estreno = s.nextLine();
        
        v.terminarEstreno(estreno);
    }
}
