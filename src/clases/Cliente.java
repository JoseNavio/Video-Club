package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cliente
 *
 * @author JoseHP
 */
public class Cliente implements Comparable<Cliente>, Serializable{

    private String nombre;
    private int nAlquileresLleva;
    private List<Alquiler> alquileres;

    public Cliente(String nombre) {

        this.nombre = nombre;
        nAlquileresLleva = 0;
        alquileres = new ArrayList();
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public List getAlquileres() {
        return alquileres;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodos
    public void openAlquiler(Pelicula pelicula) {

        alquileres.add(new Alquiler(pelicula));
        nAlquileresLleva++;
    }

    public void closeAlquiler(String titulo) {

        int indiceAlquiler = findAlquiler(titulo);

        if (indiceAlquiler > -1) {

            alquileres.get(indiceAlquiler).close();
        }
    }

    private int lastAlquiler() {
        return (nAlquileresLleva - 1);
    }

    private int findAlquiler(String titulo) {

        int indice = -1;
        for (int i = 0; i <= lastAlquiler(); i++) {
            if (alquileres.get(i).getPelicula().getTitulo().equals(titulo) && alquileres.get(i).getDias() == 0) {
                indice = i;
                i = lastAlquiler() + 1;
            }
        }
        return indice;
    }

    //Comprobar que el cliente no tiene alquileres abiertos
    public boolean alquilerPendiente() {

        boolean alquilerPendiente = false;

        for (Alquiler alquiler : alquileres) {

            if (alquiler.getDias() == 0) {
                alquilerPendiente = true;
            }
        }
        
        return alquilerPendiente;
    }

    @Override
    public String toString() {

        int totalPuntos = 0;

        double totalGastado = 0;

        String datosAlquiler = "";

        if (nAlquileresLleva < 1) {

            datosAlquiler = "Aún no ha alquilado";
        } else {

            for (int i = 0; i < nAlquileresLleva; i++) {

                Alquiler alquiler = alquileres.get(i);

                datosAlquiler = datosAlquiler.concat("\n" + alquiler.toString());

                totalPuntos += alquiler.getPelicula().getPuntos(alquiler.getDias());

                totalGastado += alquiler.getPelicula().getPrecio(alquiler.getDias());
            }
        }

        return "Nombre: " + nombre + "\nTotal puntos: " + totalPuntos + "\nAlquileres: " + datosAlquiler + "\nTotal gastado: " + totalGastado;
    }

    @Override
    public int compareTo(Cliente otro) {

        return nombre.compareTo(otro.getNombre());
    }
}
