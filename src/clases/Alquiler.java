package clases;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import peliculaFactory.GetPeliculaFactory;

/**
 * Alquiler
 *
 * @author JoseHP
 */
public class Alquiler implements Serializable{

    private LocalDateTime diaHoraInicio;
    private Pelicula pelicula;
    private int tiempo;

    //Constructores
    public Alquiler(Pelicula pelicula) {

        diaHoraInicio = LocalDateTime.now();
        this.pelicula = GetPeliculaFactory.getPelicula(pelicula.getTitulo(), pelicula.getCategoria());
        tiempo = 0;
    }

    //Getters
    public LocalDateTime getDiaInicio() {

        return diaHoraInicio;
    }

    public Pelicula getPelicula() {

        return pelicula;
    }

    public int getDias() {

        return tiempo;
    }

    public void close() {

        this.tiempo = difDias(diaHoraInicio, LocalDateTime.now());
    }

    private int difDias(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {

        double duracion = Duration.between(fechaInicial, fechaFinal).getSeconds();
        return (int) (Math.ceil(duracion / 86400.0));
    }

    @Override
    public String toString() {

        if (tiempo > 0) {
            return this.pelicula.toString() + " Precio: " + this.pelicula.getPrecio(tiempo);
        } else {
            return this.pelicula.toString() + ", aún no se ha devuelto.";
        }
    }

    public String alquilerTest() {

        return "Inicio alquiler: " + diaHoraInicio + ", Pelicula: " + toString();
    }
}
