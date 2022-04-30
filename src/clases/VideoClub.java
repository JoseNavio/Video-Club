package clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import peliculaFactory.GetPeliculaFactory;

/**
 * VideoClub
 *
 * @author JoseHP
 */
public class VideoClub implements Serializable {

    private Set<Pelicula> peliculas;

    private Set<Cliente> clientes;

    public VideoClub() {

        peliculas = new TreeSet();

        clientes = new TreeSet();
    }

    //Metodos Clientes
    public boolean addCliente(String nombre) {

        if (clientes.contains(new Cliente(nombre))) {

            return false;

        } else {

            clientes.add(new Cliente(nombre));
            return true;
        }
    }

    public boolean delCliente(String nombre) {

        Iterator<Cliente> it = clientes.iterator();

        Cliente clienteActual;

        while (it.hasNext()) {

            clienteActual = it.next();

            if (clienteActual.getNombre().equals(nombre)) {

                if (!clienteActual.alquilerPendiente()) {

                    it.remove();
                    return true;
                }
                System.out.println("");
                System.out.println("Aún debe entregar alguna película.");
            }
        }
        return false;
    }

    public Set getClientes() {

        return clientes;
    }

//    @Deprecated
//    public Cliente[] getClientes() {
//
//        Cliente[] tempArray = new Cliente[clientes.size()];
//        return clientes.toArray(tempArray);
//    }
    public Cliente getCliente(String nombre) {

        Iterator<Cliente> it = clientes.iterator();

        while (it.hasNext()) {

            Cliente clienteActual = it.next();//Posible mejora de rendimiento inicializar cliente actual antes del bucle, igual en getPelicula.

            if (clienteActual.getNombre().equals(nombre)) {

                return clienteActual;
            }
        }
        return null;
    }

    //Metodos Peliculas
    public boolean addPelicula(String titulo, Categoria categoria) {

        if (peliculas.contains(GetPeliculaFactory.getPelicula(titulo, categoria))) {

            return false;

        } else {

            Pelicula nuevaPelicula = GetPeliculaFactory.getPelicula(titulo, categoria);

            peliculas.add(nuevaPelicula);

            //Peliculas a ficheros...
            try {

                ObjectOutputStream registrandoPelicula = new ObjectOutputStream(new FileOutputStream("peliculas.txt", true));

                registrandoPelicula.writeObject(nuevaPelicula);

                registrandoPelicula.close();

            } catch (Exception e) {

                System.out.println("Hubo un fallo en el fichero peliculas.txt");
            }
            return true;
        }

    }

    public boolean delPelicula(String titulo) {

        Iterator<Pelicula> it = peliculas.iterator();

        while (it.hasNext()) {

            if (it.next().getTitulo().equals(titulo)) {

                it.remove();
                return true;
            }
        }
        return false;
    }

    public Set getPeliculas() {

        return peliculas;
    }

//    @Deprecated
//    public Pelicula[] getPeliculas() {
//
//        Pelicula[] tempArray = new Pelicula[peliculas.size()];
//        return peliculas.toArray(tempArray);
//    }
    public Pelicula getPelicula(String titulo) {

        Iterator<Pelicula> it = peliculas.iterator();

        while (it.hasNext()) {

            Pelicula peliculaActual = it.next();

            if (peliculaActual.getTitulo().equals(titulo)) {

                return peliculaActual;
            }
        }

        return null;
    }

    //Acaba con la condición de estreno de una pelicula
    public void terminarEstreno(String titulo) {

        Pelicula estreno = getPelicula(titulo);

        //Si se confirma que la película es un estreno...
        if (estreno != null && estreno.getCategoria().equals(Categoria.ESTRENO)) {

            //Elige la nueva categoría de la película
            System.out.println("");
            System.out.println("Convertir la película al tipo: \n"
                    + "1. Normal \n"
                    + "2. Infantil \n");

            Scanner s = new Scanner(System.in);

            int eleccion = 1;

            try {

                eleccion = s.nextInt();
                s.nextLine();

            } catch (Exception e) {

                eleccion = 0;
                
                System.out.print("No existe tal elección. ");
            }

            switch (eleccion) {
                case 1:
                    addPelicula(titulo, Categoria.NORMAL);

                    //Borramos anterior película
                    delPelicula(titulo);

                    System.out.println("Se cambió la categoría.");
                    break;
                case 2:
                    addPelicula(titulo, Categoria.INFANTIL);

                    //Borramos anterior película
                    delPelicula(titulo);

                    System.out.println("Se cambió la categoría.");
                    break;
                default:
                    System.out.println("No se modificó la película.");
                    break;
            }

        } else {

            System.out.println("No existe tal estreno.");
        }
    }

    //Serializacion
    //Guardar
    public void guardarDatos() {

        try {

            FileOutputStream peliculasFileOut = new FileOutputStream("peliculas.txt");
            FileOutputStream clientesFileOut = new FileOutputStream("clientes.txt");
            ObjectOutputStream peliculasOut = new ObjectOutputStream(peliculasFileOut);
            ObjectOutputStream clientestOut = new ObjectOutputStream(clientesFileOut);

            peliculasOut.writeObject(this.peliculas);
            clientestOut.writeObject(this.clientes);

            peliculasOut.close();
            peliculasFileOut.close();
            clientestOut.close();
            clientesFileOut.close();

        } catch (FileNotFoundException fnfe) {

            System.out.println("No se encontro el fichero.");

        } catch (Exception e) {

            System.out.println("No se pudieron guardar correctamente los datos...");
        }
    }

    //Cargar
    public void cargarDatos() {

        try {

            FileInputStream peliculasFileIn = new FileInputStream("peliculas.txt");
            FileInputStream clientesFileIn = new FileInputStream("clientes.txt");
            ObjectInputStream objectPeliculasIn = new ObjectInputStream(peliculasFileIn);
            ObjectInputStream objectClientesIn = new ObjectInputStream(clientesFileIn);

            this.peliculas = (TreeSet) objectPeliculasIn.readObject();
            this.clientes = (TreeSet) objectClientesIn.readObject();

            peliculasFileIn.close();
            objectPeliculasIn.close();
            clientesFileIn.close();
            objectClientesIn.close();

        } catch (Exception e) {

            System.out.println("No se encontraron datos del videoclub.");
        }
    }
}
