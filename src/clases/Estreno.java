package clases;

/**
 * Estreno
 *
 * @author JoseHP
 */
public class Estreno extends Pelicula {

    public Estreno(String titulo) {

        super(titulo, Categoria.ESTRENO);
    }

    //Getters
    @Override
    public double getPrecio(int dias) {

        return dias * 3;
    }

    @Override
    public int getPuntos(int dias) {

        int puntos = 0;

        if (dias > 0) {
            puntos++;
        }

        if (dias > 1) {
            puntos++;
        }
        return puntos;
    }
    
    //Primero se ordenan por categoria y luego por titulo
    @Override
    public int compareTo(Pelicula otra) {

        switch (otra.getCategoria()) {
            case ESTRENO:
                return this.getTitulo().compareTo(otra.getTitulo());
            default:
                return -1;
        }
    }
}
