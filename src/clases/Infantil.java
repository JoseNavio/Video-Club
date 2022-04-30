package clases;

/**
 * Infantil
 *
 * @author JoseHP
 */
public class Infantil extends Pelicula {

    public Infantil(String titulo) {

        super(titulo, Categoria.INFANTIL);
    }

    //Getters
    @Override
    public double getPrecio(int dias) {

        return dias * 1.5;
    }

    @Override
    public int getPuntos(int dias) {

        int puntos = 0;

        if (dias > 0) {
            puntos++;
        }

        return puntos;
    }

    //Primero se ordenan por categoria y luego por titulo
    @Override
    public int compareTo(Pelicula otra) {

        switch (otra.getCategoria()) {
            case INFANTIL:
                return this.getTitulo().compareTo(otra.getTitulo());
            default:
                return 1;
        }
    }
}
