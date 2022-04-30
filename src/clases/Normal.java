package clases;

/**
 *
 * @author JoseHP
 */
public class Normal extends Pelicula {

    //Constructor
    public Normal(String titulo) {

        super(titulo, Categoria.NORMAL);
    }

    //Getters
    @Override
    public double getPrecio(int dias) {

        return dias > 2 ? dias * 1.5 : dias * 2.0;
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
            case ESTRENO:
                return 1; //Va delante mia
            case INFANTIL:
                return -1;//Va detras mia
            default:
                return this.getTitulo().compareTo(otra.getTitulo());
        }
    }
}
